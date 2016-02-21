//
//  Form.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol ASTNode: ASTNodeVisitable {
}

class Form: ASTNode {
    let identifier: Identifier
    let block: Block
    
    init(identifier: Identifier, block: Block) {
        self.identifier = identifier
        self.block = block
    }
}


// MARK: Form creation

extension QLForm {
    func implode() -> Form {
        return Form(identifier: variable.implode(), block: block.implode() as! Block)
    }
}

extension QLQuestionStatement {
    func implode() -> Statement {
        return question.implode()
    }
}

extension QLBlockStatement {
    func implode() -> Statement {
        var block: [Statement] = []
        for statement in self.block {
            block.append(statement.implode())
        }
        return Block(block: block)
    }
}

extension QLVariable {
    func implode() -> Identifier {
        return Identifier(id: identifier, expression: nil)
    }
}

extension QLIf {
    func implode() -> Statement {
        return Conditional(condition: conditional.implode(), ifBlock: block.implode() as! Block, elseBlock: nil)
    }
}

extension QLQuestion {
    func implode() -> Statement {
        return Question(identifier: variable.implode(), label: stringLit.string, expression: expression.implode())
    }
}

extension QLExpressionVariable {
    func implode() -> Expression {
        return variable.implode()
    }
}

extension QLBoolean {
    func implode() -> Expression {
        return BooleanField()
    }
}

extension QLString {
    func implode() -> Expression {
        return StringField()
    }
}

extension QLMoney {
    func implode() -> Expression {
        return MoneyField(expression: expr?.implode())
    }
}

extension QLExpressionLiteral {
    func implode() -> Expression {
        return literal.implode()
    }
}

extension QLAdd {
    func implode() -> Expression {
        return Add(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLSub {
    func implode() -> Expression {
        return Sub(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLMul {
    func implode() -> Expression {
        return Mul(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLDiv {
    func implode() -> Expression {
        return Div(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLPow {
    func implode() -> Expression {
        return Pow(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLAnd {
    func implode() -> Expression {
        return And(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLOr {
    func implode() -> Expression {
        return Or(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLLt {
    func implode() -> Expression {
        return Lt(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLLe {
    func implode() -> Expression {
        return Le(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLEq {
    func implode() -> Expression {
        return Eq(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLNe {
    func implode() -> Expression {
        return Ne(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLGe {
    func implode() -> Expression {
        return Ge(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLGt {
    func implode() -> Expression {
        return Gt(lhs: lhs.implode(), rhs: rhs.implode())
    }
}

extension QLNeg {
    func implode() -> Expression {
        return Neg(rhs: rhs.implode())
    }
}

extension QLNot {
    func implode() -> Expression {
        return Not(rhs: rhs.implode())
    }
}

extension QLBooleanLiteral {
    func implode() -> Expression {
        return BooleanLiteral(bool: bool)
    }
}

extension QLStringLiteral {
    func implode() -> Expression {
        return StringLiteral(string: string)
    }
}

extension QLIntegerLiteral {
    func implode() -> Expression {
        return IntegerLiteral(integer: integer)
    }
}

extension QLFloatLiteral {
    func implode() -> Expression {
        return FloatLiteral(float: float)
    }
}
