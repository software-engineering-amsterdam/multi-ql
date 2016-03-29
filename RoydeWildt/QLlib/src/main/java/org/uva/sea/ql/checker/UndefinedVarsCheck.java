package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.evaluator.SymbolTable;
import org.uva.sea.ql.evaluator.adt.value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roy on 5-2-16.
 */
public class UndefinedVarsCheck extends BaseVisitor<Void,Void,Void,Void,Void,Map<Var,Value>> {

    private List<Node> undefined;

    public UndefinedVarsCheck(Form f) {
        this.undefined = new ArrayList<>();
        f.accept(this, new SymbolTable(f).getSymbolTable());
    }


    @Override
    public Void visit(Var var, Map<Var,Value> symbolTable) {
        if (!symbolTable.containsKey(var)) {
            undefined.add(var);
        }
        return null;
    }

    public List<Message> undefinedChecker(){
        List<Message> messages = new ArrayList<>();
        List<Node> undefined = getUndefined();

        for(Node n : undefined){
            StringBuilder sb = new StringBuilder();
            sb.append("Variable ");
            sb.append(((Var) n).toString());
            sb.append(" is undefined");
            messages.add(new ErrorMessage(sb.toString(), n));
        }
        return messages;
    }


    public List<Node> getUndefined() {
        return undefined;
    }
}
