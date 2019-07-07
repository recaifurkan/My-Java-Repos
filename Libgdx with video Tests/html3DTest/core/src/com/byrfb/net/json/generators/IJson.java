package com.byrfb.net.json.generators;

import java.util.ArrayList;

public interface IJson {
	
	public String toJsonArray(ArrayList<?> objs);
	
	public String toJson(Object obj);

	

}
