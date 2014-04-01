package edu.uncfsu.csc490;

import java.util.Vector;

@SuppressWarnings("rawtypes")
public class LLL extends Utils {

	private static int n; // vectors
	private static double[][] a;

	private static boolean done;
	public static boolean reducedBasis;

	public static Vector[] reduce(Vector[] b) {

		n = b.length;

		int count = 0;

		// run first iteration of Gram-Schmidt orthonormalization
		// and generate coefficients a[i][j] for (i < j)
		Vector[] b1 = GramSchmidt.process(b);
		a = GramSchmidt.getA();

		print("Initial Vectors:", b);
		print("Initial Gramm-Schmidt:", b1);
		print("Printing initial A:", a);

		done = false;
		reducedBasis = false;

		while (!done)
		{
			// attempt to reduce the vectors
			for (int j = 1; j <= n-1; j++) {
				for (int i = j-1; i >= 0; i--) {				
					if (Math.abs(a[i][j]) > .5) {
						b[j] = subtract(b[j], scalarMult(Math.floor(a[i][j]+.5), b[i]));
					}
				}
			}
			
			count++;
			
			// recalculate Gram-Schmidt after reduction
			b1 = GramSchmidt.process(b);
			a = GramSchmidt.getA();
			
			print("Vectors after reduction, iteration " + count + ":", b);
			print("Gramm-Schmidt after reduction, iteration " + count + ":", b1);
			print("A after reduction, iteration " + count + ":", a);
			
			// check whether we have a reduced basis or not
			// if it is reduced then we terminate the algorithm
			// else rerun Gram-Schmidt orthonormalization
			if (isReducedBasis(b, b1, a))
				done = true;
			else {
				b1 = GramSchmidt.process(b);
				a = GramSchmidt.getA();
			}
		}
		return b;
	}
	
	public static boolean isReducedBasis(Vector[] b, Vector[] b1, double[][] a) {

		// checks whether we are reduced for all vectors
		for (int j = 0; j < n-1; j++) {
			double lhs = calculateLHS(a[j][j+1], b1[j], b1[j+1]); 
			double rhs = calculateRHS(b1[j]);
			
			System.out.println(lhs + " < " + rhs);

			// add an epsilon to take care of roundoff error
			if (lhs + EPSILON < rhs) {
				swap(b, j);
				return false;
			}
		}
		reducedBasis = true;
		return true;
	}
}
