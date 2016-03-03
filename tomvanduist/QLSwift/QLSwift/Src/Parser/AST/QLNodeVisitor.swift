//
//  QLNodeVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

class QLNodeVisitor<GenericParam, GenericReturn> {
    func visit(node: QLForm, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLQuestion, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLConditional, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLBlock, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLIdentifier, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: BooleanField, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: StringField, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: MoneyField, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLStringLiteral, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLIntegerLiteral, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLBooleanLiteral, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLNeg, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLAdd, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLSub, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLMul, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLDiv, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLPow, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLEq, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLNe, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLGe, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLGt, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLLe, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLLt, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLAnd, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLOr, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: QLNot, param: GenericParam) -> GenericReturn { fatalError("Override") }
}


class DefaultQLNodeVisitor: QLNodeVisitor<Void?, Void> {
    
    typealias GenericParam = Void?
    typealias GenericReturn = Void
    
    
    override func visit(node: QLForm, param: GenericParam) -> GenericReturn {
        node.identifier.accept(self, param: param)
        node.block.accept(self, param: param)
    }
    
    override func visit(node: QLQuestion, param: GenericParam) -> GenericReturn {
        node.identifier.accept(self, param: param)
        node.expression.accept(self, param: param)
    }
    
    override func visit(node: QLConditional, param: GenericParam) -> GenericReturn {
        node.condition.accept(self, param: param)
        node.ifBlock.accept(self, param: param)
    }
    
    override func visit(node: QLBlock, param: GenericParam) -> GenericReturn {
        for statement in node.block {
            statement.accept(self, param: param)
        }
    }
    
    override func visit(node: QLIdentifier, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: BooleanField, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: StringField, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: MoneyField, param: GenericParam) -> GenericReturn {
        node.expression?.accept(self, param: param)
    }
    
    override func visit(node: QLStringLiteral, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLIntegerLiteral, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLBooleanLiteral, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLNeg, param: GenericParam) -> GenericReturn {
        node.rhs.accept(self, param: param)
    }
    
    override func visit(node: QLNot, param: GenericParam) -> GenericReturn {
        node.rhs.accept(self, param: param)
    }
    
    override func visit(node: QLAdd, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLSub, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLMul, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLDiv, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLPow, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLEq, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLNe, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLGe, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLGt, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLLe, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLLt, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLAnd, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: QLOr, param: GenericParam) -> GenericReturn {
    }
}

protocol QLNodeVisitable {
    func accept<T, U>(visitor: QLNodeVisitor<T, U>, param: T) -> U
}

extension QLForm {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLQuestion {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLConditional {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLBlock {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLIdentifier {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension BooleanField {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension StringField {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension MoneyField {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLStringLiteral {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLIntegerLiteral {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLBooleanLiteral {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLNeg {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLNot {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLAdd {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLSub {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLMul {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLDiv {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLPow {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLEq {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLNe {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLGe {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLGt {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLLe {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLLt {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLAnd {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension QLOr {
    func accept<P, R>(visitor: QLNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}
