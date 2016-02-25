package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.*;

/**
 * Created by roydewildt on 17/02/16.
 */
public class CyclicQuestionsCheck extends BaseVisitor<Void,Void,Void,Void,Void> {
    private Var current;
    private final Map<Node,List<Node>> references = new HashMap<>();

    public CyclicQuestionsCheck(Form f) {
        f.accept(this);
    }

    @Override
    public Void visit(Question stat) {
        // TODO do not use temp var (bad code smell here)
        current = stat.getVarname();

        if(!references.containsKey(stat.getVarname()))
            references.put(stat.getVarname(),new ArrayList<>());
        stat.getExpr().accept(this);

        current = null;

        return null;
    }

    @Override
    public Void visit(Var var) {

        if(references.get(current) != null){
            List<Node> l  = references.get(current);
            l.add(var);
            references.put(current,l);
        }

        return null;
    }

    public List<Node> getCyclics() {
        Set<Node> questions = references.keySet();
        List<Node> cycles = new ArrayList<>();

        for(Node n : questions){
            Node cycleNode = getCyclePathBFS(n);
            if(cycleNode != null)
                cycles.add(cycleNode);
        }
        return cycles;
    }

    public Node getCyclePathBFS(Node n){

        Queue<Node> queue = new LinkedList<>();
        queue.add(n);

        List<Node> visited = new ArrayList<>();

        while(!queue.isEmpty()){
            Node x = queue.remove();
            if(visited.contains(x)){
                return n;
            }
            else{
                visited.add(x);
                queue.addAll(references.get(x));
            }
        }

        return null;
    }

}
