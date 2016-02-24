package org.uva.sea.ql.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	private final String identifier; 
	private List<String> neighbors;
	private final boolean terminal;
	
	public Vertex(String identifier, boolean terminal) {
		neighbors = new ArrayList<String>();
		this.identifier = identifier;
		this.terminal = terminal;
	}
	
	public void addVertex(String vID) {
		neighbors.add(vID);
	}
	
	public String getIdentifier() {
		return this.identifier;
	}

}
