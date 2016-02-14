//
//  QLParserTests.swift
//  QLSwift
//
//  Created by Tom van Duist on 14/02/16.
//
//

import XCTest
@testable import QLSwift

class QLParserTests: XCTestCase {

    func LiteralsTest() {
        XCTAssertNotNil(try? QLParser().parse(getQL(self, file: "literals")))
    }
}