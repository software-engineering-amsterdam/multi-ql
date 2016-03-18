package org.uva.sea.ql.evaluator;

import javafx.collections.FXCollections;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.stat.block.If;
import org.uva.sea.ql.ast.tree.stat.block.IfElse;
import org.uva.sea.ql.ast.tree.stat.decl.Computed;
import org.uva.sea.ql.ast.tree.stat.decl.Question;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.adt.value.Bool;
import org.uva.sea.ql.adt.value.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class FormEvaluator<FORM,TYPE> extends BaseVisitor<FORM,Void,Value,TYPE,Value,Map<Var, Value>> {

    private final Map<Var,Value> symbolTable;
    private final List<EvaluatedQuestion> evaluatedQuestions;

    public FormEvaluator(Form f) {
        this.evaluatedQuestions = new ArrayList<>();
        this.symbolTable = new SymbolTable(f, FXCollections.observableHashMap()).getSymbolTable();
        f.accept(this, this.symbolTable);
    }

    public FormEvaluator(Form f, Map<Var, Value> symbolTable) {
        this.evaluatedQuestions = new ArrayList<>();
        this.symbolTable = new SymbolTable(f, symbolTable).getSymbolTable();
        f.accept(this, this.symbolTable);
    }

    @Override
    public Void visit(Question stat, Map<Var,Value> symbolTable) {
        evaluatedQuestions.add(genEvaluatedQuestion(stat));
        return null;
    }

    @Override
    public Void visit(Computed stat, Map<Var, Value> symbolTable) {
        evaluatedQuestions.add(genEvaluatedQuestion(stat));
        return null;
    }

    @Override
    public Void visit(If stat, Map<Var, Value> symbolTable) {
        Bool bool = (Bool) stat.getCond().accept(new ExprEvaluator<>(), symbolTable);

        if(bool.getValue() == null){
            return null;
        }

        if(bool.getValue()){
            for(Stat s : stat.getStms())
                s.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public Void visit(IfElse stat, Map<Var, Value> symbolTable) {
        Bool bool = (Bool) stat.getCond().accept(new ExprEvaluator<>(), symbolTable);

        if(bool.getValue() == null){
            return null;
        }

        if(bool.getValue()){
            for(Stat s : stat.getIfStms())
                s.accept(this, symbolTable);
        }
        else {
            for(Stat s : stat.getElseStms())
                s.accept(this, symbolTable);
        }
        return null;
    }

    private EvaluatedQuestion genEvaluatedQuestion(Question question){
        Value value = symbolTable.get(question.getVarname());
        return new EvaluatedQuestion(
                question.getLabel(),
                question.getVarname(),
                question.getType(),
                value,
                false
        );
    }

    private EvaluatedQuestion genEvaluatedQuestion(Computed question){
        Value value = question.getExpr().accept(new ExprEvaluator<>(), symbolTable);
        return new EvaluatedQuestion(
                question.getLabel(),
                question.getVarname(),
                question.getType(),
                value,
                true
        );
    }

    public List<EvaluatedQuestion> getEvaluatedQuestions() {
        return evaluatedQuestions;
    }

    public Map<Var, Value> getSymbolTable() {
        return symbolTable;
    }
}
