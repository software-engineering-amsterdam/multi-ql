package org.uva.sea.ql.gui.view.preview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.evaluator.FormEvaluator;
import org.uva.sea.ql.gui.builder.QuestionListBuilder;
import org.uva.sea.ql.gui.builder.QuestionUIElement;
import org.uva.sea.ql.gui.view.editor.EditorView;

import java.util.List;

/**
 * Created by roy on 29-2-16.
 */
public class PreviewView {
    private GridPane rootPane;

    public PreviewView(EditorView editor){
        rootPane = new GridPane();
        rootPane.setPrefSize(500,600);
        rootPane.setVgap(10);
        rootPane.setPadding(new Insets(0, 0, 0, 0));
        rootPane.setAlignment(Pos.TOP_CENTER);

        editor.addEditorChangedEventListener(editorChangedEvent -> updatePreview(editorChangedEvent.getSource()));
    }

    private void updatePreview(Object source) {
        Form form = (Form) source;
        FormEvaluator<Void,Void> formEvaluator = new FormEvaluator<>(form);
        QuestionListBuilder visitor = new QuestionListBuilder(formEvaluator.getEvaluatedQuestions(), formEvaluator.getSymbolTable());

        List<QuestionUIElement> UIElements = visitor.getUiElements();

        rootPane.getChildren().clear();

        Label label = new Label(form.getId());
        label.setFont(new Font("Arial", 30));
        label.setPadding(new Insets(10,0,5,20));
        rootPane.add(label, 0, 0, 2, 1);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(UIElements);
        rootPane.add(vbox, 0, 1);
    }

    /*
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
    */

    public GridPane getRootPane() {
        return rootPane;
    }

}
