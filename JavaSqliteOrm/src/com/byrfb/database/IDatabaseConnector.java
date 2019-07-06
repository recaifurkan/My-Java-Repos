package com.byrfb.database;

import java.sql.Connection;
/*
 * connectorun sahip olmasý gereken metotlarýn bulundupu yer
 * 
 * 
 */
public interface IDatabaseConnector {
	public Connection connect();
}
