package expr

type IntLit struct {
	Value int
}

func (i IntLit) Eval() interface{} {
	return int(i.Value)
}