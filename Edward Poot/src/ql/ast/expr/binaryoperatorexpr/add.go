package binaryoperatorexpr

import (
	"ql/ast/expr"
	"ql/ast/visit"
)

type Add struct {
	Lhs, Rhs expr.Expr
}

func (a Add) GetLhs() expr.Expr {
	return a.Lhs
}

func (a Add) GetRhs() expr.Expr {
	return a.Rhs
}

func (a Add) Eval() interface{} {
	return a.GetLhs().Eval().(int) + a.GetRhs().Eval().(int)
}

func (a Add) Accept(v visit.Visitor, s interface{}) interface{} {
	return v.Visit(a, s)
}