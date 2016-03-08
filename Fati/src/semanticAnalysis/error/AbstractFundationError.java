package semanticAnalysis.error;

public abstract class AbstractFundationError {
private final String fr;
	
	public AbstractFundationError(String fr) {
		this.fr = fr;
	}
	
	public String getFr() {
		return this.fr;
	}
	
	public String toString() {
		return fr;
	}

}
