package edu.uncfsu.csc.MHKC.LatticeReduction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class LLL extends Utils {

	private static int n; // vectors
	private static BigDecimal[][] a;

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
		print("Initial Gram-Schmidt:", b1);
		print("Printing initial A:", a);

		done = false;
		reducedBasis = false;

		BigDecimal oneHalf = new BigDecimal(".5");

		while (!done)
		{
			// attempt to reduce the vectors
			for (int j = 1; j <= n-1; j++) {
				for (int i = j-1; i >= 0; i--) {				
					if ((a[i][j].abs()).compareTo(oneHalf) > 0) {
						b[j] = subtract(b[j], scalarMult((a[i][j].add(oneHalf))
										.setScale(0, RoundingMode.FLOOR), b[i]));
					}
				}
			}
			
			count++;
			
			// recalculate Gram-Schmidt after reduction
			b1 = GramSchmidt.process(b);
			a = GramSchmidt.getA();
			
			print("Vectors after reduction, iteration " + count + ":", b);
			print("Gram-Schmidt after reduction, iteration " + count + ":", b1);
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
	
	public static boolean isReducedBasis(Vector[] b, Vector[] b1, BigDecimal[][] a) {

		// checks whether we are reduced for all vectors
		for (int j = 0; j < n-1; j++) {
			BigDecimal lhs = calculateLHS(a[j][j+1], b1[j], b1[j+1]); 
			BigDecimal rhs = calculateRHS(b1[j]);

			System.out.println(lhs + " < " + rhs);

			if (lhs.compareTo(rhs) < 0) {
				swap(b, j);
				return false;
			}
		}
		reducedBasis = true;
		return true;
	}
}
