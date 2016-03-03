//
//  QLStatementVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

protocol QLExpressionVisitor {
    typealias QLExpressionVisitorParam
    typealias QLExpressionVisitorReturn
    
    func visit(node: QLIdentifier, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLStringLiteral, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLIntegerLiteral, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLBooleanLiteral, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLNeg, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLAdd, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLSub, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLMul, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLDiv, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLPow, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLEq, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLNe, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLGe, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLGt, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLLe, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLLt, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLAnd, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLOr, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
    func visit(node: QLNot, param: QLExpressionVisitorParam) -> QLExpressionVisitorReturn
}

protocol QLExpressionVisitable {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn
}

extension QLIdentifier {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLStringLiteral {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLIntegerLiteral {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLBooleanLiteral {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLNeg {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLNot {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLAdd {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLSub {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLMul {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLDiv {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLPow {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLEq {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLNe {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLGe {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLGt {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLLe {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLLt {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLAnd {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

extension QLOr {
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}
