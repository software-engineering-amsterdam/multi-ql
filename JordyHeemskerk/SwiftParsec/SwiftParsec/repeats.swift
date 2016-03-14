public class Many<T:Parser> : Parser {
  public typealias Target = [T.Target]

  let body: T
  let emptyOk: Bool

  public init(body:T, emptyOk:Bool) {
    self.body = body
    self.emptyOk = emptyOk
  }

  public func parse(stream:CharStream) -> Target? {
    var result = Target()
    while let r = body.parse(stream) {
      result.append(r)
    }
    if !emptyOk && result.count == 0 {
      return nil
    }
    return result
  }
}

postfix operator * {}
public postfix func *<T:Parser>(body:T) -> Many<T> {
  return Many(body:body, emptyOk:true)
}

postfix operator + {}
public postfix func +<T:Parser>(body:T) -> Many<T> {
  return Many(body:body, emptyOk:false)
}

public class SepBy<T:Parser, S:Parser> : Parser {
  public typealias Target = [T.Target]

  let item: T
  let sep:  S
  let pair: FollowedBySecond<S,T>

  init(item:T, sep:S) {
    self.item = item
    self.sep = sep
    self.pair = sep >~ item
  }

  public func parse(stream:CharStream) -> Target? {
    var result = Target()
    if let x = item.parse(stream) {
      result.append(x)
      while let next = pair.parse(stream) {
        result.append(next)
      }
    }
    return result
  }
}

public func sepby<T:Parser, S:Parser>(item:T, sep:S) -> SepBy<T,S> {
  return SepBy(item:item, sep:sep)
}
