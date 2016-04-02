package uva.ql.gui.fields.documentfilters;

import uva.ql.ast.variables.Variable;

public class MoneyFilter<T> extends Filter {

	public MoneyFilter(Variable<T> var, String regex) {
		super(var, regex);
	}
	
}
