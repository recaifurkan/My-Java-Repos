package com.byrfb.kinematic;

import com.byrfb.math.Matrix4;
import com.byrfb.math.Vector3;

/*
 * 
 * Burada her bir baðlantýnýn bilgileri tutukup gerekli iþlemler yapýlmakta
 * 
 * 
 * 
 */

public class Link {

	double jointAngle; // oi
	double linkTwist; // alfa i
	double linkOffset; // di
	double linkLenght;// ai

	Matrix4 homogeneousMatrix;

	Vector3 coord = new Vector3();

	

	public Matrix4 getHomogeneousMatrix() {
		return homogeneousMatrix;
	}

	public void setHomogeneousMatrix(Matrix4 homogeneousMatrix) {
		this.homogeneousMatrix = homogeneousMatrix;
	}

	public Link(double jointAngle, double linkTwist, double linkOffset, double linkLenght) {
		super();

		this.linkOffset = linkOffset;
		this.jointAngle = jointAngle;
		this.linkLenght = linkLenght;
		this.linkTwist = linkTwist;
		this.setHomogenousMatrix();

	}
	
	public void update() {
		setHomogenousMatrix();
	}
	
	public void setHomogenousMatrix() {
		float [] values = new float[] {
			(float) Math.cos(Math.toRadians(jointAngle)),
			(float) (-Math.sin(Math.toRadians(jointAngle))*Math.cos(Math.toRadians(linkTwist))),
			(float) (Math.sin(Math.toRadians(jointAngle))*Math.sin(Math.toRadians(linkTwist))),
			(float) (linkLenght*Math.cos(Math.toRadians(jointAngle))),
			(float) Math.sin(Math.toRadians(jointAngle)),
			(float) (Math.cos(Math.toRadians(jointAngle))*Math.cos(Math.toRadians(linkTwist))),
			(float) (-Math.cos(Math.toRadians(jointAngle))*Math.sin(Math.toRadians(linkTwist))),
			(float) (linkLenght*Math.sin(Math.toRadians(jointAngle))),
			(float) 0.0,
			(float) Math.sin(Math.toRadians(linkTwist)),
			(float) Math.cos(Math.toRadians(linkTwist)),
			(float) (linkOffset*1.0),
			(float) 0.0,
			(float) 0.0,
			(float) 0.0,
			(float) 1.0
		};
		this.homogeneousMatrix = new Matrix4(values);
		this.homogeneousMatrix = this.homogeneousMatrix.tra();
		
		
	}
	
	public Vector3 getCoord() {
		return coord;
	}

	public void setCoord(Vector3 coord) {
		this.coord = coord;
	}
	

	public double getLinkOffset() {
		return linkOffset;
	}

	public void setLinkOffset(float linkOffset) {
		this.linkOffset = linkOffset;
	}

	public double getJointAngle() {
		return jointAngle;
	}

	public void setJointAngle(float jointAngle) {
		this.jointAngle = jointAngle;
	}

	public double getLinkLenght() {
		return linkLenght;
	}

	public void setLinkLenght(float linkLenght) {
		this.linkLenght = linkLenght;
	}

	public double getLinkTwist() {
		return linkTwist;
	}

	public void setLinkTwist(float linkTwist) {
		this.linkTwist = linkTwist;
	}


	
	
	
	
	

}
