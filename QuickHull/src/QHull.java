import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class QHull {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<ArrayList<Point>> probs = new ArrayList<ArrayList<Point>>();
		ArrayList<Double> maxPerim = new ArrayList<Double>();
		readFile(probs, maxPerim);

		//Goes through each problem and solves its convex hull
		for (int i = 0; i < probs.size(); i++) {
			System.out.println("Case #" + (i+1));
			solveCH(probs.get(i), maxPerim.get(i));
		}

	}

	/**
	 * Loads the data
	 * @param pr An ArrayList containing all of the problem sets which are ArrayLists of Points
	 * @param max An ArrayList containing all of the maximum sizes for perimeters for the problems
	 */
	public static void readFile(ArrayList<ArrayList<Point>> pr, ArrayList<Double> max) {
		try {
			File infile = new File("build3Size5.txt");
			Scanner scan = new Scanner(infile);

			int nProbs = scan.nextInt();
			for (int i = 0; i < nProbs; i++) {
				ArrayList<Point> pts = new ArrayList<Point>();

				//Gets the number of Coordinates
				int numCoord = scan.nextInt();

				//Reads each number in the coordinates.
				for (int j = 0; j < numCoord; j++) {
					pts.add(new Point(scan.nextDouble(),scan.nextDouble()));
				}	
				//Reads in the maximum perimeter
				max.add(scan.nextDouble());
				pr.add(pts);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Solves the Convex Hull
	 * @param pts The set of points that will be solved
	 * @param max The maximum size this problem will be
	 */
	public static void solveCH(ArrayList<Point> pts, double max) {
		
		//Sorts the entire array using Comparator
		Collections.sort(pts, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
            	Double o1 = p1.getX(), o2 = p2.getX();
            	int ret = o1.compareTo(o2);
            	
            	if (ret == 0)
            		return ((Double)p1.getY()).compareTo((Double)p2.getY());
                return ret;
			}
        });
		
		//Finds the extreme points for x
		Point extMin = pts.get(0), extMax = pts.get(pts.size()-1);
			
		//Contructs the lists that will have the left and right hull
		ArrayList<Point> lHull = new ArrayList<Point>(), rHull = new ArrayList<Point>();
		
		//This for loop splits the entire problem into two halves, excluding those
		//on the line between extremes
		for (int i = 1; i < pts.size() - 1; i++) {
			Point temp = pts.get(i);
			
			double line = computeLine(extMin, extMax, temp); 
			
			//Adds each point to the correct hull
			if (line > 0)
				lHull.add(temp);
			else if (line < 0)
				rHull.add(temp);
		}
		
		//Makes recursive calls for each half of the problem, also passes in the perimeter
		//to keep track of it
		System.out.println(solveCH(lHull, extMin, extMax) + solveCH(rHull, extMax, extMin));
	}
	
	public static double solveCH(ArrayList<Point> pts, Point l, Point r) {
		//If there are no other points to compare to the line, then the two passed points
		//are part of the convex hull and the distance between them is counted.
		if (pts.size() == 0) 
			return Math.sqrt(Math.pow((r.getX() - l.getX()),2) + 
					Math.pow((r.getY()-l.getY()), 2));
		else {
			Point maxPt = null;
			double maxLine = 0;
			
			ArrayList<Point> lHull = new ArrayList<Point>(), rHull = new ArrayList<Point>();
			
			for (int i = 0; i < pts.size(); i++) {
				Point temp = pts.get(i);
				
				double line = computeLine(l, r, temp);
				if (line > maxLine) {
					maxLine = line;
					maxPt = temp;
				}
			}
			
			for (int i = 0; i < pts.size(); i++) {
				Point temp = pts.get(i);
				if (computeLine(l, maxPt, temp) > 0)
					lHull.add(temp);
				
				else if (computeLine(maxPt, r, temp) > 0)
					rHull.add(temp);
			}
			
			double a = solveCH(lHull, l, maxPt);
			return  a + solveCH(rHull, maxPt, r);
		}
	}
	
	public static double computeLine(Point p1, Point p2, Point p3) {
		return (p1.getX() * p2.getY()) +
				(p3.getX() * p1.getY()) +
				(p2.getX() * p3.getY()) -
				(p3.getX() * p2.getY()) -
				(p2.getX() * p1.getY()) -
				(p1.getX() * p3.getY());
	}

}
