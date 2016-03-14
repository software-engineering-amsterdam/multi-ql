
//---------------------------------//
// Parsing
//---------------------------------//
public protocol Parser {
  typealias Target
  func parse(_: CharStream) -> Target?
}
