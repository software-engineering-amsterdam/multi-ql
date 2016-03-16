package nl.uva.sc.ql.parser;

public class CyclicDependency {

	private String identifier;
	private boolean isCyclic;
	
	public CyclicDependency() {
	}
	
	public void init(String identifier){
		this.identifier = identifier;
		this.isCyclic = false;
	}
	
	public void analyseIfCycle(String identifier){
		if (identifier.equals(this.identifier)){
			this.isCyclic = true;
		}
	}

	public boolean isCyclic(){
		return isCyclic;
	}
}
