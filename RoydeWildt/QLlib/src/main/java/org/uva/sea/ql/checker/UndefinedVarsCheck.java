package org.uva.sea.ql.checker;

import javafx.collections.FXCollections;
import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.ast.visitor.DeclVisitor;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by roy on 5-2-16.
 */
public class UndefinedVarsCheck extends BaseVisitor<Void,Void,Void,Void,Void,Void> {

    private final List<Node> undefined;
    private final Map<Var,Expr> decls;

    public UndefinedVarsCheck(Form f) {
        undefined = new ArrayList<>();

        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, FXCollections.observableHashMap());
        this.decls = dv.getDecls();

        f.accept(this, null);
    }


    @Override
    public Void visit(Var var, Void context) {
        if (!decls.containsKey(var)) {
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
            sb.append(((Var) n).getName());
            sb.append(" is undefined");
            messages.add(new ErrorMessage(sb.toString(), n));
        }
        return messages;
    }


    public List<Node> getUndefined() {
        return undefined;
    }
}
