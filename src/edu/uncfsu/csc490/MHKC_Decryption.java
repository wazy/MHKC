package edu.uncfsu.csc490;

import java.math.BigInteger;

public class MHKC_Decryption {
	
	public static void main(String[] args) {
		BigInteger I = new BigInteger(String.valueOf(MHKC_Encryption.I));
		BigInteger R = new BigInteger(String.valueOf(MHKC_Encryption.R));
		
		// the encrypted values of this array spell out "hello"
		BigInteger[] C = {new BigInteger("408"), new BigInteger("209"), 
							new BigInteger("645"), new BigInteger("442"), 
							new BigInteger("622"), new BigInteger("690"), new BigInteger("431"),
							new BigInteger("660"), new BigInteger("690")};
		//408 209 645 442 622 690 431 660 690
		
		String[] V = new String[9];
		
		BigInteger reverseMod = R.modInverse(I);
		
		for (int j = 0; j < V.length; j++) {
			V[j] = solveValue(C[j].multiply(reverseMod).mod(I));
			//System.out.println(C[j].toString() + " x " + reverseMod.toString() + 
			//					" mod " + I.toString() + " = " + V[j].toString());
			int charCode = Integer.parseInt(V[j], 2);
			String str = new Character((char)charCode).toString();
			System.out.print(str + " ");
		}
	}

	/* this finds the values of the private key that fit into the encoded letter */
	private static String solveValue(BigInteger b1) {

		BigInteger[] Prk = {new BigInteger("1"), new BigInteger("2"), 
				new BigInteger("4"), new BigInteger("8"), new BigInteger("16"), 
				new BigInteger("32"), new BigInteger("64"), new BigInteger("128")};

		int[] answerArr = new int[8];
		for (int i = 0; i < answerArr.length; i++) {
			answerArr[i] = 0;
		}

		int value = b1.intValue();

		/* NOTE ASSUMPTION IS MADE THAT PRK IS INCREASING */
		/* find PRK values that fit in descending order */
		for (int j = Prk.length-1; j >= 0; j--) {
			if (Prk[j].intValue() > value)
				continue;

			value -= Prk[j].intValue();
			answerArr[j] = 1;
		}

		String res = "";

		for (int i = 0; i < answerArr.length; i++) {
			res += answerArr[i];
		}
		System.out.println(res);
		return res;
	}
}
