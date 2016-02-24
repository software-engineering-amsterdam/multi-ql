//: Playground - noun: a place where people can play

import Cocoa

extension Array {
    func any(fn: (Element) -> Bool) -> Bool {
        return !self.filter(fn).isEmpty
    }
}

struct SymbolTable {

    internal var stack = [[String:Type]]()
    
    init() {
        stack.append([String:Type]())
    }
    
    mutating func pushState() {
        if let last = stack.last {
            stack.append(last)
        }
    }
    
    mutating func popState() {
        if stack.count > 1 {
            stack.removeLast()
        }
    }
    
    mutating func pushVariable(identifier: String, type: Type) {
        stack[stack.count - 1][identifier] = type
    }
    
    func get(identifier: String) -> Type? {
        return stack.last?[identifier]
    }
    
}

enum Type {
    case bool, money, int
}

var st = SymbolTable()
st.pushVariable("test", type: .bool)
st.pushState()
st.pushVariable("test2", type: .int)
st.get("test2")

print(st.stack)

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
