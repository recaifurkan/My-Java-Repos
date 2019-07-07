package com.byrfb.net.objects;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;


public class Post {
	
	public int userId;
	
	public Post() {

	}
	public Post(int userId, int id) {
		super();
		this.userId = userId;
		this.id = id;
	}

	public int id;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Post [userId=" + userId + ", id=" + id + " ]";
	}


}
