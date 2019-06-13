package com.byrfb.geometry.utils;

import com.byrfb.geometry.cartesian.Point;

public class MathLib {
	
	public static double calculateAngle(double P1X, double P1Y, double P2X, double P2Y,
            double P3X, double P3Y){

        double numerator = P2Y*(P1X-P3X) + P1Y*(P3X-P2X) + P3Y*(P2X-P1X);
        double denominator = (P2X-P1X)*(P1X-P3X) + (P2Y-P1Y)*(P1Y-P3Y);
        double ratio = numerator/denominator;
        
        

        double angleRad = Math.atan(ratio);
        double angleDeg = (angleRad*180)/Math.PI;

        if(angleDeg<0){
            angleDeg = 180+angleDeg;
        }
        angleDeg = 180 - 2* angleDeg;
        
        if(angleDeg < 0) {
        	angleDeg = 360 + angleDeg;
        }

        return angleDeg;
    }
	
	public static float distance(float x1 , float y1, float x2 , float y2) {
		float xDiff = x1 - x2;
		float yDiff = y1 - y2;
		float distance = (float) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		System.out.println("Distance between From and To = " + distance);
		return distance;
	}
	
	public static float distance(Point point1,Point point2) {
		float x1 = point1.x();
		float x2 = point2.x();
		float y1 = point1.y();
		float y2 = point2.y();
		float xDiff = x1 - x2;
		float yDiff = y1 - y2;
		float distance = (float) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		System.out.println("Distance between From and To = " + distance);
		return distance;
	}

}
