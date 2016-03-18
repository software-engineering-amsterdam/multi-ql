package org.uva.sea.ql.gui.observer;

/**
 * Created by roy on 3/18/16.
 */
public class Position {
    int line;
    int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public String positionString(){
        return "(" + line + ":"  + column + ")";
    }
}
