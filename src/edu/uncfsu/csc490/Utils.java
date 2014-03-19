package edu.uncfsu.csc490;

import java.util.Vector;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Utils {

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
	
	public static double dotProduct(Vector a, Vector b) {
		  if(a.size() != b.size()) {
			  System.out.println("Invalid dimensions - trying to find dot product");
			  System.exit(1);
		  }

		  double sum = 0;
		  for(int i = 0; i < a.size(); i++)
			  sum += Double.parseDouble(a.elementAt(i).toString()) * 
			  				Double.parseDouble(b.elementAt(i).toString()); 
		  
		  return sum;
		}
	
    public static double magnitude(Vector a) {
        return Math.sqrt(dotProduct(a, a));
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
		return Math.pow(magnitude(add(Bj1, scalarMult(scalar, Bj))), 2); 
	}
	
	// RHS for checking reduced basis using vector B1[j]
	public static double calculateRHS(Vector Bj) {
		return .75 * Math.pow(magnitude(Bj), 2);
	}
	
	/* transpose vector -- algorithm uses column vectors not row vectors */
	public static Vector[] transpose(Vector[] v) {
		int n = v.length;
		int m = v[0].size();
		
		Vector[] r = new Vector[n];

		for (int i = 0; i < r.length; i++) {
			r[i] = new Vector(m);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				r[i].add(v[j].get(i));
			}
		}
		return r;
	}

	// calculates weight of the vector array 
	public static double weight(Vector[] v) {
		double weight = 1.0;
		for (int i = 0; i < v.length; i++) {
				weight *= magnitude(v[i]);
		}
		return weight;
	}

	// calculates volume of the vector array
	public static double volume(Vector[] v) {
		double weight = 1.0;
		for (int i = 0; i < v.length; i++) {
				weight *= magnitude(v[i]);
		}
		return weight;
	}
	
	// this method is for printing vector array
	public static void print(Vector[] v) {
		for (int i = 0; i < v.length; i++) { // rows
			for (int j = 0; j < v[i].size(); j++) { // cols
				System.out.print(v[j].get(i) + " ");
			}
			System.out.print("\n");
		}
		System.out.println();
	}

	// this method is for printing vector array with a label
	public static void print(String tag, Vector[] v) {
		System.out.println(tag);
		print(v);
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
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
