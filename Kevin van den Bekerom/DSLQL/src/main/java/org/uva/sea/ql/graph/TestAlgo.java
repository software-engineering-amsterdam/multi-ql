package org.uva.sea.ql.graph;

import java.util.ArrayList;
import java.util.List;

public class TestAlgo {
	public static void main(String [] args) throws Exception {
		Graph g = buildTestGraph();
		System.out.println("Testing algo with startVertex 5");
		Vertex start = g.getVertex("5");
		for (String v : start.getNeighbors()) {
			System.out.println("For vertex : " + v + " outcome is:  " + algo(start, new ArrayList<String>(), g.getVertex(v), g));
		}
	}
	
	static Graph buildTestGraph() {
		Graph g = new Graph();
		for (int i = 2; i < 7; i++) {
		//	if (i != 4){
				Vertex v = new Vertex(Integer.toString(i));
				g.addVertex(v);
		//	}
		}
		g.addEdge("6", "4");
		g.addEdge("4", "2");
		g.addEdge("5", "4");
		g.addEdge("5", "6");
		g.addEdge("3", "4");
		g.addEdge("3", "6");	
		g.addEdge("2", "3");
		return g;
	}
	
	static boolean algo(Vertex start, List<String> visited, Vertex next, Graph g) {
		if (next == null) {
			return false;
		} System.out.println("Vertex visiting: " + next.getIdentifier());
		if (next.isTerminal()) {
			return true;
		}
		if (visited.contains(next.getIdentifier())) {
			System.out.println("Cycle: ");
			for (String v: visited) {
				System.out.println(v + " , ");
			}
			return false;
		}
		visited.add(next.getIdentifier());
		
		for (String neighborID : next.getNeighbors()) {
			return algo(start, visited, g.getVertex(neighborID), g);
		}	
		
		assert false;
		return true; // 
	}
	
}
