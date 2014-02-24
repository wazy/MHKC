package edu.uncfsu.csc490;

import java.util.Scanner;

public class Encryption {

	private static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/* 2X1 multiplied with 2X2 = 2X1 */
	public static int[][] matrixMultiply(int A[][], int B[][]) {
		int[][] C = new int[2][1];
		
		int a = A[0][0];
		int b = A[1][0];
		
		int c = B[0][0];
		int d = B[0][1];
		int e = B[1][0];
		int f = B[1][1];
		
		C[0][0] = a*c + b*d;
		C[1][0] = a*e + b*f;

		return C;
	}

	public static void main(String[] args) {
		
		// user inputs message as string
		System.out.print("Enter string to be encrypted: ");
		
		Scanner input = new Scanner(System.in);
		
		// user input to char array
		char [] array1 = input.nextLine().toLowerCase().toCharArray();

		int n = (int) Math.ceil(array1.length / 2.0);
		
		int[][] matrix = new int[2][n];

		int[][] temp = new int[2][1];

		int[][] a = new int[2][2];
		
		a[0][0] = 1;
		a[0][1] = 1;
		a[1][0] = 0;
		a[1][1] = 3;
		
		// store chars in 2X1 vectors and reduce to integer between 0-25
		for (int i = 0; i < matrix[0].length; i++) {
			int index = i * 2;

			// if odd re-copy last letter
			if ((index == array1.length - 1) && (array1.length % 2) == 1) {
				temp[0][0] = array1[index];
				temp[1][0] = array1[index];
				
				temp = matrixMultiply(temp, a);
				
				matrix[0][i] = (temp[0][0] % 26) + 'a';
				matrix[1][i] = (temp[1][0] % 26) + 'a';
			}
			else {
				temp[0][0] = array1[index];
				temp[1][0] = array1[index+1];
				
				temp = matrixMultiply(temp, a);
				
				matrix[0][i] = (temp[0][0] % 26) + 'a';
				matrix[1][i] = (temp[1][0] % 26) + 'a';
			}
		}

		printMatrix(matrix);

		String encryptedString = "";

		/* print out new encrypted message */
		for (int i = 0; i < matrix[0].length; i++) {
			encryptedString += (char) matrix[0][i];
			encryptedString += (char) matrix[1][i];	
		}

		System.out.println("The encrypted string is: " + encryptedString);

		input.close();
	}
}
