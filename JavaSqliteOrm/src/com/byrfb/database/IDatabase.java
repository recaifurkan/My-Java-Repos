package com.byrfb.database;


import java.util.ArrayList;
/*
 * veri taban�n yapt��� i�lemlerin listelendi�i yer
 * 
 * 
 */
import java.util.HashMap;

public interface IDatabase {

	public <T> ArrayList<HashMap<String, Object>> selectAll(Class<T> objClass);

	public ArrayList<HashMap<String, Object>> select(Object obj);

	public void insert(Object obj);

	public void update(Object obj);

	public void delete(Object obj);

}
