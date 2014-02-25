package edu.uncfsu.csc490;

import java.util.Random;

public class MHKC_Encryption {

	private static int[] privateKey;
	private static int[] publicKey;
	private static int[] cipherHolder;
	
	private static int sum = 0;
	private static int I = 0;
	private static int R = 0;

	private static String test = "Example";
	
	public static int gcd(int a, int b) {
		if (b == 0) 
			return a;
	
		return gcd(b, a%b);
	}
	
	public static void main(String[] args) {

		String[] temp = "1, 2, 4, 8, 16, 32, 64, 128".split(", ");
		
		privateKey = new int[temp.length];
		publicKey = new int[privateKey.length];
		cipherHolder = new int[test.length()];
		
		/* convert string values of private key to integers */
		for (int i = 0; i < temp.length; i++) {
			int t = Integer.parseInt(temp[i]);
			privateKey[i] = t;
			sum += t;
		}
		
		Random generator = new Random();
		
		/* I is a random integer greater than the sum of private key */
		I = generator.nextInt(sum) + sum+1;

		/* generate an R such that GCD(I,R) = 1 */
		while (gcd(I, R = generator.nextInt(sum)+sum+1) != 1) { ; }
		
		/* generate public key */
		for (int i = 0; i < privateKey.length; i++) {
			publicKey[i] = (privateKey[i] * R) % I;
		}

		/* initialize each letters cipher to zero */
		for (int i = 0; i < cipherHolder.length; i++) {
			cipherHolder[i] = 0;
		}		

		byte[] binaryString = test.getBytes();

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
