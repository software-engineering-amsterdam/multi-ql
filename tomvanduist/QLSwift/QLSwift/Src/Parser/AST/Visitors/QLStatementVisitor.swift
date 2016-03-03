//
//  QLStatementVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

protocol QLStatementVisitor {
    typealias QLStatementVisitorParam
    typealias QLStatementVisitorReturn
    
    func visit(node: QLVariableQuestion, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
    func visit(node: QLComputedQuestion, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
    func visit(node: QLConditional, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
    func visit(node: QLBlock, param: QLStatementVisitorParam) -> QLStatementVisitorReturn
}

protocol QLStatementVisitable {
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn
}

extension QLVariableQuestion {
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLComputedQuestion {
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLConditional {
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLBlock {
    func accept<T: QLStatementVisitor>(visitor: T, param: T.QLStatementVisitorParam) -> T.QLStatementVisitorReturn {
        return visitor.visit(self, param: param)
    }
}