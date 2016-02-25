package org.uva.sea.ql.gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.visitor.EvalVisitor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 25-2-16.
 */
public class GuiVisitor extends EvalVisitor<Parent,List<Parent>,Parent> {
    private final GridPane formUI;

    public GuiVisitor(Form f) {
        super(f);
        formUI = new GridPane();
        formUI.setPrefSize(500,600);
        formUI.setVgap(10);
        formUI.setPadding(new Insets(0, 0, 0, 0));
        formUI.setAlignment(Pos.TOP_CENTER);
        f.accept(this);
    }

    @Override
    public Parent visit(Form form) {
        Label label = new Label(form.getId());
        label.setFont(new Font("Arial", 30));
        formUI.add(label, 0, 0, 2, 1);

        VBox vbox = new VBox();
        //vbox.setBorder(setBorder(Color.BLACK));

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                vbox.getChildren().addAll(s.accept(this));
            }
        }

        formUI.add(vbox, 0, 1);

        return formUI;
    }

    @Override
    public List<Parent> visit(If stat) {

        List<Parent> uilist = new ArrayList<>();

        if((boolean) stat.getCond().accept(this)){
            for(Stat s : stat.getStms())
                uilist.addAll(s.accept(this));
        }

        return uilist;
    }

    @Override
    public List<Parent> visit(IfElse stat) {

        List<Parent> uilist = new ArrayList<>();

        if((boolean) stat.getCond().accept(this)){
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
        GridPane  box   = new GridPane();
        box.setHgap(5);
        box.setPadding(new Insets(5,20,5,20));

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        col1.setHalignment(HPos.LEFT);
        col1.setMaxWidth(600);

        ColumnConstraints col2 = new ColumnConstraints();
        //col2.setHgrow(Priority.ALWAYS);
        col2.setHalignment(HPos.RIGHT);
        col2.setMinWidth(200);

        box.getColumnConstraints().addAll(col1, col2);

        Label label = new Label(stat.getLabel());
        label.setWrapText(true);
        box.add(label, 0, 0);

        Parent inputfield = stat.getType().accept(this);
        box.add(inputfield, 1, 0);

        uilist.add(box);

        return uilist;
    }

    @Override
    public Parent visit(Money type) {
        return new CheckBox();
    }

    @Override
    public Parent visit(Boolean type) {
        return new TextField();
    }

    private Border setBorder(Color c){
        return new Border(new BorderStroke(c,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    }

    public GridPane getFormUI() {
        return formUI;
    }

}
