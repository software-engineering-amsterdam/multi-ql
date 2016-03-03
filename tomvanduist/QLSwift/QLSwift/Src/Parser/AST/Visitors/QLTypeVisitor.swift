//
//  QLStatementVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

protocol QLTypeVisitor {
    typealias QLTypeVisitorParam
    typealias QLTypeVisitorReturn
    
    func visit(node: QLMoneyType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
    func visit(node: QLStringType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
    func visit(node: QLBooleanType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
}

protocol QLTypeVisitable {
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn
}

extension QLMoneyType {
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLStringType {
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLBooleanType {
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn {
        return visitor.visit(self, param: param)
    }
}