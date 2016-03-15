package stmt

import (
	"fmt"
	"ql/interfaces"
)

func (this Form) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	this.Content.TypeCheck(typeChecker, symbols)
}

func (this If) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	this.typeCheckIfForNonBoolConditions(typeChecker, symbols)
	this.Cond.TypeCheck(typeChecker, symbols)
	this.Body.TypeCheck(typeChecker, symbols)
}

func (this IfElse) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	this.typeCheckIfElseForNonBoolConditions(typeChecker, symbols)
	this.Cond.TypeCheck(typeChecker, symbols)
	this.IfBody.TypeCheck(typeChecker, symbols)
	this.ElseBody.TypeCheck(typeChecker, symbols)
}

func (this ComputedQuestion) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	typeCheckQuestionForDuplicateLabels(this, typeChecker)
	typeCheckQuestionForRedeclaration(this, typeChecker)

	this.Computation.TypeCheck(typeChecker, symbols)
}

func (this InputQuestion) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	typeCheckQuestionForDuplicateLabels(this, typeChecker)
	typeCheckQuestionForRedeclaration(this, typeChecker)
}

func (this StmtList) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	for _, conditional := range this.Conditionals {
		switch conditional.(type) {
		case If:
			conditional.(If).TypeCheck(typeChecker, symbols)
		case IfElse:
			conditional.(IfElse).TypeCheck(typeChecker, symbols)
		}
	}

	for _, question := range this.Questions {
		switch question.(type) {
		case InputQuestion:
			question.(InputQuestion).TypeCheck(typeChecker, symbols)
		case ComputedQuestion:
			question.(ComputedQuestion).TypeCheck(typeChecker, symbols)
		}
	}
}

func (this If) typeCheckIfForNonBoolConditions(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	evalCond := this.Cond.Eval(symbols)

	if _, CondIsBoolType := evalCond.(bool); !CondIsBoolType {
		typeChecker.AddEncounteredErrorForCheckType("NonBoolConditionals", fmt.Errorf("Non-boolean type used as condition: %T", evalCond))
	}
}

func (this IfElse) typeCheckIfElseForNonBoolConditions(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	evalCond := this.Cond.Eval(symbols)

	if _, CondIsBoolType := evalCond.(bool); !CondIsBoolType {
		typeChecker.AddEncounteredErrorForCheckType("NonBoolConditionals", fmt.Errorf("Non-boolean type used as condition: %T", evalCond))
	}
}

func typeCheckQuestionForDuplicateLabels(question interfaces.Question, typeChecker interfaces.TypeChecker) {
	labelKnown := typeChecker.IsLabelUsed(question.GetLabel())

	if labelKnown {
		typeChecker.AddEncounteredErrorForCheckType("DuplicateLabels", fmt.Errorf("Label \"%s\" already used for question with identifier %s, using again for question with identifier %s", question.GetLabel(), typeChecker.VarIdForLabel(question.GetLabel()), question.GetVarDecl().GetIdent()))
	} else {
		typeChecker.MarkLabelAsUsed(question.GetLabel(), question.GetVarDecl())
	}
}

func typeCheckQuestionForRedeclaration(question interfaces.Question, typeChecker interfaces.TypeChecker) {
	varDecl := question.GetVarDecl()
	labelKnown := typeChecker.VarDeclIsKnown(varDecl)

	if labelKnown && typeChecker.TypeForVarDecl(varDecl) != varDecl.GetType() {
		typeChecker.AddEncounteredErrorForCheckType("DuplicateVarDeclarations", fmt.Errorf("Question redeclared with different types: %T and %T", varDecl.GetType(), typeChecker.TypeForVarDecl(varDecl)))
	} else {
		typeChecker.MarkVarDeclAsKnown(varDecl)
	}
}
