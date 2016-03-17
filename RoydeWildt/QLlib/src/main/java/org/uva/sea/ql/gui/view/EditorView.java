package org.uva.sea.ql.gui.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.evaluator.value.Value;

import java.util.List;

/**
 * Created by roy on 3/17/16.
 */
public class EditorView {
    private GridPane rootPane;

    public EditorView() {
        this.rootPane = buildEditorView();
    }

    private GridPane buildEditorView(){
        GridPane editorUI = new GridPane();
        editorUI.setPrefSize(500,600);
        editorUI.setVgap(10);
        editorUI.setPadding(new Insets(0, 0, 0, 0));
        editorUI.setAlignment(Pos.TOP_CENTER);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        row1.setPrefHeight(300);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(200);

        editorUI.getRowConstraints().addAll(row1, row2);

        TextArea editorField = new TextArea();
        editorField.setWrapText(true);
        editorUI.add(editorField,0,0);

        TextArea logField = new TextArea();
        logField.setDisable(true);
        editorUI.add(logField,0,1);

        return editorUI;
    }

    public GridPane getRootPane() {
        return rootPane;
    }
}
