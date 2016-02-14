package expr

import "ql/ast/visit"

type NEq struct {
	Lhs, Rhs Expr
}

func (n NEq) GetLhs() Expr {
	return n.Lhs
}

func (n NEq) GetRhs() Expr {
	return n.Rhs
}

func (n NEq) Eval() interface{} {
	switch n.Lhs.Eval().(type) {
	case int:
		return n.GetLhs().Eval().(int) != n.GetRhs().Eval().(int)
	case bool:
		return n.GetLhs().Eval().(bool) != n.GetRhs().Eval().(bool)
	default:
		panic("NEq error: comparing unknown types")
	}
}

func (n NEq) Accept(v visit.Visitor) interface{} {
	return v.Visit(n)
}