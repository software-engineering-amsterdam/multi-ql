package org.uva.sea.ql.gui.builder;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.uva.sea.ql.ast.tree.atom.Literal;
import org.uva.sea.ql.ast.tree.atom.val.Float;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.atom.val.Bool;
import org.uva.sea.ql.ast.tree.atom.val.Int;
import org.uva.sea.ql.ast.tree.atom.val.Str;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.gui.widget.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 25-2-16.
 */
public class QuestionListBuilder extends BaseVisitor<Void, QuestionWidget, Void, Void, Control, Question> {
    private List<QuestionWidget> uiElements;
    private List<EvaluatedQuestion> questions;
    private ObservableMap<Var, EvaluatedQuestion> symbolTable;

    public QuestionListBuilder(List<EvaluatedQuestion> questions, ObservableMap<Var, EvaluatedQuestion> symbolTable) {
        this.uiElements = new ArrayList<>();
        this.questions = questions;
        this.symbolTable = symbolTable;

        for (EvaluatedQuestion question : this.questions){
            QuestionWidget UiElem = this.visit(question, null);
            uiElements.add(UiElem);
        }
    }

    @Override
    public QuestionWidget visit(Question stat, Question parent) {
        QuestionWidget box = new QuestionWidget(stat);
        box.setHgap(5);
        box.setPadding(new Insets(5,20,5,20));

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        col1.setHalignment(HPos.LEFT);
        col1.setPrefWidth(300);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.RIGHT);
        col2.setMinWidth(200);

        box.getColumnConstraints().addAll(col1, col2);

        Label label = new Label(stat.getLabel());
        label.setWrapText(true);
        box.add(label, 0, 0);

        Primary primary = (Primary) stat.getExpr();
        Parent valueUI = primary.getValue().accept(this, stat);
        box.add(valueUI, 1, 0);

        return box;
    }

    @Override
    public Control visit(Int val, Question parent) {
        NumberFieldWidget f = new NumberFieldWidget(parent, parent.isComputed());
        f.setAlignment(Pos.BASELINE_RIGHT);

        f.setText(val.getValue().toString());

        f.textProperty().addListener((observable, oldValue, newValue) ->
                handleNumberFieldAction(f));
        return f;
    }

    @Override
    public Control visit(Float val, Question parent) {
        MoneyFieldWidget f = new MoneyFieldWidget(parent, parent.isComputed());
        f.setAlignment(Pos.BASELINE_RIGHT);

        f.setText(val.getValue().toString());

        f.textProperty().addListener((observable, oldValue, newValue) ->
                handleMoneyFieldAction(f));
        return f;
    }

    @Override
    public Control visit(Bool val, Question parent) {
        BooleanFieldWidget b = new BooleanFieldWidget(parent, parent.isComputed());
        b.setSelected(val.getValue());
        b.setOnAction(this::handleCheckBoxAction);
        return b;
    }

    @Override
    public Control visit(Str val, Question parent) {
        TextFieldWidget f = new TextFieldWidget(parent, parent.isComputed());
        f.setAlignment(Pos.BASELINE_RIGHT);

        f.setText(val.getValue().toString());

        f.textProperty().addListener((observable, oldValue, newValue) ->
                handleTextFieldAction(f));
        return f;
    }


    /*
     *  Event Handlers
     */

    private void handleNumberFieldAction(NumberFieldWidget f) {
        try {
            f.unSetInvalid();
            Integer value  = Integer.parseInt(f.getText());
            Literal newExpr = new Int(value);
            updateSymbolTable(f.getParentQuestion(), newExpr);
        }
        catch (Exception e) {
            f.setInvalid();
        }
    }

    private void handleMoneyFieldAction(MoneyFieldWidget f) {
        try {
            f.unSetInvalid();
            Double value  = Double.parseDouble(f.getText());
            Literal newExpr = new Float(value);
            updateSymbolTable(f.getParentQuestion(), newExpr);
        }
        catch (Exception e) {
            f.setInvalid();
        }
    }

    private void handleCheckBoxAction(ActionEvent actionEvent) {
        BooleanFieldWidget b = (BooleanFieldWidget) actionEvent.getSource();
        try {
            b.unSetInvalid();
            Literal newExpr = new Bool(b.isSelected());
            updateSymbolTable(b.getParentQuestion(), newExpr);
        }
        catch (Exception e) {
            b.setInvalid();
        }
    }

    private void handleTextFieldAction(TextFieldWidget f) {
        Literal newExpr = new Str(f.getText());
        updateSymbolTable(f.getParentQuestion(), newExpr);
    }

    private void updateSymbolTable(Question changedQuestion, Literal newValue){
        Question update = updateQuestionExpr(changedQuestion, newValue);
        this.symbolTable.put(changedQuestion.getVarname(), update);
    }

    private Question updateQuestionExpr(Question q, Literal atom){
        return new Question(q.getLine(),
                        q.getLabel(),
                        q.getVarname(),
                        q.getType(),
                        new Primary(atom),
                        q.isComputed()
                    );
    }

    public List<QuestionWidget> getUiElements() {
        return uiElements;
    }
}
