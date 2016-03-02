package nl.nicasso.ql;

import java.util.ArrayList;

import nl.nicasso.ql.ast.expression.Identifier;
import nl.nicasso.ql.ast.expression.Parenthesis;
import nl.nicasso.ql.ast.expression.additive.Addition;
import nl.nicasso.ql.ast.expression.additive.Subtraction;
import nl.nicasso.ql.ast.expression.conditional.And;
import nl.nicasso.ql.ast.expression.conditional.Not;
import nl.nicasso.ql.ast.expression.conditional.Or;
import nl.nicasso.ql.ast.expression.equality.Equal;
import nl.nicasso.ql.ast.expression.equality.NotEqual;
import nl.nicasso.ql.ast.expression.multiplicative.Division;
import nl.nicasso.ql.ast.expression.multiplicative.Multiplication;
import nl.nicasso.ql.ast.expression.relational.Greater;
import nl.nicasso.ql.ast.expression.relational.GreaterEqual;
import nl.nicasso.ql.ast.expression.relational.Less;
import nl.nicasso.ql.ast.expression.relational.LessEqual;
import nl.nicasso.ql.ast.literal.BooleanLit;
import nl.nicasso.ql.ast.literal.IntegerLit;
import nl.nicasso.ql.ast.literal.MoneyLit;
import nl.nicasso.ql.ast.literal.StringLit;
import nl.nicasso.ql.ast.statement.ComputedQuestion;
import nl.nicasso.ql.ast.statement.IfElseStatement;
import nl.nicasso.ql.ast.statement.IfStatement;
import nl.nicasso.ql.ast.statement.Question;
import nl.nicasso.ql.ast.statement.Statement;
import nl.nicasso.ql.ast.structure.Block;
import nl.nicasso.ql.ast.structure.Form;
import nl.nicasso.ql.ast.type.BooleanType;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.NumericType;
import nl.nicasso.ql.ast.type.StringType;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.visitor.ExpressionVisitor;
import nl.nicasso.ql.visitor.StatementVisitor;
import nl.nicasso.ql.visitor.StructureVisitor;

public class TypeCheckerVisitor implements StructureVisitor<Type>, StatementVisitor<Type>, ExpressionVisitor<Type> {

	private boolean debug = true;
		
	private ArrayList<String> errors;
	private ArrayList<String> warnings;

	private SymbolTable symbolTable;
	
	public TypeCheckerVisitor(SymbolTable symbolTable) {
		errors = new ArrayList<String>();
		warnings = new ArrayList<String>();
		this.symbolTable = symbolTable;
	}
		
	@Override
	public Type visit(And value) {	
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("And");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (And)");
			return new BooleanType();
		} else {
			return containerType;
		}
	}
	
	@Override
	public Type visit(Addition value) {		
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Addition");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (Addition)");
			// Fake a return, BAH!
			return new IntegerType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(Subtraction value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Subtraction");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (Subtraction)");
			return new IntegerType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(Or value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Or");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (Or)");
			return new BooleanType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(Not value) {
		Type exprType = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Not");
		}
		
		if (!checkType(exprType, new BooleanType())) {
			errors.add("Error: Incompatible type detected (Not)");
		}
		
		return new BooleanType();
	}

	@Override
	public Type visit(Parenthesis value) {		
		Type exprType = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Parenthesis");
		}
		
		//if (!checkType(exprType, new Type())) {
		//	errors.add("Error: Incompatible type detected");
		//}
		
		return exprType;
	}

	@Override
	public Type visit(Equal value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Equal");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (Equal)");
			return new Type();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(NotEqual value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);	
		
		if (debug) {
			System.out.println("NotEqual");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (NotEqual)");
			return new Type();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(Division value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Division");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (Division)");
			return new MoneyType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(Multiplication value) {
		Type leftType = value.getLeft().accept(this);
		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Multiplication");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (Multiplication)");
			return new MoneyType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(Greater value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Greater");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (Greater)");
			return new BooleanType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(GreaterEqual value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("GreaterEqual");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (GreaterEqual)");
			return new BooleanType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(Less value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);

		if (debug) {
			System.out.println("Less");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (Less)");
			return new BooleanType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(LessEqual value) {
		Type leftType = value.getLeft().accept(this);

		Type rightType = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("LessEqual");
		}
		
		Type containerType = value.checkAllowedTypes(leftType, rightType);
		
		if (containerType == null) {
			errors.add("Error: Incompatible types detected (LessEqual)");
			return new BooleanType();
		} else {
			return containerType;
		}
	}

	@Override
	public Type visit(Form value) {
		value.getBlock().accept(this);
		
		if (debug) {
			System.out.println("Form");
		}
		
		return null;
	}

	@Override
	public Type visit(Block value) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}
		
		if (debug) {
			System.out.println("Block");
		}
		
		return null;
	}

	@Override
	public Type visit(Question value) {
		if (debug) {
			System.out.println("Question");
		}
				
		return null;
	}

	@Override
	public Type visit(ComputedQuestion value) {
		Type expr = value.getExpr().accept(this);getClass();
		
		if (!expr.getType().equals(value.getType().getType())) {
			errors.add("Error: Incompatible types detected (ComputedQuestion): "+value.getId().getValue());
		}
		
		if (debug) {
			System.out.println("ComputedQuestion: "+value.getId().getValue()+" - Expected value: "+value.getType().getType()+" - Recieved value: "+expr.getType());
		}
		
		return null;
	}

	@Override
	public Type visit(IfStatement value) {
		Type expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		
		if (!checkType(expr, new BooleanType())) {
			errors.add("Error: Incompatible types detected (IfStatement)");
		}
		
		if (debug) {
			System.out.println("ifStatement");
		}
		return null;
	}

	@Override
	public Type visit(IfElseStatement value) {
		Type expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
		
		if (!checkType(expr, new BooleanType())) {
			errors.add("Error: Incompatible types detected (IfElseStatement)");
		}
		
		if (debug) {
			System.out.println("IfElseStatement");
		}
		return null;
	}

	@Override
	public Type visit(BooleanLit value) {
		if (debug) {
			System.out.println("BooleanLit: "+value.getValue());
		}
		return new BooleanType();
	}

	@Override
	public Type visit(Identifier value) {
		if (debug) {
			System.out.println("IdentifierLit: "+value.getValue()+" - "+symbolTable.getEntry(value).getType().getType());
		}
		
		SymbolTableEntry entry = symbolTable.getEntry(value);
		
		return entry.getType();
	}

	@Override
	public Type visit(IntegerLit value) {
		if (debug) {
			System.out.println("IntegerLit: "+value.getValue());
		}
		return new IntegerType();
	}

	@Override
	public Type visit(StringLit value) {
		if (debug) {
			System.out.println("StringLit: "+value.getValue());
		}
		return new StringType();
	}
	
	@Override
	public Type visit(MoneyLit value) {
		if (debug) {
			System.out.println("MoneyLit: "+value.getValue());
		}
		return new MoneyType();
	}
	
	private boolean checkType(Type exprType, Type type) {
		if (exprType instanceof NumericType) {
			return true;	
		} else if (exprType instanceof Type) {
			return true;	
		} else if (exprType.getType().equals(type.getType())) {
			return true;
		}
		return false;
	}
	
	public ArrayList<String> getErrors() {
		return errors;
	}
	
	public ArrayList<String> getWarnings() {
		return warnings;
	}
	
}
