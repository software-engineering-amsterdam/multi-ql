package org.uva.sea.ql.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
	private Set<Vertex> vertices;
	
	public Graph() {
		vertices = new HashSet<Vertex>();
	}
	
	public void addEdge(String from, String to) {
		
		for (Vertex v : vertices) {
			
			if (v.getIdentifier().equals(from)) {
				v.addNeighbor(to);
				System.out.println("adding vertex " + from + " dependency " + to);
			}
		}
		
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	public Vertex getVertex(String identifier) {
		for (Vertex v : vertices) {
			if (v.getIdentifier().equals(identifier)) {
				return v;
			}
		}
		return null; // vertex does not exist.
	}

	public Set<Vertex> getVertices() {
		return vertices;
	}
	
	
}
