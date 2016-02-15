//
//  Utils.swift
//  QLSwift
//
//  Created by Tom van Duist on 14/02/16.
//
//

import Foundation


internal func getQL(owner: AnyObject, file: String) throws -> QL {
    let bundle = NSBundle(forClass: owner.dynamicType)
    if let path = bundle.pathForResource(file, ofType: "ql") {
        if let content = try? QL(contentsOfFile: path, encoding: NSUTF8StringEncoding) {
            return content
        }
    }
    
    throw NSError(domain: kDomain, code: 9000, userInfo: ["info": "File \(file).ql not found!"])
}