package org.uva.sea.ql.gui.view.editor;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.uva.sea.ql.gui.observer.Position;

/**
 * Created by roy on 3/28/16.
 */
class InfoPane {
    private final Label infoPane;
    private final Position position;

    InfoPane() {
        this.position = new Position(1,0);
        this.infoPane = new Label();
        this.infoPane.setText(this.position.toString());
    }

    public void setPosition(TextArea textArea) {
        Position pos = getLineFromOffset(textArea);
        this.infoPane.setText(pos.toString());
    }

    private Position getLineFromOffset(TextArea textArea){
        String[] stringList = textArea.getText().split("\n",-1);

        int line = 1;
        int currentOffset = 0;
        int caretOffset = textArea.getCaretPosition();

        for(String str : stringList){

            if(caretOffset <= currentOffset + str.length()){
                return new Position(line, caretOffset - currentOffset);
            }

            //also count the newline
            currentOffset += str.length() + 1;
            line++;
        }

        return new Position(0,0);
    }

    Label getInfoPane() {
        return infoPane;
    }


}
