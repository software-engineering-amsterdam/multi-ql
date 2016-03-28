package org.uva.sea.ql.gui.view.editor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;


/**
 * Created by roy on 3/17/16.
 */
public class EditorView {
    private GridPane rootPane;

    public EditorView() {
        this.rootPane = buildEditorView();
    }

    private GridPane buildEditorView(){
        GridPane editorUI = getEditorPane();

        TextPane textPane = new TextPane();
        LogPane logPane = new LogPane();
        InfoPane infoPane = new InfoPane();

        textPane.addCaretChangedListener((observableValue, cursor, t1) -> infoPane.setPosition(textPane.getTextPane()));

        RowConstraints row1 = getRowConstraints(300);
        RowConstraints row2 = getRowConstraints(180);
        RowConstraints row3 = getRowConstraints(20);
        editorUI.getRowConstraints().addAll(row1, row2, row3);

        editorUI.add(textPane.getTextPane(), 0, 0);
        editorUI.add(logPane.getLogPane(), 0, 1);
        editorUI.add(infoPane.getInfoPane(), 0, 2);

        return editorUI;
    }

    private RowConstraints getRowConstraints(int prefHeight) {
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        row1.setPrefHeight(prefHeight);
        return row1;
    }

    private GridPane getEditorPane() {
        GridPane editorUI = new GridPane();
        editorUI.setPrefSize(500,600);
        editorUI.setVgap(10);
        editorUI.setPadding(new Insets(0, 0, 0, 0));
        editorUI.setAlignment(Pos.TOP_CENTER);
        return editorUI;
    }

    public GridPane getRootPane() {
        return rootPane;
    }
}
