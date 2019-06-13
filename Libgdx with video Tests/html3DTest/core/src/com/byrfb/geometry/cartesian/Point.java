package com.byrfb.geometry.cartesian;

import com.badlogic.gdx.math.Vector3;

public class Point
{

	Vector3 coord;
	
	
	public Point() {
		this(0,0,0);
	}
	
	public Point(float x, float y, float z)
	{
		coord = new Vector3();
		coord.x = x;
		coord.y = y;
		coord.z = z;
	}
	public Point(float x, float y)
	{
		this(x,y,0);

	}
	public Point(Vector3 coord)
	{
		this(coord.x,coord.y,coord.z);
	}
	public Point(Point point)
	{
		this(point.coord.x,point.coord.y,point.coord.z);
		
	}
	public Point(double centerX, double centerY) {
		this((float)centerX,(float)centerY,0);
		// TODO Auto-generated constructor stub
	}

	public float x()
	{
		return coord.x;
	}

	public float y()
	{
		return coord.y;
	}
	public float z()
	{
		return coord.z;
	}
	public void setX(double x)
	{
		coord.x = (float)x;
	}
	public void setY(double y)
	{
		coord.y = (float)y;
	}
	public void setZ(double z)
	{
		coord.z = (float)z;
	}
	
	public Vector3 getCoord() {
		return this.coord;
	}
}