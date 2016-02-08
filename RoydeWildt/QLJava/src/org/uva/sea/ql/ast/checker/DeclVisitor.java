package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.ast.stat.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class DeclVisitor extends BaseVisitor{


    @Override
    public List<? extends Node> visit(Stat stat) {
        v = new DeclVisitor();
        List<Node> result = new ArrayList<>();

        //get all vars in loop body
        if (stat.getStms() != null) {
            for (Stat s: stat.getStms()) {
                result.addAll(s.accept(v));
            }
        }

        //get all vars in alternative loop body
        if (stat.getAltStms() != null) {
            for (Stat s: stat.getAltStms()) {
                result.addAll(s.accept(v));
            }
        }


        if(stat instanceof Question){
            result.add(((Question) stat).getVarname());
        }
        return result;
    }
}
