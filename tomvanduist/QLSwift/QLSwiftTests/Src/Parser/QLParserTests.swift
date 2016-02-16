//
//  QLParserTests.swift
//  QLSwift
//
//  Created by Tom van Duist on 14/02/16.
//
//

import XCTest
@testable import QLSwift

/**
 * Unit tests for testing the grammar. 
 * Does not test type checking and dependencies and so forth
 */
class QLParserTests: XCTestCase {

    func testParseMultiple() {
        let forms = parseFileMany("ParseMultiple").filter { form in form != nil }
        XCTAssertTrue(forms.count == 2)
    }
    
    func testLiterals() {
        XCTAssertNotNil(parseFile("Literals"))
    }
    
    func testIfStatement() {
        XCTAssertNotNil(parseFile("IfStatement"))
    }
    
    func testPrefix() {
        XCTAssertNotNil(parseFile("Prefix"))
    }
    
    func testInfix() {
        XCTAssertNotNil(parseFile("Infix"))
    }
    
    func testQuestion() {
        for form in parseFileMany("FailQuestion") {
            XCTAssertNil(form)
        }
    }
}