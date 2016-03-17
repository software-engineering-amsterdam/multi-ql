//
//  QLNodeVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation

protocol QLNodeVisitor: QLStatementVisitor, QLExpressionVisitor, QLLiteralVisitor, QLTypeVisitor {
}


protocol TopDown: TopDownStatement, TopDownExpression, TopDownLiteral, TopDownType {
}
extension TopDown {
    
    // Connect statement visitor to other visitors
    func visit(node: QLVariableQuestion, param: QLStatementVisitorParam) -> QLStatementVisitorReturn {
        if let typeParam = param as? QLTypeVisitorParam {
            node.type.accept(self, param: typeParam)
        }
        return defaultReturn(node, param: param)
    }
    func visit(node: QLComputedQuestion, param: QLStatementVisitorParam) -> QLStatementVisitorReturn {
        if let expressionParam = param as? QLExpressionVisitorParam {
            node.expression.accept(self, param: expressionParam)
        }
        return defaultReturn(node, param: param)
    }
    func visit(node: QLConditional, param: QLStatementVisitorParam) -> Self.QLStatementVisitorReturn {
        if let expressionParam = param as? QLExpressionVisitorParam {
            node.condition.accept(self, param: expressionParam)
        }
        return node.ifBlock.accept(self, param: param)
    }
    
    // Connect expression visitor to other visitors
    func visit(node: QLLiteralExpression, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn {
        if let literalParam = param as? QLLiteralVisitorParam {
            node.literal.accept(self, param: literalParam)
        }
        return defaultReturn(node, param: param)
    }
}