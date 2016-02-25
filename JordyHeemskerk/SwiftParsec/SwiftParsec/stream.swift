import Foundation

//---------------------------------//
// Inputs
//---------------------------------//
public class CharStream {
  let str: String
  var pos: String.Index
    var lines = [Int]()

  public init(str: String) {
    self.str = str
    self.pos = str.startIndex
    str.enumerateLines { (line, stop) -> () in
        let lastLineEnd = self.lines.last ?? 0
        self.lines.append(lastLineEnd + line.startIndex.distanceTo(line.endIndex) + 1)
    }
  }
    
    var currentLine: Int {
        let intPos = str.startIndex.distanceTo(pos)
        for i in 0..<lines.count {
            if intPos <= lines[i] { return i + 1 }
        }
        return -1
    }

  var head:Character? {
    get {
      if pos < str.endIndex {
        return str[pos]
      }
      return nil
    }
  }

  var position:String.Index {
    get {
      return pos
    }
    set {
      pos = newValue
    }
  }

  var eof:Bool {
    get {
      return pos == str.endIndex
    }
  }

  func startsWith(query: String) -> Bool {
    return str.substringFromIndex(pos).hasPrefix(query)
  }

  func startsWithRegex(pattern: String) -> String? {
    if let range = str.rangeOfString(
      pattern,
      options: [.RegularExpressionSearch, .AnchoredSearch],
      range: pos..<str.endIndex,
      locale: nil) {
      return str.substringWithRange(range)
    }
    return nil
  }

  func advance(count: Int) -> Void {
    pos = pos.advancedBy(count)
    print("Now on line \(currentLine)")
  }

  func error(msg: String) -> Void {}
}
