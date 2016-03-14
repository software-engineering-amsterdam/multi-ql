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
    
    // Symbols
    static let skip = regex("\\s*")
    static let dquote = const("\"")
    static let ocurly = const("{") ~> skip
    static let ccurly = const("}") ~> skip
    static let obrack = const("(") ~> skip
    static let cbrack = const(")") ~> skip
    static let colon = const(":") ~> skip
    static let eq = const("=") ~> skip
    
    // Base elements
    static let form = skip ~> const("form") ~> skip >~ identifier ~>~ block ~> skip ~> eof() |> Form.init
    
    static let block = ocurly >~ (statement ~> skip)* ~> ccurly |> Block.init
    
    // Statements
    static let statement = (questionDeclaration | ifStatement)
    
    static let questionDeclaration = LateBound<Statement>()
    static let questionDeclarationImpl = dquote >~ regex("[^\"]*") ~> dquote ~> skip ~>~ identifier ~> colon ~>~ type ~>~ (eq >~ expr)❓ |> QuestionDeclaration.init
    
    static let ifStatement = LateBound<Statement>()
    static let ifStatementImpl = const("if") ~> skip >~ obrack >~ expr ~> cbrack ~>~ block ~>~ (elseIfClause | elseClause)❓ |> IfStatement.init
    
    static let elseClause = const("else") ~> skip >~ block |> ElseIfStatement.init
    
    static let elseIfClause = LateBound<ElseIfStatement>()
    static let elseIfClauseImpl = const("else") ~> skip >~ ifStatementImpl |> ElseIfStatement.init
    
    
    
    static let type = (intType | floatType | boolType) ~> skip
    
    static let intType = const("int") |> { _ -> Type in return IntegerType() }
    static let floatType = const("float") |> { _ -> Type in return FloatType() }
    static let boolType = const("bool") |> { _ -> Type in return BooleanType() }
    
    static let expr = LateBound<Expression>()
    static let exprImpl = OperatorPrecedence(opFormat: opFormat, primary: primary)
    static let opFormat = (regex("[+\\-*/]|[><]=?|[=!]=|&&|\\|\\||!")) ~> skip
    
    static let group = (literal | brackExpr) ~> skip
    static let brackExpr = obrack >~ expr ~> cbrack |> { (expression, _) -> Expression in return expression }
    
    static let primary = LateBound<Expression>()
    static let primaryImpl = brackets | literal
    
    static let brackets = obrack >~ expr ~> cbrack
    
    static let literal = (floatLiteral | integerLiteral | booleanLiteral | variable)
    
    static let floatLiteral = LateBound<Expression>()
    static let floatLiteralImpl = FloatParser() ~> skip |> FloatLiteral.init
    
    static let integerLiteral = LateBound<Expression>()
    static let integerLiteralImpl = Integer() ~> skip |> IntegerLiteral.init
    
    static let booleanLiteral = LateBound<Expression>()
    static let booleanLiteralImpl = (const("true") | const("false")) ~> skip |> BooleanLiteral.init
    
    static let variable = LateBound<Expression>()
    static let variableImpl = identifier |> Variable.init
    
    static let identifier = regex("[A-Za-z]+") ~> skip
    
    
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
            exprImpl.addOperator("||", .LeftInfix({ return  OrExpression(lhs: $0, rhs: $1, position: $2) }, 20))
            exprImpl.addOperator("&&", .LeftInfix({ return AndExpression(lhs: $0, rhs: $1, position: $2) }, 30))
            exprImpl.addOperator("==", .LeftInfix({ return  EqExpression(lhs: $0, rhs: $1, position: $2) }, 40))
            exprImpl.addOperator("!=", .LeftInfix({ return NeqExpression(lhs: $0, rhs: $1, position: $2) }, 40))
            exprImpl.addOperator("<=", .LeftInfix({ return LetExpression(lhs: $0, rhs: $1, position: $2) }, 40))
            exprImpl.addOperator(">=", .LeftInfix({ return GetExpression(lhs: $0, rhs: $1, position: $2) }, 40))
            exprImpl.addOperator("<",  .LeftInfix({ return  LtExpression(lhs: $0, rhs: $1, position: $2) }, 40))
            exprImpl.addOperator(">",  .LeftInfix({ return  GtExpression(lhs: $0, rhs: $1, position: $2) }, 40))
            exprImpl.addOperator("+",  .LeftInfix({ return AddExpression(lhs: $0, rhs: $1, position: $2) }, 50))
            exprImpl.addOperator("-",  .LeftInfix({ return SubExpression(lhs: $0, rhs: $1, position: $2) }, 50))
            exprImpl.addOperator("*",  .LeftInfix({ return MulExpression(lhs: $0, rhs: $1, position: $2) }, 70))
            exprImpl.addOperator("/",  .LeftInfix({ return DivExpression(lhs: $0, rhs: $1, position: $2) }, 70))
            exprImpl.addOperator("!",  .Prefix(   { return NotExpression(operand: $0, position: $1)      }, 80))
        }
        return form.parse(stream)
    }
    
    // Parse implementation
    typealias Target = Form
    func parse(stream:CharStream) -> Target? {
        return QLParser.parse(stream)
    }
}