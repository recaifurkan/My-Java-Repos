package com.byrfb.database;

import com.byrfb.objects.ders.Lesson;

import com.byrfb.sqlite.SqLiteDatabase;
/*
 * 
 * Bu classla genel olarak bütün sýnýflarý veri tabanýna kaydedebilirsin
 * 
 * 
 */
public class Dao extends ObjectDao {

	public Dao(IDatabaseConnector database) {
		super(database);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Lesson ders = new Lesson(0,"Protez");

		new Dao(new SqLiteDatabase()).insert(ders);

		System.out.println(new Dao(
				new SqLiteDatabase()).selectAll(Lesson.class));
		
		
		
		
	}

}
