//
//  Interpreter.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/03/16.
//
//

import Foundation


public class Interpreter: QLExpressionVisitor, QLLiteralVisitor {
    static let sharedInstance = Interpreter()
    
    func resolve(expression: QLExpression, context: Context) -> NSObject? {
        return expression.accept(self, param: context)
    }
}


// MARK: - QLExpressionVisitor conformance

extension Interpreter {
    
    func visit(node: QLVariable, param context: Context) -> NSObject? {
        if let value = context.retrieveValue(node.id) {
            return value
        }
        
        if let expression = context.retrieveExpression(node.id) {
            return expression.accept(self, param: context)
        }
        
        return nil
    }
    func visit(node: QLNeg, param context: Context) -> NSObject? {
        guard let value = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return value * -1
    }
    func visit(node: QLAdd, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? NSInteger, rval = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return lval + rval
    }
    func visit(node: QLSub, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? NSInteger, rval = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return lval - rval
    }
    func visit(node: QLMul, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? NSInteger, rval = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return lval * rval
    }
    func visit(node: QLDiv, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? NSInteger, rval = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return lval / rval
    }
    func visit(node: QLPow, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? Double, rval = node.rhs.accept(self, param: context) as? Double
            else { return nil }
        
        return NSInteger(pow(lval, rval))
    }
    func visit(node: QLEq, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context), rval = node.rhs.accept(self, param: context)
            else { return nil }
        
        return lval == rval
    }
    func visit(node: QLNe, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context), rval = node.rhs.accept(self, param: context)
            else { return nil }
        
        return lval != rval
    }
    func visit(node: QLGe, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? NSInteger, rval = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return lval >= rval
    }
    func visit(node: QLGt, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? NSInteger, rval = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return lval > rval
    }
    func visit(node: QLLe, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? NSInteger, rval = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return lval <= rval
    }
    func visit(node: QLLt, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? NSInteger, rval = node.rhs.accept(self, param: context) as? NSInteger
            else { return nil }
        
        return lval < rval
    }
    func visit(node: QLAnd, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? Bool, rval = node.rhs.accept(self, param: context) as? Bool
            else { return nil }
        
        return lval && rval
    }
    func visit(node: QLOr, param context: Context) -> NSObject? {
        guard let lval = node.lhs.accept(self, param: context) as? Bool, rval = node.rhs.accept(self, param: context) as? Bool
            else { return nil }
        
        return lval || rval
    }
    func visit(node: QLNot, param context: Context) -> NSObject? {
        guard let value = node.rhs.accept(self, param: context) as? Bool
            else { return nil }
        
        return value == false
    }
    func visit(node: QLLiteralExpression, param context: Context) -> NSObject? {
        return node.literal.accept(self, param: context)
    }
}


// MARK: - QLLiteralVisitor conformance

extension Interpreter {
    
    func visit(node: QLStringLiteral, param context: Context) -> NSObject? {
        return node.value
    }
    func visit(node: QLIntegerLiteral, param context: Context) -> NSObject? {
        return node.value
    }
    func visit(node: QLFloatLiteral, param context: Context) -> NSObject? {
        return node.value
    }
    func visit(node: QLBooleanLiteral, param context: Context) -> NSObject? {
        return node.value
    }
}