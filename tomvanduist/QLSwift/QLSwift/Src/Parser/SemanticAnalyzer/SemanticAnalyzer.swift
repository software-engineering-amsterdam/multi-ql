//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

protocol SemanticAnalyzer {
    func analyze(form: QLForm) throws -> (QLForm, [SemanticWarning])
}

class DefaultSemanticAnalyzer: SemanticAnalyzer, QLStatementVisitor, QLExpressionVisitor, QLTypeVisitor {
    
    typealias QLStatementVisitorParam   = Void?
    typealias QLExpressionVisitorParam  = Void?
    typealias QLTypeVisitorParam        = Void?
    typealias QLStatementVisitorReturn  = Void
    typealias QLExpressionVisitorReturn = Void
    typealias QLTypeVisitorReturn       = Void
    
    private var context: Context
    private var error: SemanticError = SemanticError.None
    private var warnings: [SemanticWarning] = []
    
    init(context: Context) {
        self.context = context
    }
    
    func analyze(form: QLForm) throws -> (QLForm, [SemanticWarning]) {
        error = SemanticError.None
        
        form.block.accept(self, param: nil)
        
        if case SemanticError.None = error {
            return (form, warnings)
        } else {
            throw error
        }
    }
    
    func visit(node: QLVariableQuestion, param: Void?) {
        node.identifier.accept(self, param: param)

        // TODO:
//        do { try context.assign(node.identifier, object: (type(node.type), node.expression)) }
//        catch let warning as SemanticWarning { self.warnings.append(warning) }
//        catch let e { error.collect(e) }
    }
    
    func visit(node: QLComputedQuestion, param: Void?) {
        node.identifier.accept(self, param: param)
        node.expression.accept(self, param: param)
        
        do { try context.assign(node.identifier, object: (type(node.expression), node.expression)) }
        catch let warning as SemanticWarning { self.warnings.append(warning) }
        catch let e { error.collect(e) }
    }
    
    func visit(node: QLConditional, param: Void?) {
        node.condition.accept(self, param: param)
        node.ifBlock.accept(self, param: param)
        
        if (type(node.condition) !== QLBooleanType.self) {
            error.collect(SemanticError.TypeMismatch(description: "If statement condition must be of type Bool: \(node.condition)"))
        }
    }
    
    func visit(node: QLBlock, param: Void?) {
        context = Context(parent: context)
        
        for statement in node.block {
            statement.accept(self, param: param)
        }
        
        if let parent = context.parent {
            context = parent
        }
    }
    
    func visit(node: QLIdentifier, param: Void?) {
        
    }
    
    func visit(node: QLMoneyType, param: Void?) {
//        super.visit(node, param: param)
//        
//        if let expression = node.expression {
//            if type(expression) !== QLMoneyType.self {
//                error.collect(SemanticError.TypeMismatch(description: "Money expression must result in a numerical value: \(node.expression)"))
//            }
//        }
    }
    
    func visit(node: QLStringType, param: QLTypeVisitorParam) {
        
    }
    
    func visit(node: QLBooleanType, param: QLTypeVisitorParam) {
        
    }
    
    func visit(node: QLBooleanLiteral, param: QLExpressionVisitorParam) {
        
    }
    
    func visit(node: QLIntegerLiteral, param: QLExpressionVisitorParam) {
        
    }
    
    func visit(node: QLStringLiteral, param: QLExpressionVisitorParam) {
        
    }
    
    func visit(node: QLNeg, param: Void?) {
        node.rhs.accept(self, param: param)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Unary type does not match expression type. \(node) does not match \(node.rhs)."))
        }
    }
    
    func visit(node: QLNot, param: Void?) {
        node.rhs.accept(self, param: param)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Unary type does not match expression type. \(node) does not match \(node.rhs)."))
        }
    }
    
    func collectBinaryTypeError(node: QLBinary) {
        self.error.collect(SemanticError.TypeMismatch(description: "Binary type does not match expression type(s). \(node) does not match \(node.lhs) and \(node.rhs)."))
    }
    
    func visitBinary(node: QLBinary) {
        node.lhs.accept(self, param: nil)
        node.rhs.accept(self, param: nil)
    }

    func visitBinaryNumber(node: QLBinary) {
        visitBinary(node)
        
        if (type(node.lhs) !== QLMoneyType.self || type(node.rhs) !== QLMoneyType.self) {
            collectBinaryTypeError(node)
        }
    }
    
    func visit(node: QLAdd, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visit(node: QLSub, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visit(node: QLMul, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visit(node: QLDiv, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visit(node: QLPow, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visit(node: QLGe, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visit(node: QLGt, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visit(node: QLLe, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visit(node: QLLt, param: Void?) {
        visitBinaryNumber(node)
    }
    
    func visitBinaryEq(node: QLBinary) {
        visitBinary(node)
        
        if (type(node.lhs) !== type(node.rhs)) {
            collectBinaryTypeError(node)
        }
    }
    
    func visit(node: QLEq, param: Void?) {
        visitBinaryEq(node)
    }
    
    func visit(node: QLNe, param: Void?) {
        visitBinaryEq(node)
    }
    
    func visitBinaryBool(node: QLBinary) {
        visitBinary(node)
        
        if (type(node.lhs) !== QLBooleanType.self || type(node.rhs) !== QLBooleanType.self) {
            collectBinaryTypeError(node)
        }
    }
    
    func visit(node: QLAnd, param: Void?) {
        visitBinaryBool(node)
    }
    
    func visit(node: QLOr, param: Void?) {
        visitBinaryBool(node)
    }
    
    private func type(node: QLExpression) -> QLType {
//        let type = node.type
//        
//        if node.type === QLUnknownType.self {
//            error.collect(SemanticError.NotDefined(description: "\(node) is not (yet) defined."))
//        }
    
        return QLBooleanType()
    }
}
