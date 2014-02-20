package edu.uncfsu.csc490;

import java.util.Vector;

public class GramSchmidt {

	private static double a[][];
	
	private static boolean reducedBasis = true;
	
	@SuppressWarnings("rawtypes")
	public static Vector[] execute(Vector[] v) {
		int n = v[0].size();
		
		Vector[] v1 = new Vector[n];
		
		a = new double[n][n];
		
		v1[0] = v[0];
		
		for (int j = 1; j <= n-1; j++) {
			v1[j] = v[j];
			
			for (int i = 0; i <= (j-1); i++) {
				a[i][j] = (VectorOp.dotProduct(v1[i], v[j])) / (Math.pow(VectorOp.magnitude(v1[i]), 2));

				if (reducedBasis && Math.abs(a[i][j]) > .5) {
					reducedBasis = false;
				}
				
				v1[j] = VectorOp.subtract(v1[j], (VectorOp.scalarMult(a[i][j], v1[i])));
			}
		}

		return v1;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static void isReducedBasis(Vector[] v, Vector[] v1) {
		if (reducedBasis) {
			System.out.println("M is a reduced basis for step a.");
			
			int n = v1[0].size();
			
			a = new double[n][n];
			
			for (int j = 0; j < n-2; j++) {
				double rhs = .75 * Math.pow(VectorOp.magnitude(v1[j]), 2);
				
				a[j][j] = (VectorOp.dotProduct(v1[j], v[j+1])) / (Math.pow(VectorOp.magnitude(v1[j]), 2));
				Vector x = VectorOp.scalarMult(a[j][j], v1[j]); 
				x = VectorOp.add(v1[j+1], x);
				double lhs = Math.pow(VectorOp.magnitude(x), 2);
				
				if (lhs >= rhs) {
					reducedBasis = true;
				}
				else {
					reducedBasis = false;
				}
				
			}
		}
		else {
			System.out.println("\nM is not a reduced basis.");
			return;
		}
		
	}
	
	public static boolean getIsReduced() {
		return reducedBasis;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Vector a = new Vector(3);
		Vector b = new Vector(3);
		Vector c = new Vector(3);
		
		/* output for the first part of this algorithm using this data is: 
		 * [1, 1, 1]
		 * [1/3, -2/3, 1/3]
		 * [1/2, 0, -1/2]
		*/
		
		a.add(1); a.add(1); a.add(1);
		b.add(1); b.add(0); b.add(1);
		c.add(1); c.add(1); c.add(0);
		
		Vector[] v = new Vector[3];
		Vector[] v1 = new Vector[3];
		
		v[0] = a;
		v[1] = b;
		v[2] = c;
		
		v1 = execute(v);
				
		for (int j = 0; j < v1.length; j++) {
			for (int i = 0; i < v1[j].size(); i++) {
				System.out.print(v1[j].get(i) + " ");
			}
			System.out.print("\n");
		}
		
		// v = M
		// v1 = M*
		isReducedBasis(v, v1);

		
		if (reducedBasis) {
			System.out.println("\nM is a reduced basis.");
		}
		
	}

	public static double[][] getA() {
		return a;
	}
}
 