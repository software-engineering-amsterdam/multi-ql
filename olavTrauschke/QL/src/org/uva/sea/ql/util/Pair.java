package org.uva.sea.ql.util;

public class Pair<T,U> {
    
    private T firstValue;
    private U secondValue;
    
    public Pair(T theFirstValue, U theSecondValue) {
        firstValue = theFirstValue;
        secondValue = theSecondValue;
    }
    
    public T getFirstValue() {
        return firstValue;
    }
    
    public U getSecondValue() {
        return secondValue;
    }
    
}
