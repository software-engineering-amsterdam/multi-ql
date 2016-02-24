package org.uva.sea.ql.ast;

import org.uva.sea.ql.checker.Visitable;

/**
 * Root class for the AST hierarchy.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public abstract class ASTNode implements Visitable<ASTNode> {}
