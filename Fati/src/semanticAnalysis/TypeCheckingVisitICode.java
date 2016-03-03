package semanticAnalysis;

import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.expressionNode.calculation.Add;
import semanticAction.tree.expressionNode.calculation.Division;
import semanticAction.tree.expressionNode.calculation.SUB;
import semanticAction.tree.expressionNode.calculation.Time;
import semanticAction.tree.expressionNode.comparison.Equal;
import semanticAction.tree.expressionNode.comparison.GreaterEqual;
import semanticAction.tree.expressionNode.comparison.GreaterThan;
import semanticAction.tree.expressionNode.comparison.LessEqual;
import semanticAction.tree.expressionNode.comparison.LessThan;
import semanticAction.tree.expressionNode.comparison.NotEqual;
import semanticAction.tree.expressionNode.literal.Booleanliteral;
import semanticAction.tree.expressionNode.literal.Identifier;
import semanticAction.tree.expressionNode.literal.Integerliteral;
import semanticAction.tree.expressionNode.literal.Stringliteral;
import semanticAction.tree.expressionNode.logical.And;
import semanticAction.tree.expressionNode.logical.OR;
import semanticAction.tree.expressionNode.unary.Minus;
import semanticAction.tree.expressionNode.unary.NOT;
import semanticAction.tree.expressionNode.unary.Plus;
import semanticAction.tree.formNode.Form;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.intermediate.InterfaceVisitForm;
import semanticAction.tree.intermediate.InterfaceVisitQuestion;
import semanticAction.tree.questionNode.AbsQuestion;
import semanticAction.tree.questionNode.CalculatedQuestion;
import semanticAction.tree.questionNode.IfElseQuestion;
import semanticAction.tree.questionNode.IfQuestion;
import semanticAction.tree.questionNode.NormalQuestion;

public class TypeCheckingVisitICode extends AbsTypeChecking implements
		InterfaceVisitExpression<Void>, InterfaceVisitForm<Void>,
		InterfaceVisitQuestion<Void> {

	@Override
	public Void visit(Identifier e) {
		String id = e.getID();

		if (!this.typeSaver.isDefined(id)) {
			this.errorCollector
					.addError("Error: reference to undefined question *" + id
							+ "*.");
		}
		return null;
	}

	// AbsType checking visit and check calculation
	@Override
	public Void visit(And e) {
		return this.checkExpr(e);

	}

	@Override
	public Void visit(OR e) {
		return this.checkExpr(e);

	}

	@Override
	public Void visit(Add e) {
		return this.checkExpr(e);

	}

	@Override
	public Void visit(SUB e) {
		return this.checkExpr(e);

	}

	@Override
	public Void visit(Time e) {
		return this.checkExpr(e);

	}

	@Override
	public Void visit(Division e) {
		return this.checkExpr(e);

	}

	// AbsType checking visit and check comparison expressions
	@Override
	public Void visit(GreaterThan e) {
		return this.checkComparison(e);
	}

	@Override
	public Void visit(LessEqual e) {
		return this.checkComparison(e);

	}

	@Override
	public Void visit(LessThan e) {
		return this.checkComparison(e);
	}

	@Override
	public Void visit(GreaterEqual e) {
		return this.checkComparison(e);
	}

	// AbsType checking visit and check Equal end NotEqual expressions
	@Override
	public Void visit(Equal e) {
		return this.checkEqual(e);
	}

	@Override
	public Void visit(NotEqual e) {
		return this.checkEqual(e);
	}

	// AbsType checking visit and check AbsUnary expressions
	@Override
	public Void visit(Plus e) {
		return this.checkUnary(e);
	}

	@Override
	public Void visit(Minus e) {
		return this.checkUnary(e);
	}

	@Override
	public Void visit(NOT e) {
		return this.checkUnary(e);
	}

	@Override
	public Void visit(Form form) {

		for (AbsQuestion q : form.getLabel())
			q.accept(this);
		return null;
	}

	@Override
	public Void visit(NormalQuestion NQ) {
		this.checkQ(NQ);
		typeSaver.saveType(NQ.getQId().getID(), NQ.getQType());
		typeSaver.SaveQLabel(NQ.getQId().getID(), NQ.getQText());
		return null;
	}

	@Override
	public Void visit(CalculatedQuestion CQ) {
		this.checkQ(CQ);

		String id = CQ.getQId().getID();
		typeSaver.saveType(id, CQ.getQType());
		typeSaver.SaveQLabel(id, CQ.getQText());

		CQ.getExpression().accept(this);

		checkType(CQ.getExpression(), CQ.getQType());

		return null;
	}

	@Override
	public Void visit(IfQuestion ifQ) {
		for (AbsQuestion q : ifQ.getIfQuestion()) {
			q.accept(this);
		}

		ifQ.getExpression().accept(this);
		AbsExpression e = ifQ.getExpression();

		checkType(e, e.getType());

		return null;

	}

	@Override
	public Void visit(IfElseQuestion ifelseQ) {

		for (AbsQuestion q : ifelseQ.getIfQuestion()) {
			q.accept(this);
		}

		for (AbsQuestion q : ifelseQ.getElseQuestion()) {
			q.accept(this);
		}

		ifelseQ.getExpression().accept(this);
		AbsExpression e = ifelseQ.getExpression();

		checkType(e, e.getType());

		return null;
	}

	@Override
	public Void visit(Booleanliteral e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Stringliteral e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Integerliteral e) {
		// TODO Auto-generated method stub
		return null;
	}
}
