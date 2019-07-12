package com.byrfb.net.json.tests;

import com.byrfb.jsLibraries.Library;
import com.byrfb.net.json.Json;
import com.byrfb.net.json.JsonArray;
import com.byrfb.net.json.JsonObject;
import com.byrfb.net.json.JsonValue;
import com.byrfb.net.json.tests.objects.User;
import com.byrfb.platformsbridges.PlatformsBridge;

public class JsonTest {

	public static void test() {
		User user = new User();
		user.name = "recai";
		user.surName = "bostancý";

		User user2 = new User();
		user2.name = "furkan";
		user2.surName = "temel";

		JsonObject object = user.serialize();
		JsonObject object2 = user2.serialize();

		JsonArray array = Json.array().add(object).add(object2);
		PlatformsBridge.debug(array.toString());

		String jsonText = array.toString();

		JsonValue value = Json.parse(jsonText);

		JsonArray arrayInverse = value.asArray();

		JsonObject objectInverse = arrayInverse.get(0).asObject();
		User user3 = new User();
		user3.deserialize(objectInverse);

		PlatformsBridge.debug(user3.name);

	}

}
