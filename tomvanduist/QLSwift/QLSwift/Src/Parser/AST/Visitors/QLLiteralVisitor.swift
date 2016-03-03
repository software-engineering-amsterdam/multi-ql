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
    typealias QLLiteralVisitorParam
    typealias QLLiteralVisitorReturn
    
    func visit(node: QLStringLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn
    func visit(node: QLIntegerLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn
    func visit(node: QLBooleanLiteral, param: QLLiteralVisitorParam) -> QLLiteralVisitorReturn
}
