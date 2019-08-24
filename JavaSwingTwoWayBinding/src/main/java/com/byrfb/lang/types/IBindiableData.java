package com.byrfb.lang.types;

import com.byrfb.lang.DataChangeListener;

import java.util.List;

public interface IBindiableData <T> {
    public void setValue(T value);
    public T getValue();
    public List<DataChangeListener> getListeners();
    public void addListener(DataChangeListener listener);
    public void deleteListener(DataChangeListener listener);

}
