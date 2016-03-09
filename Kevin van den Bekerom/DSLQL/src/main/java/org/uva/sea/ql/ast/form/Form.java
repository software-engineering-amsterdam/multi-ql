package org.uva.sea.ql.ast.form;

import java.util.HashMap;
import java.util.Map;

import org.uva.sea.ql.ast.expr.terminals.Variable;
import org.uva.sea.ql.ast.stat.*;

public class Form {
	private String name;
	private Block mainBlock;
	private ValueMap valueMap;

	public Form(String name, Block block) {
		this.name = name;
		this.mainBlock = block;
	}

	public void initializeContext() {

	}

	public String getName() {
		return name;
	}

	public Block getMainBlock() {
		return this.mainBlock;
	}

	private void initializeQVMap() {

	}

}
