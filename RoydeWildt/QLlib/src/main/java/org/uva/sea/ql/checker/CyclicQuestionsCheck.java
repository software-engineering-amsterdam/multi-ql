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
public class CyclicQuestionsCheck extends BaseVisitor<Void,Var> {
    private Node root;
    private final Map<Node,List<Node>> references = new HashMap<>();

    public CyclicQuestionsCheck(Form f) {
        f.accept(this,null);
    }

    @Override
    public Void visit(Question stat, Var env) {
        if(root == null)
            root = stat.getVarname();

        if(!references.containsKey(stat.getVarname()))
            references.put(stat.getVarname(),new ArrayList<>());
        stat.getExpr().accept(this,stat.getVarname());
        return null;
    }

    @Override
    public Void visit(Var var, Var env) {

        if(references.get(env) != null){
            List<Node> l  = references.get(env);
            l.add(var);
            references.put(env,l);
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
