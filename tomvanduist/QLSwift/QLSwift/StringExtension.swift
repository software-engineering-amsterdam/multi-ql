//
//  StringExtension.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation

extension String {
    
    init(stringFromFile file: String, ofType type: String) throws {
        let bundle = NSBundle.mainBundle()
        if let path = bundle.pathForResource(file, ofType: type) {
            if let content = try? NSString(contentsOfFile: path, encoding: NSUTF8StringEncoding) {
                self.init(content)
                return
            }
        }
        
        throw NSError(domain: kDomain, code: 98734, userInfo: ["info": "File \(file).\(type) not found!"])
    }
}