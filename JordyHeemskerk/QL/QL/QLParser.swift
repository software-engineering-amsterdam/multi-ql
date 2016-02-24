//
//  QLParser.swift
//  QL
//
//  Created by Jordy Heemskerk on 03/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

// Because the infixness of operators cannot be inherited from a framework
infix operator ~>~ {associativity left precedence 140}
infix operator ~>  {associativity left precedence 140}
infix operator  >~ {associativity left precedence 140}

struct QLParser : Parser {
    
    static let skip = regex("\\s*")
    static let dquote = const("\"")
    static let ocurly = const("{") ~> skip
    static let ccurly = const("}") ~> skip
    static let obrack = const("(") ~> skip
    static let cbrack = const(")") ~> skip
    static let colon = const(":") ~> skip
    static let ddot = const("..") ~> skip
    static let eq = const("=") ~> skip
    
    
    static let form = const("form") ~> skip >~ ID ~>~ block |> Form.init
    
    static let block = ocurly >~ (statement ~> skip)* ~> ccurly |> Block.init
    
    static let statement = (questionDeclaration | ifStatement)
    
    static let questionDeclaration = LateBound<Statement>()
    static let questionDeclarationImpl = dquote >~ regex("[^\"]*") ~> dquote ~> skip ~>~ ID ~> colon ~>~ type ~> skip ~>~ (eq >~ expr)❓ |> QuestionDeclaration.init
    
    static let ifStatement = LateBound<Statement>()
    static let ifStatementImpl = const("if") ~> skip >~ conditionClause ~>~ block ~>~ (elseIfClause | elseClause)❓ |> IfStatement.init
    
    static let elseClause = const("else") ~> skip >~ block |> ElseIfStatement.init
    
    static let elseIfClause = LateBound<ElseIfStatement>()
    static let elseIfClauseImpl = const("else") ~> skip >~ ifStatementImpl |> ElseIfStatement.init
    
    static let conditionClause = obrack >~ expr ~> cbrack |> ConditionClause.init
    
    
    
    static let type = (const("int") | const("money") | const("bool")) |> { type in Type(rawValue: type)! }
    
    static let expr = LateBound<Expression>()
    static let exprImpl = OperatorPrecedence(opFormat: opFormat, primary: primary)
    static let opFormat = (regex("[+\\-*/]|[><]=?|[=!]=|&&|\\|\\||!")) ~> skip
    
    static let group = (literal | brackExpr) ~> skip
    static let brackExpr = obrack >~ expr ~> cbrack |> { x -> Expression in return x }
    
    static let primary = LateBound<Expression>()
    static let primaryImpl = brackets | literal
    
    static let brackets = obrack >~ expr ~> cbrack
    
    static let literal = floatLiteral | integerLiteral | booleanLiteral | variable
    
    static let floatLiteral = LateBound<Expression>()
    static let floatLiteralImpl = FloatParser(strict: false) ~> skip |> FloatLiteral.init
    
    static let integerLiteral = LateBound<Expression>()
    static let integerLiteralImpl = Integer() ~> skip |> IntegerLiteral.init
    
    static let booleanLiteral = LateBound<Expression>()
    static let booleanLiteralImpl = (const("true") | const("false")) ~> skip |> BooleanLiteral.init
    
    static let variable = LateBound<Expression>()
    static let variableImpl = ID |> Variable.init
    
    
    
    static let ID = regex("[A-Za-z]+") ~> skip
    
    
    static func parse(str:String) -> Target? {
        let stream = CharStream(str:str)
        return parse(stream)
    }
    
    static func parse(stream:CharStream) -> Target? {
        if(questionDeclaration.inner == nil) {
            // Pass through late bound parsers
            questionDeclaration.inner = questionDeclarationImpl.parse
            ifStatement.inner = ifStatementImpl.parse
            elseIfClause.inner = elseIfClauseImpl.parse
            expr.inner = exprImpl.parse
            primary.inner = primaryImpl.parse
            floatLiteral.inner = floatLiteralImpl.parse
            integerLiteral.inner = integerLiteralImpl.parse
            booleanLiteral.inner = booleanLiteralImpl.parse
            variable.inner = variableImpl.parse
            
            // Add implementation of the operators
            exprImpl.addOperator("||", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .OR, rightOperand: $1) }, 20))
            exprImpl.addOperator("&&", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .AND, rightOperand: $1) }, 30))
            exprImpl.addOperator("==", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .EQ, rightOperand: $1) }, 40))
            exprImpl.addOperator("!=", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .NEQ, rightOperand: $1) }, 40))
            exprImpl.addOperator("<=", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .LET, rightOperand: $1) }, 40))
            exprImpl.addOperator(">=", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .GET, rightOperand: $1) }, 40))
            exprImpl.addOperator("<", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .LT, rightOperand: $1) }, 40))
            exprImpl.addOperator(">", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .GT, rightOperand: $1) }, 40))
            exprImpl.addOperator("+", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .Add, rightOperand: $1) }, 50))
            exprImpl.addOperator("-", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .Sub, rightOperand: $1) }, 50))
            exprImpl.addOperator("*", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .Mul, rightOperand: $1) }, 70))
            exprImpl.addOperator("/", .LeftInfix({ return BinaryExpression(leftOperand: $0, op: .Div, rightOperand: $1) }, 70))
            exprImpl.addOperator("!", .Prefix({ return UnaryExpression(operand: $0, op: .NOT) }, 80))
        }
        return form.parse(stream)
    }
    
    // Parse implementation
    typealias Target = Form
    func parse(stream:CharStream) -> Target? {
        return QLParser.parse(stream)
    }
}
