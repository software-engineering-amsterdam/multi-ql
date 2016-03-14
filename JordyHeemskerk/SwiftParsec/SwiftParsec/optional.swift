public class Opt<T:Parser> : Parser {
  public typealias Target = [T.Target]
    
    let body: T
    
    public init(body:T) {
        self.body = body
    }
    
    public func parse(stream:CharStream) -> Target? {
        var result = Target()
        if let r = body.parse(stream) {
            result.append(r)
        }
        return result
    }
}

postfix operator ❓ {}
public postfix func ❓ <T:Parser>(body:T) -> Opt<T> {
    return Opt(body:body)
}

