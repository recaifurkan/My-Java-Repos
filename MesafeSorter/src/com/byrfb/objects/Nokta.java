package com.byrfb.objects;

public class Nokta implements Comparable<Nokta> {
	private double x;
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}



	private double y;

	public Nokta(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double distance(Nokta n) {
		double dX = this.x - n.x;
		double dY = this.y - n.y;

		double distance = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
//		System.out.println(distance);
		return distance;
	}

	@Override
	public String toString() {
		return ("(" + x + "," + y + ")");
	}

	

	@Override
	public int compareTo(Nokta n1) {
		
		return this.x > n1.x ? 1 : -1;
	}

}
