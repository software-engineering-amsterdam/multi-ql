package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.graph.Graph;
import org.uva.sea.ql.graph.Vertex;

public class TestDependencyAlgo {
	
	List<String> visited = new ArrayList<String>();
	List<String> errors = new ArrayList<String>();
	
	public void collectErrors(List<Vertex> startVertices, Graph g) {
		for (Vertex v : startVertices) {
			System.out.println("I am now examining vertex: " + v.getIdentifier());
			if (visited.contains(v)){
				continue;
			}
			DFSGraph(v, visited, new ArrayList<String>(), errors, g);
		}
		

		for (String error: errors) {
			System.out.println(error);
		}
	}
	
	private void DFSGraph(Vertex start, List<String> vis, List<String> path, List<String> errs, Graph g) {
		visited.add(start.getIdentifier());
		path.add(start.getIdentifier());
		
		System.out.println("path");
		for (String p : path) {
			System.out.println(" " + p);
		}
		
		for (String neighbor : start.getNeighbors()) {
			if (path.contains(neighbor)) { // cycle;
				String message = "cycle detected! ";
				for (String p : path) {
					message += p + " -> ";
				}
				message += neighbor;
				
				System.out.println(message);
				errors.add(message);
			}

			if (visited.contains(neighbor)) {
				continue;
			}
			
			DFSGraph(g.getVertex(neighbor), visited, path, errors, g);
		}
		path.remove(start.getIdentifier());
		
	}
}
