//
//  AppLogger.swift
//  QLSwift
//
//  Created by Julian Jansen on 30-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class AppLogger {
    
    static let sharedInstance = AppLogger()
    
    private var warnings = Array<ErrorType>()
    private var errors = Array<ErrorType>()
    
    func logWarning(warning: ErrorType) {
        warnings.append(warning)
    }
    
    func logError(error: ErrorType) {
        errors.append(error)
    }
    
    func getErrorCount() -> Int {
        return errors.count
    }
    
    func didLog() -> Bool {
        if ((errors.count + warnings.count) > 0) {
            return true
        } else {
            return false
        }
    }
    
    func getReport() -> String {
        var messages = String()
        
        for warning in warnings {
            messages += String(warning)
            messages += "\n"
        }
        
        for error in errors {
            messages += String(error)
            messages += "\n"
        }
        
        return messages
    }
    
    func printLogInTerminal() {
        print("Warnings")
        for warning in warnings {
            print(warning)
        }
        
        print("Errors")
        for error in errors {
            print(error)
        }
    }
}

// MARK: Error and warning types

struct DuplicateWarning: ErrorType {
    let message: String
}

struct ReadError: ErrorType {
    let message: String
}

struct ASTError: ErrorType {
    let message: String
}

struct SymbolError: ErrorType {
    let message: String
}

struct EnvironmentError: ErrorType {
    let message: String
}

struct TypeError: ErrorType {
    let message: String
}