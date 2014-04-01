package edu.uncfsu.csc490;

import java.util.Vector;

@SuppressWarnings("rawtypes")
public class KR extends Utils {
	public static Vector[] weightReduce(Vector[] M) {
		sortVectors(M);

		M = LLL.reduce(M);

		int n = M.length; // vectors
		int m = M[0].size(); // indices
		
		double[][] delta = new double [m][n];
		
		for (int i = 0; i <= n-1; i++) {
			for (int j = 0; j <= n-1; j++) {
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
	public static void sortVectors(Vector[] M) {

		double[] temp = new double[M.length]; 

		for (int i = 0; i < M.length; i++)
			temp[i] = magnitude(M[i]);
		
		boolean hasChanged;
		do {
			hasChanged = false;
			for (int i = 0; i < temp.length-1; i++) {
				if (temp[i] > temp[i+1]) {
					double tem = temp[i];
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
