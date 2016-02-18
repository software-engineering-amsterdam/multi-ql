package eu.bankersen.kevin.ql.symboltable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.Type;

public final class SymbolTabel {

    private static HashMap<String, SymbolTableObject> tableList = new HashMap<>();
    private static Set<String> errorList = new HashSet<String>();

    public static void addVariable(final String name, final Type type) {
	Log.debug("Added: " + type.toString() + " : " + name);
	
	if(tableList.get(name) == null){
	    tableList.put(name, new SymbolTableObject(type));
	} else {
	    errorList.add("Question " + name + " with type " + type + " already exists!");
	}
	
    }

    public static Type getType(final String name) {
	try {	 
	    SymbolTableObject object = tableList.get(name);
	    
	    if (object.getVis()) {
		return tableList.get(name).getType();
	    } else {
		throw new NullPointerException();
	    }
	} catch (NullPointerException e) {
	    errorList.add("Object " + name + " not found/ accesable");
	    return Type.UNDIFINED;
	}
    }

    public static Object getValue(final String name) {
	Log.debug("Getting value for " + name + " value is " + tableList.get(name).getValue());
	return tableList.get(name).getValue();
    }
    
    public static void updateValue(final String name, final Object value) {
	
	Log.debug("Updating " + name);
	
	SymbolTableObject object = tableList.get(name);
	
	object.setValue(value);
	tableList.put(name, object);
	
	Log.debug("\t new value is " + tableList.get(name).getValue());
    }

    public static String getContents() {
	return tableList.toString();
    }
    
    public static void setVisibility(final String name, final Boolean vis) {
	SymbolTableObject object = tableList.get(name);
	
	object.setVis(vis);
	tableList.put(name, object);
    }
    
    public static Boolean getVisibility(final String name) {
	return tableList.get(name).getVis();
    }
    
    public static void addError(final String error) {
	Log.debug(error);
	errorList.add(error);
    }
    
    public static Set<String> getErrors() {
	return errorList;
    }

    private SymbolTabel() {
    }
}

class SymbolTableObject {

    private final Type type;
    private Object value;
    private Boolean visibility = true;


    SymbolTableObject(final Type type) {
	this.type = type;
    }
    
    public void setVis(final Boolean vis) {
	visibility = vis;
    }
    
    public Boolean getVis() {
	return visibility;
    }


    public Type getType() {
	return type;
    }

    public Object getValue() {
	return value;
    }
    
    public void setValue(final Object value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return type.toString() + " : " + value + " : vis=" + visibility;

    }
}