package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;

import java.util.*;

/**
 * Created by roydewildt on 17/02/16.
 */
public class CyclicQuestionsCheck extends BaseVisitor<Void,Void,Void,Void,Void,Question> {
    private final Map<Node,List<Node>> references = new HashMap<>();

    public CyclicQuestionsCheck(Form f) {
        f.accept(this, null);
    }

    @Override
    public Void visit(Question stat, Question context) {

        if(!references.containsKey(stat.getVarname()))
            references.put(stat.getVarname(),new ArrayList<>());
        stat.getExpr().accept(this, stat);

        return null;
    }

    @Override
    public Void visit(Var var, Question context) {

        Var v = null;

        if(context != null)
            v = context.getVarname();

        if(references.get(v) != null){
            List<Node> l  = references.get(v);
            l.add(var);
            references.put(v,l);
        }

        return null;
    }

    private List<Node> getCycles() {
        Set<Node> questions = references.keySet();
        List<Node> cycles = new ArrayList<>();

        for(Node n : questions){
            Node cycleNode = getCyclePathBFS(n);
            if(cycleNode != null)
                cycles.add(cycleNode);
        }
        return cycles;
    }

    private Node getCyclePathBFS(Node n){

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

    public List<Message> cyclicQuestionChecker(){
        List<Message> messages = new ArrayList<>();
        List<Node> cyclics = getCycles();

        for(Node n : cyclics){
            Var v = (Var) n;
            StringBuilder sb = new StringBuilder();
            sb.append("EvaluatedQuestion ");
            sb.append(v.toString());
            sb.append(" contains cyclic dependencies");

            messages.add(new ErrorMessage(sb.toString(),v));
        }
        return messages;
    }

}
