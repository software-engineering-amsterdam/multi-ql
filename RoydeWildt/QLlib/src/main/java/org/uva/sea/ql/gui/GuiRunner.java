package org.uva.sea.ql.gui;
/**
 * Created by roydewildt on 18/02/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.Checker;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.gui.observer.ObjectObserver;
import org.uva.sea.ql.gui.observer.Observable;
import org.uva.sea.ql.gui.view.EditorView;
import org.uva.sea.ql.gui.view.PreviewView;
import org.uva.sea.ql.parser.QLRunner;

import java.util.List;

public class GuiRunner extends Application implements ObjectObserver {

    private Stage preview;
    private Observable<String> editorText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage editor = EditorStage();
        editor.show();

        preview = new Stage();
        preview.setWidth(editor.getWidth());
        preview.setHeight(editor.getHeight());
        preview.setX(editor.getX() - editor.getWidth() - 10);
        preview.setY(editor.getY() - 14);
        preview.show();
    }


    public Stage EditorStage() {
        //List<Message> messages = new Checker(f).getMessages();

        EditorView editor = new EditorView();
        editorText = editor.getObservableString();
        editorText.addObserver(this);
        Scene scene = new Scene(editor.getRootPane());
        scene.getStylesheets().add("customStylesheet.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    private Form parseString(String path){
        try {
            return QLRunner.parseString(path);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }


    @Override
    public void update() {
        try {
            Form form = parseString(editorText.getValue());
            Pane previewPane = new PreviewView(form).getRootPane();

            Scene scene = new Scene(previewPane);
            scene.getStylesheets().add("customStylesheet.css");
            preview.setScene(scene);

        }
        catch (Exception e) {

        }
    }
}
