package edu.uncfsu.csc490;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class LLL {

	private static boolean done;
	private static double[][] a;

	@SuppressWarnings({ "rawtypes" })
	public static Vector[] reduce(Vector[] v) {

		int n = v.length; // rows
		
		Vector[] v1 = GramSchmidt.execute(v);
		
		a = GramSchmidt.getA();

		System.out.println("Printing A:");
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		done = false;
		
		while (!done)
		{
			for (int j = 1; j <= n-1; j++) {
				for (int i = j-2; i >= 0; i--) {
					if (Math.abs(a[i][j]) > .5) {
						v[j] = VectorOp.subtract(v[j], VectorOp.scalarMult(Math.floor(a[i][j]+.5), v[i]));
					}
				}
			}
			
			boolean pass = false;
			
			for (int j = 0; j < n-1; j++) {
				
				double rhs = .75 * Math.pow(VectorOp.magnitude(v1[j]), 2);
					
				Vector x = VectorOp.scalarMult(a[j][j+1], v1[j]); 
				x = VectorOp.add(v1[j+1], x);
				double lhs = Math.pow(VectorOp.magnitude(x), 2);
				
				if (lhs < rhs) {
					Vector temp = new Vector();
					temp = v[j];
					v[j] = v[j+1];
					v[j+1] = temp;
					pass = true;
				}
			}
			if (!pass) {
				done = true;
			}
			v1 = GramSchmidt.execute(v);
			a = GramSchmidt.getA();
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

		Vector[] v = new Vector[5];
		
		/* 
		 * IN:
		 * 1 1 1
		 * 1 0 1
		 * 1 1 0
		 * 
		 * EXPECTED OUT:
		 * 0 -1 0
		 * 1 0 0
		 * 0 0 1
		 */
		
		BufferedReader in = new BufferedReader(new FileReader("in/simple.txt"));
		String line = null;
		
		while((line = in.readLine()) != null) {
			String[] strArr = line.split(" ");
			
			v[count] = new Vector(strArr.length);
			
			Collections.addAll(v[count], strArr);
			
			count++;
		}
		
		in.close();
		if (v[0] == null){
			System.out.println("v is null");
		}
		
		v = reduce(v);
		
		printMatrix(v);
		
	}
}
