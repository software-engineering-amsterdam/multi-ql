package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.form.Form;


/**
 * Created by roy on 5-2-16.
 */
public interface FormVisitor<T,C> {

    T visit(Form form, C context);

}
