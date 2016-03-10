//
//  QLStatementVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation


protocol QLTypeVisitable {
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn
}


protocol QLTypeVisitor {
    typealias QLTypeVisitorParam
    typealias QLTypeVisitorReturn
    
    func visit(node: QLStringType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
    func visit(node: QLIntegerType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
    func visit(node: QLFloatType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
    func visit(node: QLBooleanType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
    func visit(node: QLVoidType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
    func visit(node: QLUnknownType, param: QLTypeVisitorParam) -> QLTypeVisitorReturn
}
