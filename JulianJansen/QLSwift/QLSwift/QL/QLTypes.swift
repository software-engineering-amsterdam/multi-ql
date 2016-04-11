//
//  QLTypes.swift
//  QLSwift
//
//  Created by Julian Jansen on 08-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

enum QLTypes {
    case Unknown
    case Boolean
    case String
    case Integer
}

class QLType: Equatable {
    var type: QLTypes
    var description: String {
        return "unknown"
    }
    
    init() {
        type = .Unknown
    }
}

func ==(lhs: QLType, rhs: QLType) -> Bool {
    return lhs.type == rhs.type
}

class QLBoolType: QLType {
    override init() {
        super.init()
        type = .Boolean
    }
    
    override var description : String {
        return "boolean"
    }
}

class QLStringType: QLType {
    override init() {
        super.init()
        type = .String
    }
    
    override var description : String {
        return "string"
    }
}

class QLIntegerType: QLType {
    override init() {
        super.init()
        type = .Integer
    }
    
    override var description : String {
        return "integer"
    }
}

class QLUnknownType: QLType {
    override init() {
        super.init()
        type = .Unknown
    }
    
    override var description : String {
        return "unknown"
    }
}