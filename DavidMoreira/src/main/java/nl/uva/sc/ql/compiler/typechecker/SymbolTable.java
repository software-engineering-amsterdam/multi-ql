package nl.uva.sc.ql.compiler.typechecker;

import java.util.Hashtable;
import java.util.Map;

import nl.uva.sc.ql.compiler.parser.ast.QuestionNode;

public class SymbolTable {
    private Map<String, QuestionNode> map;
    
    public SymbolTable() {
    	map = new Hashtable<String, QuestionNode>();
    }

    public QuestionNode add(String id, QuestionNode info) {
		return map.put(id, info);
    }

    public QuestionNode lookup(String id) {
		return map.get(id);
    }
}