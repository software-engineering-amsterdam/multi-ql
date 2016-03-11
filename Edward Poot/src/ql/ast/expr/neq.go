package expr

import "ql/interfaces"

type NEq struct {
	BinaryOperator
}

func NewNEq(lhs interfaces.Expr, rhs interfaces.Expr) NEq {
	return NEq{BinaryOperator{Lhs: lhs, Rhs: rhs}}
}
