package expr

import "ql/ast/visit"

type IntLit struct {
	Value int
}

func (i IntLit) Eval() interface{} {
	return i.Value
}

func (i IntLit) Accept(v visit.Visitor) interface{} {
	return v.Visit(i)
}