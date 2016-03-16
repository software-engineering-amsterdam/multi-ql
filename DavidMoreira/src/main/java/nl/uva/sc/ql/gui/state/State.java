package nl.uva.sc.ql.gui.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.ast.IdentifierNode;
import nl.uva.sc.ql.parser.value.Value;

public class State {
	private Map<IdentifierNode, ElementMap> map;
		
	public State() {
		this.map = new HashMap<IdentifierNode, ElementMap>();
	}

    public Value add(IdentifierNode node, Value value) {
    	ElementMap newElementMap = new ElementMap(value);

    	ElementMap oldElementMap = map.put(node, newElementMap);
    	
    	if (oldElementMap != null){
    		newElementMap.setObservers(oldElementMap.getObservers());
    	}
    	
    	Value oldValue = (oldElementMap != null) ? oldElementMap.getValue() : null;
    	
    	if(oldValue != null && !oldValue.equals(value)){
    		newElementMap.notifyObservers();
		} else if (oldValue != value){
			newElementMap.notifyObservers();
		}
    	
		return oldValue;
    }
    
    public Value add(String identifier, Value value) {
    	IdentifierNode node = new IdentifierNode(identifier);
    	
    	return this.add(node, value);
    } 

	public Value lookup(IdentifierNode node) {    	
		ElementMap elementMap = map.get(node);
		
		return elementMap.getValue();
    }
	
	public Value lookup(String identifier) {
    	IdentifierNode node = new IdentifierNode(identifier);
    	
    	return this.lookup(node);
	}
		
    public void registerObserverForExpressionNode(Observer o, ExpressionNode expressionNode){
    	List<String> listIdentifiersInExpression = new ArrayList<String>();
    	CreateListOfIdentifiersInExpression createListOfIdentifiersInExpression = new CreateListOfIdentifiersInExpression(listIdentifiersInExpression);
    	
    	expressionNode.accept(createListOfIdentifiersInExpression);
    	
    	for (String identifier : listIdentifiersInExpression){
    		IdentifierNode node = new IdentifierNode(identifier);
    		ElementMap elementMap = map.get(node);
    		
    		elementMap.registerObserver(o);
    	}    	
    }
    
    public void removeObserverForExpressionNode(Observer o, ExpressionNode expressionNode){
    	List<String> listIdentifiersInExpression = new ArrayList<String>();
    	CreateListOfIdentifiersInExpression createListOfIdentifiersInExpression = new CreateListOfIdentifiersInExpression(listIdentifiersInExpression);
    	
    	expressionNode.accept(createListOfIdentifiersInExpression);
    	
    	for (String identifier : listIdentifiersInExpression){
    		IdentifierNode node = new IdentifierNode(identifier);
    		ElementMap elementMap = map.get(node);
    		
    		elementMap.removeObserver(o);
    	}
    }
}
