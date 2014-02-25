package edu.uncfsu.csc490;

public class MHKC_Decryption {
	
	public static void main(String[] args) {
		int I = MHKC_Encryption.I;

		// test hello decryption
		int[] C = {594,501,718,718,811};
		int[] V = new int[5];
		
		int reverseMod = 17;
		
		for (int j = 0; j < V.length; j++) {
			V[j] = (C[j]*reverseMod) % I;
			System.out.print((char)V[j] + " ");
		}
	}
}
