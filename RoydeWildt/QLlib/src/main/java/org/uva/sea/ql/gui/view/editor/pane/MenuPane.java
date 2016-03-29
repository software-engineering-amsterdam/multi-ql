package org.uva.sea.ql.gui.view.editor.pane;

import com.sun.javafx.stage.StageHelper;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.uva.sea.ql.gui.view.editor.EditorView;
import org.uva.sea.ql.gui.view.preview.PreviewView;

/**
 * Created by roy on 3/29/16.
 */
public class MenuPane extends MenuBar {
    private EditorView editorView;
    private PreviewView previewView;

    public MenuPane(EditorView editorView){
        this.editorView = editorView;

        Menu fileMenu = new Menu("File");
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(actionEvent -> loadFromFile());

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(actionEvent -> saveToFile());

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(openMenuItem, saveMenuItem, exitMenuItem);

        Menu viewMenu = new Menu("View");
        MenuItem previewMenuItem = new MenuItem("Preview");
        previewMenuItem.setOnAction(actionEvent -> openPreview());

        viewMenu.getItems().add(previewMenuItem);

        this.getMenus().addAll(fileMenu, viewMenu);
    }

    private void loadFromFile(){

    }

    private void saveToFile(){

    }

    private void openPreview() {
        if(previewView == null){
            previewView = new PreviewView(editorView);
        }
        previewView.showPreviewStage();
    }
}
