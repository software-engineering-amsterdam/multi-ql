package expr

import (
	"fmt"
	"ql/interfaces"
)

func (this VarExpr) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	typeCheckArgs.TypeChecker().AddDependencyForVarDecl(this.Identifier, typeCheckArgs.CurrentVarDeclVisited())

	// Return the true type of the VarExpr; the type of the Expr referred to
	if typeCheckArgs.Symbols().IsTypeSetForVarId(this.Identifier) {
		return typeCheckArgs.Symbols().GetTypeForVarId(this.Identifier).(interfaces.ValueType)
	}

	// We don't already mark it as an error; because there is only one scope, the VarDecl may be simply declared later on
	// After the whole Form is typechecked, it is checked which VarIds remain unknown (those that were not declared at a later point)
	typeCheckArgs.TypeChecker().MarkVarIdAsUnknown(this.GetIdentifier())

	// No type info in symboltable (reference to undefined question)
	return NewUnknownType()
}

func (this Add) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewIntType(), typeCheckArgs)

	return NewIntType()
}

func (this And) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewBoolType(), typeCheckArgs)

	return NewBoolType()
}

func (this Div) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewIntType(), typeCheckArgs)

	return NewIntType()
}

func (this Eq) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkForEqualTypes(NewBoolType(), typeCheckArgs)

	return NewBoolType()
}

func (this GEq) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewBoolType(), typeCheckArgs)

	return NewBoolType()
}

func (this GT) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewIntType(), typeCheckArgs)

	return NewBoolType()
}

func (this LEq) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewIntType(), typeCheckArgs)

	return NewBoolType()
}

func (this LT) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewIntType(), typeCheckArgs)

	return NewBoolType()
}

func (this Mul) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewIntType(), typeCheckArgs)

	return NewIntType()
}

func (this Neg) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperand(NewIntType(), typeCheckArgs)

	return NewIntType()
}

func (this NEq) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkForEqualTypes(NewBoolType(), typeCheckArgs)

	return NewBoolType()
}

func (this Not) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperand(NewBoolType(), typeCheckArgs)

	return NewBoolType()
}

func (this Or) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewBoolType(), typeCheckArgs)

	return NewBoolType()
}

func (this Pos) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperand(NewIntType(), typeCheckArgs)

	return NewIntType()
}

func (this Sub) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	this.checkOperands(NewIntType(), typeCheckArgs)

	return NewIntType()
}

func (this IntLit) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	return NewIntType()
}

func (this BoolLit) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	return NewBoolType()
}

func (this StrLit) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	return NewStringType()
}

// TypeCheck on Expr is the default implementation, which basically asserts that parent structs have overridden this method
func (this Expr) TypeCheck(typeCheckArgs interfaces.TypeCheckArgs) interfaces.ValueType {
	panic("Expr TypeCheck method not overridden")
}

// checkOperand checks if the value of a unaryExpr is of the expected type
func (unaryExpression UnaryOperator) checkOperand(expectedType interfaces.ValueType, typeCheckArgs interfaces.TypeCheckArgs) {
	checkIfOperandHasExpectedType(unaryExpression.GetValue(), expectedType, typeCheckArgs)
}

// checkOperands checks if the left-hand and right-hand sides are of the expected type
func (binaryExpr BinaryOperator) checkOperands(expectedType interfaces.ValueType, typeCheckArgs interfaces.TypeCheckArgs) {
	checkIfOperandHasExpectedType(binaryExpr.GetLhs(), expectedType, typeCheckArgs)
	checkIfOperandHasExpectedType(binaryExpr.GetRhs(), expectedType, typeCheckArgs)
}

// checkForEqualTypes checks if the operands in a BinaryOperator have the same type, and if the types are unequal adds an error to the typechecker
func (binaryExpr BinaryOperator) checkForEqualTypes(expectedType interfaces.ValueType, typeCheckArgs interfaces.TypeCheckArgs) {
	lhsType := binaryExpr.GetLhs().TypeCheck(typeCheckArgs)
	rhsType := binaryExpr.GetRhs().TypeCheck(typeCheckArgs)

	if lhsType == NewUnknownType() || rhsType == NewUnknownType() {
		return
	}

	if lhsType != rhsType {
		typeCheckArgs.TypeChecker().AddEncounteredError(fmt.Errorf("Encountered BinaryOperator with operands of different types: %s and %s", lhsType, rhsType))
	}
}

func checkIfOperandHasExpectedType(expr interfaces.Expr, expectedType interfaces.ValueType, typeCheckArgs interfaces.TypeCheckArgs) {
	actualType := expr.TypeCheck(typeCheckArgs)

	if actualType == NewUnknownType() {
		return
	}

	if actualType != expectedType {
		typeCheckArgs.TypeChecker().AddEncounteredError(fmt.Errorf("Encountered unexpected operand type, expected type: %s, actual type: %s", expectedType, actualType))
	}
}
