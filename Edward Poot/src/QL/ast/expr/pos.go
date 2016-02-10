package expr

import "math"

type Pos struct {
	Value Expr
}

func (pos Pos) Eval() interface{} {
	return int(math.Abs(float64(pos.Value.Eval().(int))))
}
