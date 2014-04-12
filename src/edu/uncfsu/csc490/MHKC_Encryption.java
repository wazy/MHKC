package edu.uncfsu.csc490;

import java.util.Random;

public class MHKC_Encryption {

	public static int[] privateKey = {1, 2, 4, 8, 16, 32, 64, 128};
	public static int[] publicKey = {31, 62, 124, 248, 233, 203, 143, 23};
	public static int[] cipherHolder;
	
	private static int sum = 255;
	public static int I = 263; // modulo
	public static int R = 31; // multiplier

	private static String text = "HashMap in Java is one of the most popular Collection class among Java programmers. After by article How HashMap works in Java, which describes theory part of Java HashMap ,I thought to share, How to use HashMap in Java with fundamental HashMap examples, but couldn't do that and it was slipped. HashMap is a a data structure, based on hashing, which allows you to store object as key value pair, advantage of using HashMap is that, you can retrieve object on constant time i.e. O(1), if you know the key. HashMap implements Map interface and supports Generics from Java 1.5 release, which makes it type safe. There are couple of more Collections, which provides similar functionalities like HashMap, which can also be used to store key value pair. Hashtable is one of them, but Hashtable is synchronized and performs poor in single threaded environment. See Hashtable vs HashMap for complete differences between them. Another one, relatively new is ConcurrentHashMap, which provides better performance than Hashtable in concurrent environment and should be preferred. See difference between ConcurrentHashMap and HashMap for detail differences. In this Java tutorial, we will see different examples of  HashMap, like adding and removing entries, iterating over Java HashMap, checking size map, finding if a key or value exists on Map and various other examples, which we used frequently.";
	
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
			while (bi.length() < 8)
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

		cipherHolder = new int[text.length()];

		/* initialize each letters cipher to zero */
		for (int i = 0; i < cipherHolder.length; i++) {
			cipherHolder[i] = 0;
		}

		encrypt(text);

		/* print summary of the encryption process and result */
		System.out.print("The ciphertext output for '" + text + "' is => ");
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
