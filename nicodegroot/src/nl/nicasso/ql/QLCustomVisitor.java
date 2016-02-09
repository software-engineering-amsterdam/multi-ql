package nl.nicasso.ql;

import org.uva.sea.ql.parser.antlr.QLBaseVisitor;
import org.uva.sea.ql.parser.antlr.QLParser;

public class QLCustomVisitor extends QLBaseVisitor<String> {

	@Override 
	public String visitForm(QLParser.FormContext ctx) { 
		System.out.println("ENTER FORM: "+ctx.getText());
		return visitChildren(ctx);
	}
}
