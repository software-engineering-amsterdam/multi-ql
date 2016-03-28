package org.uva.sea.ql.gui.view.editor;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.Checker;
import org.uva.sea.ql.gui.view.editor.listener.EditorChangedEvent;
import org.uva.sea.ql.gui.view.editor.listener.EditorChangedListener;
import org.uva.sea.ql.gui.view.editor.pane.InfoPane;
import org.uva.sea.ql.gui.view.editor.pane.LogPane;
import org.uva.sea.ql.gui.view.editor.pane.TextPane;
import org.uva.sea.ql.gui.view.preview.PreviewView;
import org.uva.sea.ql.parser.QLRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 3/17/16.
 */
public class EditorView {
    private GridPane rootPane;
    private List<EditorChangedListener> listenerList;

    public EditorView() {
        this.listenerList = new ArrayList<>();
        this.rootPane = buildEditorView();

        Stage preview = previewStage();
        preview.show();
    }

    private Stage previewStage() {

        PreviewView preview = new PreviewView(this);

        Scene scene = new Scene(preview.getRootPane());
        scene.getStylesheets().add("customStylesheet.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    private GridPane buildEditorView(){
        GridPane editorUI = new GridPane();
        editorUI.setPrefSize(500,560);
        editorUI.setVgap(10);
        editorUI.setPadding(new Insets(0, 0, 0, 0));
        editorUI.setAlignment(Pos.TOP_CENTER);

        TextPane textPane = new TextPane();
        LogPane logPane = new LogPane();
        InfoPane infoPane = new InfoPane();

        textPane.addCaretChangedListener((observableValue, cursor, t1) ->
                infoPane.setPosition(textPane.getTextPane()));

        textPane.addTextChangedListener((observableValue, s, t1) ->
                updatePreview(textPane.getTextPane(), logPane.getLogPane()));

        RowConstraints row1 = new RowConstraints(300);
        row1.setVgrow(Priority.ALWAYS);
        RowConstraints row2 = new RowConstraints(180);
        RowConstraints row3 = new RowConstraints(20);
        editorUI.getRowConstraints().addAll(row1, row2, row3);

        editorUI.add(textPane.getTextPane(), 0, 0);
        editorUI.add(logPane.getLogPane(), 0, 1);
        editorUI.add(infoPane.getInfoPane(), 0, 2);

        return editorUI;
    }

    private void updatePreview(TextArea textPane, TextArea logPane) {
        logPane.setText("");

        try {
            Form form = QLRunner.parseString(textPane.getText());
            List<String> messages = new Checker(form).getMessageStrings();

            if(messages.isEmpty()){
                fireEditorChangedEvent(form);
            }

            else {
                logPane.setText(String.join("\n", messages));
            }
        }

        catch (ParseCancellationException e) {
            logPane.setText(e.getMessage());
        }
    }

    private void fireEditorChangedEvent(Form form){
        for(EditorChangedListener listener : listenerList){
            listener.editorChanged(new EditorChangedEvent(form));
        }
    }

    public void addEditorChangedEventListener(EditorChangedListener listener) {
        listenerList.add(listener);
    }

    public GridPane getRootPane() {
        return rootPane;
    }
}
