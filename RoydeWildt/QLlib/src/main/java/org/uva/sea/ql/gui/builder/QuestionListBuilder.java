package org.uva.sea.ql.gui.builder;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.adt.value.Bool;
import org.uva.sea.ql.adt.value.Str;
import org.uva.sea.ql.adt.value.Value;
import org.uva.sea.ql.adt.value.numeric.Double;
import org.uva.sea.ql.adt.value.numeric.Int;
import org.uva.sea.ql.adt.visitor.ValueVisitor;
import org.uva.sea.ql.gui.widget.*;
import org.uva.sea.ql.gui.widget.binary.BooleanWidget;
import org.uva.sea.ql.gui.widget.factory.DefaultWidgets;
import org.uva.sea.ql.gui.widget.factory.WidgetFactory;
import org.uva.sea.ql.gui.widget.number.NumberWidget;
import org.uva.sea.ql.gui.widget.string.StringWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by roy on 25-2-16.
 */
public class QuestionListBuilder implements ValueVisitor<Widget,EvaluatedQuestion> {
    private List<QuestionUIElement> uiElements;
    private WidgetFactory widgetFactory;
    private Map<Var, Value> symbolTable;

    public QuestionListBuilder(List<EvaluatedQuestion> questions, Map<Var, Value> symbolTable) {
        this.uiElements = new ArrayList<>();
        this.widgetFactory = new DefaultWidgets();
        this.symbolTable = symbolTable;

        for (EvaluatedQuestion question : questions){
            QuestionUIElement UiElem = genQuestionUI(question);
            uiElements.add(UiElem);
        }
    }

    private QuestionUIElement genQuestionUI(EvaluatedQuestion q) {
        QuestionUIElement box = new QuestionUIElement(q.getVarname());
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
        Widget valueWidget = value.accept(this, q);
        box.add(valueWidget.getUiElement(), 1, 0);

        return box;
    }

    @Override
    public Widget visit(Int val, EvaluatedQuestion parent) {
        NumberWidget widget = widgetFactory.getNumberWidget(parent);
        widget.setValue(val.getValue());
        widget.addListener((observable, oldValue, newValue) ->
                handleNumberWidgetAction(widget));
        return widget;
    }

    @Override
    public Widget visit(Double val, EvaluatedQuestion parent) {
        NumberWidget widget = widgetFactory.getNumberWidget(parent);
        widget.setValue(val.getValue());
        widget.addListener((observable, oldValue, newValue) ->
                handleNumberWidgetAction(widget));
        return widget;
    }

    @Override
    public Widget visit(Bool val, EvaluatedQuestion parent) {
        BooleanWidget widget = widgetFactory.getBooleanWidget(parent);
        widget.setValue(val.getValue());
        widget.addListener((observable, oldValue, newValue) ->
                handleBooleanWidgetAction(widget));
        return widget;
    }

    @Override
    public Widget visit(Str val, EvaluatedQuestion parent) {
        StringWidget widget = widgetFactory.getStringWidget(parent);
        widget.setValue(val.getValue());
        widget.addListener((observable, oldValue, newValue) ->
                handleStringAction(widget));
        return widget;
    }


    /*
     *  Event Handlers
     */

    private void handleNumberWidgetAction(NumberWidget f) {
        try {
            f.unSetInvalid();
            Value newExpr = new Int(Integer.parseInt(f.getValue()));
            updateSymbolTable(f.getParentQuestion(), newExpr);
        }
        catch (Exception e) {
            f.setInvalid();
            removeFromSymbolTable(f.getParentQuestion());
        }
    }

    private void handleBooleanWidgetAction(BooleanWidget f) {
        try {
            f.unSetInvalid();
            Value newExpr = new Bool(f.isSelected());
            updateSymbolTable(f.getParentQuestion(), newExpr);
        }
        catch (Exception e) {
            f.setInvalid();
            removeFromSymbolTable(f.getParentQuestion());
        }
    }

    private void handleStringAction(StringWidget f) {
        java.lang.String fieldvalue = f.getText();
        if(isLetterString(fieldvalue)){
            f.unSetInvalid();
            Value newExpr = new Str(f.getText());
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
    private boolean isLetterString(java.lang.String name) {
        return name.chars().allMatch(Character::isLetter);
    }

    public List<QuestionUIElement> getUiElements() {
        return uiElements;
    }
}
