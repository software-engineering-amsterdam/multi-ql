//
//  SemanticAnalyzerTests.swift
//  QLSwift
//
//  Created by Tom van Duist on 16/02/16.
//
//

import XCTest
@testable import QLSwift

/**
 * Unit tests for testing the sememantic analyzer of the AST.
 */
class SemanticAnalyzerTests: XCTestCase {
    
    func testValid() {
        if let form = assertToForm(parseFile("TypedValidForm")) {
            let sa = SemanticAnalyser(context: Context())
            
            do {
                try sa.analyze(form)
            }
            catch let error {
                print("\(error)")
                XCTAssertTrue(false, "\(error)")
            }
        }
    }
    
    func testInvalid() {
        let qlForms = parseFileMany("TypedInvalidForms")
        
        XCTAssertNotNil(qlForms)
        XCTAssertTrue(qlForms.count == 4)
        
        
        for qlForm in qlForms {
            XCTAssertNotNil(qlForm)
            guard qlForm != nil
                else { continue }
            
            
            let form = assertToForm(qlForm)
            
            XCTAssertNotNil(form)
            guard form != nil
                else { continue }
            
            
            let sa = SemanticAnalyser(context: Context())
            
            
            do {
                try sa.analyze(form!)
                XCTAssertTrue(false)
            }
            catch {
                // Expected behaviour, move along!
            }
        }
    }
    
    func testCyclicDependency() {
        if let form = assertToForm(parseFile("CyclicDependency")) {
            let sa = SemanticAnalyser(context: Context())
            
            do {
                try sa.analyze(form)
                XCTAssertTrue(false)
            }
            catch {
                // Expected behaviour, move along!
            }
        }
    }
}