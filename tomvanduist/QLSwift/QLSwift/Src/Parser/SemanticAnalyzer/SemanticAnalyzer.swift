//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation


class SemanticAnalyzer: QLStatementVisitor, QLExpressionVisitor, QLLiteralVisitor, QLTypeVisitor {
    
    typealias QLStatementVisitorParam   = Void?
    typealias QLExpressionVisitorParam  = Void?
    typealias QLLiteralVisitorParam     = Void?
    typealias QLTypeVisitorParam        = Void?
    typealias QLStatementVisitorReturn  = Void
    typealias QLExpressionVisitorReturn = QLType
    typealias QLLiteralVisitorReturn    = QLType
    typealias QLTypeVisitorReturn       = QLType
    
    private var symbolTable: SymbolTable = SymbolTable()
    private var error: SemanticError = SemanticError.None
    private var warnings: [SemanticWarning] = []
    
    
    func analyze(form: QLForm) throws -> (QLForm, [SemanticWarning]) {
        symbolTable = SymbolTable()
        error = SemanticError.None
        
        form.block.accept(self, param: nil)
        
        if case SemanticError.None = error {
            return (form, warnings)
        } else {
            throw error
        }
    }
}


// MARK: - QLStatementVisitor conformance

extension SemanticAnalyzer {

    func visit(node: QLVariableQuestion, param: Void?) -> Void {
        do {
            try symbolTable.assign(node.identifier.id, object: (node.type, node))
        } catch let warning as SemanticWarning {
            self.warnings.append(warning)
        } catch let e {
            error.collect(e)
        }
    }
    
    func visit(node: QLComputedQuestion, param: Void?) -> Void {
        do {
            try symbolTable.assign(node.identifier.id, object: (node.expression.accept(self, param: nil), node))
        } catch let warning as SemanticWarning {
            self.warnings.append(warning)
        } catch let e {
            error.collect(e)
        }
    }
    
    func visit(node: QLConditional, param: Void?) -> Void {
        node.condition.accept(self, param: param)
        node.ifBlock.accept(self, param: param)
        
        if (node.condition.accept(self, param: nil) !== QLBooleanType.self) {
            error.collect(SemanticError.TypeMismatch(description: "If statement condition must be of type Bool: \(node.condition.toString())"))
        }
    }
    
    func visit(node: QLBlock, param: Void?) -> Void {
        symbolTable = SymbolTable(parent: symbolTable)
        
        for statement in node.block {
            statement.accept(self, param: param)
        }
        
        if let parent = symbolTable.parent {
            symbolTable = parent
        }
    }
}


// MARK: - QLExpressionVisitor conformance

extension SemanticAnalyzer {

    func visit(node: QLVariable, param: Void?) -> QLType {
        guard let type = symbolTable.retrieveType(node.id)
            else { return QLUnknownType() }
        
        return type
    }
    
    func visit(node: QLLiteralExpression, param: Void?) -> QLType {
        return node.literal.accept(self, param: param)
    }
    
    func collectUnaryTypeError(node: QLUnary) {
        error.collect(SemanticError.TypeMismatch(description: "Unary operator '\(node.toString())' cannot be applied to operand of type '\(node.rhs.toString())'!"))
    }
    
    func visit(node: QLNeg, param: Void?) -> QLType {
        if (node.rhs.accept(self, param: param) !== QLIntegerType.self) {
            collectUnaryTypeError(node)
        }
        
        return QLIntegerType()
    }
    
    func visit(node: QLNot, param: Void?) -> QLType {
        if (node.rhs.accept(self, param: param) !== QLBooleanType.self) {
            collectUnaryTypeError(node)
        }
        
        return QLBooleanType()
    }
    
    func collectBinaryTypeError(node: QLBinary) {
        self.error.collect(SemanticError.TypeMismatch(description: "Binary operator '\(node.toString())' cannot be applied to operands of type '\(node.lhs.toString())' and '\(node.rhs.toString())'!"))
    }

    func visitBinaryNumber(node: QLBinary) -> QLType {
        if (node.lhs.accept(self, param: nil) !== QLIntegerType.self || node.rhs.accept(self, param: nil) !== QLIntegerType.self) {
            collectBinaryTypeError(node)
        }
        
        return QLIntegerType()
    }
    
    func visit(node: QLAdd, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visit(node: QLSub, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visit(node: QLMul, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visit(node: QLDiv, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visit(node: QLPow, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visitBinaryOrder(node: QLBinary) -> QLType {
        if (node.lhs.accept(self, param: nil) !== QLIntegerType.self || node.rhs.accept(self, param: nil) !== QLIntegerType.self) {
            collectBinaryTypeError(node)
        }
        
        return QLBooleanType()
    }
    
    func visit(node: QLGe, param: Void?) -> QLType {
        return visitBinaryOrder(node)
    }
    
    func visit(node: QLGt, param: Void?) -> QLType {
        return visitBinaryOrder(node)
    }
    
    func visit(node: QLLe, param: Void?) -> QLType {
        return visitBinaryOrder(node)
    }
    
    func visit(node: QLLt, param: Void?) -> QLType {
        return visitBinaryOrder(node)
    }
    
    func visitBinaryEq(node: QLBinary) -> QLType {
        if (node.lhs.accept(self, param: nil) !== node.rhs.accept(self, param: nil)) {
            collectBinaryTypeError(node)
        }
        
        return QLBooleanType()
    }
    
    func visit(node: QLEq, param: Void?) -> QLType {
        return visitBinaryEq(node)
    }
    
    func visit(node: QLNe, param: Void?) -> QLType {
        return visitBinaryEq(node)
    }
    
    func visitBinaryBool(node: QLBinary) -> QLType {
        if (node.lhs.accept(self, param: nil) !== QLBooleanType.self || node.rhs.accept(self, param: nil) !== QLBooleanType.self) {
            collectBinaryTypeError(node)
        }
        
        return QLBooleanType()
    }
    
    func visit(node: QLAnd, param: Void?) -> QLType {
        return visitBinaryBool(node)
    }
    
    func visit(node: QLOr, param: Void?) -> QLType {
        return visitBinaryBool(node)
    }
}


// MARK: - QLLiteralVisitor conformance 

extension SemanticAnalyzer {
    
    func visit(node: QLIntegerLiteral, param: Void?) -> QLType {
        return QLIntegerType()
    }
    
    func visit(node: QLStringLiteral, param: Void?) -> QLType {
        return QLStringType()
    }
    
    func visit(node: QLBooleanLiteral, param: Void?) -> QLType {
        return QLBooleanType()
    }
}


// MARK: - QLTypeVisitor conformance

extension SemanticAnalyzer {
    
    func visit(node: QLIntegerType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLStringType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLBooleanType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLUnknownType, param: Void?) -> QLType {
        return node
    }
}
