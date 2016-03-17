//
//  QLStatementVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation


protocol QLStatementVisitable {
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn
}


protocol QLStatementVisitor {
    typealias QLStatementVisitorParam
    typealias QLStatementVisitorReturn
    
    func visit(node: QLVariableQuestion, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
    func visit(node: QLComputedQuestion, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
    func visit(node: QLConditional, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
    func visit(node: QLBlock, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
    
    func defaultReturn(statement: QLStatement?, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
}


protocol TopDownStatement: QLStatementVisitor {
}
extension TopDownStatement {
    func visit(node: QLVariableQuestion, param: QLStatementVisitorParam) -> QLStatementVisitorReturn {
        return defaultReturn(node, param: param)
    }
    func visit(node: QLComputedQuestion, param: QLStatementVisitorParam) -> QLStatementVisitorReturn {
        return defaultReturn(node, param: param)
    }
    func visit(node: QLConditional, param: QLStatementVisitorParam) -> QLStatementVisitorReturn {
        return node.ifBlock.accept(self, param: param)
    }
    func visit(node: QLBlock, param: QLStatementVisitorParam) -> QLStatementVisitorReturn {
        for statement in node.block {
            statement.accept(self, param: param)
        }
        
        return defaultReturn(nil, param: param)
    }
}