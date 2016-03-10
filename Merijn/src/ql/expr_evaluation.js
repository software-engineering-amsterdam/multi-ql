import { NodeVisitor } from 'src/ql/ast';
import * as evaluation from 'src/ql/evaluation';

export class ExprEvaluator extends NodeVisitor {
	evaluate(node, variableMap) {
		return node.accept(this, variableMap);
	}
	handleUnaryPrefixOperation(unaryExpressionNode, variableMap, evaluator) {
		let operandValue = unaryExpressionNode.operand.accept(this, variableMap);

		return evaluator.evaluate(operandValue);
	}
	visitNotNode(notNode, variableMap) {
		return this.handleUnaryPrefixOperation(notNode, variableMap, new evaluation.NotEvaluator());
	}
	visitNegationNode(negationNode, variableMap) {
		return this.handleUnaryPrefixOperation(negationNode, variableMap, new evaluation.NegationEvaluator());
	}
	handleInfixOperation(infixNode, variableMap, evaluator) {
		let leftOperandValue = infixNode.leftOperand.accept(this, variableMap),
			rightOperandValue = infixNode.rightOperand.accept(this, variableMap);

		return evaluator.evaluate(leftOperandValue, rightOperandValue);
	}
	visitAddNode(addNode, variableMap) {
		return this.handleInfixOperation(addNode, variableMap, new evaluation.AddEvaluator());
	}
	visitSubtractNode(subtractNode, variableMap) {
		return this.handleInfixOperation(subtractNode, variableMap, new evaluation.SubtractEvaluator());
	}
	visitMultiplyNode(multiplyNode, variableMap) {
		return this.handleInfixOperation(multiplyNode, variableMap, new evaluation.MultiplyEvaluator());
	}
	visitDivideNode(divideNode, variableMap) {
		return this.handleInfixOperation(divideNode, variableMap, new evaluation.DivideEvaluator());
	}
	visitGreaterNode(greaterNode, variableMap) {
		return this.handleInfixOperation(greaterNode, variableMap, new evaluation.GreaterEvaluator());
	}
	visitGreaterEqualNode(greaterEqualNode, variableMap) {
		return this.handleInfixOperation(greaterEqualNode, variableMap, new evaluation.GreaterEqualEvaluator());
	}
	visitLessNode(lessNode, variableMap) {
		return this.handleInfixOperation(lessNode, variableMap, new evaluation.LessEvaluator());
	}
	visitLessEqualNode(lessEqualNode, variableMap) {
		return this.handleInfixOperation(lessEqualNode, variableMap, new evaluation.LessEqualEvaluator());
	}
	visitEqualNode(equalNode, variableMap) {
		return this.handleInfixOperation(equalNode, variableMap, new evaluation.EqualEvaluator());
	}
	visitNotEqualNode(notEqualNode, variableMap) {
		return this.handleInfixOperation(notEqualNode, variableMap, new evaluation.NotEqualEvaluator());
	}
	visitAndNode(andNode, variableMap) {
		return this.handleInfixOperation(andNode, variableMap, new evaluation.AndEvaluator());
	}
	visitOrNode(orNode, variableMap) {
		return this.handleInfixOperation(orNode, variableMap, new evaluation.OrEvaluator());
	}
	visitLiteralNode(literalNode, variableMap) {
		return literalNode.value;
	}
	visitIdentifierNode(identifierNode, variableMap) {
		let name = identifierNode.name;

		return variableMap.get(name).getValue();
	}
}