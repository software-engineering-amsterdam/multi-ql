//
//  QLLiteralVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation


protocol QLLiteralVisitable {
    func accept<T: QLLiteralVisitor>(visitor: T, param: T.QLLiteralVisitorParam) -> T.QLLiteralVisitorReturn
}


protocol QLLiteralVisitor {
    associatedtype QLLiteralVisitorParam
    associatedtype QLLiteralVisitorReturn
    
    func visit(node: QLStringLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn
    func visit(node: QLIntegerLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn
    func visit(node: QLFloatLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn
    func visit(node: QLBooleanLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn
    
    func defaultLeafResult(literal: QLLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn
}


protocol TopDownLiteral: QLLiteralVisitor {
}
extension TopDownLiteral {
    func visit(node: QLStringLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn {
        return defaultLeafResult(node, param: param)
    }
    func visit(node: QLIntegerLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn {
        return defaultLeafResult(node, param: param)
    }
    func visit(node: QLFloatLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn {
        return defaultLeafResult(node, param: param)
    }
    func visit(node: QLBooleanLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn {
        return defaultLeafResult(node, param: param)
    }
}