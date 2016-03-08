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
    internal func parseFile(file: String, doEval: Bool = false) -> QLForm? {
        do {
            return try parseQL(getQL(self, file: file), doEval: doEval)
        } catch let e {
            print (e)
            return nil
        }
    }
    
    internal func parseQL(ql: QL, doEval: Bool = false) -> QLForm? {
        do {
            let form = try QLParser().parse(ql)
            
            if doEval {
                try SemanticAnalyzer().analyze(form)
            }
            
            return form
        } catch let e {
            print(e)
            return nil
        }
    }
    
    internal func parseFileMany(file: String, doEval: Bool = false) -> [QLForm?] {
        var result: [QLForm?] = []
        
        if let ql = try? getQL(self, file: file) {
            for s in ql.componentsSeparatedByString("#->") {
                result.append(parseQL(s, doEval: doEval))
            }
        }
        
        return result
    }
}