package com.byrfb.lang;


import com.byrfb.lang.types.AbstractBindiableData;

public interface DataChangeListener  {

    public void bind(AbstractBindiableData data);

    public void onDataChange();


}
