package ql2;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ql2.parser.generated.Ql2Parser.AndContext;
import ql2.parser.generated.Ql2Parser.BinaryexprContext;
import ql2.parser.generated.Ql2Parser.BlockContext;
import ql2.parser.generated.Ql2Parser.CalculatedquestionContext;
import ql2.parser.generated.Ql2Parser.ConditionContext;
import ql2.parser.generated.Ql2Parser.ConditionsContext;
import ql2.parser.generated.Ql2Parser.EqContext;
import ql2.parser.generated.Ql2Parser.ExprContext;
import ql2.parser.generated.Ql2Parser.FormContext;
import ql2.parser.generated.Ql2Parser.FormnameContext;
import ql2.parser.generated.Ql2Parser.FormsContext;
import ql2.parser.generated.Ql2Parser.GeContext;
import ql2.parser.generated.Ql2Parser.GteContext;
import ql2.parser.generated.Ql2Parser.IfelseifstatementContext;
import ql2.parser.generated.Ql2Parser.IfelsestatementContext;
import ql2.parser.generated.Ql2Parser.IfstatementContext;
import ql2.parser.generated.Ql2Parser.InputquestionContext;
import ql2.parser.generated.Ql2Parser.LtContext;
import ql2.parser.generated.Ql2Parser.LteContext;
import ql2.parser.generated.Ql2Parser.NameContext;
import ql2.parser.generated.Ql2Parser.NegexprContext;
import ql2.parser.generated.Ql2Parser.NeqContext;
import ql2.parser.generated.Ql2Parser.NotexprContext;
import ql2.parser.generated.Ql2Parser.OrContext;
import ql2.parser.generated.Ql2Parser.PosexprContext;
import ql2.parser.generated.Ql2Parser.QuestionContext;
import ql2.parser.generated.Ql2Parser.QuestionnaireContext;
import ql2.parser.generated.Ql2Parser.QuestionnameContext;
import ql2.parser.generated.Ql2Parser.QuestionsContext;
import ql2.parser.generated.Ql2Parser.QuestiontextContext;
import ql2.parser.generated.Ql2Parser.QuestiontypeContext;
import ql2.parser.generated.Ql2Parser.StatementsContext;
import ql2.parser.generated.Ql2Parser.StatementzContext;
import ql2.parser.generated.Ql2Parser.UnaryexprContext;
import ql2.parser.generated.Ql2Parser.ValueContext;
import ql2.parser.generated.Ql2Parser.WhilestatementContext;

public class Ql2SemanticVisitor<T> extends BaseVisitor<T> {

	
}
