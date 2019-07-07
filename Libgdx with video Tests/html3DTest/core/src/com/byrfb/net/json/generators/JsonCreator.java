package com.byrfb.net.json.generators;

import java.util.ArrayList;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.byrfb.net.json.constants.JSONConstants;

public class JsonCreator implements IJson {

	@Override
	public String toJsonArray(ArrayList<?> objs) {
		StringBuilder builder = new StringBuilder();
		builder.append(JSONConstants.JSON_ARRAY_START);
		for (int i = 0; i < objs.size(); i++) {
			builder.append(toJson(objs.get(i)));
			if (i != objs.size() - 1)
				builder.append(JSONConstants.COMMA);

		}
		builder.append(JSONConstants.JSON_ARRY_END);

//		Library.consoleLog(builder.toString());
//		System.out.println(builder.toString());
		return builder.toString();

	}

	@Override
	public String toJson(Object obj) {
		Field[] fields = ClassReflection.getDeclaredFields(obj.getClass());

		StringBuilder builder = new StringBuilder();
		builder.append(JSONConstants.OCBRACE_TOKEN);
		builder.append("\"" + "class" + "\"");
		builder.append(JSONConstants.COLON);
		builder.append("\"" + obj.getClass().getName() + "\"");
		builder.append(JSONConstants.COMMA);

		for (int i = 0; i < fields.length; i++) {
			try {

				String name = fields[i].getName();
				String value = fields[i].get(obj).toString();

				builder.append("\"" + name + "\"");
				builder.append(JSONConstants.COLON);
				if (fields[i].get(obj) instanceof String) {
					builder.append("\"" + value + "\"");
				} else {
					builder.append(value);
				}

				if (i != fields.length - 1)
					builder.append(JSONConstants.COMMA);

			} catch (ReflectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		builder.append(JSONConstants.CCBRACE_TOKEN);
		return builder.toString();
	}

}
