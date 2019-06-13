package com.byrfb.geometry.delta;

public class Kinematic {
	/*
	 * Gerekli uzunluklar�n belirlenmesi i�in resim
	 * https://www.marginallyclever.com/other/samples/fk-ik-test.html
	 * 
	 * 
	 * Base radius (f)
	 * Bicep length (rf)
	 * Forearm length (re)
	 * End Effector radius (e)
	 * Base to floor distance (b)
	 * 
	 */
	
	 // robot geometry
	 double e = 115.0;     // end effector
	  double f = 457.3;     // base
	  double re = 232.0;
	  double rf = 112.0;
	 
	 // trigonometric ants
	  double sqrt3 = Math.sqrt(3.0);
	  double pi = 3.141592653;    // PI
	  double sin120 = sqrt3/2.0;   
	  double cos120 = -0.5;        
	  double tan60 = sqrt3;
	  double sin30 = 0.5;
	  double tan30 = 1/sqrt3;
	 
	 // forward kinematics: (theta1, theta2, theta3) -> (x0, y0, z0)
	 // returned status: 0=OK, -1=non-existing position
	 int delta_calcForward(float theta1, float theta2, float theta3, double x0, double y0, double z0) {
	     double t = (f-e)*tan30/2;
	     double dtr = pi/(float)180.0;
	 
	     theta1 *= dtr;
	     theta2 *= dtr;
	     theta3 *= dtr;
	 
	     double y1 = -(t + rf*Math.cos(theta1));
	     double z1 = -rf*Math.sin(theta1);
	 
	     double y2 = (t + rf*Math.cos(theta2))*sin30;
	     double x2 = y2*tan60;
	     double z2 = -rf*Math.sin(theta2);
	 
	     double y3 = (t + rf*Math.cos(theta3))*sin30;
	     double x3 = -y3*tan60;
	     double z3 = -rf*Math.sin(theta3);
	 
	     double dnm = (y2-y1)*x3-(y3-y1)*x2;
	 
	     double w1 = y1*y1 + z1*z1;
	     double w2 = x2*x2 + y2*y2 + z2*z2;
	     double w3 = x3*x3 + y3*y3 + z3*z3;
	     
	     // x = (a1*z + b1)/dnm
	     double a1 = (z2-z1)*(y3-y1)-(z3-z1)*(y2-y1);
	     double b1 = -((w2-w1)*(y3-y1)-(w3-w1)*(y2-y1))/2.0;
	 
	     // y = (a2*z + b2)/dnm;
	     double a2 = -(z2-z1)*x3+(z3-z1)*x2;
	     double b2 = ((w2-w1)*x3 - (w3-w1)*x2)/2.0;
	 
	     // a*z^2 + b*z + c = 0
	     double a = a1*a1 + a2*a2 + dnm*dnm;
	     double b = 2*(a1*b1 + a2*(b2-y1*dnm) - z1*dnm*dnm);
	     double c = (b2-y1*dnm)*(b2-y1*dnm) + b1*b1 + dnm*dnm*(z1*z1 - re*re);
	  
	     // discriminant
	     double d = b*b - (double)4.0*a*c;
	     if (d < 0) return -1; // non-existing point
	 
	     z0 = -0.5*(b+Math.sqrt(d))/a;
	     x0 = (a1*z0 + b1)/dnm;
	     y0 = (a2*z0 + b2)/dnm;
	     return 0;
	 }
	 
	 // inverse kinematics
	 // helper functions, calculates angle theta1 (for YZ-pane)
	 int delta_calcAngleYZ(double g, double h, float z0, double theta) {
	     double y1 = -0.5 * 0.57735 * f; // f/2 * tg 30
	     h -= 0.5 * 0.57735    * e;    // shift center to edge
	     // z = a + b*y
	     double a = (g*g + h*h + z0*z0 +rf*rf - re*re - y1*y1)/(2*z0);
	     double b = (y1-h)/z0;
	     // discriminant
	     double d = -(a+b*y1)*(a+b*y1)+rf*(b*b*rf+rf); 
	     if (d < 0) return -1; // non-existing point
	     double yj = (y1 - a*b - Math.sqrt(d))/(b*b + 1); // choosing outer point
	     double zj = a + b*yj;
	     theta = 180.0*Math.atan(-zj/(y1 - yj))/pi + ((yj>y1)?180.0:0.0);
	     return 0;
	 }
	 
	 // inverse kinematics: (x0, y0, z0) -> (theta1, theta2, theta3)
	 // returned status: 0=OK, -1=non-existing position
	 int delta_calcInverse(float x0, float y0, float z0, float theta1, float theta2, float theta3) {
	     theta1 = theta2 = theta3 = 0;
	     int status = delta_calcAngleYZ(x0, y0, z0, theta1);
	     if (status == 0) status = delta_calcAngleYZ(x0*cos120 + y0*sin120, y0*cos120-x0*sin120, z0, theta2);  // rotate coords to +120 deg
	     if (status == 0) status = delta_calcAngleYZ(x0*cos120 - y0*sin120, y0*cos120+x0*sin120, z0, theta3);  // rotate coords to -120 deg
	     return status;
	 }

}
