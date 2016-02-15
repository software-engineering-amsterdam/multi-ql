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


// MARK: Convenience methods

extension QLParserTests {
    private func parseFile(file: String) -> QLForm? {
        return try? QLParser().parse(getQL(self, file: file))
    }
    
    private func parseFileMany(file: String) -> [QLForm?] {
        let parser = QLParser()
        var result: [QLForm?] = []
        
        if let ql = try? getQL(self, file: file) {
            for s in ql.componentsSeparatedByString("#->") {
                result.append(try? parser.parse(s))
            }
        }
        
        return result
    }
}