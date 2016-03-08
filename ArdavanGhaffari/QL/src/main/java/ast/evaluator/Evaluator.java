package ast.evaluator;

import java.util.HashMap;

import ast.model.binaryexpression.Addition;
import ast.model.binaryexpression.Conjunction;
import ast.model.binaryexpression.Disjunction;
import ast.model.binaryexpression.Division;
import ast.model.binaryexpression.Equal;
import ast.model.binaryexpression.GreaterThan;
import ast.model.binaryexpression.GreaterThanEqual;
import ast.model.binaryexpression.LessThan;
import ast.model.binaryexpression.LessThanEqual;
import ast.model.binaryexpression.Multiplication;
import ast.model.binaryexpression.NotEqual;
import ast.model.binaryexpression.Subtraction;
import ast.model.literal.BooleanLiteral;
import ast.model.literal.DecimalLiteral;
import ast.model.literal.Identifier;
import ast.model.literal.IntegerLiteral;
import ast.model.literal.StringLiteral;
import ast.model.unaryexpression.Negation;
import ast.visitor.ExpressionVisitor;

public class Evaluator implements ExpressionVisitor<Object> {
	
	private HashMap<Identifier, Object> identifierValueMap = new HashMap<>();

	@Override
	public Object visit(Negation negation) {
		Object expression = negation.getExpression().accept(this);
		return !(Boolean) expression;
	}

	@Override
	public Object visit(Addition addition) {
		Object leftOperand = addition.getLeftExpression().accept(this);
		Object rightOperand = addition.getRightExpression().accept(this);
		
		if (leftOperand instanceof String) {
			return (String) leftOperand + (String) rightOperand;
		}
		
		if (leftOperand instanceof Double) {
			if (rightOperand instanceof Double) {
				return (Double) leftOperand + (Double) rightOperand;
			} else {
				return (Double) leftOperand + (Integer) rightOperand;
			}
		} else {
			if (rightOperand instanceof Double) {
				return (Integer) leftOperand + (Double) rightOperand;
			} else {
				return (Integer) leftOperand + (Integer) rightOperand;
			}
		}
	}

	@Override
	public Object visit(Subtraction subtraction) {
		Object leftOperand = subtraction.getLeftExpression().accept(this);
		Object rightOperand = subtraction.getRightExpression().accept(this);
		
		if (leftOperand instanceof Double) {
			if (rightOperand instanceof Double) {
				return (Double) leftOperand - (Double) rightOperand;
			} else {
				return (Double) leftOperand - (Integer) rightOperand;
			}
		} else {
			if (rightOperand instanceof Double) {
				return (Integer) leftOperand - (Double) rightOperand;
			} else {
				return (Integer) leftOperand - (Integer) rightOperand;
			}
		}
	}

	@Override
	public Object visit(Multiplication multiplication) {
		Object leftOperand = multiplication.getLeftExpression().accept(this);
		Object rightOperand = multiplication.getRightExpression().accept(this);
		
		if (leftOperand instanceof Double) {
			if (rightOperand instanceof Double) {
				return (Double) leftOperand * (Double) rightOperand;
			} else {
				return (Double) leftOperand * (Integer) rightOperand;
			}
		} else {
			if (rightOperand instanceof Double) {
				return (Integer) leftOperand * (Double) rightOperand;
			} else {
				return (Integer) leftOperand * (Integer) rightOperand;
			}
		}
	}

	@Override
	public Object visit(Division division) {
		Object leftOperand = division.getLeftExpression().accept(this);
		Object rightOperand = division.getRightExpression().accept(this);
		
		double leftValue;
		double rightValue;
		
		if (leftOperand instanceof Double) {
			leftValue = (Double) leftOperand;
		} else {
			leftValue = (Integer) leftOperand;
		}
		
		if (rightOperand instanceof Double) {
			rightValue = (Double) rightOperand;
		} else {
			rightValue = (Integer) rightOperand;
		}
		
		if (rightValue != 0) {
			return leftValue / rightValue;
		}
		return null;
	}

	@Override
	public Object visit(Conjunction conjunction) {
		Object leftOperand = conjunction.getLeftExpression().accept(this);
		Object rightOperand = conjunction.getRightExpression().accept(this);
		
		return new Boolean((Boolean) leftOperand && (Boolean) rightOperand);
	}

	@Override
	public Object visit(Disjunction disjunction) {
		Object leftOperand = disjunction.getLeftExpression().accept(this);
		Object rightOperand = disjunction.getRightExpression().accept(this);
		
		return new Boolean((Boolean) leftOperand || (Boolean) rightOperand);
	}

	@Override
	public Object visit(Equal equal) {
		Object leftOperand = equal.getLeftExpression().accept(this);
		Object rightOperand = equal.getRightExpression().accept(this);
		
		if (leftOperand instanceof Double && rightOperand instanceof Integer) {
			return ((Double) leftOperand).doubleValue() == ((Integer) rightOperand).intValue();
		}
		
		if (leftOperand instanceof Integer && rightOperand instanceof Double) {
			return ((Integer) leftOperand).intValue() == ((Double) rightOperand).doubleValue();
		}
		
		return leftOperand.equals(rightOperand);
	}

	@Override
	public Object visit(NotEqual notEqual) {
		Object leftOperand = notEqual.getLeftExpression().accept(this);
		Object rightOperand = notEqual.getRightExpression().accept(this);
		
		if (leftOperand instanceof Double && rightOperand instanceof Integer) {
			return ((Double) leftOperand).doubleValue() != ((Integer) rightOperand).intValue();
		}
		
		if (leftOperand instanceof Integer && rightOperand instanceof Double) {
			return ((Integer) leftOperand).intValue() != ((Double) rightOperand).doubleValue();
		}
		
		return !leftOperand.equals(rightOperand);
	}

	@Override
	public Object visit(GreaterThan greaterThan) {
		Object leftOperand = greaterThan.getLeftExpression().accept(this);
		Object rightOperand = greaterThan.getRightExpression().accept(this);
		
		if (leftOperand instanceof Double) {
			if (rightOperand instanceof Double) {
				return (Double) leftOperand > (Double) rightOperand;
			} else {
				return (Double) leftOperand > (Integer) rightOperand;
			}
		} else {
			if (rightOperand instanceof Double) {
				return (Integer) leftOperand > (Double) rightOperand;
			} else {
				return (Integer) leftOperand > (Integer) rightOperand;
			}
		}
	}

	@Override
	public Object visit(GreaterThanEqual greaterThanEqual) {
		Object leftOperand = greaterThanEqual.getLeftExpression().accept(this);
		Object rightOperand = greaterThanEqual.getRightExpression().accept(this);
		
		if (leftOperand instanceof Double) {
			if (rightOperand instanceof Double) {
				return (Double) leftOperand >= (Double) rightOperand;
			} else {
				return (Double) leftOperand >= (Integer) rightOperand;
			}
		} else {
			if (rightOperand instanceof Double) {
				return (Integer) leftOperand >= (Double) rightOperand;
			} else {
				return (Integer) leftOperand >= (Integer) rightOperand;
			}
		}
	}

	@Override
	public Object visit(LessThan lessThan) {
		Object leftOperand = lessThan.getLeftExpression().accept(this);
		Object rightOperand = lessThan.getRightExpression().accept(this);
		
		if (leftOperand instanceof Double) {
			if (rightOperand instanceof Double) {
				return (Double) leftOperand < (Double) rightOperand;
			} else {
				return (Double) leftOperand < (Integer) rightOperand;
			}
		} else {
			if (rightOperand instanceof Double) {
				return (Integer) leftOperand < (Double) rightOperand;
			} else {
				return (Integer) leftOperand < (Integer) rightOperand;
			}
		}
	}

	@Override
	public Object visit(LessThanEqual lessThanEqual) {
		Object leftOperand = lessThanEqual.getLeftExpression().accept(this);
		Object rightOperand = lessThanEqual.getRightExpression().accept(this);
		
		if (leftOperand instanceof Double) {
			if (rightOperand instanceof Double) {
				return (Double) leftOperand <= (Double) rightOperand;
			} else {
				return (Double) leftOperand <= (Integer) rightOperand;
			}
		} else {
			if (rightOperand instanceof Double) {
				return (Integer) leftOperand <= (Double) rightOperand;
			} else {
				return (Integer) leftOperand <= (Integer) rightOperand;
			}
		}
	}

	@Override
	public Object visit(BooleanLiteral booleanLiteral) {
		return new Boolean(booleanLiteral.getValue());
	}

	@Override
	public Object visit(StringLiteral stringLiteral) {
		return new String(stringLiteral.getValue());
	}

	@Override
	public Object visit(DecimalLiteral decimalLiteral) {
		return new Double(decimalLiteral.getValue());
	}

	@Override
	public Object visit(IntegerLiteral integerLiteral) {
		return new Integer(integerLiteral.getValue());
	}

	@Override
	public Object visit(Identifier identifier) {
		return getValue(identifier);
	}
	
	public void addValue(Identifier identifier, Object value) {
		identifierValueMap.put(identifier, value);
	}
	
	public Object getValue(Identifier identifier) {
		if (identifierValueMap.containsKey(identifier)) {
			return identifierValueMap.get(identifier);
		}
		return null;
	}

}
