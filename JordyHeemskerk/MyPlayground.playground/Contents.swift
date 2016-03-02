//: Playground - noun: a place where people can play

import Cocoa

protocol Loggable {}

enum Error: Loggable {
    case SomeError
}

enum Warning: Loggable {
    case SomeWarning
    
    func test() {
        print("x")
    }
}

var log = [Loggable]()
log.append(Error.SomeError)
log.append(Warning.SomeWarning)

let x: Warning = .SomeWarning
x.test()


func log(error: Error) {
    print(error)
}

func log(warning: Warning) {
    print(warning)
}

func log(loggable: Loggable) {
    print(loggable)
}

log.forEach {
    let subjectType = Mirror(reflecting: $0).subjectType
    switch $0 {
    case is Error:
        print("error")
    case is Warning:
        print("warning")
    default:
        print("unknown")
    }
}