package org.uva.sea.ql.gui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 18/02/16.
 */
public class GuiBuilder extends BaseVisitor<Parent,Void> {
    private final GridPane formUI;

    public GuiBuilder(Form f) {

        formUI = new GridPane();
        formUI.setHgap(10);
        formUI.setVgap(10);
        formUI.setPadding(new Insets(0, 10, 0, 10));

        f.accept(this.getV(),null);
    }

    @Override
    public Parent visit(Form form, Void env) {
        Label label = new Label(form.getId());
        label.setFont(new Font("Arial", 30));
        GridPane.setConstraints(label, 0, 0);

        VBox vbox = new VBox();
        GridPane.setConstraints(vbox, 0, 1);

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                vbox.getChildren().add((Node) s.accept(this.getV(),env));
            }
        }

        formUI.getChildren().addAll(label,vbox);

        return formUI;
    }

    @Override
    public Parent visit(Question stat, Void env) {
        HBox  box   = new HBox();

        Label label = new Label(stat.getLabel());
        box.getChildren().add(label);



        //super.visit(stat, env);
        return box;
    }

    @Override
    public Parent visit(Money type, Void env) {
        return super.visit(type, env);
    }

    @Override
    public Parent visit(Boolean type, Void env) {
        CheckBox cb = new CheckBox();
        return cb;
    }

    public GridPane getFormUI() {
        return formUI;
    }
}
