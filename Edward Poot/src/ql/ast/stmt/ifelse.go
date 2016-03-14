package stmt

import (
	"fmt"
	"ql/interfaces"
)

type IfElse struct {
	Cond     interfaces.Expr
	IfBody   StmtList
	ElseBody StmtList
	Stmt
}

func NewIfElse(condition interfaces.Expr, ifBody StmtList, thenBody StmtList, sourceInfo interface{}) IfElse {
	return IfElse{condition, ifBody, thenBody, NewStmt(sourceInfo)}
}

func NewIfElseNoSourceInfo(condition interfaces.Expr, ifBody StmtList, thenBody StmtList) IfElse {
	return NewIfElse(condition, ifBody, thenBody, nil)
}

func (this IfElse) GetCondition() interfaces.Expr {
	return this.Cond
}

func (this IfElse) GetIfBody() interfaces.StmtList {
	return this.IfBody
}

func (this IfElse) GetElseBody() interfaces.StmtList {
	return this.ElseBody
}

func (this IfElse) String() string {
	return fmt.Sprintf("An if/else statement with condition %s", this.Cond)
}

func (this IfElse) EvalCondition(symbols interfaces.Symbols) bool {
	return this.Cond.Eval(symbols).(bool)
}
