package org.uva.sea.ql.gui;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.visitor.EvalVisitor;
import org.uva.sea.ql.ast.visitor.UpdateVisitor;
import org.uva.sea.ql.ast.visitor.context.NodeUpdate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 25-2-16.
 */
public class GuiVisitor extends EvalVisitor<Parent,List<Parent>,Parent> {
    private final GridPane formUI;
    private Form f;
    private Main parent;
    private Parent valUI;

    public GuiVisitor(Form f, Main m) {
        super(f);
        this.parent = m;
        this.f = f;

        formUI = new GridPane();
        formUI.setPrefSize(500,600);
        formUI.setVgap(10);
        formUI.setPadding(new Insets(0, 0, 0, 0));
        formUI.setAlignment(Pos.TOP_CENTER);
        f.accept(this, null);
    }

    @Override
    public Parent visit(Form form, Void context) {
        Label label = new Label(form.getId());
        label.setFont(new Font("Arial", 30));
        label.setPadding(new Insets(10,0,5,20));
        formUI.add(label, 0, 0, 2, 1);

        VBox vbox = new VBox();
        //vbox.setBorder(setBorder(Color.BLACK));

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                vbox.getChildren().addAll(s.accept(this, context));
            }
        }

        formUI.add(vbox, 0, 1);

        return formUI;
    }

    @Override
    public List<Parent> visit(If stat, Void context) {

        List<Parent> uilist = new ArrayList<>();

        if((boolean) stat.getCond().accept(this, context)){
            for(Stat s : stat.getStms())
                uilist.addAll(s.accept(this, context));
        }

        return uilist;
    }

    @Override
    public List<Parent> visit(IfElse stat, Void context) {

        List<Parent> uilist = new ArrayList<>();

        if((boolean) stat.getCond().accept(this, context)){
            for(Stat s : stat.getIfStms())
                uilist.addAll(s.accept(this, context));
        }
        else {
            for(Stat s : stat.getElseStms())
                uilist.addAll(s.accept(this, context));
        }

        return uilist;
    }

    @Override
    public List<Parent> visit(Question stat, Void context) {

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


        stat.getExpr().accept(this,context);
        Parent inputfield = valUI;
        box.add(inputfield, 1, 0);

        uilist.add(box);

        return uilist;
    }

    @Override
    public Object visit(Int val, Void context) {
        return super.visit(val, context);
    }

    @Override
    public Object visit(Bool val, Void context) {
        ASTCheckBox b = new ASTCheckBox(val);
        b.setOnAction(this::handleCheckBoxAction);
        valUI = b;
        return super.visit(val, context);
    }

    private Border setBorder(Color c){
        return new Border(new BorderStroke(c,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    }

    public GridPane getFormUI() {
        return formUI;
    }

    private void handleCheckBoxAction(ActionEvent event) {
        ASTCheckBox b = (ASTCheckBox) event.getSource();
        Node oldNode = b.getSource();
        Bool newNode = new Bool(b.isSelected());

        NodeUpdate u = new NodeUpdate(oldNode,newNode);
        UpdateVisitor uv = new UpdateVisitor();
        this.f = f.accept(uv, u);

    }

}
