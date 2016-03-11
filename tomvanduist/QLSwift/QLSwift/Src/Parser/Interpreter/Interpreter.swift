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
    
    private func resolveUnary(unary: QLUnary, context: Context, resolver: UnaryResolver) -> NSObject? {
        guard let value = unary.rhs.accept(self, param: context)
            else { return nil }
        
        let type = TypeInferer.sharedInstance.inferType(unary, context: context)
        
        return resolver.resolve(type, value: value)
    }
    func visit(node: QLNeg, param context: Context) -> NSObject? {
        return resolveUnary(node, context: context, resolver: NegResolver())
    }
    func visit(node: QLNot, param context: Context) -> NSObject? {
        return resolveUnary(node, context: context, resolver: NotResolver())
    }
    
    private func resolveBinary(binary: QLBinary, context: Context, resolver: BinaryResolver) -> NSObject? {
        guard let
            lVal = binary.lhs.accept(self, param: context),
            rVal = binary.rhs.accept(self, param: context)
            else { return nil }
        
        let type = TypeInferer.sharedInstance.inferType(binary, context: context)
        
        return resolver.resolve(type, value: (lVal, rVal))
    }
    func visit(node: QLAdd, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: AddResolver())
    }
    func visit(node: QLSub, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: SubResolver())
    }
    func visit(node: QLMul, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: MulResolver())
    }
    func visit(node: QLDiv, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: DivResolver())
    }
    func visit(node: QLPow, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: PowResolver())
    }
    func visit(node: QLEq, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: EqualsResolver())
    }
    func visit(node: QLNe, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: NotEqualsResolver())
    }
    func visit(node: QLGe, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: GreaterEqualsResolver())
    }
    func visit(node: QLGt, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: GreaterThanResolver())
    }
    func visit(node: QLLe, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: LowerEqualsResolver())
    }
    func visit(node: QLLt, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: LowerThanResolver())
    }
    func visit(node: QLAnd, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: AndResolver())
    }
    func visit(node: QLOr, param context: Context) -> NSObject? {
        return resolveBinary(node, context: context, resolver: OrResolver())
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


private protocol Resolver {
    typealias GenericParam
    
    func resolve(type: QLType, value: GenericParam?) -> NSObject?
}

private class AbstractResolver<T>: Resolver, QLTypeVisitor {
    func resolve(type: QLType, value: T?) -> NSObject? {
        guard value != nil
            else { return nil }
        
        return type.accept(self, param: value!)
    }
    
    func visit(node: QLStringType, param value: T) -> NSObject? {
        return nil
    }
    func visit(node: QLIntegerType, param value: T) -> NSObject? {
        return nil
    }
    func visit(node: QLFloatType, param value: T) -> NSObject? {
        return nil
    }
    func visit(node: QLBooleanType, param value: T) -> NSObject? {
        return nil
    }
    func visit(node: QLVoidType, param value: T) -> NSObject? {
        return nil
    }
    func visit(node: QLUnknownType, param value: T) -> NSObject? {
        return nil
    }
}

private class UnaryResolver: AbstractResolver<NSObject> {
}
private class NegResolver: UnaryResolver {
    override func visit(node: QLFloatType, param value: NSObject) -> NSObject? {
        if let value = value as? QLFLoat {
            return value * -1
        }
        return nil
    }
    override func visit(node: QLIntegerType, param value: NSObject) -> NSObject? {
        if let value = value as? QLInteger {
            return value * -1
        }
        return nil
    }
}
private class NotResolver: UnaryResolver {
    override func visit(node: QLBooleanType, param value: NSObject) -> NSObject? {
        if let value = value as? QLBoolean {
            return !value
        }
        return nil
    }
}

private class BinaryResolver: AbstractResolver<(left: NSObject, right: NSObject)> {
}
private class AddResolver: BinaryResolver {
    override func visit(node: QLFloatType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return lVal + rVal
        }
        return nil
    }
    override func visit(node: QLIntegerType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLInteger, rVal = value.right as? QLInteger {
            return lVal + rVal
        }
        return nil
    }
}
private class SubResolver: BinaryResolver {
    override func visit(node: QLFloatType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return lVal - rVal
        }
        return nil
    }
    override func visit(node: QLIntegerType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLInteger, rVal = value.right as? QLInteger {
            return lVal - rVal
        }
        return nil
    }
}
private class MulResolver: BinaryResolver {
    override func visit(node: QLFloatType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return lVal * rVal
        }
        return nil
    }
    override func visit(node: QLIntegerType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLInteger, rVal = value.right as? QLInteger {
            return NSInteger(lVal * rVal)
        }
        return nil
    }
}
private class DivResolver: BinaryResolver {
    override func visit(node: QLFloatType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return lVal / rVal
        }
        return nil
    }
    override func visit(node: QLIntegerType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLInteger, rVal = value.right as? QLInteger {
            return NSInteger(lVal / rVal)
        }
        return nil
    }
}
private class PowResolver: BinaryResolver {
    override func visit(node: QLFloatType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return pow(lVal, rVal)
        }
        return nil
    }
}
private class EqualsResolver: BinaryResolver {
    override func visit(node: QLBooleanType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        return value.left == value.right
    }
}
private class NotEqualsResolver: BinaryResolver {
    override func visit(node: QLBooleanType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        return value.left != value.right
    }
}
private class GreaterEqualsResolver: BinaryResolver {
    override func visit(node: QLBooleanType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return lVal >= rVal
        }
        return nil
    }
}
private class GreaterThanResolver: BinaryResolver {
    override func visit(node: QLBooleanType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return lVal > rVal
        }
        return nil
    }
}
private class LowerEqualsResolver: BinaryResolver {
    override func visit(node: QLBooleanType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return lVal <= rVal
        }
        return nil
    }
}
private class LowerThanResolver: BinaryResolver {
    override func visit(node: QLBooleanType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLFLoat, rVal = value.right as? QLFLoat {
            return lVal < rVal
        }
        return nil
    }
}
private class AndResolver: BinaryResolver {
    override func visit(node: QLBooleanType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLBoolean, rVal = value.right as? QLBoolean {
            return lVal && rVal
        }
        return nil
    }
}
private class OrResolver: BinaryResolver {
    override func visit(node: QLBooleanType, param value: (left: NSObject, right: NSObject)) -> NSObject? {
        if let lVal = value.left as? QLBoolean, rVal = value.right as? QLBoolean {
            return lVal || rVal
        }
        return nil
    }
}