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

    func testNeg() {
        eval("EvalNeg", expectedValues: [
            "q2": -10,
            "q3": -10,
            "q4": 10,
            ]
        )
    }
    
    func testNot() {
        eval("EvalNot", expectedValues: [
            "q1": false,
            "q2": true,
            ]
        )
    }
    
    func testMul() {
        eval("EvalMul", expectedValues: [
            "q1": QLFloat(3)
            ]
        )
    }
    
    func testAdd() {
        eval("EvalAdd", expectedValues: [
            "q1": 2,
            "q2": QLFloat(3.1),
            ]
        )
    }
    
    func testCombination() {
        eval("EvalCombination", expectedValues: [
            "q1": 1,
            "q2": 2,
            "q3": 12,
            "q4": true,
            "q5": QLFloat(120),
            ]
        )
    }
    
    func testSub() {
        eval("EvalSub", expectedValues: [
            "q1": 0,
            "q2": QLFloat(-1.1),
            ]
        )
    }
    
    func testPow() {
        eval("EvalPow", expectedValues: [
            "q2": 4
            ]
        )
    }
    
    func testPrec() {
        eval("EvalPrec", expectedValues: [
            "q3": 8
            ]
        )
    }
    
    func testBool() {
        eval("EvalBool", expectedValues: [
                "q1": true
            ]
        )
    }
    
    private func eval(file: String, var expectedValues: [String: NSObject?]) {
        
        guard let form = parseFile(file, doEval: true)
            else { XCTFail("Parse failed"); return }
        
        
        guard let context = try? Context(form: form)
            else { XCTFail("Parse failed"); return }
        
        let interpreter = Interpreter.sharedInstance
        
        for (id, expectedValue) in expectedValues {
            guard let expression = context.retrieveExpression(id)
                else { XCTFail("Question with id \'\(id)\' does not exist!"); return }
            
            let realValue = interpreter.resolve(expression, context: context)
            
            guard realValue == expectedValue
                else { XCTFail("Values for \'\(id)\' are not equal (\(expectedValue) != \(realValue)"); return }
            
            expectedValues.removeValueForKey(id)
        }
        
        
        XCTAssertTrue(expectedValues.isEmpty, "Not all expected values are resolved!")
    }
}