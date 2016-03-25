package org.uva.sea.ql.gui.view;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.evaluator.FormEvaluator;
import org.uva.sea.ql.adt.value.Value;
import org.uva.sea.ql.gui.builder.QuestionListBuilder;
import org.uva.sea.ql.gui.builder.QuestionUIElement;

import java.util.List;
import java.util.Map;

/**
 * Created by roy on 29-2-16.
 */
public class PreviewView {
    private Form form;
    private GridPane rootPane;
    private VBox vbox;

    public PreviewView(Form form) {
        this.form = form;

        FormEvaluator<Void,Void> evaluator = new FormEvaluator<>(this.form);
        QuestionListBuilder visitor = new QuestionListBuilder(evaluator.getEvaluatedQuestions(), evaluator.getSymbolTable());

        addFormListener(evaluator);

        List<QuestionUIElement> UIElements = visitor.getUiElements();
        this.rootPane = buildFormUI(form.getId(), UIElements);
    }

    private GridPane buildFormUI(String name, List<QuestionUIElement> questions){
        GridPane formUI = new GridPane();
        formUI.setPrefSize(500,600);
        formUI.setVgap(10);
        formUI.setPadding(new Insets(0, 0, 0, 0));
        formUI.setAlignment(Pos.TOP_CENTER);

        Label label = new Label(name);
        label.setFont(new Font("Arial", 30));
        label.setPadding(new Insets(10,0,5,20));
        formUI.add(label, 0, 0, 2, 1);

        vbox = new VBox();
        vbox.getChildren().addAll(questions);
        formUI.add(vbox, 0, 1);

        return formUI;
    }

    private void addFormListener(FormEvaluator<Void,Void> evaluator) {
        ObservableMap<Var,Value> symbolTable = (ObservableMap<Var, Value>) evaluator.getSymbolTable();
        symbolTable.addListener((MapChangeListener<Var,Value>) c -> {
            Var changedQuestion = c.getKey();

            FormEvaluator<Void,Void> fe = new FormEvaluator<>(this.form, (Map<Var, Value>) c.getMap());

            QuestionListBuilder visitor = new QuestionListBuilder(fe.getEvaluatedQuestions(),  fe.getSymbolTable());
            List<QuestionUIElement> UIElements = visitor.getUiElements();
            updateFormUI(changedQuestion, UIElements);
        });
    }

    private void updateFormUI(Var changedQuestion, List<QuestionUIElement> UIElements){
        QuestionUIElement changedQuestionUI = null;
        for(QuestionUIElement questionUI : UIElements){
            if(questionUI.getVarName().equals(changedQuestion)){
                changedQuestionUI = questionUI;
            }
        }

        this.vbox.getChildren().clear();

        for(QuestionUIElement questionUI : UIElements){
            if(questionUI.getVarName().equals(changedQuestion)){
                this.vbox.getChildren().add(changedQuestionUI);
            }
            else {
                this.vbox.getChildren().add(questionUI);
            }
        }
    }

    public GridPane getRootPane() {
        return rootPane;
    }

}
