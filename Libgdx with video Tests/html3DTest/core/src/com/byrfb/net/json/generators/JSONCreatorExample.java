package com.byrfb.net.json.generators;

import java.util.ArrayList;

import com.byrfb.net.objects.Post;

public class JSONCreatorExample {

	public static void main(String[] args) {
		ArrayList<Post> posts = new ArrayList<Post>();
		posts.add(new Post(0, 0));
		posts.add(new Post(0, 0));
		posts.add(new Post(0, 0));
		posts.add(new Post(0, 0));
		posts.add(new Post(0, 0));
		posts.add(new Post(0, 0));
		posts.add(new Post(1, 2));
		posts.add(new Post(0, 0));
		posts.add(new Post(0, 0));
		
//		String jsonText = json.toJsonArray(posts);
		
		JsonCreator creator = new JsonCreator();
		String text = creator.toJsonArray(posts);
		System.out.println(text);
//		Library.consoleLog(text);
	}

}
