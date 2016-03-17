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
        runValidForms("TypedValidForm")
    }
    
    func testInvalidTyped() {
        runInvalidForms("TypedInvalidForms", semanticRules: [TypeChecker()])
    }

    func testCyclicDependency() {
        runInvalidForms("CyclicDependency", semanticRules: [CyclicDependencyChecker()])
    }

    func testScoped() {
        runInvalidForms("ScopedInvalid", semanticRules: [ScopeChecker()])
    }

    func testValidTypeInference() {
        runValidForms("ValidTypeInference", semanticRules: [])
    }
    
    private func runValidForms(file: String, semanticRules: [SemanticAnalysisRule]? = nil) {
        if let form = parseFile(file) {
            let sa = SemanticAnalyzer()
            
            do {
                if semanticRules != nil {
                    try sa.analyze(form, rules: semanticRules!)
                } else {
                    try sa.analyze(form)
                }
            }
            catch let error {
                print("\(error)")
                XCTAssertTrue(false, "\(error)")
            }
        } else {
            XCTFail("Parse error")
        }
    }
    
    private func runInvalidForms(file: String, semanticRules: [SemanticAnalysisRule]? = nil) {
        let forms = parseFileMany(file)
        
        XCTAssertNotNil(forms)
        
        
        for form in forms {
            XCTAssertNotNil(form)
            
            guard form != nil
                else { continue }
            
            
            XCTAssertNotNil(form)
            guard form != nil
                else { continue }
            
            
            let sa = SemanticAnalyzer()
            
            
            do {
                if semanticRules != nil {
                    try sa.analyze(form!, rules: semanticRules!)
                } else {
                    try sa.analyze(form!)
                }
                XCTAssertTrue(false)
            }
            catch let error {
                print(error)
                // Expected behaviour, move along!
            }
        }
    }
}