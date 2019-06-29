package com.byrfb.main;
import com.byrfb.kinematic.DHTable;
import com.byrfb.kinematic.ForwardKinematic;

public class Robot {
	
	/*
	 * burada dh tablosu ile forward kinematik ile baðlantýsý yapýlýp gerekli iþlemler yaoýlmakta
	 * 
	 * 
	 * 
	 */
	

	DHTable table = new DHTable();
	ForwardKinematic kinematic;
	
	
	public Robot() {
		
		kinematic = new ForwardKinematic(table.getLinks());
		kinematic.calculateEndPoint();
	}
	
	
	public void update() {
		kinematic.calculateEndPoint();
		
	}
	
	public DHTable getTable() {
		return table;
	}


	public void setTable(DHTable table) {
		this.table = table;
	}


	public ForwardKinematic getKinematic() {
		return kinematic;
	}


	public void setKinematic(ForwardKinematic kinematic) {
		this.kinematic = kinematic;
	}

	public static void main(String[] args) {
		DHTable table = new DHTable();
		ForwardKinematic kinematic = new ForwardKinematic(table.getLinks());
		kinematic.calculateEndPoint();
		System.out.println(kinematic.getThLinkCoord(4));
		

	}

}
