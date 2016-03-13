public class LateBound<T>: Parser {
  public typealias Target = T
  public typealias ParseFunc  = CharStream -> T?

  public var inner: ParseFunc?

  public init() {}

  public func parse(stream:CharStream) -> T? {
    if let impl = inner {
      return impl(stream)
    }
    fatalError("No inner implementation was provided for late-bound parser.")    
  }
}
