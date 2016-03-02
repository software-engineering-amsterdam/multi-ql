package org.uva.sea.ql.gui;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.gui.widget.CheckboxWidget;


/**
 * Created by roy on 25-2-16.
 */
public class GuiVisitor extends BaseVisitor<Void, Parent, Void, Void, Parent, Void> {
    private GridPane box;

    @Override
    public Parent visit(Question stat, Void context) {

        box = new GridPane();
        box.setHgap(5);
        box.setPadding(new Insets(5,20,5,20));

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        col1.setHalignment(HPos.LEFT);
        col1.setMaxWidth(600);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.RIGHT);
        col2.setMinWidth(200);

        box.getColumnConstraints().addAll(col1, col2);

        Label label = new Label(stat.getLabel());
        label.setWrapText(true);
        box.add(label, 0, 0);

        Primary primary = (Primary) stat.getExpr();
        Parent valueUI = primary.getValue().accept(this, null);
        box.add(valueUI, 1, 0);

        return box;
    }

    @Override
    public Parent visit(Int val, Void context) {
        return new TextField();
    }

    @Override
    public Parent visit(Bool val, Void context) {
        CheckboxWidget b = new CheckboxWidget(val);
        b.setOnAction(this::handleCheckBoxAction);
        return b;
    }

    private void handleCheckBoxAction(ActionEvent event) {
        CheckboxWidget b = (CheckboxWidget) event.getSource();
        //form.updateVal(b.getSource(), b.isSelected());
    }
}
