public class Alternates<T1:Parser, T2:Parser where T1.Target==T2.Target> : Parser {
  public typealias Target = T1.Target

  let first : T1
  let second: T2

  public init(first: T1, second: T2) {
    self.first = first
    self.second = second
  }

  public func parse(stream:CharStream) -> Target? {
    if let fst = first.parse(stream) {
      return fst
    }
    if let snd = second.parse(stream) {
      return snd
    }
    return nil
  }
}

public func | <T1:Parser, T2:Parser where T1.Target==T2.Target>(
    first : T1,
    second: T2) ->
    Alternates<T1,T2> {
  return Alternates(first:first, second:second)
}
