//: Playground - noun: a place where people can play

import Cocoa

struct CharStream {
    let str: String
    var pos: String.Index
    var lines = [Int]()
    
    init(str: String) {
        self.str = str
        self.pos = str.startIndex
        str.enumerateLines { (line, stop) -> () in
            let lastLineEnd = self.lines.last ?? 0
            self.lines.append(lastLineEnd + line.startIndex.distanceTo(line.endIndex) + 1)
        }
    }
}

let fn: (Int, Int) -> () = { (start, eind) -> () in
    let x = 1
}
