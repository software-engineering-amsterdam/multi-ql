//
//  Block.swift
//  QL
//
//  Created by Jordy Heemskerk on 04/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

//protocol Visitable {
//    func accept(visitor: FormVisitor)
//}


//protocol FormVisitor {
//    static func visit(form: Form)
//    func visit(form: Form)
//    func visit(block: Block)
//    func visit(questionDeclaration: QuestionDeclaration)
//    func visit(ifStatement: IfStatement)
//    func visit(elseIfStatement: ElseIfStatement)
//    func visit(conditionClause: ConditionClause)
//    func visit(binaryExpression: BinaryExpression)
//    func visit(unaryExpression: UnaryExpression)
//    func visit(integerLiteral: IntegerLiteral)
//    func visit(doubleLiteral: DoubleLiteral)
//    func visit(booleanLiteral: BooleanLiteral)
//    func visit(variable: Variable)
//}
//
//class SemanticAnalyser: FormVisitor {
//    
//    var messages = [String]()
//    
//    static func visit(form: Form) {
//        self.visit(form)
//    }
//    
//    internal func visit(form: Form) {
//        form.block.accept(self)
//    }
//    
//    internal func visit(block: Block) {
//        for statement in block.statements {
//            statement.accept(self)
//        }
//    }
//    
//    internal func visit(questionDeclaration: QuestionDeclaration) {
//        
//    }
//    
//    internal func visit(ifStatement: IfStatement) {
//        ifStatement.conditionClause.accept(self)
//        ifStatement.block.accept(self)
//        ifStatement.elseClause?.accept(self)
//    }
//    
//    internal func visit(elseIfStatement: ElseIfStatement) {
//        elseIfStatement.conditionClause?.accept(self)
//        elseIfStatement.block.accept(self)
//        elseIfStatement.elseClause?.accept(self)
//    }
//    
//    internal func visit(conditionClause: ConditionClause) {
//        print(conditionClause)
//    }
//    
//    internal func visit(binaryExpression: BinaryExpression) {
//        binaryExpression.leftOperand.accept(self)
//        binaryExpression.rightOperand.accept(self)
//    }
//    
//    internal func visit(unaryExpression: UnaryExpression) {
//        unaryExpression.operand.accept(self)
//    }
//    
//    internal func visit(integerLiteral: IntegerLiteral) {
//        
//    }
//    
//    internal func visit(doubleLiteral: DoubleLiteral) {
//
//    }
//    
//    internal func visit(booleanLiteral: BooleanLiteral) {
//
//    }
//    
//    internal func visit(variable: Variable) {
//    }
//}

//struct Form: Visitable {
//    
//    let identifier: String
//    let block: Block
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}

//struct Block: Visitable {
//    let statements: [Statement]
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//    
//}

//protocol Statement: Visitable {}

//struct QuestionDeclaration: Statement {
//    let name: String
//    let identifier: String
//    let type: Type
//    let computation: Expression?
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}

//struct IfStatement: Statement {
//    let conditionClause: ConditionClause
//    let block: Block
//    let elseClause: ElseIfStatement?
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}

//struct ElseIfStatement: Statement {
//    let conditionClause: ConditionClause?
//    let block: Block
//    let elseClause: Statement? // Because structs dont allow recursive values
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}

//struct ConditionClause: Visitable {
//    let condition: Expression
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}

//protocol Expression: Visitable { }


struct BinaryExpression: Expression {
    let leftOperand: Expression
    let op: BinaryOperator
    let rightOperand: Expression
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}

struct UnaryExpression: Expression {
    let operand: Expression
    let op: UnaryOperator
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}

//protocol Literal: Expression {}

//struct IntegerLiteral: Literal {
//    let value: Int
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}

//struct FloatLiteral: Literal {
//    let value: Double
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}

//struct BooleanLiteral: Literal {
//    let value: Bool
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}

//struct Variable: Literal {
//    let identifier: String
//    
//    func accept(visitor: FormVisitor) {
//        visitor.visit(self)
//    }
//}


enum Type: StringLiteralType {
    case int
    case money
    case bool
}

enum BinaryOperator: StringLiteralType {
    case Add = "+"
    case Sub = "-"
    case Mul = "*"
    case Div = "/"
    case LT = "<"
    case GT = ">"
    case LET = "<="
    case GET = ">="
    case EQ = "=="
    case NEQ = "!="
    case OR = "||"
    case AND = "&&"
}

enum UnaryOperator: StringLiteralType {
    case NOT = "!"
}

