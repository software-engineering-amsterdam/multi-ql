package expr

import "ql/interfaces"

type Div struct {
	BinaryOperator
}

func NewDiv(lhs interfaces.Expr, rhs interfaces.Expr) Div {
	return Div{BinaryOperator{Lhs: lhs, Rhs: rhs}}
}
