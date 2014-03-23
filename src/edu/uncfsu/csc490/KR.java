package edu.uncfsu.csc490;

import java.util.Vector;
import edu.uncfsu.csc490.Utils;

@SuppressWarnings("rawtypes")
public class KR extends Utils {
	public static Vector[] weightReduce(Vector[] M) {
		sortVectors(M);
		
		M = LLL.reduce(M);
		
		int n = M.length;
		int m = M[0].size();
		
		double[][] delta = new double [n][m];
		
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-1; j++) {
				delta[i][j] = dotProduct(M[i], M[j]);
			}
		}
		
		double weight = weight(n, delta);
		
		boolean done = false;
		while (!done) {
			M = WeightReduction.wtReduct(M);

			delta = WeightReduction.getDelta();

			double new1 = weight(n, delta);

			if (new1 < weight)
				weight = new1;
			else
				done = true;
		}
		
		return M;
	}
	
	// sort basis vectors by magnitude (swap sort)
	public static Vector[] sortVectors(Vector[] M) {
		for (int i = 0; i < M.length-2; i++) {
			if (magnitude(M[i]) > magnitude(M[i+1])) {
				Vector temp = (Vector) M[i].clone();
				M[i] = (Vector) M[i+1].clone();
				M[i+1] = temp;
			}
		}
		return M;
	}
}
