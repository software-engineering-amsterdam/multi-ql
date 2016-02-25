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

let cs = CharStream(str: "SOmeaklsdfjadfj ald\n dsfjaklfajsd ;lfajlf\n sdfkjashf kjasfljsak hdjshfsjk fh\na kfha askldfj khfakf l;")
print(cs.lines)
