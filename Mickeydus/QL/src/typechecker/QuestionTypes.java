/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typechecker;

import AST.types.Ident;
import AST.types.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dominique
 */
public class QuestionTypes {
    private Map<String, Type> Questiontypes;
    
    public QuestionTypes(){
        
        this.Questiontypes = new HashMap();
    }
    
    public void addQuestionTypes(Ident id, Type type){
        String stringid = id.getName();
        Questiontypes.put(stringid, type);
    }
    
    public Type getType(Ident id){
          if(this.Questiontypes.containsKey(id.getName()))
        {
            return this.Questiontypes.get(id.getName());
        }
        else
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
