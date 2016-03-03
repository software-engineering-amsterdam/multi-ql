//
//  EvalTests.swift
//  QLSwift
//
//  Created by Tom van Duist on 25/02/16.
//
//

import XCTest
@testable import QLSwift

/**
 * Unit tests for AST eval
 */
class EvalTests: XCTestCase {

    func testAdd() {
        let form = parseFile("EvalAdd", doEval: true)
        
        guard form != nil
            else { return }
        
        let q1 = form!.block.block.first as? QLComputedQuestion
        
        XCTAssertNotNil(q1)
        guard q1 != nil
            else { return }
        
        
        XCTAssertTrue(q1!.expression.eval() == 2)
    }
    
    func testPow() {
        let form = parseFile("EvalPow", doEval: true)
        
        guard form != nil
            else { return }
        
        let q2 = form!.block.block.last as? QLComputedQuestion
        
        XCTAssertNotNil(q2)
        guard q2 != nil
            else { return }
        
        
        XCTAssertTrue(q2!.expression.eval() == 4)
    }
    
    func testPrec() {
        let form = parseFile("EvalPrec", doEval: true)
        
        guard form != nil
            else { return }
        
        let q3 = form!.block.block.last as? QLComputedQuestion
        
        XCTAssertNotNil(q3)
        guard q3 != nil
            else { return }
        
        
        XCTAssertTrue(q3!.expression.eval() == 8)
    }
    
    func testBool() {
        let form = parseFile("EvalBool", doEval: true)
        
        guard form != nil
            else { return }
        
        let q1 = form!.block.block.first as? QLComputedQuestion
        
        XCTAssertNotNil(q1)
        guard q1 != nil
            else { return }
        
        
        XCTAssertTrue(q1!.expression.eval() == true)
    }
}