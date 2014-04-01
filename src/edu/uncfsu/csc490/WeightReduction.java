package edu.uncfsu.csc490;

import java.util.Vector;

public class WeightReduction extends Utils {
	
	private static double[][] delta;
	private static double[] epilson = {-1.0, 1.0};
	
	@SuppressWarnings("rawtypes")
	public static Vector[] wtReduct(Vector[] v) {
		int n = v.length;
		int m = v[0].size();

		delta = new double [m][n];

		// global delta ij, 1<=i,j<=n
		// delta i,j = b[i] * b[j]
		for (int i = 0; i <= n-1; i++){
			for (int j = 0; j <= n-1; j++) {
				delta[i][j] = (dotProduct(v[i], v[j]));
			}
		}

		int k = 0;

		//for i <- i+1 to n
		for (int i = 0; i <= n-1; i++) {
			//for j <- i+1 to n
			for (int j = i+1; j <= n-1; j++) {
				//for each t E {-1,1}
				for (double epsil : epilson) {
					if (delta[i][i] < delta[j][j])
						k = j;
					else
						k = i;

					Vector temp = add(v[i], scalarMult(epsil, v[j]));
					double value = Math.pow(magnitude(temp), 2);

					if (value + EPSILON < delta[k][k]) {
						//delta k,k <- delta i,i + delta j,j + 2t delta i,j
						delta[k][k] = delta[i][i] + delta[j][j] + (2.0 * epsil * delta[i][j]);

						for (int h = 0; h <= n-1; h++) {
							if (h != i && h != j) {
								delta[k][h] = delta[i][h] + epsil * delta[j][h];
								delta[h][k] = delta[k][h];
							}
						}
						if (k != i) {
							delta[k][i] = delta[i][i] + epsil * delta[j][i];
							delta[i][k] = delta[k][i];
						}
						else {
							delta[k][j] = delta[i][j] + epsil * delta[j][j];
							delta[j][k] = delta[k][j];
						}
						v[k] = temp;
					}
				}
			}
		}
		return v;
	}

	public static double[][] getDelta() {
		return delta;
	}
}
