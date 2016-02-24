public class Constant<T> : Parser {
  let value: T
  let str: String

  init(value:T) {
    self.value = value
    self.str = "\(value)"
  }

  public typealias Target = T

  public func parse(stream: CharStream) -> Target? {
    if stream.startsWith(str) {
      stream.advance(str.characters.count)
      return value
    }
    stream.error("Expected \(str)")
    return nil
  }
}

public func const<T>(value:T) -> Constant<T> {
  return Constant(value:value)
}

public class Regex : Parser {
  public typealias Target = String

  let pattern:String

  init(pattern:String) {
    self.pattern = pattern
  }

  public func parse(stream:CharStream) -> Target? {
    if let match = stream.startsWithRegex(pattern) {
      stream.advance(match.characters.count)
      return match
    }
    return nil
  }
}

public func regex(pattern:String) -> Regex {
  return Regex(pattern:pattern)
}

class Satisfy : Parser {
  let condition: Character -> Bool
 
  typealias Target = Character

  init(condition:Character -> Bool) {
    self.condition = condition
  }

  func parse(stream:CharStream) -> Target? {
    if let ch = stream.head {
      if condition(ch) {
        stream.advance(1)
        return ch
      }
    }
    return nil
  }
}

func satisfy(condition:Character->Bool) -> Satisfy {
      return Satisfy(condition:condition)
}

class EndOfFile : Parser {
  typealias Target = Void

  func parse(stream:CharStream) -> Target? {
    if stream.eof {
      return Target()
    } else {
      return nil
    }
  }
}

func eof() -> EndOfFile {
  return EndOfFile()
}


// Helpful versions which turn arrays of Characters into Strings
func arrayToString<T:Parser where T.Target==Array<Character>>
  (parser: T) -> Pipe<T, String> {
  return pipe(parser, fn: {return String($0)})
}

func manychars<T:Parser where T.Target==Character>
  (item:T) -> Pipe<Many<T>, String> {
  return arrayToString(item*)
}

func many1chars<T:Parser where T.Target==Character>
  (item:T) -> Pipe<Many<T>, String> {
  return arrayToString(item+)
}

// Overloaded followed-by operators
func >~ <T: Parser>(first: String, second: T) -> FollowedBySecond<Constant<String>,T> {
  return FollowedBySecond(first: const(first), second: second)
}
func ~> <T: Parser>(first: T, second: String) -> FollowedByFirst<T,Constant<String>> {
  return FollowedByFirst(first: first, second: const(second))
}
