package uva.TaxForm.Utils;

import java.util.ArrayList;

import org.antlr.v4.runtime.misc.NotNull;

import uva.ql.antlr4.QLParser;
import uva.ql.antlr4.QLParser.ExpressionContext;

public class ContextUtils {

	public static ArrayList<Object> expressionToInfix( @NotNull QLParser.ExpressionContext ctx, ArrayList<Object> infixList ) {
		
		infixList = (infixList != null)? infixList : new ArrayList<Object>(0);
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			if (ctx.getChild(i).getChildCount() > 1) {
				expressionToInfix((ExpressionContext) ctx.getChild(i), infixList);
			} else {
				infixList.add(ctx.getChild(i).getText());
			}
		}
		return infixList;
	}
}
