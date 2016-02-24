package org.uva.sea.ql.gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.type.BooleanType;
import org.uva.sea.ql.ast.type.MoneyType;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.evaluator.EvalVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 18/02/16.
 */
public class GuiBuilder {
    private final GridPane formUI;

    private void handleCheckboxAction(ActionEvent event) {
        System.out.println("checkbox changed");
    }

    public GuiBuilder(String formName, List<Question> questions) {

        //setup form
        formUI = new GridPane();
        formUI.setHgap(10);
        formUI.setVgap(10);
        formUI.setPadding(new Insets(0, 10, 0, 10));

        //set form label
        Label label = new Label(formName);
        label.setFont(new Font("Arial", 30));
        GridPane.setConstraints(label, 0, 0);

        //add questions
        VBox qsBox = questionsToVBox(questions);
        GridPane.setConstraints(qsBox, 0, 1);

        //add all ui elements
        formUI.getChildren().addAll(label, qsBox);

    }

    public VBox questionsToVBox (List<Question> questions) {


        VBox vbox = new VBox();
        GridPane.setConstraints(vbox, 0, 1);

        for(Question q : questions){
            HBox qbox = questionToHBox(q);
            vbox.getChildren().add(qbox);
        }

        return vbox;
    }

    public HBox questionToHBox(Question q){
        HBox box = new HBox();
        Label label = new Label(q.getLabel());
        box.getChildren().add(label);

        // TODO Refactor
        Type questionType = q.getType();
        if(questionType.getType() == new BooleanType()){
            CheckBox b = new CheckBox();
            b.setOnAction(this::handleCheckboxAction);
            box.getChildren().add(b);
        }
        else if ( questionType.getType() == new MoneyType()){
            TextField f = new TextField();
            box.getChildren().add(f);
        }

        return box;
    }


    public GridPane getFormUI() {
        return formUI;
    }
}
