package semanticAnalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import semanticAction.tree.typeNode.AbsType;
public class SymTabel {


	private final Map<String, AbsType> typeSaver; 
	private final Map<String, String> qLabelSaver; 
	
	public SymTabel() {
		this.typeSaver = new HashMap<String, AbsType>();
		this.qLabelSaver = new HashMap<String, String>();
	}

	public void saveType(String id, AbsType type) {
		typeSaver.put(id, type);
	}
	//every label should be matched to a id. 
	public void SaveQLabel(String id, String qlabel) {
		qLabelSaver.put(id, qlabel);
	}
	
	private Map<String, AbsType> gettypeSaver() {
		return typeSaver;
	}
	
	public Map<String, String> getqLabelServer() {
		return qLabelSaver;
	}
	
	public boolean isDefined(String id) {
		return typeSaver.containsKey(id);
	}
	
	public boolean isDefinedQLabel(String id) {
		return qLabelSaver.containsKey(id);
	}
	
	public AbsType getValue(String id) {
		if(isDefined(id)) {
			return typeSaver.get(id);
		}
		return null;
	}
	
	public String getLabelValue(String id) {
		if(isDefined(id)) {
			return qLabelSaver.get(id);
		}
		return null;
	}
	
	public boolean empty() {
		if(!this.gettypeSaver().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Set<String> getIDkeys() {
		Set<String> keys = typeSaver.keySet();
		return keys;
	}
}