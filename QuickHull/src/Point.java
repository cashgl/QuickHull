/**
 * This class stores coordinates on a plane
 * @author Grayson Cash
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
