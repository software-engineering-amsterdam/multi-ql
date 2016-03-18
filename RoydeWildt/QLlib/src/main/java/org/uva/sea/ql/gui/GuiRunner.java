package org.uva.sea.ql.gui;
/**
 * Created by roydewildt on 18/02/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.Checker;
import org.uva.sea.ql.gui.observer.ObjectObserver;
import org.uva.sea.ql.gui.observer.Observable;
import org.uva.sea.ql.gui.view.EditorView;
import org.uva.sea.ql.gui.view.PreviewView;
import org.uva.sea.ql.parser.QLRunner;

import java.util.ArrayList;
import java.util.List;

public class GuiRunner extends Application implements ObjectObserver {

    private Stage preview;
    private Stage editor;
    private Observable<String> editorText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        editor = EditorStage();
        editor.show();

        preview = new Stage();
        preview.setWidth(editor.getWidth());
        preview.setHeight(editor.getHeight());
        preview.setX(editor.getX() - editor.getWidth() - 10);
        preview.setY(editor.getY() - 14);
        preview.show();
    }


    public Stage EditorStage() {

        EditorView editor = new EditorView();
        editorText = editor.getObservableString();
        editorText.addObserver(this);
        Scene scene = new Scene(editor.getRootPane());
        scene.getStylesheets().add("customStylesheet.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    public void updateLogView(List<String> messages){

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

            updateLogView(messages);
        }

        catch (ParseCancellationException e) {
            List<String> parseMessages = new ArrayList<>();
            parseMessages.add(e.getMessage());
            updateLogView(parseMessages);
        }
    }
}
