package com.byrfb.lang.types;


import com.byrfb.lang.DataChangeListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBindiableData <T> implements IBindiableData<T> {
    List<DataChangeListener> listeners = new ArrayList<>();

    T value;
    T oldValue;

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        if(this.value != null){
            oldValue = this.value;
        }
        this.value = value;
        notifyDataListeners();

    }

    @Override
    public List<DataChangeListener> getListeners() {
        return listeners;
    }

    @Override
    public void addListener(DataChangeListener listener) {
        if(!listeners.contains(listener))
            this.listeners.add(listener);
    }

    @Override
    public void deleteListener(DataChangeListener listener) {
        if(listeners.contains(listener))
            this.listeners.remove(listener);
    }


    private void notifyDataListeners() {
//        if(oldValue == null) return;
        for(int i = 0 ; i <listeners.size() ; i++){
            listeners.get(i).onDataChange();
        }

    }
}
