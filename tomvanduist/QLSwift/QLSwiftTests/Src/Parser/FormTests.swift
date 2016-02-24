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
        let forms = parseFileMany("SimpleForms")
        
        XCTAssertTrue(forms.count == 2)
        
        guard let form1 = forms[0], form2 = forms[1] else {
            XCTAssertTrue(false, "Grammar error")
            return
        }
        
        XCTAssertTrue(form1.identifier.id == "form1")
        
        let block1 = form1.block
        
        XCTAssertNotNil(block1)
        XCTAssertTrue(block1.block.count == 0)
        
        
        
        XCTAssertTrue(form2.identifier.id == "form2")
        
        let block2 = form2.block
        
        XCTAssertNotNil(block2)
        XCTAssertTrue(block2.block.count == 3)
        
        guard block2.block.count != 3
            else { return }
        
        
        let q1 = block2.block[0] as? Question
        let q2 = block2.block[1] as? Question
        let _if = block2.block[2] as? Conditional
        
        XCTAssertNotNil(q1)
        XCTAssertNotNil(q2)
        XCTAssertNotNil(_if)
        
        
        guard q1 != nil && q2 != nil && _if != nil
            else { return }
        
        XCTAssertTrue(q1!.label == "question")
        XCTAssertTrue(q2!.label == "question")
       
        XCTAssertNotNil(q1!.expression as? BooleanLiteral)
        XCTAssertNotNil(q2!.expression as? BooleanField)
        
        
        let cond = _if!.condition as? Binary
        let ifBlock = _if!.ifBlock
        
        XCTAssertNotNil(cond)
        XCTAssertNotNil(ifBlock)
        
        guard cond != nil
            else { return }
        
        XCTAssertTrue(cond!.dynamicType == And.self)
        
        
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