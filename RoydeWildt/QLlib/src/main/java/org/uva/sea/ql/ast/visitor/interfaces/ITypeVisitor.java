package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;


/**
 * Created by roy on 5-2-16.
 */
public interface ITypeVisitor<T> {

    T visit(Boolean type);
    T visit(Money type);

}
