package com.byrfb.kinematic;

import java.util.ArrayList;
import java.util.List;

import com.byrfb.math.Matrix4;
import com.byrfb.math.Quaternion;
import com.byrfb.math.Vector3;

public class DHTable {
	/*
	 * 
	 * burasý dh tablosu için gerekli bilgileri toplar
	 * 
	 * 
	 */

	List<Link> links = new ArrayList<Link>();

	public DHTable() {
		Link link1 = new Link(0, 0, 0, 10);
		Link link2 = new Link(0, 0, 0, 10);
		Link link3 = new Link(0, 0, 0, 10);
		Link link4 = new Link(0, 0, 0, 10);
		Link link5 = new Link(0, 0, 0, 10);
		Link link6 = new Link(0, 0, 0, 10);
		links.add(link1);
		links.add(link2);
		links.add(link3);
		links.add(link4);
		links.add(link5);
		links.add(link6);

	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public static void main(String[] args) {

		DHTable table = new DHTable();
		
	}
}
