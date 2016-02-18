//
//  Form.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol FormNode: FormNodeVisitable {
}

class Form: FormNode {
    private let _context: Context
    weak var context: Context?
    
    let identifier: Identifier
    let statement: Statement
    
    required init(context: Context, identifier: Identifier, statement: Statement) {
        self._context = context
        self.context = _context
        self.identifier = identifier
        self.statement = statement
    }
}


// MARK: Form creation

extension QLForm {
    func implode(context: Context) -> Form {
        return Form(context: context, identifier: variable.implode(context), statement: block.implode(context))
    }
}

extension QLQuestionStatement {
    func implode(context: Context) -> Statement {
        return question.implode(context)
    }
}

extension QLBlockStatement {
    func implode(context: Context) -> Statement {
        var block: [Statement] = []
        for statement in self.block {
            block.append(statement.implode(context))
        }
        return Block(block: block)
    }
}

extension QLVariable {
    func implode(context: Context) -> Identifier {
        return Identifier(id: identifier, expression: nil)
    }
}

extension QLIf {
    func implode(context: Context) -> Statement {
        return Conditional(condition: conditional.implode(context), ifBlock: block.implode(context), elseBlock: nil)
    }
}

extension QLQuestion {
    func implode(context: Context) -> Statement {
        return Question(identifier: variable.implode(context), label: stringLit.string, expression: expression.implode(context))
    }
}

extension QLExpressionVariable {
    func implode(context: Context) -> Expression {
        return variable.implode(context)
    }
}

extension QLBoolean {
    func implode(context: Context) -> Expression {
        return BooleanField()
    }
}

extension QLString {
    func implode(context: Context) -> Expression {
        return StringField()
    }
}

extension QLMoney {
    func implode(context: Context) -> Expression {
        return MoneyField(expression: expr?.implode(context))
    }
}

extension QLExpressionLiteral {
    func implode(context: Context) -> Expression {
        return literal.implode(context)
    }
}

extension QLAdd {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Add, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLSub {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Sub, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLMul {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Mul, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLDiv {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Div, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLPow {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Pow, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLAnd {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.And, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLOr {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Or, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLLt {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Lt, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLLe {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Le, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLEq {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Eq, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLNe {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Ne, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLGe {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Ge, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLGt {
    func implode(context: Context) -> Expression {
        return Infix(op: BinaryOp.Gt, lhs: lhs.implode(context), rhs: rhs.implode(context))
    }
}

extension QLNeg {
    func implode(context: Context) -> Expression {
        return Prefix(op: UnaryOp.Neg, rhs: rhs.implode(context))
    }
}

extension QLNot {
    func implode(context: Context) -> Expression {
        return Prefix(op: UnaryOp.Not, rhs: rhs.implode(context))
    }
}

extension QLBooleanLiteral {
    func implode(context: Context) -> Expression {
        return BooleanLiteral(bool: bool)
    }
}

extension QLStringLiteral {
    func implode(context: Context) -> Expression {
        return StringLiteral(string: string)
    }
}

extension QLIntegerLiteral {
    func implode(context: Context) -> Expression {
        return IntegerLiteral(integer: integer)
    }
}

extension QLFloatLiteral {
    func implode(context: Context) -> Expression {
        return FloatLiteral(float: float)
    }
}
