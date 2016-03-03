package org.uva.sea.ql.gui;

import com.sun.javafx.collections.ObservableMapWrapper;
import javafx.beans.binding.MapExpression;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;

import java.util.*;

/**
 * Created by roy on 29-2-16.
 */
public class GuiBuilder{
    private Form form;
    private AnchorPane rootPane;

    public GuiBuilder(Form form) {
        this.rootPane = new AnchorPane();
        this.form = form;

        FormEvaluator evaluator = new FormEvaluator(this.form);
        List<Question> questions = evaluator.getQuestions();
        GuiVisitor visitor = new GuiVisitor(questions, evaluator.getSymbolTable());

        addFormListener(evaluator);

        List<Parent> UIElements = visitor.getUiElements();
        GridPane formUI = buildFormUI(form.getId(), UIElements);
        this.rootPane.getChildren().add(formUI);
    }

    private GridPane buildFormUI(String name, List<Parent> questions){
        GridPane formUI = new GridPane();
        formUI.setPrefSize(500,600);
        formUI.setVgap(10);
        formUI.setPadding(new Insets(0, 0, 0, 0));
        formUI.setAlignment(Pos.TOP_CENTER);

        Label label = new Label(name);
        label.setFont(new Font("Arial", 30));
        label.setPadding(new Insets(10,0,5,20));
        formUI.add(label, 0, 0, 2, 1);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(questions);
        formUI.add(vbox, 0, 1);

        return formUI;
    }

    private void addFormListener(FormEvaluator evaluator) {

        evaluator.getSymbolTable().addListener((MapChangeListener<Var,Question>) c -> {
            System.out.println("Changed " + c.toString());

            FormEvaluator fe = new FormEvaluator(this.form, (ObservableMap<Var, Question>) c.getMap());
            List<Question> questions = fe.getQuestions();

            GuiVisitor visitor = new GuiVisitor(questions, fe.getSymbolTable());
            List<Parent> UIElements = visitor.getUiElements();
            GridPane formUI = buildFormUI(this.form.getId(), UIElements);
            this.rootPane.getChildren().clear();
            this.rootPane.getChildren().add(formUI);
        });
    }

    public AnchorPane getRootPane() {
        return rootPane;
    }

}
