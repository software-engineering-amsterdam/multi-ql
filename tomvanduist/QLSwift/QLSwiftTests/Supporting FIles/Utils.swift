//
//  Utils.swift
//  QLSwift
//
//  Created by Tom van Duist on 14/02/16.
//
//

import XCTest


internal func getQL(owner: AnyObject, file: String) throws -> QL {
    let bundle = NSBundle(forClass: owner.dynamicType)
    if let path = bundle.pathForResource(file, ofType: "ql") {
        if let content = try? QL(contentsOfFile: path, encoding: NSUTF8StringEncoding) {
            return content
        }
    }
    
    throw NSError(domain: kDomain, code: 9000, userInfo: ["info": "File \(file).ql not found!"])
}


// MARK: Convenience methods

extension XCTestCase {
    internal func parseFile(file: String) -> QLForm? {
        return try? QLParser().parse(getQL(self, file: file))
    }
    
    internal func parseFileMany(file: String) -> [QLForm?] {
        let parser = QLParser()
        var result: [QLForm?] = []
        
        if let ql = try? getQL(self, file: file) {
            for s in ql.componentsSeparatedByString("#->") {
                result.append(try? parser.parse(s))
            }
        }
        
        return result
    }
    
    internal func assertToForm(qlForm: QLForm?) -> Form? {
        XCTAssertNotNil(qlForm)
        
        let form = qlForm?.implode()
        XCTAssertNotNil(form)
        
        return form
    }
}