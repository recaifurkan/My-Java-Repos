package com.byrfb.geometry.cartesian;

import java.util.ArrayList;
import java.util.List;

public class Line {
	
	
	public static List<Point> line(Point startPoint,Point endPoint) {
		return line(startPoint.x(),startPoint.y(),startPoint.z(),endPoint.x(),endPoint.y(),endPoint.z());
	}

	public static List<Point> line(float x1, float y1, float z1, float x2, float y2, float z2) {
		List<Point> points = new ArrayList<Point>();
//		points.add(new Point(x1, y1, z1));

		double dx = Math.abs(x2 - x1);
		double dy = Math.abs(y2 - y1);
		double dz = Math.abs(z2 - z1);
		double xs;
		double ys;
		double zs;
	    if (x2 > x1) 
	        xs = 1;
	    else
	        xs = -1;
	    if (y2 > y1)
	        ys = 1;
	    else
	        ys = -1;
	    if (z2 > z1) 
	        zs = 1;
		else
			zs = -1;

		if (dx >= dy && dx >= dz) {
			double p1 = 2 * dy - dx;
			double p2 = 2 * dz - dx;
			for (int i = 0; i < dx ; i++) {
				x1 += xs;
				if (p1 >= 0) {
					y1 += ys;
					p1 -= 2 * dx;
				}

				if (p2 >= 0) {
					z1 += zs;
					p2 -= 2 * dx;
				}

				p1 += 2 * dy;
				p2 += 2 * dz;
				points.add(new Point(x1, y1, z1));

			}

		}

		else if (dy >= dx && dy >= dz) {
			double p1 = 2 * dx - dy;
			double p2 = 2 * dz - dy;
			for (int i = 0; i < dy ; i++) {

				y1 += ys;
				if (p1 >= 0) {
					x1 += xs;
					p1 -= 2 * dy;
				}

				if (p2 >= 0) {
					z1 += zs;
					p2 -= 2 * dy;
				}

				p1 += 2 * dx;
				p2 += 2 * dz;
				points.add(new Point(x1, y1, z1));

			}

		}

		else {

			double p1 = 2 * dy - dz;
			double p2 = 2 * dx - dz;
			for (int i = 0; i < dz ; i++) {
				z1 += zs;
				if (p1 >= 0) {
					y1 += ys;
					p1 -= 2 * dz;

				}

				if (p2 >= 0) {
					x1 += xs;
					p2 -= 2 * dz;

				}

				p1 += 2 * dy;
				p2 += 2 * dx;
				points.add(new Point(x1, y1, z1));

			}

		} 

		return points;

	}
	    
	    


//	  
//	    // Driving axis is X-axis" 

//	  
//	    // Driving axis is Y-axis" 

//	  
//	    // Driving axis is Z-axis" 

//	    return ListOfPoints 

}
