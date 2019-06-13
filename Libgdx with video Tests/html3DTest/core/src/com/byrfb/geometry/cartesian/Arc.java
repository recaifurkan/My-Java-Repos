package com.byrfb.geometry.cartesian;

import java.util.ArrayList;
import java.util.List;


import com.badlogic.gdx.math.MathUtils;


public class Arc {
	


	/**
	 * Calls {@link #arc(float, float, float, float, float, int)} by estimating the
	 * number of segments needed for a smooth arc.
	 */
	public static List<Point> arc(Point center, float radius, float start, float degrees) {
		return arc(center, radius, start, degrees,
				Math.max(1, (int) (6 * (float) Math.cbrt(radius) * (degrees / 360.0f))));
	}

	/** Draws an arc using {@link ShapeType#Line} or {@link ShapeType#Filled}. */
	public static List<Point> arc(Point center, float radius, float start, float degrees, int segments) {
		if (segments <= 0)
			throw new IllegalArgumentException("segments must be > 0.");
		List<Point> pOutPut = new ArrayList<Point>();
		float theta = (2 * MathUtils.PI * (degrees / 360.0f)) / segments;
		float cos = MathUtils.cos(theta);
		float sin = MathUtils.sin(theta);
		float cx = radius * MathUtils.cos(start * MathUtils.degreesToRadians);
		float cy = radius * MathUtils.sin(start * MathUtils.degreesToRadians);
//		System.out.println("theta  = "  + theta);
//		System.out.println("segments  = "  + segments);

		Point firstPoint = new Point(center.x() + cx, center.y() + cy, 0);
		pOutPut.add(firstPoint);
		for (int i = 0; i < segments; i++) {

			float temp = cx;
			cx = cos * cx - sin * cy;
			cy = sin * temp + cos * cy;

			pOutPut.add(new Point(center.x() + cx, center.y() + cy, 0));
		}

//			Vector3 tmp1 = pOutPut.get(0).getCoord();
//			Vector3 tmp2 = center.getCoord();
//			Vector3 tmp3 = pOutPut.get(pOutPut.size()-1).getCoord();
//		
//			
//			double angle = calculateAngle(tmp1.x, tmp1.y, tmp2.x, tmp2.y, tmp3.x, tmp3.y);
//			
//	
//			System.out.println(angle  + "-----" +  degrees);

		return pOutPut;
	}
	
	public static List<Point> arc(Point startPoint, Point endPoint, boolean clockWise, float radius
			) {
		
		float pMinDistance= 20;
		boolean shortest = false;
		return arc(startPoint,endPoint,radius,pMinDistance,shortest,clockWise);
	}
	

	/*
	 * 
	 * bu metot baþlangýç bitiþ v radius alarak çizer
	 */
	public static List<Point> arc(Point startPoint, Point endPoint, float radius, float pMinDistance, boolean shortest,
			boolean clockWise) {

		List<Point> pOutPut = new ArrayList<Point>();

		// Calculate the middle of the two given points.
		Point mPoint = new Point(startPoint.x() + endPoint.x(), startPoint.y() + endPoint.y());
		mPoint.getCoord().x /= 2.0f;
		mPoint.getCoord().y /= 2.0f;
		System.out.println("Middle Between From and To = " + mPoint);

		// Calculate the distance between the two points
		float xDiff = endPoint.x() - startPoint.x();
		float yDiff = endPoint.y() - startPoint.y();
		float distance = (float) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		System.out.println("Distance between From and To = " + distance);

		if (radius * 2.0f < distance) {
			throw new IllegalArgumentException("The radius is too small! The given points wont fall on the circle.");
		}

		Point circleMiddlePoint = getCenterPoint(startPoint, endPoint, radius, clockWise);
		

		// Calculate the two reference angles
		float angle1 = (float) Math.atan2(startPoint.y() - circleMiddlePoint.y(), startPoint.x() - circleMiddlePoint.x());
		float angle2 = (float) Math.atan2(endPoint.y() - circleMiddlePoint.y(), endPoint.x() - circleMiddlePoint.x());

		// Calculate the step.
		float step = pMinDistance / radius;
		System.out.println("Step = " + step);

		// Swap them if needed
		if (angle1 > angle2) {
			float temp = angle1;
			angle1 = angle2;
			angle2 = temp;

		}
		boolean flipped = false;
		if (!shortest) {
			if (angle2 - angle1 < Math.PI) {
				float temp = angle1;
				angle1 = angle2;
				angle2 = temp;
				angle2 += Math.PI * 2.0f;
				flipped = true;
			}
		}
		for (float f = angle1; f < angle2; f += step) {
			Point p = new Point((float) Math.cos(f) * radius + circleMiddlePoint.x(),
					(float) Math.sin(f) * radius + circleMiddlePoint.y());
			pOutPut.add(p);
		}
		if (flipped ^ clockWise) {
			pOutPut.add(startPoint);
		} else {
			pOutPut.add(endPoint);
		}

		return pOutPut;
	}
	
	public static Point getCenterPoint(Point startPoint, Point endPoint, float radius , boolean clockWise) {
		// Calculate the middle of the expected curve.
				float factor = (float) Math.sqrt((radius * radius)
						/ ((endPoint.x() - startPoint.x()) * (endPoint.x() - startPoint.x()) + (endPoint.y() - startPoint.y()) * (endPoint.y() - startPoint.y()))
						- 0.25f);
				Point circleMiddlePoint = new Point(0, 0);
				if (clockWise) {
					circleMiddlePoint.getCoord().x = 0.5f * (startPoint.x() + endPoint.x()) + factor * (endPoint.y() - startPoint.y());
					circleMiddlePoint.getCoord().y = 0.5f * (startPoint.y() + endPoint.y()) + factor * (startPoint.x() - endPoint.x());
				} else {
					circleMiddlePoint.getCoord().x = 0.5f * (startPoint.x() + endPoint.x()) - factor * (endPoint.y() - startPoint.y());
					circleMiddlePoint.getCoord().y = 0.5f * (startPoint.y() + endPoint.y()) - factor * (startPoint.x() - endPoint.x());
				}
				System.out.println("Middle = " + circleMiddlePoint);
				return circleMiddlePoint;
	}

	
	/*
	 * 
	 * En doðru þekilde doðruyu çizen bu 
	 * baþlanguç noktasý
	 * merkez noktasý
	 * bitiþ noktasýný alýp ta çizme iþlemi yapýyor
	 * ze ekseninde de hesaplama yapar
	 */
	public static List<Point> arc(Point startPoint, Point center, Point endpoint, boolean clockWise) {
		// System.out.println("Arc from " + current.toString() + " to " +
		// endpoint.toString() + " with center " + center);
		// Calculate the middle of the expected curve.
	

		List<Point> points = new ArrayList<Point>();
		double curveSection = 2.0;

		// angle variables.
		double angleA;
		double angleB;
		double angle;
		double radius;
		double length;

		// delta variables.
		double aX;
		double aY;
		double bX;
		double bY;

		// figure out our deltas
		Point current = startPoint;
		aX = current.x() - center.x();
		aY = current.y() - center.y();
		bX = endpoint.x() - center.x();
		bY = endpoint.y() - center.y();

		// clockWise
		if (clockWise) {
			angleA = Math.atan2(bY, bX);
			angleB = Math.atan2(aY, aX);
		}
		// CounterclockWise
		else {
			angleA = Math.atan2(aY, aX);
			angleB = Math.atan2(bY, bX);
		}

		// Make sure angleB is always greater than angleA
		// and if not add 2PI so that it is (this also takes
		// care of the special case of angleA == angleB,
		// ie we want a complete circle)
		if (angleB <= angleA)
			angleB += 2 * Math.PI;
		angle = angleB - angleA;
		// calculate a couple useful things.
		radius = Math.sqrt(aX * aX + aY * aY);
		length = radius * angle;

		// for doing the actual move.
		int steps;
		int s;
		int step;

		// Maximum of either 2.4 times the angle in radians
		// or the length of the curve divided by the curve section constant
		steps = (int) Math.ceil(Math.max(angle * 2.4, length / curveSection));

		// this is the real draw action.

		double arcStartZ = current.z();
		for (s = 1; s <= steps; s++) {
			Point newPoint = new Point();
			// Forwards for CCW, backwards for CW
			if (!clockWise)
				step = s;
			else
				step = steps - s;

			// calculate our waypoint.
			newPoint.setX(center.x() + radius * Math.cos(angleA + angle * ((double) step / steps)));
			newPoint.setY(center.y() + radius * Math.sin(angleA + angle * ((double) step / steps)));
			newPoint.setZ(arcStartZ + (endpoint.z() - arcStartZ) * s / steps);

			// start the move
			points.add(newPoint);
		}

		return points;
//		drawLine(points)
	}
	
	
	/*
	 * buradaki isHighDegree parametresi çemberin merkezini bulurken ayný radiusa sahip 2 tane merkez noktasý olabileceði için 
	 * koyulmultur.
	 * eðer saatin tersi yöndeyse veya saat yönndeyse o deðiþtirilerek bulunur
	 * 
	 */
	public static List<Point> arc(Point startPoint, Point endpoint,float radius, boolean clockWise) {
		// System.out.println("Arc from " + current.toString() + " to " +
		// endpoint.toString() + " with center " + center);
		// Calculate the middle of the expected curve.
		
		Point center = getCenterPoint(startPoint, endpoint, radius, clockWise);
		
		return arc(startPoint, center, endpoint, clockWise);
	

	
	}

	// Execute an arc in offset mode format. position == current xyz, target ==
	// target xyz,
	// offset == offset from current xyz, axis_X defines circle plane in tool space,
	// axis_linear is
	// the direction of helical travel, radius == circle radius, isclockWise
	// boolean. Used
	// for vector transformation direction.
	// The arc is approximated by generating a huge number of tiny, linear segments.
	// The chordal tolerance
	// of each segment is configured in settings.arc_tolerance, which is defined to
	// be the maximum normal
	// distance from segment to the circle when the end points both lie on the
	// circle.

	/*
	 * þimdiki posizyon hedef posizyon verilen radius deðeri saat yönü mü tersi mi
	 * feed rate ilerleme hýzý
	 * 
	 * 
	 */
	
//	public static List<Point> arc(Point positionPoint, Point targetPoint, 
//			float radius,boolean is_clockWise_arc){
//		Point offsetPoint = new Point();
//		return arc(positionPoint,targetPoint,offsetPoint,radius,is_clockWise_arc);
//		
//	}
	
//	public static List<Point> arc(Point positionPoint, Point targetPoint, Point offsetPoint, 
//			float radius,boolean is_clockWise_arc){
//		int feed_rate = 10;
//		int inverse_feed_rate = 0;
//		return arc(positionPoint,targetPoint,offsetPoint,radius,feed_rate,inverse_feed_rate,is_clockWise_arc);
//		
//	}
//	
	
//	public static List<Point> arc(Point positionPoint, Point targetPoint, Point offsetPoint, float radius, float feed_rate, int invert_feed_rate,
//			boolean is_clockWise_arc){
//		int axis_0 = 0;
//		int axis_1 = 1;
//		int axis_linear = 2;
//		
//		return arc(positionPoint,targetPoint,offsetPoint,radius,feed_rate,invert_feed_rate,axis_0,axis_1,axis_linear,is_clockWise_arc);
//		
//	}
	
	
	/*
	 * 
	 * 
	 * bu alt taraf direk grbl üzeinden kopyalandý ama iþlem olarak ayný iþlemi yapan yukarda metot var çalýþan
	 * 
	 */

//	public static List<Point> arc(Point positionPoint, Point targetPoint, Point offsetPoint, float radius, float feed_rate, int invert_feed_rate,
//			int axis_0, int axis_1, int axis_linear, boolean is_clockWise_arc)
//
//	{
//		
//		
////		float factor = (float) Math.sqrt((radius * radius)
////				/ ((targetPoint.x() - positionPoint.x()) * (targetPoint.x() - positionPoint.x()) + (targetPoint.y() - positionPoint.y()) * (targetPoint.y() - positionPoint.y()))
////				- 0.25f);
////		Point circleMiddlePoint = new Point(0, 0);
////		if (is_clockWise_arc) {
////			circleMiddlePoint.getCoord().x = 0.5f * (positionPoint.x() + targetPoint.x()) + factor * (targetPoint.y() - positionPoint.y());
////			circleMiddlePoint.getCoord().y = 0.5f * (positionPoint.y() + targetPoint.y()) + factor * (positionPoint.x() - targetPoint.x());
////		} else {
////			circleMiddlePoint.getCoord().x = 0.5f * (positionPoint.x() + targetPoint.x()) - factor * (targetPoint.y() - positionPoint.y());
////			circleMiddlePoint.getCoord().y = 0.5f * (positionPoint.y() + targetPoint.y()) - factor * (positionPoint.x() - targetPoint.x());
////		}
//		
//		
//		
//		Float[] position = new Float[3];
//		position[0] = positionPoint.x();
//		position[1] = positionPoint.y();
//		position[2] = positionPoint.z();
//		
//		Float[] target = new Float[3];
//		target[0] = targetPoint.x();
//		target[1] = targetPoint.y();
//		target[2] = targetPoint.z();
//		
//		Float[] offset = new Float[3];
////		offset[0] = circleMiddlePoint.getCoord().sub(positionPoint.getCoord()).x;
////		offset[1] = circleMiddlePoint.getCoord().sub(positionPoint.getCoord()).y;
////		offset[2] = circleMiddlePoint.getCoord().sub(positionPoint.getCoord()).z;
//		offset[0] = offsetPoint.x();
//		offset[1] = offsetPoint.y();
//		offset[2] = offsetPoint.z();
//		
//		List<Point> points = new ArrayList<Point>();
//
//		float ARC_ANGULAR_TRAVEL_EPSILON = Float.valueOf("5E-7");
//		float N_ARC_CORRECTION = 12;
//		float arc_tolerance = 5;
//
//		float center_axis0 = position[axis_0] + offset[axis_0];
//		float center_axis1 = position[axis_1] + offset[axis_1];
//		float r_axis0 = -offset[axis_0]; // Radius vector from center to current location
//		float r_axis1 = -offset[axis_1];
//		float rt_axis0 = target[axis_0] - center_axis0;
//		float rt_axis1 = target[axis_1] - center_axis1;
//
//		// CCW angle between position and target from circle center. Only one atan2()
//		// trig computation required.
//		float angular_travel = (float) Math.atan2(r_axis0 * rt_axis1 - r_axis1 * rt_axis0,
//				r_axis0 * rt_axis0 + r_axis1 * rt_axis1);
//		if (is_clockWise_arc) { // Correct atan2 output per direction
//			if (angular_travel >= -ARC_ANGULAR_TRAVEL_EPSILON) {
//				angular_travel -= 2 * Math.PI;
//			}
//		} else {
//			if (angular_travel <= ARC_ANGULAR_TRAVEL_EPSILON) {
//				angular_travel += 2 * Math.PI;
//			}
//		}
//
//		// NOTE: Segment end points are on the arc, which can lead to the arc diameter
//		// being smaller by up to
//		// (2x) settings.arc_tolerance. For 99% of users, this is just fine. If a
//		// different arc segment fit
//		// is desired, i.e. least-squares, midpoint on arc, just change the
//		// mm_per_arc_segment calculation.
//		// For the intended uses of Grbl, this value shouldn't exceed 2000 for the
//		// strictest of cases.
//		double segments = Math.floor(
//				Math.abs(0.5 * angular_travel * radius) / Math.sqrt(arc_tolerance * (2 * radius - arc_tolerance)));
//
//		if (segments > 0) {
//			// Multiply inverse feed_rate to compensate for the fact that this movement is
//			// approximated
//			// by a number of discrete segments. The inverse feed_rate should be correct for
//			// the sum of
//			// all segments.
//			if (invert_feed_rate > 0) {
//				feed_rate *= segments;
//			}
//
//			float theta_per_segment = (float) (angular_travel / segments);
////			float linear_per_segment = (float) ((target[axis_linear] - position[axis_linear]) / segments);
//
//			/*
//			 * Vector rotation by transformation matrix: r is the original vector, r_T is
//			 * the rotated vector, and phi is the angle of rotation. Solution approach by
//			 * Jens Geisler. r_T = [cos(phi) -sin(phi); sin(phi) cos(phi] * r ;
//			 * 
//			 * For arc generation, the center of the circle is the axis of rotation and the
//			 * radius vector is defined from the circle center to the initial position. Each
//			 * line segment is formed by successive vector rotations. Single precision
//			 * values can accumulate error greater than tool precision in rare cases. So,
//			 * exact arc path correction is implemented. This approach avoids the problem of
//			 * too many very expensive trig operations [sin(),cos(),tan()] which can take
//			 * 100-200 usec each to compute.
//			 * 
//			 * Small angle approximation may be used to reduce computation overhead further.
//			 * A third-order approximation (second order sin() has too much error) holds for
//			 * most, if not, all CNC applications. Note that this approximation will begin
//			 * to accumulate a numerical drift error when theta_per_segment is greater than
//			 * ~0.25 rad(14 deg) AND the approximation is successively used without
//			 * correction several dozen times. This scenario is extremely unlikely, since
//			 * segment lengths and theta_per_segment are automatically generated and scaled
//			 * by the arc tolerance setting. Only a very large arc tolerance setting,
//			 * unrealistic for CNC applications, would cause this numerical drift error.
//			 * However, it is best to set N_ARC_CORRECTION from a low of ~4 to a high of ~20
//			 * or so to avoid trig operations while keeping arc generation accurate.
//			 * 
//			 * This approximation also allows mc_arc to immediately insert a line segment
//			 * into the planner without the initial overhead of computing cos() or sin(). By
//			 * the time the arc needs to be applied a correction, the planner should have
//			 * caught up to the lag caused by the initial mc_arc overhead. This is important
//			 * when there are successive arc motions.
//			 */
//			// Computes: cos_T = 1 - theta_per_segment^2/2, sin_T = theta_per_segment -
//			// theta_per_segment^3/6) in ~52usec
//			float cos_T = (float) (2.0 - theta_per_segment * theta_per_segment);
//			float sin_T = (float) (theta_per_segment * 0.16666667 * (cos_T + 4.0));
//			cos_T *= 0.5;
//
//			float sin_Ti;
//			float cos_Ti;
//			float r_axisi;
//			int i;
//			int count = 0;
//
//			for (i = 0; i < segments; i++) { // Increment (segments-1).
//
//				if (count < N_ARC_CORRECTION) {
//					// Apply vector rotation matrix. ~40 usec
//					r_axisi = r_axis0 * sin_T + r_axis1 * cos_T;
//					r_axis0 = r_axis0 * cos_T - r_axis1 * sin_T;
//					r_axis1 = r_axisi;
//					count++;
//				} else {
//					// Arc correction to radius vector. Computed only every N_ARC_CORRECTION
//					// increments. ~375 usec
//					// Compute exact location by applying transformation matrix from initial radius
//					// vector(=-offset).
//					cos_Ti = (float) Math.cos(i * theta_per_segment);
//					sin_Ti = (float) Math.sin(i * theta_per_segment);
//					r_axis0 = -offset[axis_0] * cos_Ti + offset[axis_1] * sin_Ti;
//					r_axis1 = -offset[axis_0] * sin_Ti - offset[axis_1] * cos_Ti;
//					count = 0;
//				}
//
//				// Update arc_target location
//				position[axis_0] = center_axis0 + r_axis0;
//				position[axis_1] = center_axis1 + r_axis1;
////				position[axis_linear] += linear_per_segment;
//				
//				points.add(new Point(position[axis_0],position[axis_1]));
//
////	        mc_line(position, feed_rate, invert_feed_rate);
//
//				// Bail mid-circle on system abort. Runtime command check already performed by
//				// mc_line.
//
//			}
//			
//		}
//		return points;
//
//	}
//	
//	public static List<Point> calculateArc(Point positionPoint, Point targetPoint,float radius,boolean isCw) {
//		// [G2/3 Errors All-Modes]: Feed rate undefined.
//        // [G2/3 Radius-Mode Errors]: No axis words in selected plane. Target point is same as current.
//        // [G2/3 Offset-Mode Errors]: No axis words and/or offsets in selected plane. The radius to the current 
//        //   point and the radius to the target point differs more than 0.002mm (EMC def. 0.5mm OR 0.005mm and 0.1% radius).   
//        // [G2/3 Full-Circle-Mode Errors]: NOT SUPPORTED. Axis words exist. No offsets programmed. P must be an integer.        
//        // NOTE: Both radius and offsets are required for arc tracing and are pre-computed with the error-checking.
//      
//
//        // Calculate the change in position along each selected axis
//        float x,y;
//        x = targetPoint.x()-positionPoint.x(); // Delta x between current position and target
//        y = targetPoint.y()-positionPoint.y(); // Delta y between current position and target
//
//          // Convert radius value to proper units.
//          /*  We need to calculate the center of the circle that has the designated radius and passes
//              through both the current position and the target position. This method calculates the following
//              set of equations where [x,y] is the vector from current to target position, d == magnitude of 
//              that vector, h == hypotenuse of the triangle formed by the radius of the circle, the distance to
//              the center of the travel vector. A vector perpendicular to the travel vector [-y,x] is scaled to the 
//              length of h [-y/d*h, x/d*h] and added to the center of the travel vector [x/2,y/2] to form the new point 
//              [i,j] at [x/2-y/d*h, y/2+x/d*h] which will be the center of our arc.
//  
//              d^2 == x^2 + y^2
//              h^2 == r^2 - (d/2)^2
//              i == x/2 - y/d*h
//              j == y/2 + x/d*h
//  
//                                                                   O <- [i,j]
//                                                                -  |
//                                                      r      -     |
//                                                          -        |
//                                                       -           | h
//                                                    -              |
//                                      [0,0] ->  C -----------------+--------------- T  <- [x,y]
//                                                | <------ d/2 ---->|
//            
//              C - Current position
//              T - Target position
//              O - center of circle that pass through both C and T
//              d - distance from C to T
//              r - designated radius
//              h - distance from center of CT to O
//  
//              Expanding the equations:
//
//              d -> sqrt(x^2 + y^2)
//              h -> sqrt(4 * r^2 - x^2 - y^2)/2
//              i -> (x - (y * sqrt(4 * r^2 - x^2 - y^2)) / sqrt(x^2 + y^2)) / 2 
//              j -> (y + (x * sqrt(4 * r^2 - x^2 - y^2)) / sqrt(x^2 + y^2)) / 2
// 
//              Which can be written:
//  
//              i -> (x - (y * sqrt(4 * r^2 - x^2 - y^2))/sqrt(x^2 + y^2))/2
//              j -> (y + (x * sqrt(4 * r^2 - x^2 - y^2))/sqrt(x^2 + y^2))/2
//  
//              Which we for size and speed reasons optimize to:
//
//              h_x2_div_d = sqrt(4 * r^2 - x^2 - y^2)/sqrt(x^2 + y^2)
//              i = (x - (y * h_x2_div_d))/2
//              j = (y + (x * h_x2_div_d))/2       
//          */      
//
//          // First, use h_x2_div_d to compute 4*h^2 to check if it is negative or r is smaller
//          // than d. If so, the sqrt of a negative number is complex and error out.
//          float h_x2_div_d = (float) (4.0 * radius*radius - x*x - y*y);
//
//  
//          // Finish computing h_x2_div_d.
//          h_x2_div_d = (float) (-Math.sqrt(h_x2_div_d) / hypot_f(x,y)); // == -(h * 2 / d)
//          // Invert the sign of h_x2_div_d if the circle is counter clockWise (see sketch below)
//          if (!isCw) { h_x2_div_d = -h_x2_div_d; }  
//
//          /* The counter clockWise circle lies to the left of the target direction. When offset is positive,
//             the left hand circle will be generated - when it is negative the right hand circle is generated.
//        
//                                                                 T  <-- Target position
//                                                 
//                                                                 ^ 
//                      clockWise circles with this center         |          clockWise circles with this center will have
//                      will have > 180 deg of angular travel      |          < 180 deg of angular travel, which is a good thing!
//                                                       \         |          /   
//          center of arc when h_x2_div_d is positive ->  x <----- | -----> x <- center of arc when h_x2_div_d is negative
//                                                                 |
//                                                                 |
//                                                 
//                                                                 C  <-- Current position                                
//          */  
//          // Negative R is g-code-alese for "I want a circle with more than 180 degrees of travel" (go figure!), 
//          // even though it is advised against ever generating such circles in a single line of g-code. By 
//          // inverting the sign of h_x2_div_d the center of the circles is placed on the opposite clockWise of the line of
//          // travel and thus we get the unadvisably long arcs as prescribed.
//             
//          // Complete the operation by calculating the actual center of the arc
//          Point center = new Point();
//          center.getCoord().x =  (float) (0.5*(x-(y*h_x2_div_d)));
//          center.getCoord().y =  (float) (0.5*(y+(x*h_x2_div_d)));
//          
//          return arc(positionPoint, targetPoint, center, radius, 
//                  true); 
//        
//        
//        } 
//     
//		
//		
//		
//		
//		
//	
//
//static float hypot_f(float x, float y) { return (float) (Math.sqrt(x*x + y*y)); }
}
