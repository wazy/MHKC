package edu.uncfsu.csc.MHKC.LatticeReduction;

import java.math.BigDecimal;
import java.math.MathContext;
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
    public static Vector<BigDecimal> add(
    				Vector<BigDecimal> a, Vector<BigDecimal> b) {
		int m = a.size();
    	Vector<BigDecimal> c = new Vector(m);
		for (int i = 0; i < m; i++) {
			BigDecimal value = ((BigDecimal) a.elementAt(i))
								.add((BigDecimal) b.elementAt(i)); 
			c.add(value);
		}
		return c;
    }

    public static Vector<BigDecimal> divide(Vector<BigDecimal> v, 
    		BigDecimal magnitude) {

    	Vector<BigDecimal> c = (Vector<BigDecimal>) v.clone();
    	
    	for (int i = 0; i < c.size(); i++) {
			c.set(i, c.elementAt(i).divide(magnitude, MathContext.DECIMAL128));
		}

		return c;
	}

    public static BigDecimal dotProduct(Vector a) {
    	Vector b = (Vector) a.clone();
    	return dotProduct(a, b);

    }

	public static BigDecimal dotProduct(Vector a, Vector b) {
    	BigDecimal sum = new BigDecimal("0");
    	for (int i = 0; i < a.size(); i++)
    		sum = sum.add((((BigDecimal) a.elementAt(i))
    						.multiply((BigDecimal) b.elementAt(i))));

    	return sum;
    }

    public static BigDecimal magnitude(Vector a) {
        return sqrt(dotProduct(a));
    }

    // negate each BigDecimal in the vector
    public static Vector negate(Vector B) {
    	for (int i = 0; i < B.size(); i++) {
			B.set(i, ((BigDecimal) B.get(i)).negate());
		}
    	return B;
    }

    /**
     * This method will print U, the solution vector.
     * @param m The index of the solution is from 0 to m in the vector.
     * @param solution The solution vector.
     * @return 
     */
    public static Vector printSolution(int m, Vector solution) {    		
		/* Note: most of this code is for formatting
		   e.g. U = [1, 0, 0, 0, 1, 0, 0, 1, 0, 0] */

		String prefix = "U = [";
		String answer = "";

		Vector result = new Vector(m);
		
		for (int i = 0; i < m; i++) {
			int res = Math.abs((int)Double.parseDouble(solution.get(i).toString()));
			answer += res + " ";
			result.add(res);
		}

		answer = answer.replaceAll(" ", ", ");
		answer = answer.substring(0, answer.length()-2);

		answer = prefix + answer;
		answer += "]";

		System.out.println(answer);

		return result;
	}

    public static Vector<BigDecimal> scalarMult(
    							BigDecimal scalar, Vector<BigDecimal> a) {
    	Vector<BigDecimal> c = (Vector<BigDecimal>) a.clone();
		for (int i = 0; i < a.size(); i++)
			c.set(i, scalar.multiply(((BigDecimal) a.get(i))));

		return c;
	}

    public static Vector<BigDecimal> subtract(
    							Vector<BigDecimal> a, Vector<BigDecimal> b) {    	
    	int m = a.size();

    	Vector<BigDecimal> c = new Vector(m);

		for (int i = 0; i < m; i++) {
			BigDecimal value = ((BigDecimal) a.elementAt(i))
								.subtract((BigDecimal) b.elementAt(i));
			c.add(value);
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

		Vector temp = b[j];
		b[j] = b[j+1];
		b[j+1] = temp;
	}


	/***                               ***/
	/*** BEGIN Vector Array Operations ***/
	/***                               ***/

	// LHS for checking reduced basis using vector B1[j], B1[j+1] and a scalar
	public static BigDecimal calculateLHS(BigDecimal a, Vector Bj, Vector Bj1) {
		return dotProduct(add(Bj1, scalarMult(a, Bj))); 
	}

	// RHS for checking reduced basis using vector B1[j]
	public static BigDecimal calculateRHS(Vector Bj) {
		return dotProduct(Bj).multiply(new BigDecimal(".75"));
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
	public static BigDecimal weight(Vector[] b) {
		BigDecimal weight = new BigDecimal("1.0");
		for (int i = 0; i < b.length; i++) {
			weight = weight.multiply(magnitude(b[i]));
		}
		return weight;
	}
	
	// calculates weight from the delta matrix in KR
	public static BigDecimal weight(int n, BigDecimal[][] delta) {
		BigDecimal weight = new BigDecimal("1.0");
		for (int i = 0; i < n; i++) {
			weight = weight.multiply(delta[i][i]);
		}
		return sqrt(weight);
	}

	// 32 digits of precision with square roots of BigDecimals
	public static BigDecimal sqrt(BigDecimal value) {
		
		double val = Math.sqrt(value.doubleValue());
		
		if (Double.isNaN(val) || Double.isInfinite(val))
			return value;

		BigDecimal x = new BigDecimal(val);
		return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}

	// this method is for printing vector array transposed
	public static void print(Vector[] b) {
		for (int i = 0; i < b[0].size(); i++) {  // cols
			for (int j = 0; j < b.length; j++) { // rows
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
	public static void print(String tag, BigDecimal[][] a) {
		System.out.println(tag);
		print(a);
	}

	// this method is for printing the A matrix
	public static void print(BigDecimal[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++)
				System.out.print(df.format(Double.parseDouble(a[j][i].toPlainString())) + " ");

			System.out.println();
		}
		System.out.println();
	}
}
