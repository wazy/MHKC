package edu.uncfsu.csc490;

import java.util.Random;

public class MHKC_Encryption {

	public static int[] privateKey = {1, 2, 4, 8, 16, 32, 64, 128};
	public static int[] publicKey = {31, 62, 124, 248, 233, 203, 143, 23};
	public static int[] cipherHolder;
	
	private static int sum = 255;
	public static int I = 263; // modulo
	public static int R = 31; // multiplier

	private static String test = "hello world";
	
	public static int gcd(int a, int b) {
		if (b == 0) 
			return a;
	
		return gcd(b, a%b);
	}
	
	public static void generateIRValues() {
		Random generator = new Random();
		
		/* I is a random integer greater than the sum of private key */
		I = generator.nextInt(sum) + sum+1;

		/* generate an R such that GCD(I,R) = 1 */
		while (gcd(I, R = generator.nextInt(1000)+1) != 1) { ; }
	}
	
	public static void generatePublicKey() {
		
		//generateIRValues()
		
		/* generate public key */
		for (int i = 0; i < privateKey.length; i++) {
			publicKey[i] = (privateKey[i] * R) % I;
		}
	}
	
	public static void encrypt(String input) {

		byte[] binaryString = input.getBytes();

		/* nasty way to multiply each bit by the public key index and sum */
		for (int i = 0; i < binaryString.length; i++) {
			String bi = Integer.toBinaryString(binaryString[i]);
			if (bi.length() < 8)
				bi = "0" + bi;
			
			char arr[] = bi.toCharArray();
			
			for (int j = 0; j < arr.length; j++) {
				int result = (((int) arr[j])-48) * publicKey[j];
				//System.out.println((((int) arr[i])-48) + " " + publicKey[i] + " = " + result);
				cipherHolder[i] += result;
			}
		}
	}
	
	public static void main(String[] args) {

		//generatePublicKey();

		cipherHolder = new int[test.length()];

		/* initialize each letters cipher to zero */
		for (int i = 0; i < cipherHolder.length; i++) {
			cipherHolder[i] = 0;
		}

		encrypt(test);

		/* print summary of the encryption process and result */
		System.out.print("The ciphertext output for '" + test + "' is => ");
		for (int i = 0; i < cipherHolder.length; i++) {
			System.out.print(cipherHolder[i] + " ");
		}
		
		System.out.print("\n\nThe private key is: => ");
		for (int i = 0; i < privateKey.length; i++) {
			System.out.print(privateKey[i] + " ");
		}
		
		System.out.print("\nThe public key is: => ");
		for (int i = 0; i < publicKey.length; i++) {
			System.out.print(publicKey[i] + " ");
		}
		System.out.println("\n\nThis was generated using a sum of: " + sum + ", an I of: " + I + 
																			", and an R of: " + R + ".");
	}
}
