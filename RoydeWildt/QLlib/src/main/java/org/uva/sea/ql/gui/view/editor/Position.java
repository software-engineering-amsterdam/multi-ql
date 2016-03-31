package org.uva.sea.ql.gui.view.editor;

/**
 * Created by roy on 3/18/16.
 */
public class Position {
    private int line;
    private int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString(){
        return "(" + line + ":"  + column + ")";
    }
}
