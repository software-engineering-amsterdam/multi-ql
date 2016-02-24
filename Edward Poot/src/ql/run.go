package main

import (
	"fmt"
	log "github.com/Sirupsen/logrus"
	"io/ioutil"
	"ql/ast/expr"
	"ql/ast/expr/binaryoperatorexpr"
	"ql/ast/expr/lit"
	"ql/ast/expr/unaryoperatorexpr"
	"ql/ast/stmt"
	"ql/ast/vari"
	"ql/ast/visit"
	"ql/env"
	"ql/gui"
	"ql/lexer"
	"ql/parser"
)

func main() {
	initLog()

	log.Info("Initiating parsing of file")

	qlFile, _ := ioutil.ReadFile("example.ql")
	qlFileAsString := string(qlFile)

	lex := lexer.NewLexer([]byte(qlFileAsString))

	p := parser.NewParser()
	result, err := p.Parse(lex)

	if err != nil {
		panic(err)
	}

	if parsedForm, ok := result.(stmt.Form); !ok {
		panic("Parse result is not form")
	} else {
		log.WithFields(log.Fields{"Result": result}).Info("Form parsed")

		visitor := VisitorAdapter{}
		symbolTableStack := env.NewSymbolTableStack()

		parsedForm.Accept(visitor, symbolTableStack)

		gui.CreateGUI(parsedForm, symbolTableStack.Peek())
	}
}

func initLog() {
	log.SetLevel(log.DebugLevel)
}

type VisitorAdapter struct {
	visit.Visitor
}

func (v VisitorAdapter) Visit(t interface{}, s interface{}) interface{} {
	stack := s.(env.SymbolTableStack)

	switch t.(type) {
	default:
		panic(fmt.Sprintf("Unexpected node type %T", t))
	case stmt.Form:
		log.Debug("Visit Form")
		t.(stmt.Form).Identifier.Accept(v, s)
		t.(stmt.Form).Content.Accept(v, s)
	case vari.VarId:
		log.Debug("Visit VarId")
	case vari.VarDecl:
		log.Debug("Visit VarDecl")
		stack.SetValueForIdentifierInTopSymbolTable(nil, t.(vari.VarDecl).Ident)
		t.(vari.VarDecl).Ident.Accept(v, s)
	case stmt.StmtList:
		log.Debug("Visit StmtList")
		stack = stack.NewSymbolTableWithParentScope()

		for i := range t.(stmt.StmtList).Questions {
			t.(stmt.StmtList).Questions[i].(stmt.Question).Accept(v, stack)
		}

		for i := range t.(stmt.StmtList).Conditionals {
			t.(stmt.StmtList).Conditionals[i].(stmt.Conditional).Accept(v, stack)
		}

		stack.Pop()
	case stmt.InputQuestion:
		log.Debug("Visit InputQuestion")
		t.(stmt.InputQuestion).Label.Accept(v, s)
		t.(stmt.InputQuestion).VarDecl.Accept(v, s)
	case stmt.ComputedQuestion:
		log.Debug("Visit ComputedQuestion")
		t.(stmt.ComputedQuestion).Label.Accept(v, s)
		t.(stmt.ComputedQuestion).VarDecl.Accept(v, s)
		t.(stmt.ComputedQuestion).Computation.Accept(v, s)
	case stmt.If:
		log.Debug("Visit If")
		t.(stmt.If).Cond.Accept(v, s)
		t.(stmt.If).Body.Accept(v, s)
	case stmt.IfElse:
		log.Debug("Visit IfElse")
		t.(stmt.IfElse).Cond.Accept(v, s)
		t.(stmt.IfElse).IfBody.Accept(v, s)
		t.(stmt.IfElse).ElseBody.Accept(v, s)
	case lit.StrLit:
		log.Debug("Visit StrLit")
	case lit.BoolLit:
		log.Debug("Visit BoolLit")
	case lit.IntLit:
		log.Debug("Visit IntLit")
	case binaryoperatorexpr.BinaryOperatorExpr:
		log.Debug("Visit BinaryOperatorExpr")
		t.(binaryoperatorexpr.BinaryOperatorExpr).GetLhs().(expr.Expr).Accept(v, s)
		t.(binaryoperatorexpr.BinaryOperatorExpr).GetRhs().(expr.Expr).Accept(v, s)
	case unaryoperatorexpr.UnaryOperatorExpr:
		log.Debug("Visit UnaryOperatorExpr")
		t.(unaryoperatorexpr.UnaryOperatorExpr).GetValue().(expr.Expr).Accept(v, s)
	case expr.VarExpr:
		log.Debug("Visit VarExpr")
		valueOfSymbolInSymbolTable := stack.GetValueForIdentifierInTopSymbolTable(t.(expr.VarExpr).GetIdentifier())
		t.(expr.VarExpr).SetExpr(valueOfSymbolInSymbolTable)
		t.(expr.VarExpr).GetIdentifier().Accept(v, s)
	}

	return false
}
