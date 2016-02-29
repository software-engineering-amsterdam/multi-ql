package org.uva.sea.ql.gui;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by roy on 29-2-16.
 */
public class GuiBuilder implements Observer {
    private AnchorPane rootPane;

    public GuiBuilder(Form form) {
        this.rootPane = new AnchorPane();

        List<Parent> UIElements = getUIElements(evaluateForm(form));
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

    private List<Question> evaluateForm(Form form) {
        FormEvaluator evaluator = new FormEvaluator(form);
        return evaluator.getQuestions();
    }

    private List<Parent> getUIElements(List<Question> questions) {
        List<Parent> UiElems = new ArrayList<>();
        for (Question question : questions){
            GuiVisitor gv = new GuiVisitor();
            Parent UiElem = gv.visit(question, null);
            UiElems.add(UiElem);
        }
        return UiElems;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public AnchorPane getRootPane() {
        return rootPane;
    }
}
