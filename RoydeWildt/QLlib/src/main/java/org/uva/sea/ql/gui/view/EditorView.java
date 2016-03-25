package org.uva.sea.ql.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import org.uva.sea.ql.gui.observer.Observable;
import org.uva.sea.ql.gui.observer.Position;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 3/17/16.
 */
public class EditorView {
    private GridPane rootPane;
    private final Observable<String> observableString;
    private final Observable<Position> observablePosition;

    public EditorView() {
        this.rootPane = buildEditorView();
        this.observableString = new Observable<>();
        this.observablePosition = new Observable<>();
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
        row2.setMinHeight(180);

        RowConstraints row3 = new RowConstraints();
        row2.setMinHeight(20);

        editorUI.getRowConstraints().addAll(row1, row2, row3);

        TextArea editorField = new TextArea();
        editorField.setWrapText(true);
        editorField.textProperty().addListener((observable, oldValue, newValue) -> handleEditorFieldAction(editorField));
        editorUI.add(editorField,0,0);

        TextArea logField = new TextArea();
        logField.setEditable(false);
        editorUI.add(logField,0,1);

        Label infoField = new Label();
        infoField.setText("(0,0)");
        editorUI.add(infoField,0,2);

        return editorUI;
    }

    private void handleEditorFieldAction(TextArea f) {
        observableString.setValue(f.getText());
        observablePosition.setValue(getLineFromOffset(f));
    }

    private Position getLineFromOffset(TextArea textArea){
        String[] stringList = textArea.getText().split("\n");

        int line = 0;
        int currentOffset = 0;
        int caretOffset = textArea.getCaretPosition();

        for(String str : stringList){

            currentOffset += str.length();

            if(caretOffset < currentOffset + str.length()){
                return new Position(line, caretOffset - currentOffset);
            }

            currentOffset += str.length();
            line++;
        }

        return null;
    }

    public GridPane getRootPane() {
        return rootPane;
    }

    public Observable<String> getObservableString() {
        return observableString;
    }

    public Observable<Position> getObservablePosition() {
        return observablePosition;
    }
}
