//: Playground - noun: a place where people can play

import Cocoa

extension Array {
    func any(fn: (Element) -> Bool) -> Bool {
        return !self.filter(fn).isEmpty
    }
}

struct SymbolTable {

    internal var symbolTable = [String:Type]()
    
    mutating func pushVariable(identifier: String, type: Type) {
        symbolTable[identifier] = type
    }
    
    func variableExists(identifier: String) -> Bool {
        return symbolTable[identifier] != nil
    }
    
}

protocol Type {}





protocol ASTVisitor {
    func visit(form form: Form)
    func visit(node node: ASTNode)
}

class ASTNode {
    func accept(visitor: ASTVisitor) { visitor.visit(node: self) }
}

class Form: ASTNode {
    
}

class SomeVisitor: ASTVisitor {
    func visit(node node: ASTNode) {
        print("not implemented")
    }
    
    func visit(form form: Form) {
        print("visiting form!")
    }
}
