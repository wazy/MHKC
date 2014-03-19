package edu.uncfsu.csc490;

import java.util.Vector;

public class GramSchmidt extends Utils {

	private static double a[][];

	@SuppressWarnings("rawtypes")
	public static Vector[] process(Vector[] v) {
		int n = v.length;
		int m = v[0].size();
		
		Vector[] v1 = new Vector[n];

		a = new double[n][m];
		
		v1[0] = v[0];
		
		for (int j = 1; j <= n-1; j++) {
			v1[j] = v[j];
			
			for (int i = 0; i <= (j-1); i++) {

				if (v1[i] == null || v[j] == null) {
					System.out.println("Fatal: out of bounds in Gramm-Schmidt");
					System.exit(1);
				}

				a[i][j] = (dotProduct(v1[i], v[j])) / (Math.pow(magnitude(v1[i]), 2));
				
				v1[j] = subtract(v1[j], (scalarMult(a[i][j], v1[i])));
			}
		}

		return v1;
	}

	public static double[][] getA() {
		return a;
	}
}
 