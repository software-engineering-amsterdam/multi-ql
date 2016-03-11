//
//  QLStatement.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation

protocol QLStatement: QLNode, QLStatementVisitable {
    func isConditional() -> Bool
}

protocol QLQuestion: QLStatement {
    var identifier: QLIdentifier { get }
    var  label: String { get }
    
    func isComputed() -> Bool
}

class QLVariableQuestion: QLQuestion {
    let identifier: QLIdentifier
    let label: String
    let type: QLType
    
    init(identifier: QLIdentifier, label: String, type: QLType) {
        self.identifier = identifier
        self.label = label
        self.type = type
    }
    
    func isComputed() -> Bool {
        return false
    }
    
    func isConditional() -> Bool {
        return false
    }
    
    func toString() -> String {
        return "\(identifier.toString())"
    }
    
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLComputedQuestion: QLQuestion {
    let identifier: QLIdentifier
    let label: String
    let expression: QLExpression
    
    init(identifier: QLIdentifier, label: String, expression: QLExpression) {
        self.identifier = identifier
        self.label = label
        self.expression = expression
    }
    
    func isComputed() -> Bool {
        return true
    }
    
    func isConditional() -> Bool {
        return false
    }
    
    func toString() -> String {
        return "\(identifier.toString())"
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
    
    func isConditional() -> Bool {
        return true
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

class QLBlock {
    let block: [QLStatement]
    
    init(block: [QLStatement]) {
        self.block = block
    }
    
    func questions() -> [QLQuestion] {
        return block.filter { !$0.isConditional() }.map { $0 as! QLQuestion }
    }
    
    func conditionals() -> [QLConditional] {
        return block.filter { $0.isConditional() }.map { $0 as! QLConditional }
    }
    
    func toString() -> String {
        return "\(block)"
    }
    
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}