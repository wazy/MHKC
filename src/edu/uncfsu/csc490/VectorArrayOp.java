package edu.uncfsu.csc490;

import java.util.Vector;

public class VectorArrayOp {
	/* transpose vector */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector[] columnVectors(Vector[] v) {
		int n = v.length;
		int m = v[0].size();
		
		Vector[] r = new Vector[n];

		for (int i = 0; i < r.length; i++) {
			r[i] = new Vector(m);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				r[i].add(v[j].get(i));
			}
		}
		return r;
	}
	
	@SuppressWarnings("rawtypes")
	public static double weight(Vector[] v) {
		double weight = 1.0;
		for (int i = 0; i < v.length; i++) {
				weight *= VectorOp.magnitude(v[i]);
		}
		return weight;
	}
	
	@SuppressWarnings("rawtypes")
	public static double volume(Vector[] v) {
		double weight = 1.0;
		for (int i = 0; i < v.length; i++) {
				weight *= VectorOp.magnitude(v[i]);
		}
		return weight;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static void printVectors(Vector[] v) {
		for (int j = 0; j < v.length; j++) { // rows
			//System.out.print("Vector: " + j + " ");
			for (int i = 0; i < v[j].size(); i++) { // cols
				System.out.print(v[j].get(i) + " ");
			}
			System.out.print("\n");
		}
	}
	
}
