package semanticAnalysis;

import java.util.List;
import java.util.Map;

import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.expressionNode.BinSearchTree;
import semanticAction.tree.expressionNode.unary.AbsUnary;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.intermediate.InterfaceVisitForm;
import semanticAction.tree.intermediate.InterfaceVisitQuestion;
import semanticAction.tree.questionNode.NormalQuestion;
import semanticAction.tree.typeNode.AbsType;
import semanticAction.tree.typeNode.IntegerQL_Type;
import semanticAnalysis.error.Error;
import semanticAnalysis.error.ErrorWarningList;
import semanticAnalysis.error.Warning;

public abstract class AbsTypeChecking implements InterfaceVisitForm<Void>,
		InterfaceVisitQuestion<Void>, InterfaceVisitExpression<Void> {
	protected final ErrorWarningList errorCollector;
	protected final SymTabel typeSaver;

	public AbsTypeChecking() {
		this.errorCollector = new ErrorWarningList();
		this.typeSaver = new SymTabel();
	}

	public List<Error> getError() {
		return this.errorCollector.getErrorList();
	}

	public List<Warning> getWarning() {
		return this.errorCollector.getWarningList();
	}

	public void checkQ(NormalQuestion q) {
		CheckQId(q);
		CheckQLabel(q);
	}

	// check weather the id is defined for other type
	public void CheckQId(NormalQuestion q) {
		String id = q.getQId().getID();
		AbsType type = q.getQType();

		if (!this.typeSaver.empty()) {
			if (!this.typeSaver.isDefined(id))
				if (this.typeSaver.getValue(id).equals(type))
					return;
		}

		this.errorCollector.addError("Question *" + id
				+ " is defined for another type.");
	}

	// duplicate QLabels (warning)
	public void CheckQLabel(NormalQuestion q) {
		String id = q.getQId().getID(); // violate the law of demeter
		String QLabel = q.getQText();

		if (this.typeSaver != null) {
			for (Map.Entry<String, String> input : this.typeSaver
					.getqLabelServer().entrySet()) {
				String key = input.getKey();
				String QLabelValue = input.getValue();

				if (!QLabelValue.equals(QLabel) || key.equals(id)) {
					continue;// skip the current statement and jump to the next
								// one
				}
				this.errorCollector
						.addWarning("Warning! there is another question with the same*"
								+ QLabelValue + " or*" + key + "*.");
			}
		}
	}

	public void checkType(AbsExpression e, AbsType t) {
		if (e.getType() != null) {
			if (e.getType().isCompatibleToType(t)) {
				return;
			}
			this.errorCollector.addError(" Error:Type of the AbsExpression *"
					+ e.toString() + "is not correct" + e.getType()
					+ "the tyype should be *" + t + "*.");
		}
	}

	public Void checkComparison(BinSearchTree e) {
		e.getLeftExpression().accept(this);
		e.getRightExpression().accept(this);

		checkType(e.getLeftExpression(), new IntegerQL_Type());
		checkType(e.getRightExpression(), new IntegerQL_Type());
		return null;
	}

	public Void checkUnary(AbsUnary e) {
		e.getUnaryExpression().accept(this);

		checkType(e.getUnaryExpression(), e.getType());
		return null;
	}

	public Void checkEqual(BinSearchTree e) {
		e.getLeftExpression().accept(this);
		e.getRightExpression().accept(this);

		checkType(e.getLeftExpression(), e.getRightExpression().getType());

		return null;
	}

	public Void checkExpr(BinSearchTree e) {
		e.getLeftExpression().accept(this);
		e.getRightExpression().accept(this);

		checkType(e.getLeftExpression(), e.getType());
		checkType(e.getRightExpression(), e.getType());
		return null;
	}

}