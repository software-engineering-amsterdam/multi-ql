package ql2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ql2.ast.CalculatedQuestion;
import ql2.ast.InputQuestion;
import ql2.ast.Question;
import ql2.ast.expression.IdentityExpr;
import ql2.conflict.CyclicDependancy;

/**
 * Visitor checks for Questions that depend on eachother.
 * @author felixbarten
 *
 * @param <T>
 */
public class DependencyVisitor<T> extends BaseVisitor<T> {

	private Context context; 
	private List<Dependency> dependencies; 
	private CalculatedQuestion current;
	
	private class Dependency {
		private CalculatedQuestion source;
		private Set<String> destinations; // no duplicates.

		public Dependency(CalculatedQuestion src, String dest) {
			destinations = new HashSet<String>();
			destinations.add(dest);
			this.source = src;
		}
		
		public Dependency(CalculatedQuestion src, Set<String> destinations) {
			this.source = src;
			this.destinations = destinations;
		}
		
		public void addDependency(String id) {
			destinations.add(id);
		}

		public CalculatedQuestion getSource() {
			return source;
		}		
		
		public Set<String> getDependencies() {
			return destinations;
		}
		
	}
	
	public DependencyVisitor(Context ctx) {
		//dependencies = new HashMap<String, String>();
		dependencies = new ArrayList<Dependency>();
		this.context = ctx;
	}

	@Override
	public T visit(CalculatedQuestion node) {
		current = node;
		
		node.getCalculation().accept(this);
		
		current = null; // reset current state to null to prevent ID expressions from creating bad dependencies
		return null;
	}

	@Override
	public T visit(IdentityExpr node) {
		if (current != null) {
			addDependency(node.getID());
		}
		return null;
	}
	
	private void addDependency(String id) {
		for (Dependency d : dependencies) { 
			if (d.getSource() == current) {
				d.addDependency(id);
				return;
			}
		}
		dependencies.add(new Dependency(current, id));
	}
	
	void gatherDeps() {
		
	}
	
	public void process() {
		for (Dependency d : dependencies) {
			for (String id : d.getDependencies()) {
				
				searchDeps(d, id);
				
			}
			System.out.println("Finished dependency checking");
		}
		
	}

	private void searchDeps(Dependency dep, String id) {
		for (Dependency d : dependencies) {
			String srcID= d.getSource().getQuestionID();
			
			if (srcID.equals(id)) {
				context.addConflict(new CyclicDependancy(dep.getSource(), id));
			}
		}
		
	}

	public Context getContext() {
		return context;
	}
}
