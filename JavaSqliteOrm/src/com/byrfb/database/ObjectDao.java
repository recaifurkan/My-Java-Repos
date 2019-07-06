package com.byrfb.database;

import java.lang.reflect.Field;

/*
 * 
 * 
 * 
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


/*
 * bir classýn sql sorgusuna döndürlerek kaydedilmesi deðiþtirlmesi silinmesi update edimesi ile ilgili metotlarýn bulunduðu ana sýnýf
 * 
 */
public abstract class ObjectDao implements IDatabase {

	IDatabaseConnector database;

	public ObjectDao(IDatabaseConnector database) {
		super();
		this.database = database;
	}

	public HashMap<String, Object> parseVariables(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();

		HashMap<String, Object> variables = new HashMap<>();

		for (int i = 0; i < fields.length; i++) {

			String name = fields[i].getName();
			String value = null;
			try {
				value = fields[i].get(obj).toString();
			} catch (NullPointerException | IllegalArgumentException | IllegalAccessException e) {
				value = "";
			}

			variables.put(name, value);
		}

		return variables;

	}

	public String process(DatabaseProcess process, Object obj) {

		String tableName = obj.getClass().getSimpleName().toLowerCase();

		HashMap<String, Object> variables = parseVariables(obj);

		String sql = null;
		boolean isFirst = true;
		switch (process) {
		case Delete:
			sql = "DELETE FROM " + tableName + " WHERE id =" + variables.get("id");
			break;
		case Insert:
			sql = insertProcess(obj, variables, isFirst);
			break;
		case Select:

			sql = "SELECT * FROM " + tableName + " WHERE id = " + variables.get("id");
			break;
		case Update:
			sql = updateProcess(tableName, variables, isFirst);
			break;
		default:
			break;

		}

		System.out.println(sql);
		return sql;

	}

	private String insertProcess(Object obj, HashMap<String, Object> variables, boolean isFirst) {
		String sql;
		String campos = "";
		String valores = "";

		for (Entry<String, Object> entry : variables.entrySet()) {
			String name = entry.getKey();
			if(name.equals("id")){
				continue;
			}
			String value = null;
			try {
				value = entry.getValue().toString();
			} catch (NullPointerException e) {
				value = "";
			}

			if (!isFirst) {
				campos = campos + ",";
				valores = valores + ",";
			}
			if (entry.getValue() instanceof String) {
				valores = valores + "'" + value + "'";
			} else {
				valores = valores + value;
			}
			campos = campos + name;
			isFirst = false;
		}

		sql = "INSERT INTO " + obj.getClass().getSimpleName().toLowerCase() + "(" + campos + ")VALUES(" + valores
				+ ");";
		return sql;
	}

	private String updateProcess(String tableName, HashMap<String, Object> variables, boolean isFirst) {
		String sql;
		String variableText = "";
		for (Entry<String, Object> entry : variables.entrySet()) {
			String name = entry.getKey();
			String value = null;
			try {
				value = entry.getValue().toString();
			} catch (NullPointerException e) {
				value = "";
			}

			if (!isFirst) {
				variableText = variableText + ",";
			}
			if (entry.getValue() instanceof String) {
				variableText += name + " = '" + value + "'";
			} else {
				variableText += name + value;
			}

			isFirst = false;
		}
		sql = "UPDATE " + tableName + " SET " + variableText + " WHERE id =" + variables.get("id");
		return sql;
	}

	public void insert(Object obj) {
		String query = process(DatabaseProcess.Insert, obj);
		try  {
			Connection conn = database.connect();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

	}

	public void update(Object obj) {

		String query = process(DatabaseProcess.Update, obj);
		try {
			Connection conn = database.connect();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public <T> ArrayList<HashMap<String, Object>> selectAll(Class<T> objClass) {
		String tableName = objClass.getSimpleName().toLowerCase();
		System.out.println(tableName);
		String query = "SELECT * FROM " + tableName;

		ResultSet rs = null;
		try {
			Connection conn = database.connect();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			return convertResultSet(rs);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	private ArrayList<HashMap<String, Object>> convertResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData data = rs.getMetaData();
		ArrayList<HashMap<String, Object>> objects = new ArrayList<>();
		

		// loop through the result set
		while (rs.next()) {
			HashMap<String, Object> variables = new HashMap<>();
			for (int i = 1; i <= data.getColumnCount(); i++) {

				variables.put(data.getColumnName(i), rs.getObject(data.getColumnName(i)));

			}
			objects.add(variables);
		}

		return objects;
	}

	public void delete(Object obj) {
		String query = process(DatabaseProcess.Delete, obj);
		try {
			Connection conn = database.connect();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public ArrayList<HashMap<String, Object>> select(Object obj) {
		String query = process(DatabaseProcess.Select, obj);
		ArrayList<HashMap<String, Object>> objects = new ArrayList<>();
		ResultSet rs = null;
		try {
			Connection conn = database.connect();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			return convertResultSet(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
