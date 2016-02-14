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
}


// MARK: Convenience methods

extension QLParserTests {
    func parseFile(file: String) -> QLForm? {
        return try? QLParser().parse(getQL(self, file: file))
    }
}