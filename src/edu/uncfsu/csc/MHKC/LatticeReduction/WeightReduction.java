package edu.uncfsu.csc.MHKC.LatticeReduction;

import java.math.BigDecimal;
import java.util.Vector;

public class WeightReduction extends Utils {
	
	private static BigDecimal[][] delta;
	private static BigDecimal[] epilson = {new BigDecimal("-1.0"), new BigDecimal("1.0")};
	
	@SuppressWarnings("rawtypes")
	public static Vector[] wtReduct(Vector[] v, BigDecimal[][] deltaArr) {

		int n = v.length;
		int k = 0;

		delta = deltaArr;

		//for i <- i+1 to n
		for (int i = 0; i <= n-1; i++) {
			//for j <- i+1 to n
			for (int j = i+1; j <= n-1; j++) {
				//for each t E {-1,1}
				for (BigDecimal epsil : epilson) {
					if (delta[i][i].compareTo(delta[j][j]) < 0)
						k = j;
					else
						k = i;

					Vector temp = add(v[i], scalarMult(epsil, v[j]));
					BigDecimal value = dotProduct(temp);

					if (value.compareTo(delta[k][k]) < 0) {
						//delta k,k <- delta i,i + delta j,j + 2t delta i,j
						delta[k][k] = (delta[i][i].add(delta[j][j]))
										.add((epsil.multiply(new BigDecimal ("2.0"))
										.multiply(delta[i][j])));

						for (int h = 0; h <= n-1; h++) {
							if (h != i && h != j) {
								delta[k][h] = delta[i][h].add(epsil).multiply(delta[j][h]);
								delta[h][k] = delta[k][h];
							}
						}
						if (k != i) {
							delta[k][i] = delta[i][i].add(epsil).add(delta[j][i]);
							delta[i][k] = delta[k][i];
						}
						else {
							delta[k][j] = delta[i][j].add(epsil).add(delta[j][j]);
							delta[j][k] = delta[k][j];
						}
						v[k] = temp;
					}
				}
			}
		}
		return v;
	}

	public static BigDecimal[][] getDelta() {
		return delta;
	}
}
