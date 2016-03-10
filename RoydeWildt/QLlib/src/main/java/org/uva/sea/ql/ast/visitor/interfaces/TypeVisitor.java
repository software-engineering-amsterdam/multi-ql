package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Text;


/**
 * Created by roy on 5-2-16.
 */
public interface TypeVisitor<TYPE, CONTEXT> {

    TYPE visit(Boolean type, CONTEXT context);
    TYPE visit(Money type, CONTEXT context);
    TYPE visit(Text type, CONTEXT context);

}
