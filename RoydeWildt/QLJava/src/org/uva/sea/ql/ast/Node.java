package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.checker.Visitable;
import org.uva.sea.ql.ast.checker.Visitor;

import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public interface Node {
    @Override
    String toString();
    List<? extends Node> accept(Visitor visitor);
}
