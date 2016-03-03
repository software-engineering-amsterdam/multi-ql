package org.uva.sea.ql.gui;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.gui.widget.CheckBoxWidget;
import org.uva.sea.ql.gui.widget.MoneyFieldWidget;
import org.uva.sea.ql.gui.widget.QuestionWidget;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 25-2-16.
 */
public class QuestionUIBuilder extends BaseVisitor<Void, QuestionWidget, Void, Void, Control, Question> {
    private GridPane box;
    private List<QuestionWidget> uiElements;
    private List<Question> questions;
    private ObservableMap<Var, Question> symbolTable;

    public QuestionUIBuilder(List<Question> questions, ObservableMap<Var, Question> symbolTable) {
        this.box = new GridPane();
        this.uiElements = new ArrayList<>();
        this.questions = questions;
        this.symbolTable = symbolTable;

        for (Question question : this.questions){
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
        MoneyFieldWidget f = new MoneyFieldWidget(parent, parent.isComputed());
        f.setAlignment(Pos.BASELINE_RIGHT);
        f.setText(val.getValue().toString());
        f.textProperty().addListener((observable, oldValue, newValue) ->
                handleMoneyFieldAction(f));
        return f;
    }

    @Override
    public Control visit(Bool val, Question parent) {
        CheckBoxWidget b = new CheckBoxWidget(parent, parent.isComputed());
        b.setSelected(val.getValue());
        b.setOnAction(this::handleCheckBoxAction);
        return b;
    }

    private void handleMoneyFieldAction(MoneyFieldWidget f) {
        Question parent = f.getParentQuestion();
        Expr newExpr = new Primary(new Int(
                parent.getExpr().getLine(),
                f.getText()));
        Question update = updateQuestionExpr(parent, newExpr);
        this.symbolTable.put(parent.getVarname(), update);
    }

    private void handleCheckBoxAction(ActionEvent actionEvent) {
        CheckBoxWidget b = (CheckBoxWidget) actionEvent.getSource();
        Question parent = b.getParentQuestion();
        Expr newExpr = new Primary(new Bool(
                        parent.getLine(),
                        b.isSelected()));
        Question update = updateQuestionExpr(parent, newExpr);
        this.symbolTable.put(parent.getVarname(), update);
    }

    private Question updateQuestionExpr(Question q, Expr e){
        Question update = new Question(q.getLine(),
                q.getLabel(),
                q.getVarname(),
                q.getType(),
                e,
                q.isComputed());
        return update;
    }

    public List<QuestionWidget> getUiElements() {
        return uiElements;
    }
}
