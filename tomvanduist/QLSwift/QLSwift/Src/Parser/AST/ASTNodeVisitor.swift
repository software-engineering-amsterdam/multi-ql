//
//  ASTNodeVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

class ASTNodeVisitor<GenericParam, GenericReturn> {
    func visit(node: Form, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Question, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Conditional, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Block, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Identifier, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: BooleanField, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: StringField, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: MoneyField, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: StringLiteral, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: IntegerLiteral, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: BooleanLiteral, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Neg, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Add, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Sub, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Mul, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Div, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Pow, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Eq, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Ne, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Ge, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Gt, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Le, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Lt, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: And, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Or, param: GenericParam) -> GenericReturn { fatalError("Override") }
    func visit(node: Not, param: GenericParam) -> GenericReturn { fatalError("Override") }
}


class DefaultASTNodeVisitor: ASTNodeVisitor<Void?, Void> {
    
    typealias GenericParam = Void?
    typealias GenericReturn = Void
    
    
    override func visit(node: Form, param: GenericParam) -> GenericReturn {
        node.identifier.accept(self, param: param)
        node.block.accept(self, param: param)
    }
    
    override func visit(node: Question, param: GenericParam) -> GenericReturn {
        node.identifier.accept(self, param: param)
        node.expression.accept(self, param: param)
    }
    
    override func visit(node: Conditional, param: GenericParam) -> GenericReturn {
        node.condition.accept(self, param: param)
        node.ifBlock.accept(self, param: param)
        node.elseBlock?.accept(self, param: param)
    }
    
    override func visit(node: Block, param: GenericParam) -> GenericReturn {
        for statement in node.block {
            statement.accept(self, param: param)
        }
    }
    
    override func visit(node: Identifier, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: BooleanField, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: StringField, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: MoneyField, param: GenericParam) -> GenericReturn {
        node.expression?.accept(self, param: param)
    }
    
    override func visit(node: StringLiteral, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: IntegerLiteral, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: BooleanLiteral, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Neg, param: GenericParam) -> GenericReturn {
        node.rhs.accept(self, param: param)
    }
    
    override func visit(node: Not, param: GenericParam) -> GenericReturn {
        node.rhs.accept(self, param: param)
    }
    
    override func visit(node: Add, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Sub, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Mul, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Div, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Pow, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Eq, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Ne, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Ge, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Gt, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Le, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Lt, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: And, param: GenericParam) -> GenericReturn {
    }
    
    override func visit(node: Or, param: GenericParam) -> GenericReturn {
    }
}

protocol ASTNodeVisitable {
    func accept<T, U>(visitor: ASTNodeVisitor<T, U>, param: T) -> U
}

extension Form {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Question {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Conditional {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Block {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Identifier {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension BooleanField {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension StringField {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension MoneyField {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension StringLiteral {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension IntegerLiteral {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension BooleanLiteral {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Neg {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Not {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Add {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Sub {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Mul {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Div {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Pow {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Eq {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Ne {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Ge {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Gt {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Le {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Lt {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension And {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}

extension Or {
    func accept<P, R>(visitor: ASTNodeVisitor<P, R>, param: P) -> R {
        return visitor.visit(self, param: param)
    }
}
