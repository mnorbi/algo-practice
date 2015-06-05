import java.util.*;

/**

Problem Statement
    
Little Timmy has a rectangular frame and several shapes cut out from cardboard. He tries to fit each of the shapes into the frame (one shape at a time). Sometimes the piece fits easily, sometimes it is clear that the shape is too big to fit... and sometimes Timmy just doesn't know. Then he always comes to ask you for help.
You decided to write a program that will answer Timmy's questions.
You will be given the width and height of the frame and a String[] shapes describing Tommy's shapes. Each of the shapes is either a circle or a rectangle. Each element of shapes is of the one of the following forms:
"CIRCLE RADIUS", where RADIUS is the radius of the circle.
"RECTANGLE WIDTH LENGTH", where WIDTH and LENGTH are the dimensions of the rectangle.
Your method is supposed to return a String[] results, where the i-th element of results is "YES" or "NO", depending on whether the i-th shape fits into the empty frame.
Definition
    
Class:
PackingShapes
Method:
tryToFit
Parameters:
int, int, String[]
Returns:
String[]
Method signature:
String[] tryToFit(int width, int height, String[] shapes)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
The shapes may be rotated arbitrarily.
-
The shapes may touch the frame, and they may even have a common part of the boundary.
Constraints
-
width and height are between 1 and 1000, inclusive.
-
shapes contains between 1 and 50 elements, inclusive.
-
Each element of shapes is of one of the following forms:
"CIRCLE RADIUS"
"RECTANGLE WIDTH LENGTH"
-
All numbers in shapes are integers between 1 and 1000, inclusive, with no leading zeroes.
Examples
0)

    
100
100
{"RECTANGLE 3 3", 
 "RECTANGLE 3 230",
 "RECTANGLE 140 1"}
Returns: {"YES", "NO", "YES" }
The first rectangle clearly fits, but the second one clearly doesn't. The third one can be placed inside the frame after it is rotated 45 degrees.
1)

    
100
100
{"RECTANGLE 100 100",
 "CIRCLE 50"}
Returns: {"YES", "YES" }
Touching the frame is allowed.
2)

    
10
100
{"RECTANGLE 99 9",
 "CIRCLE 22"}
Returns: {"YES", "NO" }
The rectangle can be rotated, but the circle is too large.
3)

    
170
900
{"RECTANGLE 200 700",
 "RECTANGLE 3 910",
 "RECTANGLE 1000 7",
 "CIRCLE 5",
 "CIRCLE 50",
 "CIRCLE 500",
 "RECTANGLE 1000 99"}
Returns: {"NO", "YES", "NO", "YES", "YES", "NO", "NO" }

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class PackingShapes{
	public String[] tryToFit(int width, int height, String[] shapes){
		if (width > height){
			int tmp = width;
			width = height;
			height = tmp;
		}
		int n = shapes.length;
		String[] ret = new String[n];
		for(int i = 0; i<n; i++){
			ret[i] = parse(shapes[i]).tryToFit(width, height);
		}
		return ret;
	}
	Shape parse(String shapeStr){
		if (shapeStr.startsWith("CIRCLE")){
			return new Circle(shapeStr);
		} else return new Rectangle(shapeStr);
	}
	interface Shape{
		static final String YES = "YES";
		static final String NO = "NO";
		
		String tryToFit(int width, int height);
	}
	static class Circle implements Shape{
		private final int radius;
		Circle(String shapeStr){
			Scanner sc = new Scanner(shapeStr); sc.next();
			this.radius = sc.nextInt();
		}
		public String tryToFit(int width, int height){
			if (radius <= width && radius <= height) return YES;
			return NO;
		}
	}
	static class Rectangle implements Shape{
		private int width;
		private int height;
		Rectangle(String shapeStr){
			Scanner sc = new Scanner(shapeStr); sc.next();
			this.width = sc.nextInt();
			this.height = sc.nextInt();
			if (this.width > this.height){
				int tmp = this.width;
				this.width = this.height;
				this.height = tmp;
			}			
		}
		
		public String tryToFit(int w, int h){
			if (width > w && height > h) return NO;
			if (width <= w && height <= h) return YES;
			Double rotRad = rotRad(w, h);
			if (rotRad == null) return NO;
			Point p2 = new Point(width/2.0, height/2.0).cwRot(rotRad);
			if (Double.compare(p2.x, w/2.0) <= 0 &&
			    Double.compare(p2.y, -h/2.0) >= 0) return YES;
			return NO;
		}
		
		Double rotRad(int w, int h){
			Double ret = null;
			double lo = 0;
			double hi = Math.PI/4.0;
			
			for(int i = 0; i < 100; i++){
				double mid = lo + (hi-lo)/2.0;
				
				Point p = new Point(-this.width/2, this.height/2).cwRot(mid);
				if (Double.compare(p.y, h/2.0) > 0){
					lo = mid;
				} else {
					hi = mid;
					if (Double.compare(p.x, w/2.0) <= 0){
						ret = mid;
					}
				}
			}
			
			return ret;
		}
		
		
		
	}
	static class Point {
		double x; double y;
		Point(double x, double y){
			this.x = x; this.y = y;
		}
		
		Point cwRot(Double rad){
			return new Point(x*Math.cos(rad)+y*Math.sin(rad),
						     -x*Math.sin(rad)+y*Math.cos(rad));
		}
		
		public String toString(){
			return String.format("[%f,%f]", x, y);
		}
	}
}
