package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.parser.ast.AdditionNode;
import nl.uva.sc.ql.parser.ast.AndNode;
import nl.uva.sc.ql.parser.ast.AssignedQuestionNode;
import nl.uva.sc.ql.parser.ast.BinaryExpressionNode;
import nl.uva.sc.ql.parser.ast.BlockNode;
import nl.uva.sc.ql.parser.ast.BooleanNode;
import nl.uva.sc.ql.parser.ast.ConditionBlockNode;
import nl.uva.sc.ql.parser.ast.DifferentNode;
import nl.uva.sc.ql.parser.ast.DivisionNode;
import nl.uva.sc.ql.parser.ast.EqualsNode;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.ast.FormNode;
import nl.uva.sc.ql.parser.ast.GreatEqualsThanNode;
import nl.uva.sc.ql.parser.ast.GreatThanNode;
import nl.uva.sc.ql.parser.ast.IdentifierNode;
import nl.uva.sc.ql.parser.ast.LessEqualsThanNode;
import nl.uva.sc.ql.parser.ast.LessThanNode;
import nl.uva.sc.ql.parser.ast.ListStatementsNode;
import nl.uva.sc.ql.parser.ast.MultiplicationNode;
import nl.uva.sc.ql.parser.ast.NotNode;
import nl.uva.sc.ql.parser.ast.IfNode;
import nl.uva.sc.ql.parser.ast.IntegerNode;
import nl.uva.sc.ql.parser.ast.Node;
import nl.uva.sc.ql.parser.ast.OrNode;
import nl.uva.sc.ql.parser.ast.QuestionNode;
import nl.uva.sc.ql.parser.ast.StatementNode;
import nl.uva.sc.ql.parser.ast.StringNode;
import nl.uva.sc.ql.parser.ast.SubtractionNode;
import nl.uva.sc.ql.parser.value.BooleanVal;
import nl.uva.sc.ql.parser.value.IntegerVal;
import nl.uva.sc.ql.parser.value.StringVal;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CreateASTTree extends QLBaseVisitor<Node> {
    
    @Override
    public FormNode visitForm(@NotNull QLParser.FormContext context) {    	
    	BlockNode block = (BlockNode) this.visit(context.block());
    	
    	FormNode node = new FormNode(context.IDENTIFIER().getText(), block);
        node.setLine(context.stop.getLine());
    	return node;
    }
    
    // assignment/identifiers overrides
    @Override
    public QuestionNode visitDeclaration(@NotNull QLParser.DeclarationContext context) {
        String identifier = context.IDENTIFIER().getText();
        String type = context.type().getText();
        String question = context.String().getText();
        
		QuestionNode vn = new QuestionNode(question, identifier, type); 
        vn.setLine(context.stop.getLine());

        return vn;
    }
    
    @Override
    public AssignedQuestionNode visitAssignment(@NotNull QLParser.AssignmentContext context) {        
    	QuestionNode variable = (QuestionNode) this.visit(context.declaration());
        ExpressionNode expression = (ExpressionNode) this.visit(context.expression());
        
        AssignedQuestionNode avn = new AssignedQuestionNode(variable, expression);
        avn.setLine(context.stop.getLine());
        
        return avn;
    }
    
    // unity overrides
    @Override
    public IdentifierNode visitIdentifierUnity(@NotNull QLParser.IdentifierUnityContext context) {
    	IdentifierNode in = new IdentifierNode(context.getText());       
        in.setLine(context.start.getLine());
        return in;
    }

    @Override
    public StringNode visitStringUnity(@NotNull QLParser.StringUnityContext context) {
    	StringVal sv = new StringVal(context.getText());
    	StringNode sn = new StringNode(sv);
        sn.setLine(context.start.getLine());
    	return sn;
    }

    @Override
    public BooleanNode visitBooleanUnity(@NotNull QLParser.BooleanUnityContext context) {
    	BooleanVal bv = new BooleanVal(Boolean.parseBoolean(context.getText()));
    	BooleanNode bn = new BooleanNode(bv);
        bn.setLine(context.start.getLine());
    	return bn;
    }
    
    @Override 
    public IntegerNode visitIntegerUnity(@NotNull QLParser.IntegerUnityContext context) { 
    	IntegerVal iv = new IntegerVal(Integer.parseInt(context.getText()));
    	IntegerNode in = new IntegerNode(iv);
        in.setLine(context.start.getLine());
    	return in;
    }

    // expression overrides
    @Override
    public Node visitParentisisExpression(@NotNull QLParser.ParentisisExpressionContext context) {
        return this.visit(context.expression());
    }
    
    @Override
    public IntegerNode visitMinusExpression(@NotNull QLParser.MinusExpressionContext context) {
    	IntegerVal iv = new IntegerVal(Integer.parseInt(context.INT().getText()) * -1);
    	IntegerNode in = new IntegerNode(iv);
    	in.setLine(context.start.getLine());
    	return in;
    }

    @Override
    public NotNode visitNotExpression(@NotNull QLParser.NotExpressionContext context) {
    	ExpressionNode expression = (ExpressionNode) this.visit(context.expression());
        
        NotNode nn = new NotNode(expression);
        nn.setLine(context.start.getLine());
        return nn;
    }
    
    @Override
    public BinaryExpressionNode visitMultDivExpression(@NotNull QLParser.MultDivExpressionContext context) {
    	ExpressionNode left = (ExpressionNode) this.visit(context.expression(0));
    	ExpressionNode right = (ExpressionNode) this.visit(context.expression(1));
        
        BinaryExpressionNode be = null;
        
        switch (context.op.getText()) {
        	case "*":
        		be = new MultiplicationNode(left, right);
        		break;
        	case "/":
        		be = new DivisionNode(left, right);
        		break;
        }

        be.setLine(context.start.getLine());
    	return be;
    }

    @Override
    public BinaryExpressionNode visitAdditiveExpression(@NotNull QLParser.AdditiveExpressionContext context) {
    	ExpressionNode left = (ExpressionNode) this.visit(context.expression(0));
    	ExpressionNode right = (ExpressionNode) this.visit(context.expression(1));
        
        BinaryExpressionNode be = null;
        
        switch (context.op.getText()) {
        	case "+":
        		be = new AdditionNode(left, right);
        		break;
        	case "-":
        		be = new SubtractionNode(left, right);
        		break;
        }

        be.setLine(context.start.getLine());
    	return be;
    }

    @Override
    public BinaryExpressionNode visitRelationalExpression(@NotNull QLParser.RelationalExpressionContext context) {
    	ExpressionNode left = (ExpressionNode) this.visit(context.expression(0));
    	ExpressionNode right = (ExpressionNode) this.visit(context.expression(1));
        
        BinaryExpressionNode be = null;
        
        switch (context.op.getText()) {
        	case "<":
        		be = new LessThanNode(left, right);
        		break;
        	case ">":
        		be = new GreatThanNode(left, right);
        		break;
			case "<=":
				be = new LessEqualsThanNode(left, right);
				break;
			case ">=":
				be = new GreatEqualsThanNode(left, right);
				break;
			case "==":
				be = new EqualsNode(left, right);
				break;
			case "!=":
				be = new DifferentNode(left, right);
				break;				
		}
        
        be.setLine(context.start.getLine());
        return be;
    }

    @Override
    public BinaryExpressionNode visitAndExpression(@NotNull QLParser.AndExpressionContext context) {
    	ExpressionNode left = (ExpressionNode) this.visit(context.expression(0));
    	ExpressionNode right = (ExpressionNode) this.visit(context.expression(1));
        
        BinaryExpressionNode be = new AndNode(left, right);
        be.setLine(context.start.getLine());
        return be;  
    }

    @Override
    public BinaryExpressionNode visitOrExpression(@NotNull QLParser.OrExpressionContext context) {
    	ExpressionNode left = (ExpressionNode) this.visit(context.expression(0));
    	ExpressionNode right = (ExpressionNode) this.visit(context.expression(1));
        
        BinaryExpressionNode be = new OrNode(left, right);
        be.setLine(context.start.getLine());
        return be;   
    }

    // if override
    @Override
    public IfNode visitIf_stat(@NotNull QLParser.If_statContext context) {
    	BlockNode elseBlock;
		
    	if (context.stat_block() != null) {
			elseBlock = (BlockNode) this.visit(context.stat_block());
		} else {
			elseBlock = null;
		}
    	
    	List<ConditionBlockNode> lcbn = new ArrayList<ConditionBlockNode>();
    	
    	for (QLParser.Condition_blockContext cc : context.condition_block()){
    		ConditionBlockNode cbn = (ConditionBlockNode) this.visit(cc);
    		lcbn.add(cbn);
    	}
    	
    	IfNode ifNode = new IfNode(lcbn, elseBlock);
        return ifNode;
    }
    
    @Override
    public BlockNode visitBlock(@NotNull QLParser.BlockContext context) {        
    	ListStatementsNode lsn = new ListStatementsNode();
    	
    	for (QLParser.StatementContext sc : context.statement()){
    		StatementNode s = (StatementNode) this.visit(sc);
    		lsn.add(s);
    	}
    	
    	return new BlockNode(lsn);
    }

    @Override 
    public ConditionBlockNode visitCondition_block(@NotNull QLParser.Condition_blockContext context) { 
    	ExpressionNode expression = (ExpressionNode) this.visit(context.expression());
    	BlockNode statement = (BlockNode) this.visit(context.stat_block());
    	
    	ConditionBlockNode cbn = new ConditionBlockNode(expression, statement);
    	cbn.setLine(context.start.getLine());
    	return cbn;
    }
    
    @Override
    public BlockNode visitBlockStat_Block(@NotNull QLParser.BlockStat_BlockContext context) { 
    	return (BlockNode) this.visit(context.if_block());
    }
    
    @Override
    public BlockNode visitStatStat_Block(@NotNull QLParser.StatStat_BlockContext context) { 
    	StatementNode statement = (StatementNode) this.visit(context.if_statement());
    	
    	ListStatementsNode lsn = new ListStatementsNode();
    	lsn.add(statement);
    	
    	return new BlockNode(lsn);
    }
    
    @Override
    public BlockNode visitIf_block(@NotNull QLParser.If_blockContext context) { 
    	ListStatementsNode lsn = new ListStatementsNode();
    	
    	for (QLParser.If_statementContext sc : context.if_statement()){
    		StatementNode s = (StatementNode) this.visit(sc);
    		lsn.add(s);
    	}
    	
    	return new BlockNode(lsn);
    }
}
