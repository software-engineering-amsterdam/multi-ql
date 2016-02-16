//
//  FormTests.swift
//  QLSwift
//
//  Created by Tom van Duist on 16/02/16.
//
//

import XCTest
@testable import QLSwift

/**
 * Unit tests for testing if the parse tree properly generates an AST
 */
class FormTests: XCTestCase {

    func testASTGeneration() {
        let qlForms = parseFileMany("SimpleForms")
        
        XCTAssertTrue(qlForms.count == 2)
        
        if let form1 = assertToForm(qlForms[0]) {
            XCTAssertTrue(form1.identifier.id == "form1")
            
            let block = form1.statement as? Block
            
            XCTAssertNotNil(block)
            XCTAssertTrue(block!.block.count == 0)
        }
        
        if let form2 = assertToForm(qlForms[1]) {
            XCTAssertTrue(form2.identifier.id == "form2")
            
            let block = form2.statement as? Block
            
            XCTAssertNotNil(block)
            XCTAssertTrue(block!.block.count == 3)
            
            guard block != nil || block!.block.count != 3
                else { return }
            
            
            let q1 = block!.block[0] as? Question
            let q2 = block!.block[1] as? Question
            let _if = block!.block[2] as? Conditional
            
            XCTAssertNotNil(q1)
            XCTAssertNotNil(q2)
            XCTAssertNotNil(_if)
            
            
            guard q1 != nil && q2 != nil && _if != nil
                else { return }
            
            XCTAssertTrue(q1!.label == "question")
            XCTAssertTrue(q2!.label == "question")
           
            XCTAssertNotNil(q1!.expression as? BooleanLiteral)
            XCTAssertNotNil(q2!.expression as? BooleanField)
            
            
            let cond = _if!.condition as? Infix
            let ifBlock = _if!.ifBlock as? Block
            
            XCTAssertNotNil(cond)
            XCTAssertNotNil(ifBlock)
            
            guard cond != nil
                else { return }
            
            XCTAssertTrue(cond!.op == BinaryOp.And)
            
            
            let lhs = cond!.lhs as? Identifier
            let rhs = cond!.rhs as? Identifier
            
            XCTAssertNotNil(rhs)
            XCTAssertNotNil(lhs)
            
            guard lhs != nil && rhs != nil
                else { return }
            
            XCTAssertTrue(lhs!.id == q1!.identifier.id)
            XCTAssertTrue(rhs!.id == q2!.identifier.id)
        }
    }
}