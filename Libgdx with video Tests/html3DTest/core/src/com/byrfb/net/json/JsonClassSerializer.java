package com.byrfb.net.json;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;


public abstract class JsonClassSerializer implements JsonClassSerializable {
	
	@Override
	public abstract JsonObject serialize();

	public JsonObject serialize(Object obj) {
		Field[] fields = ClassReflection.getDeclaredFields(obj.getClass());
		JsonObject object = Json.object();
//		for (int i = 0; i < fields.length; i++) {
//			try {
//
//				String name = fields[i].getName();
//				String value = fields[i].get(obj).toString();
//				
//				object.add(name, value);			
//
//			} catch (ReflectionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
		return object;
	}


	protected static  Object deserialize(JsonObject obj, Object o) {
		
		Field[] fields = ClassReflection.getDeclaredFields(o.getClass());
		
		for (int i = 0; i < fields.length; i++) {
			try {

				String name = fields[i].getName();
				JsonValue value = obj.get(name);
				fields[i].set(o, value.toString());
						

			} catch (ReflectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return o;
	}


	
}
