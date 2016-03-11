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
        eval("EvalAdd", expectedValues: [
            "q1": 2
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