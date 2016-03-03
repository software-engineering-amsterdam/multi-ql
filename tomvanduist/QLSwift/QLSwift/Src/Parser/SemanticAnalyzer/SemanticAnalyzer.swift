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

class DefaultSemanticAnalyzer: DefaultQLNodeVisitor, SemanticAnalyzer {
    
    private var context: Context
    private var error: SemanticError = SemanticError.None
    private var warnings: [SemanticWarning] = []
    
    init(context: Context) {
        self.context = context
    }
    
    func analyze(form: QLForm) throws -> (QLForm, [SemanticWarning]) {
        error = SemanticError.None
        
        form.accept(self, param: nil)
        
        if case SemanticError.None = error {
            return (form, warnings)
        } else {
            throw error
        }
    }
    
    override func visit(node: QLQuestion, param: GenericParam) {
        super.visit(node, param: param)
        
        do { try context.assign(node.identifier, object: (type(node.expression), node.expression)) }
        catch let warning as SemanticWarning { self.warnings.append(warning) }
        catch let e { error.collect(e) }
    }
    
    override func visit(node: QLConditional, param: GenericParam) {
        super.visit(node, param: param)
        
        if (type(node.condition) !== QLBooleanType.self) {
            error.collect(SemanticError.TypeMismatch(description: "If statement condition must be of type Bool: \(node.condition)"))
        }
    }
    
    override func visit(node: QLBlock, param: GenericParam) {
        context = Context(parent: context)
        
        super.visit(node, param: param)
        
        if let parent = context.parent {
            context = parent
        }
    }
    
    override func visit(node: QLIdentifier, param: GenericParam) {
        if let o: Object = context.retrieve(node) {
            node.expression = o.expression
        }
    }
    
    override func visit(node: MoneyField, param: GenericParam) {
        super.visit(node, param: param)
        
        if let expression = node.expression {
            if type(expression) !== QLMoneyType.self {
                error.collect(SemanticError.TypeMismatch(description: "Money expression must result in a numerical value: \(node.expression)"))
            }

        }
    }
    
    override func visit(node: QLNeg, param: GenericParam) {
        super.visit(node, param: param)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Unary type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    override func visit(node: QLNot, param: GenericParam) {
        super.visit(node, param: param)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Unary type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    func collectBinaryTypeError(node: QLBinary) {
        self.error.collect(SemanticError.TypeMismatch(description: "Binary type does not match expression type(s). \(node.type) does not match \(node.lhs.type) and \(node.rhs.type)."))
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
    
    override func visit(node: QLAdd, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: QLSub, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: QLMul, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: QLDiv, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: QLPow, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: QLGe, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: QLGt, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: QLLe, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: QLLt, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    func visitBinaryEq(node: QLBinary) {
        visitBinary(node)
        
        if (type(node.lhs) !== type(node.rhs)) {
            collectBinaryTypeError(node)
        }
    }
    
    override func visit(node: QLEq, param: GenericParam) {
        visitBinaryEq(node)
    }
    
    override func visit(node: QLNe, param: GenericParam) {
        visitBinaryEq(node)
    }
    
    func visitBinaryBool(node: QLBinary) {
        visitBinary(node)
        
        if (type(node.lhs) !== QLBooleanType.self || type(node.rhs) !== QLBooleanType.self) {
            collectBinaryTypeError(node)
        }
    }
    
    override func visit(node: QLAnd, param: GenericParam) {
        visitBinaryBool(node)
    }
    
    override func visit(node: QLOr, param: GenericParam) {
        visitBinaryBool(node)
    }
    
    private func type(node: QLExpression) -> QLType {
        let type = node.type
        
        if node.type === QLUnknownType.self {
            error.collect(SemanticError.NotDefined(description: "\(node) is not (yet) defined."))
        }
    
        return type
    }
}
