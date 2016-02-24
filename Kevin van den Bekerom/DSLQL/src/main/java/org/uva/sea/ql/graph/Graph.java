package org.uva.sea.ql.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private List<Vertex> vertices;
	
	public Graph() {
		vertices = new ArrayList<Vertex>();
	}
	
	public void addEdge(String from, String to) {
		
		for (Vertex v : vertices) {
			if (v.getIdentifier().equals(from)) {
				v.addVertex(to);
			}
		}
		
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	
}
