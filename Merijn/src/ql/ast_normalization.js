import {NodeVisitor} from 'src/ql/ast';

class NormalizingVisitor extends NodeVisitor {
	visitFormNode (formNode) {
		return ['Form', {
			block: formNode.block.accept(this)
		}];
	}
	visitBlockNode (blockNode) {
		return ['Block', {
			'statements': blockNode.statements.map((statement) => {
				return statement.accept(this);
			})
		}];
	}
	visitIfNode (ifNode) {
		return ['If', {
			'condition': ifNode.condition.accept(this),
			'thenBlock': ifNode.thenBlock.accept(this),
			'elseBlock': ifNode.elseBlock !== null ? ifNode.elseBlock.accept(this) : null
		}];
	}
	visitQuestionNode (questionNode) {
		return ['Question', {
			'description': questionNode.description,
			'name': questionNode.name,
			'type': questionNode.type
		}];
	}
	visitUnaryPrefixNode (unaryPrefixNode) {
		return ['UnaryPrefix', {
			'operation': unaryPrefixNode.operation,
			'operand': unaryPrefixNode.operand
		}];
	}
	visitInfixNode (infixNode) {
		return ['Infix', {
			'leftOperand': infixNode.leftOperand,
			'operation': infixNode.operation,
			'rightOperand': infixNode.rightOperand
		}];
	}
	visitLiteralNode (literalNode) {
		return ['Literal', {
			'value': literalNode.value,
			'type': literalNode.type
		}];
	}
	visitIdentifierNode (identifierNode) {
		return ['Identifier', {
			'name': name
		}];
	}
}

export class NodeNormalizer {
	normalize(node) {
		return node.accept(new NormalizingVisitor());
	}
}