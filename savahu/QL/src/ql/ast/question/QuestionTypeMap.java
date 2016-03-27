package ql.ast.question;

import java.util.HashMap;
import java.util.Map;
import ql.ast.expression.Ident;
import ql.ast.type.Type;

/**
 *
 * @author sander
 */
public class QuestionTypeMap {
    
    private Map<String, Type> _QuestionTypeMap;
    
    public QuestionTypeMap()
    {
        this._QuestionTypeMap = new HashMap<>();
    }
    
    public void addQuestion(Ident identifier, Type theType)
    {
        this._QuestionTypeMap.put(identifier.getName(), theType);
    }
    
    public Type getTypeForQuestion(Ident identifier)
    {
        if(this._QuestionTypeMap.containsKey(identifier.getName()))
        {
            return this._QuestionTypeMap.get(identifier.getName());
        }
        else
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
