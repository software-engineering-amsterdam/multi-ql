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
    private Stage editor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        editor = editorStage();
        editor.show();
    }

    private Stage editorStage() {

        EditorView editor = new EditorView();

        Scene scene = new Scene(editor.getRootPane());
        scene.getStylesheets().add("customStylesheet.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }
/*
Pane previewPane = new PreviewView(form).getRootPane();

                Scene scene = new Scene(previewPane);
                scene.getStylesheets().add("customStylesheet.css");
                Stage preview = new Stage();
                preview.setScene(scene);
                preview.show();
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
