package uva.ql.typechecker.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTypeChecker {

	protected final List<ErrorWarning> errorMessages = new ArrayList<ErrorWarning>(0);
	
	
	public List<ErrorWarning> getErrors() {
		return this.errorMessages;
	}
}
