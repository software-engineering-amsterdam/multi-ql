package org.uva.sea.ql.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
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

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 25-2-16.
 */
public class GuiVisitor extends BaseVisitor<Void, Parent, Void, Void, Parent, Question> {
    private GridPane box;
    private List<Parent> uiElements;
    private List<Question> questions;
    private ObservableMap<Var, Question> symbolTable;

    public GuiVisitor(List<Question> questions, ObservableMap<Var, Question> symbolTable) {
        this.box = new GridPane();
        this.uiElements = new ArrayList<>();
        this.questions = questions;
        this.symbolTable = symbolTable;

        for (Question question : this.questions){
            Parent UiElem = this.visit(question, null);
            uiElements.add(UiElem);
        }
    }

    @Override
    public Parent visit(Question stat, Question parent) {
        GridPane box = new GridPane();
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
    public Parent visit(Int val, Question parent) {
        MoneyFieldWidget f = new MoneyFieldWidget(parent, parent.isComputed());
        f.setAlignment(Pos.BASELINE_RIGHT);
        f.setText(val.getValue().toString());
        f.textProperty().addListener((observable, oldValue, newValue) ->
                handleMoneyFieldAction(f));
        return f;
    }

    @Override
    public Parent visit(Bool val, Question parent) {
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
                false);
        return update;
    }

    public List<Parent> getUiElements() {
        return uiElements;
    }
}
