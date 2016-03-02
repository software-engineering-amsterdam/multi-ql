//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

protocol SemanticAnalyzer {
    func analyze(form: Form) throws -> (Form, [SemanticWarning])
}

class DefaultSemanticAnalyzer: DefaultASTNodeVisitor, SemanticAnalyzer {
    
    private var context: Context
    private var error: SemanticError = SemanticError.None
    private var warnings: [SemanticWarning] = []
    
    init(context: Context) {
        self.context = context
    }
    
    func analyze(form: Form) throws -> (Form, [SemanticWarning]) {
        error = SemanticError.None
        
        form.accept(self, param: nil)
        
        if case SemanticError.None = error {
            return (form, warnings)
        } else {
            throw error
        }
    }
    
    override func visit(node: Question, param: GenericParam) {
        super.visit(node, param: param)
        
        do { try context.assign(node.identifier, object: (type(node.expression), node.expression)) }
        catch let warning as SemanticWarning { self.warnings.append(warning) }
        catch let e { error.collect(e) }
    }
    
    override func visit(node: Conditional, param: GenericParam) {
        super.visit(node, param: param)
        
        if (type(node.condition) !== BooleanType.self) {
            error.collect(SemanticError.TypeMismatch(description: "If statement condition must be of type Bool: \(node.condition)"))
        }
    }
    
    override func visit(node: Block, param: GenericParam) {
        context = Context(parent: context)
        
        super.visit(node, param: param)
        
        if let parent = context.parent {
            context = parent
        }
    }
    
    override func visit(node: Identifier, param: GenericParam) {
        if let o: Object = context.retrieve(node) {
            node.expression = o.expression
        }
    }
    
    override func visit(node: MoneyField, param: GenericParam) {
        super.visit(node, param: param)
        
        if let expression = node.expression {
            if type(expression) !== MoneyType.self {
                error.collect(SemanticError.TypeMismatch(description: "Money expression must result in a numerical value: \(node.expression)"))
            }

        }
    }
    
    override func visit(node: Neg, param: GenericParam) {
        super.visit(node, param: param)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Unary type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    override func visit(node: Not, param: GenericParam) {
        super.visit(node, param: param)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Unary type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    func collectBinaryTypeError(node: Binary) {
        self.error.collect(SemanticError.TypeMismatch(description: "Binary type does not match expression type(s). \(node.type) does not match \(node.lhs.type) and \(node.rhs.type)."))
    }
    
    func visitBinary(node: Binary) {
        node.lhs.accept(self, param: nil)
        node.rhs.accept(self, param: nil)
    }

    func visitBinaryNumber(node: Binary) {
        visitBinary(node)
        
        if (type(node.lhs) !== MoneyType.self || type(node.rhs) !== MoneyType.self) {
            collectBinaryTypeError(node)
        }
    }
    
    override func visit(node: Add, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: Sub, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: Mul, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: Div, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: Pow, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: Ge, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: Gt, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: Le, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    override func visit(node: Lt, param: GenericParam) {
        visitBinaryNumber(node)
    }
    
    func visitBinaryEq(node: Binary) {
        visitBinary(node)
        
        if (type(node.lhs) !== type(node.rhs)) {
            collectBinaryTypeError(node)
        }
    }
    
    override func visit(node: Eq, param: GenericParam) {
        visitBinaryEq(node)
    }
    
    override func visit(node: Ne, param: GenericParam) {
        visitBinaryEq(node)
    }
    
    func visitBinaryBool(node: Binary) {
        visitBinary(node)
        
        if (type(node.lhs) !== BooleanType.self || type(node.rhs) !== BooleanType.self) {
            collectBinaryTypeError(node)
        }
    }
    
    override func visit(node: And, param: GenericParam) {
        visitBinaryBool(node)
    }
    
    override func visit(node: Or, param: GenericParam) {
        visitBinaryBool(node)
    }
    
    private func type(node: Expression) -> ExpressionType {
        let type = node.type
        
        if node.type === UnknownType.self {
            error.collect(SemanticError.NotDefined(description: "\(node) is not (yet) defined."))
        }
    
        return type
    }
}
