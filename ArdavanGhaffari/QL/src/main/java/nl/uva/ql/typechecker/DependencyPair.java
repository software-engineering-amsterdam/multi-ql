package nl.uva.ql.typechecker;

public class DependencyPair {
	private final String firstIdentifier;
	private final String secondIdentifier;
	
	public DependencyPair(String firstIdentifier, String secondIdentifier){
		this.firstIdentifier = firstIdentifier;
		this.secondIdentifier = secondIdentifier;
	}
	
	public String getFirstIdentifier(){
		return this.firstIdentifier;
	}
	
	public String getSecondIdentifier(){
		return this.secondIdentifier;
	}
}
