package edu.uncfsu.csc490;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Vector;

public class GramSchmidt extends Utils {

	private static BigDecimal[][] a;

	@SuppressWarnings("rawtypes")
	public static Vector[] process(Vector[] v) {
		int n = v.length;
		int m = v[0].size();

		Vector[] v1 = new Vector[n];

		a = new BigDecimal[m][n];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = new BigDecimal("0");
			}
		}

		v1[0] = v[0];

		for (int j = 1; j <= n-1; j++) {
			v1[j] = v[j];

			for (int i = 0; i <= (j-1); i++) {

				if (v1[i] == null || v[j] == null) {
					System.out.println("Fatal: out of bounds in Gram-Schmidt");
					System.exit(1);
				}

				a[i][j] = (dotProduct(v1[i], v[j]))
							.divide(dotProduct(v1[i]), MathContext.DECIMAL128);

				v1[j] = subtract(v1[j], (scalarMult(a[i][j], v1[i])));
			}
		}

		return v1;
	}

	public static BigDecimal[][] getA() {
		return a;
	}
}
