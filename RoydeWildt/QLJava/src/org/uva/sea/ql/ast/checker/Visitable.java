package org.uva.sea.ql.ast.checker;

import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public interface Visitable {

    List<String> accept(Visitor visitor);

}
