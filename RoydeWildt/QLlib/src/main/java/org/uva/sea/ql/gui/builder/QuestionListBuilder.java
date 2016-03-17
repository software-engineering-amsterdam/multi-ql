package org.uva.sea.ql.gui.builder;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.evaluator.value.Bool;
import org.uva.sea.ql.evaluator.value.String;
import org.uva.sea.ql.evaluator.value.Value;
import org.uva.sea.ql.evaluator.value.numeric.Double;
import org.uva.sea.ql.evaluator.value.numeric.Int;
import org.uva.sea.ql.evaluator.value.visitor.ValueVisitor;
import org.uva.sea.ql.gui.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by roy on 25-2-16.
 */
public class QuestionListBuilder implements ValueVisitor<Parent,EvaluatedQuestion> {
    private List<QuestionWidget> uiElements;
    private List<EvaluatedQuestion> questions;
    private Map<Var, Value> symbolTable;

    public QuestionListBuilder(List<EvaluatedQuestion> questions, Map<Var, Value> symbolTable) {
        this.uiElements = new ArrayList<>();
        this.questions = questions;
        this.symbolTable = symbolTable;

        for (EvaluatedQuestion question : this.questions){
            QuestionWidget UiElem = genQuestionUI(question);
            uiElements.add(UiElem);
        }
    }

    public QuestionWidget genQuestionUI(EvaluatedQuestion q) {
        QuestionWidget box = new QuestionWidget(q);
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

        Label label = new Label(q.getLabel());
        label.setWrapText(true);
        box.add(label, 0, 0);

        Value value = q.getValue();
        Parent valueUI = value.accept(this, q);
        box.add(valueUI, 1, 0);

        return box;
    }

    @Override
    public Control visit(Int val, EvaluatedQuestion parent) {
        NumberFieldWidget f = new NumberFieldWidget(parent, parent.isComputed());
        f.setAlignment(Pos.BASELINE_RIGHT);

        f.setText(val.getValue().toString());

        f.textProperty().addListener((observable, oldValue, newValue) ->
                handleNumberFieldAction(f));
        return f;
    }

    @Override
    public Control visit(Double val, EvaluatedQuestion parent) {
        MoneyFieldWidget f = new MoneyFieldWidget(parent, parent.isComputed());
        f.setAlignment(Pos.BASELINE_RIGHT);

        f.setText(val.getValue().toString());

        f.textProperty().addListener((observable, oldValue, newValue) ->
                handleMoneyFieldAction(f));
        return f;
    }

    @Override
    public Control visit(Bool val, EvaluatedQuestion parent) {
        BooleanFieldWidget b = new BooleanFieldWidget(parent, parent.isComputed());
        b.setSelected(val.getValue());
        b.setOnAction(this::handleCheckBoxAction);
        return b;
    }

    @Override
    public Control visit(String val, EvaluatedQuestion parent) {
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
            Value newExpr = new Int(value);
            updateSymbolTable(f.getParentQuestion(), newExpr);
        }
        catch (Exception e) {
            f.setInvalid();
            removeFromSymbolTable(f.getParentQuestion());
        }
    }

    private void handleMoneyFieldAction(MoneyFieldWidget f) {
        try {
            f.unSetInvalid();
            double value  = java.lang.Double.parseDouble(f.getText());
            Value newExpr = new Double(value);
            updateSymbolTable(f.getParentQuestion(), newExpr);
        }
        catch (Exception e) {
            f.setInvalid();
            removeFromSymbolTable(f.getParentQuestion());
        }
    }

    private void handleCheckBoxAction(ActionEvent actionEvent) {
        BooleanFieldWidget b = (BooleanFieldWidget) actionEvent.getSource();
        try {
            b.unSetInvalid();
            Value newExpr = new Bool(b.isSelected());
            updateSymbolTable(b.getParentQuestion(), newExpr);
        }
        catch (Exception e) {
            b.setInvalid();
            removeFromSymbolTable(b.getParentQuestion());
        }
    }

    private void handleTextFieldAction(TextFieldWidget f) {
        java.lang.String fieldvalue = f.getText();
        if(isLetterString(fieldvalue)){
            f.unSetInvalid();
            Value newExpr = new String(f.getText());
            updateSymbolTable(f.getParentQuestion(), newExpr);
        }
        else{
            f.setInvalid();
            removeFromSymbolTable(f.getParentQuestion());
        }
    }

    private void updateSymbolTable(EvaluatedQuestion changedQuestion, Value newValue){
        this.symbolTable.put(changedQuestion.getVarname(), newValue);
    }

    private void removeFromSymbolTable(EvaluatedQuestion changedQuestion){
        this.symbolTable.remove(changedQuestion.getVarname());
    }
    public boolean isLetterString(java.lang.String name) {
        return name.chars().allMatch(Character::isLetter);
    }

    public List<QuestionWidget> getUiElements() {
        return uiElements;
    }
}
