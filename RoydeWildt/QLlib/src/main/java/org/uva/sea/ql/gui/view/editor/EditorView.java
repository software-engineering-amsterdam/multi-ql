package org.uva.sea.ql.gui.view.editor;


import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.Checker;
import org.uva.sea.ql.gui.view.editor.listener.EditorChangedEvent;
import org.uva.sea.ql.gui.view.editor.listener.EditorChangedListener;
import org.uva.sea.ql.gui.view.editor.pane.InfoPane;
import org.uva.sea.ql.gui.view.editor.pane.LogPane;
import org.uva.sea.ql.gui.view.editor.pane.MenuPane;
import org.uva.sea.ql.gui.view.editor.pane.TextPane;
import org.uva.sea.ql.parser.QLRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by roy on 3/17/16.
 */
public class EditorView {
    private BorderPane rootPane;
    private TextPane textPane;
    private LogPane logPane;
    private InfoPane infoPane;
    private List<EditorChangedListener> listenerList;

    public EditorView() {
        this.listenerList = new ArrayList<>();
        this.rootPane = buildEditorView();
    }



    private BorderPane buildEditorView(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new MenuPane(this));
        borderPane.setCenter(buildEditorGrid());
        return borderPane;
    }

    private GridPane buildEditorGrid(){
        setupEditorPanes();

        GridPane editorUI = new GridPane();
        editorUI.setAlignment(Pos.TOP_CENTER);

        RowConstraints row1 = new RowConstraints(300);
        row1.setVgrow(Priority.ALWAYS);
        RowConstraints row2 = new RowConstraints(180);
        RowConstraints row3 = new RowConstraints(20);
        editorUI.getRowConstraints().addAll(row1, row2, row3);

        editorUI.add(textPane, 0, 0);
        editorUI.add(logPane, 0, 1);
        editorUI.add(infoPane, 0, 2);

        return editorUI;
    }

    private void setupEditorPanes(){
        this.textPane = new TextPane();
        this.logPane = new LogPane();
        this.infoPane = new InfoPane();

        textPane.addCaretChangedListener((observableValue, cursor, t1) ->
                infoPane.setPosition(textPane));

        textPane.addTextChangedListener((observableValue, s, t1) ->
                updatePreview());
    }


    public void updatePreview() {
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

    public BorderPane getRootPane() {
        return rootPane;
    }
}
