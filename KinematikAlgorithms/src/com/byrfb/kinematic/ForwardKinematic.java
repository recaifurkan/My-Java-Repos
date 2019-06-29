package com.byrfb.kinematic;

import java.util.List;

import com.byrfb.math.Matrix4;
import com.byrfb.math.Vector3;

public class ForwardKinematic {
	
	
	/*
	 * forward kinematik hesaplamalarý burada yapýlýr
	 * 
	 * 
	 * 
	 */
	List<Link> links;
	
	
	Vector3 coord = new Vector3();
	Vector3 orientation = new Vector3();
	/*
	 * x-> yaw
	 * y-> pitch
	 * z->raw
	 */
	
	

	public ForwardKinematic(List<Link> links) {
		super();
		this.links = links;
	}
	
	public void calculateEndPoint()
	{
		Matrix4 totalHomogenous = new Matrix4();

		for (Link link : links) {
			link.update();
			totalHomogenous.mul(link.homogeneousMatrix);
			link.coord = totalHomogenous.getTranslation(link.coord);
		}

		Matrix4 tmp = totalHomogenous.cpy();


		totalHomogenous.getTranslation(coord);
		
		
		
		orientation.y = (float) Math.atan2(
				-tmp.val[tmp.M20],
				Math.sqrt((tmp.val[tmp.M00] * tmp.val[tmp.M00]) + (tmp.val[tmp.M10] * tmp.val[tmp.M10])));
		orientation.y = (float) Math.toDegrees(orientation.y);
		
		orientation.x = (float) Math.atan2(
				tmp.val[tmp.M21] / orientation.y , 
				tmp.val[tmp.M20] / orientation.y);
		orientation.x = (float) Math.toDegrees(orientation.x);
		
		orientation.z = (float) Math.atan2(
				tmp.val[tmp.M12] / orientation.x,
				tmp.val[tmp.M02] / orientation.x);
		orientation.z = (float) Math.toDegrees(orientation.z);
		
	}
	
	public Vector3 getThLinkCoord(int link) {
		return links.get(link).coord;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Position: " +  this.coord +" Orientation: "+ this.orientation;
	}

}
