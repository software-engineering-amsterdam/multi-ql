//
//  SemanticLog.swift
//  QL
//
//  Created by Jordy Heemskerk on 25/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct SemanticLog {
    
    var semanticLog = [SemanticLoggable]()
    
    mutating func logError(semanticError: SemanticError) {
        semanticLog.append(semanticError)
    }
    
    mutating func logWarning(semanticWarning: SemanticWarning) {
        semanticLog.append(semanticWarning)
    }
    
    func printLog() {
        if semanticLog.count > 0 {
            print("Semantic Log:")
            semanticLog.forEach {
                print($0)
            }
        }
    }
    
}
