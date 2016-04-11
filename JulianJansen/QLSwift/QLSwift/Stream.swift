//
//  Stream.swift
//  QLSwift
//
//  Created by Julian Jansen on 05-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class Stream {
    
    private var stream = String()
    
    func readFile(path: String?) throws {
        
        if (path != nil) {
            do {
                stream = try String(contentsOfFile: path!, encoding: NSUTF8StringEncoding)
            } catch {
                throw ReadError(message: "Unsupported file type")
            }
        } else {
            throw ReadError(message: "Could not find file with path \(path))")
        }
    }
    
    func parseStream() throws -> QLForm? {
        var ast: QLForm?
        
        do {
            ast = try QLParser().parseStream(stream)
        } catch {
            throw error
        }
        
        return ast
    }
}