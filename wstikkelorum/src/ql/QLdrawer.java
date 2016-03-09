package ql;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.gui.QLFrame;

public class QLdrawer {
	public QLdrawer(){
		
	}
	
	public void drawForm(Form form, Context context){
		new QLFrame(form);
	}

}
