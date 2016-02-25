package org.uva.sea.ql.gui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.visitor.EvalVisitor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 25-2-16.
 */
public class GuiVisitor extends EvalVisitor<Parent,List<Parent>,Parent> {
    private final GridPane formUI = new GridPane();

    public GuiVisitor(Form f) {
        super(f);
        formUI.setHgap(10);
        formUI.setVgap(10);
        formUI.setPadding(new Insets(0, 10, 0, 10));
        f.accept(this);
    }

    @Override
    public Parent visit(Form form) {
        Label label = new Label(form.getId());
        label.setFont(new Font("Arial", 30));
        GridPane.setConstraints(label, 0, 0);

        VBox vbox = new VBox();
        GridPane.setConstraints(vbox, 0, 1);

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                vbox.getChildren().addAll(s.accept(this));
            }
        }

        formUI.getChildren().addAll(label,vbox);

        return formUI;
    }

    @Override
    public List<Parent> visit(If stat) {

        List<Parent> uilist = new ArrayList<>();

        if((Boolean) stat.getCond().accept(this)){
            for(Stat s : stat.getStms())
                uilist.addAll(s.accept(this));
        }

        return uilist;
    }

    @Override
    public List<Parent> visit(IfElse stat) {

        List<Parent> uilist = new ArrayList<>();

        if((Boolean)stat.getCond().accept(this)){
            for(Stat s : stat.getIfStms())
                uilist.addAll(s.accept(this));
        }
        else {
            for(Stat s : stat.getElseStms())
                uilist.addAll(s.accept(this));
        }

        return uilist;
    }

    @Override
    public List<Parent> visit(Question stat) {
        List<Parent> uilist = new ArrayList<>();
        HBox  box   = new HBox();

        Label label = new Label(stat.getLabel());
        box.getChildren().add(label);
        uilist.add(box);

        return uilist;
    }

    public GridPane getFormUI() {
        return formUI;
    }

}
