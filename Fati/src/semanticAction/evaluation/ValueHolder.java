package semanticAction.evaluation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/** Goal make a repository to save the identifier in the order in which they were inserted in the repository.
 * @return a set of keys.
 *  
 * @author Fati
 *
 */
public class ValueHolder {
	
	
	private final Map<String, Value> valueHolder; 
	
	public ValueHolder() {
		this.valueHolder = new LinkedHashMap<String, Value>();
	}
	public boolean isDefined(String identifier) {
		return valueHolder.containsKey(identifier);
	}
	public Value getValue(String identifier) {
		if(isDefined(identifier)) {
			return valueHolder.get(identifier);
		}
		return null;
	}
	
	public void putValue(String id, StringValue value) {
		valueHolder.put(id, value);
	}
	
	public Set<String> getIDkeys() {
		Set<String> keys = valueHolder.keySet();
		return keys;
	}
	
	public Map<String, Value> getSymbol() {
		return valueHolder;
	}
}