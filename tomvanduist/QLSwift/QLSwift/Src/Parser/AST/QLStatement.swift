//
//  QLStatement.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation

protocol QLStatement: QLNode, QLStatementVisitable {
}

class QLQuestion {
    let identifier: QLIdentifier
    let label: String
    
    init(identifier: QLIdentifier, label: String) {
        self.identifier = identifier
        self.label = label
    }
    
    func toString() -> String {
        fatalError("Override")
    }
    
    func eval(context: QLContext) -> NSObject {
        fatalError("Override")
    }
}

class QLVariableQuestion: QLQuestion, QLStatement {
    let type: QLType
    
    init(identifier: QLIdentifier, label: String, type: QLType) {
        self.type = type
        
        super.init(identifier: identifier, label: label)
    }
    
    override func toString() -> String {
        return "\(identifier.toString())"
    }
    
    override func eval(context: QLContext) -> NSObject {
        return context.retrieve(self.identifier.id)
    }
    
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLComputedQuestion: QLQuestion, QLStatement {
    let expression: QLExpression
    
    init(identifier: QLIdentifier, label: String, expression: QLExpression) {
        self.expression = expression
        
        super.init(identifier: identifier, label: label)
    }
    
    override func toString() -> String {
        return "\(identifier.toString())"
    }
    
    override func eval(context: QLContext) -> NSObject {
        return expression.eval(context)
    }
    
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLConditional: QLStatement {
    let condition: QLExpression
    let ifBlock: QLBlock
    
    init(condition: QLExpression, ifBlock: QLBlock) {
        self.condition = condition
        self.ifBlock = ifBlock
    }
    
    func toString() -> String {
        return "\(condition.toString())"
    }
    
    func isSatisfied(context: QLContext) -> Bool {
        if let isSatisfied = condition.eval(context) as? Bool {
            return isSatisfied
        }
        return false
    }
    
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLBlock: QLStatement {
    let block: [QLStatement]
    
    init (block: [QLStatement]) {
        self.block = block
    }
    
    func toString() -> String {
        return "\(block)"
    }
    
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}