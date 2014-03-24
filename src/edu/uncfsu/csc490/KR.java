package edu.uncfsu.csc490;

import java.util.Arrays;
import java.util.Vector;

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

		double[] temp = new double[M.length]; 
		double[] lookup = new double[M.length];

		Vector[] M1 = new Vector[M.length];
		
		for (int i = 0; i < M.length; i++) {
			temp[i] = magnitude(M[i]);
			lookup[i] = magnitude(M[i]);
		}
		
		Arrays.sort(temp);
		
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < lookup.length; j++) {
				if (lookup[j] == temp[i]) {
					M1[i] = (Vector) M[j].clone();
					break;
				}
			}
		}

		return M1;
	}
}
