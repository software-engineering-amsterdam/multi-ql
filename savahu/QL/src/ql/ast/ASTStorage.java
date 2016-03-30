/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sander
 */
public class ASTStorage {

    private final Map<String, String> _LabelDictionary;

    public ASTStorage() {
        _LabelDictionary = new HashMap<>();
    }

    public Boolean KeyIsInStorage(String key) {
        return this._LabelDictionary.containsKey(key);
    }

    public void AddToStorage(String key, String value) {
        this._LabelDictionary.put(key, value);
    }

}
