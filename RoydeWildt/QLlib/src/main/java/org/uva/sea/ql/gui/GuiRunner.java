package org.uva.sea.ql.gui;
/**
 * Created by roydewildt on 18/02/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.Checker;
import org.uva.sea.ql.gui.observer.Observer;
import org.uva.sea.ql.gui.observer.Observable;
import org.uva.sea.ql.gui.observer.Position;
import org.uva.sea.ql.gui.view.editor.EditorView;
import org.uva.sea.ql.gui.view.preview.PreviewView;
import org.uva.sea.ql.parser.QLRunner;

import java.util.ArrayList;
import java.util.List;

public class GuiRunner extends Application {

    private Stage preview;
    private Stage editor;
    private Observable<String> editorText;
    private Observable<Position> infoText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        editor = editorStage();
        editor.show();

        preview = previewStage();
        preview.show();
    }

    private Stage editorStage() {

        EditorView editor = new EditorView();

        Scene scene = new Scene(editor.getRootPane());
        scene.getStylesheets().add("customStylesheet.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    private Stage previewStage() {
        preview = new Stage();
        preview.setWidth(editor.getWidth());
        preview.setHeight(editor.getHeight());
        preview.setX(editor.getX() - editor.getWidth() - 10);
        preview.setY(editor.getY() - 14);
        return preview;
    }
/*
    private void updateLogView(List<String> messages){

        GridPane editorPane = (GridPane) editor.getScene().getRoot();
        TextArea logField = new TextArea();

        if(messages.isEmpty()){
            logField.setText("");
        }
        else {
            logField.setText(String.join("\n", messages));
        }

        logField.setEditable(false);
        editorPane.add(logField,0,1);
    }

    private void updateInfoView(Position position){

        GridPane editorPane = (GridPane) editor.getScene().getRoot();
        Label infoField = new Label();

        if(position == null){
            infoField.setText("");
        }
        else {
            infoField.setText(position.positionString());
        }
        editorPane.add(infoField,0,2);
    }


    @Override
    public void update() {
        try {
            Form form = QLRunner.parseString(editorText.getValue());
            List<String> messages = new Checker(form).getMessageStrings();

            if(messages.isEmpty()){
                Pane previewPane = new PreviewView(form).getRootPane();

                Scene scene = new Scene(previewPane);
                scene.getStylesheets().add("customStylesheet.css");
                preview.setScene(scene);
            }

            updateInfoView(infoText.getValue());
            updateLogView(messages);
        }

        catch (ParseCancellationException e) {
            List<String> parseMessages = new ArrayList<>();
            parseMessages.add(e.getMessage());
            updateLogView(parseMessages);
        }
    }
    */
}
