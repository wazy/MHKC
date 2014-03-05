package edu.uncfsu.csc490;

import java.util.Vector;

public class WeightReduction {
	
	private static double delta[][];
	
	@SuppressWarnings("rawtypes")
	public static Vector[] wtReduct(Vector[] v){
		int n = v.length;
		int m = v[0].size();
		
		delta = new double [n][m];
		
		//global delta ij, 1<=i,j<=n
		for (int i = 1; i <= n; i++){
			for (int j = 1; j <= n; j++) {
				delta[i][j] = (VectorOp.dotProduct(v[i], v[j]));
			}
		}
		//for i <- i+1 to n
		for (int i = 1; i < n; i++) {
			//for j <- i+1 to n
			for (int j = i + 1; j < n; j++) {
				//for each t E {-1,1}
				for (int k = 0; k < n-1; k++) {
					//if delta i,i < delta j,j then k <- j else k <- i
					delta[i][i] = (VectorOp.dotProduct(v[i], v[i]));
					delta[j][j] = (VectorOp.dotProduct(v[j], v[j]));
					if (delta[i][i] < delta[j][j])
						v[k] = v[j];
					else
						v[k] = v[i];
					//vector v <- vector bi + E vector bj
					Vector x = (VectorOp.add(v[i],v[j]));
					//if magnitude (v)^2 < delta k,k
					delta[k][k] = (VectorOp.dotProduct(v[k], v[k]));
					if (Math.pow(VectorOp.magnitude(x), 2) < delta[k][k]){
						//delta k,k <- delta i,i + delta j,j + 2t delta i,j
						delta[k][k] = delta[i][i] + delta[j][j] + (2 * delta[i][j]);
						//for h <- 1 to n
						for (int h = 1; h < n; h++) {
							delta[k][h] = (VectorOp.dotProduct(v[k], v[h]));
							delta[i][h] = (VectorOp.dotProduct(v[i], v[h]));
							delta[j][h] = (VectorOp.dotProduct(v[j], v[h]));
							//if h != i and h != j
							if (h != i && h != j){
								//delta k,h <- delta i,h + t delta j,h
								//delta h,k <- delta k,h
								delta[k][k] = (VectorOp.add(delta[i][h], delta[j][h]));
								delta[h][k] = delta[k][h];
							}
							delta[k][i] = (VectorOp.dotProduct(v[k], v[i]));
							delta[j][i] = (VectorOp.dotProduct(v[j], v[i]));
							//if k != i
							if(k != i) {
								//delta k,i <- delta i,i + t delta j,i
								delta[k][i] = delta[i][i] + delta [j][i];
								//delta i,k <- delta k,i
								delta[i][k] = delta[k][i];
							}
							delta[k][j] = (VectorOp.dotProduct(v[k], v[j]));
							//else delta k,j <- delta i,j + t delta j,j
							else {
								delta[k][j] = delta[i][j] + delta[j][j];
								// delta j,k <- delta k,j
								delta[j][k] = delta[k][j];
							}
							//vector bk <- vector v
							v[k] = v;
						}
						
					}
					
				}
			return v;
		}
		
}
