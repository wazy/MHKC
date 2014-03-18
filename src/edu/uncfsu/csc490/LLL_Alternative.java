package edu.uncfsu.csc490;

import java.util.Vector;

public class LLL_Alternative {
	
	@SuppressWarnings("rawtypes")
	private static Vector[] reduce(Vector[] b, double[][] u) {

		int k = b.length;

		for (int i = 1; i <= k-1; i++) {
			for (int j = i-1; j >= 0; j--) {
				b[i] = VectorOp.subtract(b[i], 
						VectorOp.scalarMult(Math.round(u[i][j]), b[j]));
				u[i][j] = u[i][j] - Math.round(u[i][j]);
				
				for (int l = 0; l <= j-2; l++) {
					u[i][l] = u[i][l] - (Math.round(u[i][j]) * u[i][j]);
				}
			}
		}		
		return b;
	}
	
	@SuppressWarnings("rawtypes")
	private static Vector[] LLL(Vector[] b, double delta) {

		Vector[] bs = new Vector[b.length];
		
		bs = GramSchmidt.execute(b);
		
		double[][] u = GramSchmidt.getA();
		
		int i = 1;
		int k = b[0].size();
		
		while (i <= k) {
			b = reduceBi(b, i, u);
			
			double lhs = delta * Math.pow(VectorOp.magnitude(bs[i-1]), 2);
			double rhs = Math.pow(VectorOp.magnitude(bs[i]), 2);
			rhs += u[i][i-1] * Math.pow(VectorOp.magnitude(bs[i-1]), 2);

			if (lhs > rhs) {
				// swap b[i] and b[i-1]
				Vector temp = new Vector(b[0].size());
				temp = b[i];
				b[i] = b[i-1];
				b[i-1] = temp;
				
				bs = GramSchmidt.execute(b);
				u = GramSchmidt.getA();

				// i = max(i-1, 1)
				if (i-1 >= 1)
					i--;
				else
					i = 1;
			}
			else {
				i++;
			}
			
		}
		
		return b;
	}
	
	// need to reduce vectors b[i] to b[0]
	@SuppressWarnings("rawtypes")
	private static Vector[] reduceBi(Vector[] b, int idx, double[][] u) {
		Vector[] c = new Vector[idx+1];
		
		// reduce vectors b[i] to b[0] in temporary vector list
		for (int i = idx; i >= 0; i--) {
			c[i] = b[i];
		}

		c = reduce(c, u);
		
		// copy reduced vectors back into b
		for (int i = idx; i <= 0; i--) {
			b[i] = c[i];
		}
		
		return b;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Vector[] b = new Vector[3];
		
		Vector v1 = new Vector(3);
		Vector v2 = new Vector(3);
		Vector v3 = new Vector(3);
		
		v1.add(1); v1.add(1); v1.add(1);
		v2.add(-1); v2.add(0); v2.add(2);
		v3.add(3); v3.add(5); v3.add(6);
		
		b[0] = v1;
		b[1] = v2;
		b[2] = v3;
		
		b = LLL(b, 0.75);
		
		VectorArrayOp.printVectors(b);
		
	}

}
