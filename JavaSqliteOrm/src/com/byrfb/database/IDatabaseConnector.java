package com.byrfb.database;

import java.sql.Connection;
/*
 * connectorun sahip olmas� gereken metotlar�n bulundupu yer
 * 
 * 
 */
public interface IDatabaseConnector {
	public Connection connect();
}
