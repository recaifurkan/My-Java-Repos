package com.byrfb.net.json.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.byrfb.jsLibraries.Library;
import com.byrfb.net.objects.Post;

public class JsonParserExample {

	public static void main(String[] args) {
		
		
		String jsonText = "";
		JSONParser parser = new JSONParser();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> mapValue =  (HashMap<String, Object>) parser.parseJson(jsonText);
		for (Entry<String, Object> entry : mapValue.entrySet()) {
//			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			
			ArrayList<Object> list = (ArrayList<Object>) entry.getValue();
			
			 for(Object obj : list){
				HashMap<String, Object> values = (HashMap<String, Object>) obj;
//				System.out.println(values.get("class"));
				
				try {
					Class classs = ClassReflection.forName((String) values.get("class"));
					Post post = (Post) ClassReflection.newInstance(classs);
					System.out.println(post.id);
					Library.consoleLog(String.valueOf(post.id));
				} catch (ReflectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
	}
}

	
		


