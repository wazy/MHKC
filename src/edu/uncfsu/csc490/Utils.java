package edu.uncfsu.csc490;

import java.text.DecimalFormat;
import java.util.Vector;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Utils {

	public final static double EPSILON = 1E-9; 	

	// format to two decimal places and put a space in front
	// if not negative to align columns
	private static DecimalFormat df = new DecimalFormat(" 0.00;-0");
	
	/***                         ***/
	/*** BEGIN Vector Operations ***/
	/***                         ***/
	
	// TODO: write vector class that incorporates most vector operations
    public static Vector add(Vector a, Vector b) {
		Vector c = (Vector)a.clone();
		for (int i = 0; i < a.size(); i++) {
			double value = Double.parseDouble(a.elementAt(i).toString()) + 
							Double.parseDouble(b.elementAt(i).toString()); 
			c.set(i, value);
		}
		return c;
    }

    public static double dotProduct(Vector a) {
    	Vector b = (Vector) a.clone();
    	
    	double sum = 0;
    	for (int i = 0; i < a.size(); i++)
    		sum += Double.parseDouble(a.elementAt(i).toString()) * 
    						Double.parseDouble(b.elementAt(i).toString()); 

    	return sum;
    }

	public static double dotProduct(Vector a, Vector b) {
	   	double sum = 0;
    	for (int i = 0; i < a.size(); i++)
    		sum += Double.parseDouble(a.elementAt(i).toString()) * 
    						Double.parseDouble(b.elementAt(i).toString()); 

    	return sum;
    }
	
    public static double magnitude(Vector a) {
        return Math.sqrt(dotProduct(a, a));
    }

    // negate the vector by subtracting from zero
    public static Vector negate(Vector B) {
    	for (int i = 0; i < B.size(); i++) {
			B.set(i, (0 - Integer.parseInt(B.get(i).toString())));
		}
    	return B;
    }

    /**
     * This method will print U, the solution vector.
     * @param m The index of the solution is from 0 to m in the vector.
     * @param solution The solution vector.
     */
    public static void printSolution(int m, Vector solution) {    		
		/* Note: most of this code is for formatting
		   e.g. U = [1, 0, 0, 0, 1, 0, 0, 1, 0, 0] */

		String prefix = "U = [";
		String answer = "";

		for (int i = 0; i < m; i++) {
			answer += Math.abs((int)Double.parseDouble(solution.get(i).toString())) + " ";
		}

		answer = answer.replaceAll(" ", ", ");
		answer = answer.substring(0, answer.length()-2);

		answer = prefix + answer;
		answer += "]";

		System.out.println(answer);
	}

    public static Vector scalarMult(double scalar, Vector a) {
        Vector c = (Vector) a.clone();
		for (int i = 0; i < a.size(); i++) {
        	double value = scalar * Double.parseDouble(a.get(i).toString());
        	c.set(i, value);
        }
        return c;
    }
	
    public static Vector subtract(Vector a, Vector b) {
		Vector c = (Vector)a.clone();
		for (int i = 0; i < a.size(); i++) {
			double value = Double.parseDouble(a.elementAt(i).toString()) - 
							Double.parseDouble(b.elementAt(i).toString()); 
			c.set(i, value);
		}
		return c;
    }

	// swaps vectors j and j+1 in vector array B
	public static void swap(Vector[] b, int j) {
		if ((j >= b.length) || (b[j].size() != b[j+1].size())) {
			System.out.println("Fatal: unable to swap vectors with " +
								"different dimensions or out of bounds j!");
			System.exit(1);
		}
		
		Vector temp = new Vector(b[j].size());
		temp = b[j];
		b[j] = b[j+1];
		b[j+1] = temp;
	}


	/***                               ***/
	/*** BEGIN Vector Array Operations ***/
	/***                               ***/

	// LHS for checking reduced basis using vector B1[j], B1[j+1] and a scalar
	public static double calculateLHS(double scalar, Vector Bj, Vector Bj1) {
		return dotProduct(add(Bj1, scalarMult(scalar, Bj))); 
	}
	
	// RHS for checking reduced basis using vector B1[j]
	public static double calculateRHS(Vector Bj) {
		return .75 * dotProduct(Bj);
	}

	/* transpose vector -- algorithm uses column vectors not row vectors */
	public static Vector[] transpose(Vector[] b) {
		int n = b.length;
		int m = b[0].size();
		
		Vector[] r = new Vector[m];

		for (int i = 0; i < r.length; i++) {
			r[i] = new Vector(n);
		}

		// a b c
		// d e f

		// a d
		// b e
		// c f
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				r[i].add(b[j].get(i));
			}
		}
		return r;
	}

	// calculates weight of the vector array 
	public static double weight(Vector[] b) {
		double weight = 1.0;
		for (int i = 0; i < b.length; i++) {
				weight *= magnitude(b[i]);
		}
		return weight;
	}
	
	// calculates weight from the delta matrix in KR
	public static double weight(int n, double[][] delta) {
		double weight = 1.0;
		for (int i = 0; i <= n-1; i++) {
				weight *= Math.sqrt(delta[i][i]);
		}
		return weight;
	}

	// calculates volume of the vector array
	public static double volume(Vector[] b) {
		double weight = 1.0;
		for (int i = 0; i < b.length; i++) {
				weight *= magnitude(b[i]);
		}
		return weight;
	}
	
	// this method is for printing vector array
	public static void print(Vector[] b) {
		for (int i = 0; i < b[0].size(); i++) { // rows
			for (int j = 0; j < b.length; j++) { // cols
				System.out.print(df.format(Double.parseDouble(b[j].get(i).toString())) + " ");
			}
			System.out.print("\n");
		}
		System.out.println();
	}

	// this method is for printing vector array with a label
	public static void print(String tag, Vector[] b) {
		System.out.println(tag);
		print(b);
	}


	/***                         ***/
	/*** BEGIN Normal Operations ***/
	/***                         ***/

	// this method is for printing the A matrix with a label
	public static void print(String tag, double[][] a) {
		System.out.println(tag);
		print(a);
	}

	// this method is for printing the A matrix
	public static void print(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(df.format(a[i][j]) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
