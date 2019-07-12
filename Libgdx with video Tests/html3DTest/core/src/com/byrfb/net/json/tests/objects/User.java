package com.byrfb.net.json.tests.objects;

import com.byrfb.net.json.Json;
import com.byrfb.net.json.JsonClassSerializable;
import com.byrfb.net.json.JsonObject;

public class User implements JsonClassSerializable{
	public String name;
	public String surName;
	

	@Override
	public JsonObject serialize() {
		JsonObject obj = Json.object();
		obj.add("name", name);
		obj.add("surName", surName);
		return obj;
	}


	@Override
	public void deserialize(JsonObject obj) {
		name = obj.get("name").asString();
		surName = obj.get("surName").asString();
		
	}
}