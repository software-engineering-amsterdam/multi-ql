//
//  FormVisitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 02/03/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

protocol ExpressionVisitor {
    typealias ExpressionReturnType
    func visit(integerLiteral: IntegerLiteral) -> ExpressionReturnType
    func visit(floatLiteral: FloatLiteral) -> ExpressionReturnType
    func visit(booleanLiteral: BooleanLiteral) -> ExpressionReturnType
    func visit(variable: Variable) -> ExpressionReturnType
    func visit(addExpression: AddExpression) -> ExpressionReturnType
    func visit(subExpression: SubExpression) -> ExpressionReturnType
    func visit(mulExpression: MulExpression) -> ExpressionReturnType
    func visit(divExpression: DivExpression) -> ExpressionReturnType
    func visit(ltExpression: LtExpression) -> ExpressionReturnType
    func visit(letExpression: LetExpression) -> ExpressionReturnType
    func visit(gtExpression: GtExpression) -> ExpressionReturnType
    func visit(getExpression: GetExpression) -> ExpressionReturnType
    func visit(eqExpression: EqExpression) -> ExpressionReturnType
    func visit(neqExpression: NeqExpression) -> ExpressionReturnType
    func visit(andExpression: AndExpression) -> ExpressionReturnType
    func visit(orExpression: OrExpression) -> ExpressionReturnType
    func visit(notExpression: NotExpression) -> ExpressionReturnType
}