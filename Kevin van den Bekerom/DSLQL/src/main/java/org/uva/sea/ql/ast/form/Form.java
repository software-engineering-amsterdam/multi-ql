package org.uva.sea.ql.ast.form;

import java.util.List;

import org.uva.sea.ql.ast.stat.*;

public class Form {
	private String name;
	private Block mainBlock;
	
	public Form(String name, Block block) {
		this.name = name;
		this.mainBlock = block;
	}

	public String getName() {
		return name;
	}
	
}
