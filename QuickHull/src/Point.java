/*Name: Grayson
  Assignment: Program 2
  Title: Convex Hull
  Course: CSCE 371
  Semester: Fall 2013
  Instructor: Kenneth Blaha
  Date: Wed Oct 9 2013
  Sources consulted: Course book, wikipedia about Convex Hull
  Program description: This program will solve the convex hull of a set of points
  	and calculate its minimum perimeter. From there it'll compare with another
  	perimeter that is given to it and determine whether the wall can be built
  Known Bugs: When there is a point 0,0 and it is exactly between two points, it
  	won't calculate the perimeter correctly
  Creativity: N/A
  */

/**
 * This class stores coordinates on a plane
 * @author Grayson
 */
public class Point {
	double x, y;
	
	/**
	 * Constructor for Point. Stores both coordinates
	 * @param x X value
	 * @param y Y value
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the X value
	 * @return X value
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the Y value
	 * @return Y value
	 */
	public double getY() {
		return y;
	}
	
	public String toString() {
		return x + " " + y;
	}
}
