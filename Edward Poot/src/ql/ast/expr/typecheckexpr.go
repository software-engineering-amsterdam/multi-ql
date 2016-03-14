package expr

import (
	"fmt"
	"ql/interfaces"
)

func (b BinaryOperator) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	typeCheckForUnequalTypes(b, typeChecker, symbols)
	typeCheckInvalidBinaryOperatorForOperands(b, typeChecker, symbols)

	b.Lhs.TypeCheck(typeChecker, symbols)
	b.Rhs.TypeCheck(typeChecker, symbols)
}

func (u UnaryOperator) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	typeCheckInvalidUnaryOperatorForOperands(u, typeChecker, symbols)

	u.Value.(interfaces.Expr).TypeCheck(typeChecker, symbols)
}

func (v VarExpr) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	typeCheckUndefinedQuestionReference(v, typeChecker, symbols)

	v.Identifier.TypeCheck(typeChecker, symbols)
}

func (this Expr) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	panic("Expr TypeCheck method not overridden")
}

func (i IntLit) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {

}

func (b BoolLit) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {

}

func (s StrLit) TypeCheck(typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {

}

func typeCheckForUnequalTypes(expr BinaryOperator, typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	lhsTypeOfValue := fmt.Sprintf("%T", expr.Lhs.Eval(symbols))
	rhsTypeOfValue := fmt.Sprintf("%T", expr.Rhs.Eval(symbols))

	if lhsTypeOfValue != rhsTypeOfValue {
		typeChecker.AddEncounteredErrorForCheckType("InvalidOperandsDifferentTypes", fmt.Errorf("Encountered BinaryOperator with operands of different types: %s and %s", lhsTypeOfValue, rhsTypeOfValue))
	}
}

func typeCheckInvalidBinaryOperatorForOperands(binaryOperator BinaryOperator, typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	exprType := fmt.Sprintf("%T", binaryOperator)
	lhsTypeOfValue := binaryOperator.Lhs.Eval(symbols)

	switch lhsTypeOfValue.(type) {
	case bool:
		if exprType != "And" && exprType != "Or" && exprType != "Eq" && exprType != "NEq" {
			typeChecker.AddEncounteredErrorForCheckType("InvalidOperationOnOperands", fmt.Errorf("Encountered invalid operation for bool operands"))
		}
	case int:
		if exprType != "Add" && exprType != "Div" && exprType != "Eq" && exprType != "NEq" && exprType != "GEq" && exprType != "GT" && exprType != "GEq" && exprType != "LEq" && exprType != "LT" && exprType != "Mul" && exprType != "Sub" {
			typeChecker.AddEncounteredErrorForCheckType("InvalidOperationOnOperands", fmt.Errorf("Encountered invalid operation for int operands"))
		}
	case string:
		if exprType != "Eq" && exprType != "NEq" && exprType != "GEq" && exprType != "GT" && exprType != "GEq" && exprType != "LEq" && exprType != "LT" {
			typeChecker.AddEncounteredErrorForCheckType("InvalidOperationOnOperands", fmt.Errorf("Encountered invalid operation for string operands"))
		}
	}
}

func typeCheckInvalidUnaryOperatorForOperands(unaryOperator UnaryOperator, typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	exprType := fmt.Sprintf("%T", unaryOperator)
	evalValue := unaryOperator.Value.Eval(symbols)

	switch evalValue.(type) {
	case bool:
		if exprType != "unaryoperatorNot" {
			typeChecker.AddEncounteredErrorForCheckType("InvalidOperationOnOperands", fmt.Errorf("Encountered invalid operation for bool operand"))
		}
	case int:
		if exprType != "unaryoperatorNeg" && exprType != "unaryoperatorPos" {
			typeChecker.AddEncounteredErrorForCheckType("InvalidOperationOnOperands", fmt.Errorf("Encountered invalid operation for int operand"))
		}
	case string:
		// there are no unary operators on strings, so always return error
		typeChecker.AddEncounteredErrorForCheckType("InvalidOperationOnOperands", fmt.Errorf("Encountered invalid operation for string operand"))
	}
}

func typeCheckUndefinedQuestionReference(varExpr VarExpr, typeChecker interfaces.TypeChecker, symbols interfaces.Symbols) {
	if symbols.GetNodeForIdentifier(varExpr.GetIdentifier()) == nil {
		typeChecker.AddEncounteredErrorForCheckType("ReferenceToUndefinedQuestion", fmt.Errorf("Reference to unknown question identifier: %s", varExpr.GetIdentifier()))
	}
}