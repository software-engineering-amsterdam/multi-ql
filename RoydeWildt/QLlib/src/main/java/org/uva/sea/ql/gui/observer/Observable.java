package org.uva.sea.ql.gui.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 3/17/16.
 */
public class Observable<T> {
    private List<Observer> observers;
    protected T value;

    public Observable(){
        this.observers = new ArrayList<>();
        this.value = null;
    }

    public T getValue(){
        return value;}

    public void setValue(T value){
        this.value = value;
        observers.forEach(Observer::update);
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }
}
