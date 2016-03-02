package semanticAnalysis.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorWarningList {
	private final List<Error> errorList;
	private final List<Warning> warningList;
	
	public ErrorWarningList() {
		errorList = new ArrayList<Error>();
		warningList = new ArrayList<Warning>();
	}
	
	public List<Error> getErrorList() {
		return this.errorList;
	}
	
	public List<Warning> getWarningList() {
		return this.warningList;
	}
	
	public void addError(String e) {
		this.errorList.add(new Error(e));
	}
	
	public void addWarning(String warningMessage) {
		this.warningList.add(new Warning(warningMessage));
	}
	
	public boolean containsError() {
		return !this.errorList.isEmpty() || !this.warningList.isEmpty();
	}
	
	
}