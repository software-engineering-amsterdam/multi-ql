package stmt

import (
	"fmt"
	"ql/ast/vari"
	"ql/ast/visit"
)

type Form struct {
	Identifier vari.VarId
	Content    StmtList
}

func (f Form) String() string {
	return fmt.Sprintf("A form with identifier %s, statement list %v", f.Identifier, f.Content)
}

func (f Form) Accept(v visit.Visitor) interface{} {
	return v.Visit(f)
}
