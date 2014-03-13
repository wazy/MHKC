package edu.uncfsu.csc490;

import java.math.BigInteger;

public class MHKC_Decryption {
	
	public static void main(String[] args) {
		BigInteger I = new BigInteger(String.valueOf(MHKC_Encryption.I));
		BigInteger R = new BigInteger(String.valueOf(MHKC_Encryption.R));

		BigInteger[] Prk = {new BigInteger("1"), new BigInteger("2"), 
				new BigInteger("4"), new BigInteger("8"), new BigInteger("16"), 
				new BigInteger("32"), new BigInteger("64"), new BigInteger("128")};
		
		// encrypted values to be solved for
		BigInteger[] C = {  new BigInteger("295"), new BigInteger("412"), 
							new BigInteger("622"), new BigInteger("622"), 
							new BigInteger("788"), new BigInteger("62"),
							new BigInteger("679"), new BigInteger("788"),
							new BigInteger("577"), new BigInteger("622"),
							new BigInteger("389")};
		
		String[] V = new String[C.length];
		
		BigInteger reverseMod = R.modInverse(I);
		
		String result = "";
		
		for (int j = 0; j < V.length; j++) {
			BigInteger z = C[j].multiply(reverseMod).mod(I);
			V[j] = superIncreasingSolver(Prk, z);
			//System.out.println(C[j].toString() + " x " + reverseMod.toString() + 
			//					" mod " + I.toString() + " = " + V[j].toString());
			int charCode = Integer.parseInt(V[j], 2);
			
			if ((char)charCode == '@') {
				result += " ";
			}
			else {
				result += new Character((char)charCode).toString();
			}
		}
		
		System.out.print("The ciphertext input is => ");
		for (int j = 0; j < C.length; j++) {
			System.out.print(C[j].toString() + " ");
		}
		System.out.println("\n\nAnd after decrypting this we get: '" + result + "'");
	}

	/* this finds the values of the private key that fit into the encoded letter */
	private static String superIncreasingSolver(BigInteger[] a, BigInteger b1) {
		
		int n = a.length-1;
		int z = b1.intValue();
		byte[] answerArr = new byte[a.length];
		
		/* find PRK values that fit in descending order */
		for (int i = n; i >= 0; i--) {
			if (z >= a[i].intValue()) {
				z -= a[i].intValue();
				answerArr[i] = 1;
			}
			else {
				answerArr[i] = 0;
			}
		}
		
		// no solution -- error
		if (z != 0) {
			System.out.println("NO SOLUTION");
			System.exit(1);
		}

		String res = "";
		// put binary array to string
		for (int i = 0; i < answerArr.length; i++) {
			res += answerArr[i];
		}

		return res;
	}
}
