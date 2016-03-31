package ql.gui;

import java.util.Iterator;
import java.util.Map.Entry;

import ql.ast.type.ValueType;
import ql.ast.value.Value;
import ql.ast.visitor.Context;

public class FormSaver {
	private String path;
	private Context context;
	
	public FormSaver(String path, Context context){
		this.path = path;
		this.context = context;
	}
	
	public void saveForm(){
		if(nothingToSave()){
			return;
		}
		save();
	}

	private void save() {
		StringBuilder builder = new StringBuilder();
		builder.append(path + "\n");
		builder.append("Identifier - type: \n");
		Iterator<Entry<String, ValueType>> iterator = context.getIdentifierToTypeMap().entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, ValueType> entry = iterator.next();
			builder.append(entry.getKey() + ' ' + entry.getValue().toString() + "\n");
		}
		
		builder.append("\n");
		builder.append("Identifier - Value: \n");
		Iterator<Entry<String, Value>> iterator2 = context.getIdentifierToValueMap().entrySet().iterator();
		while(iterator2.hasNext()){
			Entry<String, Value> entry2 = iterator2.next();
			builder.append(entry2.getKey() + ' ' + entry2.getValue().getValue() + "\n");
		}
		
		System.out.println(builder.toString());
	}
	
	private boolean nothingToSave(){
		return (context == null || path == null);
	}
}
