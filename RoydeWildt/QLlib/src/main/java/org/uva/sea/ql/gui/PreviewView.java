package org.uva.sea.ql.gui;

import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.var.Var;
import org.uva.sea.ql.evaluator.FormEvaluator;
import org.uva.sea.ql.gui.widget.QuestionWidget;

import java.util.*;

/**
 * Created by roy on 29-2-16.
 */
public class PreviewView {
    private Form form;
    private GridPane rootPane;
    private VBox vbox;

    public PreviewView(Form form) {
        this.form = form;

        FormEvaluator evaluator = new FormEvaluator(this.form);
        List<Question> questions = evaluator.getQuestions();
        QuestionsView visitor = new QuestionsView(questions, evaluator.getSymbolTable());

        addFormListener(evaluator);

        List<QuestionWidget> UIElements = visitor.getUiElements();
        this.rootPane = buildFormUI(form.getId(), UIElements);
    }

    private GridPane buildFormUI(String name, List<QuestionWidget> questions){
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

    private void addFormListener(FormEvaluator evaluator) {

        evaluator.getSymbolTable().addListener((MapChangeListener<Var,Question>) c -> {
            //System.out.println("Changed " + c.toString());
            Question changedQuestion = c.getValueAdded();

            FormEvaluator fe = new FormEvaluator(this.form, (ObservableMap<Var, Question>) c.getMap());
            List<Question> questions = fe.getQuestions();

            QuestionsView visitor = new QuestionsView(questions, fe.getSymbolTable());
            List<QuestionWidget> UIElements = visitor.getUiElements();
            updateFormUI(changedQuestion, UIElements);
        });
    }

    public void updateFormUI(Question changedQuestion, List<QuestionWidget> UIElements){
        QuestionWidget currentQuestionWidget = null;

        //get question ui-element of changed question
        for(Node n: this.vbox.getChildren()){
            if (n instanceof QuestionWidget){
                if(isQuestion((QuestionWidget) n, changedQuestion)){
                    currentQuestionWidget = (QuestionWidget) n;
                }
            }
        }

        this.vbox.getChildren().clear();

        //add ui-elements except for the changed question (to keep it focused)
        for(QuestionWidget qw : UIElements){
            if(isQuestion(qw, changedQuestion)){
                this.vbox.getChildren().add(currentQuestionWidget);
            }
            else {
                this.vbox.getChildren().add(qw);
            }
        }
    }

    public boolean isQuestion(QuestionWidget p, Question q){
        if(p.getQuestion().getVarname().equals(q.getVarname())){
            return true;
        }
        return false;
    }

    public GridPane getRootPane() {
        return rootPane;
    }

}
