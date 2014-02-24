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
	
	public static int[][] matrixMultiply(int A[][], int B[][]) {
		int[][] C = new int[2][1];
		for (int i = 0; i < 2; i++) { // aRow
			for (int j = 0; j < 2; j++) { // bColumn
				for (int k = 0; k < 1; k++) { // aColumn
					C[i][j] = A[i][k] * B[k][j];
				}
			}
		}

		return C;
	}

	public static void main(String[] args) {
		//user inputs message as string
		System.out.println("Enter your message");
		
		Scanner input = new Scanner (System.in);
		
		//put message into character array
		char [] array1 = input.nextLine().toCharArray();

		int n = (int) Math.ceil(array1.length / 2.0);
		
		int[][] matrix = new int[2][n];

		// store chars in 2X1 vectors
		for (int i = 0; i < matrix[0].length; i++) {
			int index = i * 2;
			matrix[0][i] = array1[index];
			
			// if odd re-copy last letter
			if ((index == array1.length - 1) && (array1.length % 2) == 1) {
				matrix[1][i] = array1[index];
			}
			else {
				matrix[1][i] = array1[index+1];
			}
		}
		
		int[][] a = new int[2][2];
		
		a[0][0] = 1;
		a[0][1] = 1;
		a[1][0] = 0;
		a[1][1] = 3;

		int[][] temp = new int[2][1];
		
		printMatrix(matrix);
		
		for (int i = 0; i < matrix[0].length-1; i++) {
			int index = i * 2;
			temp[0][0] = matrix[0][index];
			temp[1][0] = matrix[1][index+1];
			
			temp = matrixMultiply(temp, a);
			
			matrix[0][index] = temp[0][0];
			matrix[1][index+1] = temp[1][0];
			
		}
		
		System.out.println();
		
		printMatrix(matrix);
		//printMatrix(matrix1);
		
		//multiply each vector individually with a matrix A and store in new 2X1 vector
		
		//use modulo 26 to reduce each vectors values to ints between 0 and 26
		
		//switch the new vector values to their char value
		
		//combine the new char groups into one string
		
		//print the new encrypted message
		
		//}
		input.close();
	}
	

}
