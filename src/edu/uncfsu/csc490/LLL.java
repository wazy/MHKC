package edu.uncfsu.csc490;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class LLL {

	public static boolean done;
	public static double aij;

	@SuppressWarnings({ "rawtypes" })
	public static Vector[] reduce(Vector[] v) {
		
		int n = v.length;
		Vector[] v1 = GramSchmidt.execute(v);
		
		done = false;
		
		while (!done)
		{
			for (int j = 1; j < n-1; j++) {
				for (int i = j-1; i > 0; i--) {
					aij = (VectorOp.dotProduct(v1[i], v[j])) / (Math.pow(VectorOp.magnitude(v1[i]), 2));
					
					if (Math.abs(aij) > .5) {
						v[j] = VectorOp.subtract(v[j], VectorOp.scalarMult(Math.floor(aij+.5), v[i]));
					}
				}

				double rhs = .75 * Math.pow(VectorOp.magnitude(v1[j]), 2);
					
				double ajj = (VectorOp.dotProduct(v1[j], v[j+1])) / (Math.pow(VectorOp.magnitude(v1[j]), 2));
				Vector x = VectorOp.scalarMult(ajj, v1[j]); 
				x = VectorOp.add(v1[j+1], x);
				double lhs = Math.pow(VectorOp.magnitude(x), 2);
				
				System.out.println(lhs);
				System.out.println(rhs);
				
				if (lhs < rhs) {
					System.out.println("TAKE");
					Vector temp = new Vector();
					temp = v[j];
					v[j] = v[j+1];
					v[j+1] = temp;
				}
				else {
					done = true;
				}
				v1 = GramSchmidt.execute(v);
			}
		}
		return v;
	}

	@SuppressWarnings({ "rawtypes" })
	private static void printMatrix(Vector[] v) {
		for (int j = 0; j < v.length; j++) {
			for (int i = 0; i < v[j].size(); i++) {
				System.out.print(v[j].get(i) + " ");
			}
			System.out.print("\n");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws IOException {
		
		int count = 0;

		Vector[] v = new Vector[3];
		
		BufferedReader in = new BufferedReader(new FileReader("in/simple.txt"));
		String line = null;
		
		while((line = in.readLine()) != null) {
			String[] strArr = line.split(" ");
			
			v[count] = new Vector(strArr.length);
			
			Collections.addAll(v[count], strArr);
			
			count++;
		}
		
		in.close();
		
		v = reduce(v);
		
		printMatrix(v);
		
	}
}
