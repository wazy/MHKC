package edu.uncfsu.csc490;

import java.math.BigDecimal;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class KR extends Utils {
	public static Vector[] weightReduce(Vector[] M) {

		sortVectors(M);

		M = LLL.reduce(M);

		int n = M.length; // vectors
		int m = M[0].size(); // indices
		
		BigDecimal[][] delta = new BigDecimal [m][n];
		
		for (int i = 0; i <= n-1; i++) {
			for (int j = 0; j <= n-1; j++) {
				delta[i][j] = dotProduct(M[i], M[j]);
			}
		}
		
		BigDecimal weight = weight(n, delta);
		
		boolean done = false;
		while (!done) {

			M = WeightReduction.wtReduct(M, delta);

			delta = WeightReduction.getDelta();

			BigDecimal newWeight = weight(n, delta);
 
			if (newWeight.compareTo(weight) < 0)
				weight = newWeight;
			else
				done = true;

		}

		return M;
	}

	/**
	 * This method sorts basis vectors by magnitude (a swap sort).
	 * @param M The vectors to be sorted.
	 */
	public static void sortVectors(Vector[] M) {

		BigDecimal[] temp = new BigDecimal[M.length]; 

		for (int i = 0; i < M.length; i++)
			temp[i] = magnitude(M[i]);
		
		boolean hasChanged;
		do {
			hasChanged = false;
			for (int i = 0; i < temp.length-1; i++) {
				if (temp[i].compareTo(temp[i+1]) > 0) {
					BigDecimal tem = temp[i];
					temp[i] = temp[i+1];
					temp[i+1] = tem;
					
					Vector tempV = M[i];
					M[i] = M[i+1];
					M[i+1] = tempV;
					
					hasChanged = true;
				}
			}
		} while(hasChanged);
	}
}
