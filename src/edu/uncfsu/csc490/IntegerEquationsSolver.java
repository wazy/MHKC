package edu.uncfsu.csc490;

import java.io.IOException;
//just a test
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Vector;

/** solving systems of integer equations -- this will break MHKC (sometimes) */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IntegerEquationsSolver extends Utils {

	public static int originalN = 0;
	public static int originalM = 0;

	public static void main(String[] args) throws IOException {

		String line = null;

		//String filename = "in/3by3.txt";
		//String filename = "in/letter_h.txt";
		String filename = "in/example85.txt";

		int count = 0;
		int lines = 0;

		// for file I/O
		RandomAccessFile in = new RandomAccessFile(filename, "r");
		
		// count number of lines
		while (in.readLine() != null) {
			lines++;
		}

		// allocate enough memory dynamically
		Vector[] A = new Vector[lines];

		// reset to beginning of file
		in.seek(0);

		// populate vectory array
		while((line = in.readLine()) != null) {
			String[] strArr = line.split(" ");
			A[count] = new Vector(strArr.length);
			Collections.addAll(A[count], strArr);
			count++;
		}

		in.close();

		if (A[0] == null) {
			System.out.println("Fatal: vectors are null");
			System.exit(1);
		}
		
		originalN = A.length;
		originalM = A[0].size();
		
		A = transpose(A);

		Vector[] M = generateM(A);

		// transpose M and calculate M'
		Vector[] M1 = M.clone();

		M1 = LLL.reduce(M1);

		printResults(M, M1, 1);
		
	}

	/** 
	 * this prints the result of the attempt to break MHKC
	 * 
	 * @param M the original vectors
	 * @param M1 the reduced vectors either by KR or LLL
	 * @param flag determines whether LLL == 1 or KR == 2 was used
	 */
	private static void printResults(Vector[] M, Vector[] M1, int flag) {
		// showcase the results
		System.out.println("**************************************************\n");
		print("M is:", M);

		if (flag == 1)
			print("M' is:", M1);
		else
			print("M'' is:", M1);

		System.out.println("**************************************************\n");

		System.out.println("Weight of M is: " + weight(M));

		if (flag == 1)
			System.out.println("Weight of M' is: " + weight(M1));
		else
			System.out.println("Weight of M'' is: " + weight(M1));
		
		// search for a solution in the reduced vectors
		Vector U = solveKnapsack(M1);

		if (U == null) {
			System.out.println("\nNo solution found!");

			if (flag == 1) {
				System.out.println("\nTrying KR to generate M''...\n");
				M1 = KR.weightReduce(M1);
				printResults(M, M1, 2);
			}
		}
		else {
			System.out.println("\nSolution found!");
			printSolution(originalM, U);
		}
	}

	public static Vector[] generateM(Vector[] A /*, Vector B */) {

		// vectors (amount of columns) with (amount of rows) indices each
		System.out.println("M has " + (originalM + 1) + " vectors with " + (originalN + originalM) + " indices.\n");

		// TODO: this vector will be passed in as a parameter
		Vector B = new Vector(originalN);

		// the letter h in binary is 01101000
		//B.add(419);

		// 8.4
		//B.add(1); B.add(1); B.add(1); B.add(1); B.add(1); B.add(1); B.add(7);

		// 8.5
		B.add(1); B.add(1); B.add(1); B.add(1); B.add(1); B.add(35);

		// 8.7
		//B.add(6665);

		B = negate(B);
		
		// create a new matrx n+m by m+1
		Vector[] M = new Vector[originalM + 1];
		
		int n = M.length;
		int m = originalN + originalM;

		// set identity matrix -- diagonal of 1's
		for (int i = 0; i < n; i++) {
			M[i] = new Vector(m);
			for (int j = 0; j < originalM; j++) {
				M[i].add(0);
			}
			if (i != n-1)
				M[i].set(i, 1);
		}

		// copy A's values into the new matrix
		for (int i = 0; i < n-1; i++) {
			int idx = 0;
			for (int j = n; j <= m; j++) {
				M[i].add(A[i].get(idx++));
			}
		}

		// copy the negated vector B into the new matrix
		int idx = 0;
		for (int j = n; j <= m; j++) {
			M[n-1].add(B.get(idx++));
		}
		
		return M;
	}
	
	public static Vector solveKnapsack(Vector[] M1) {

		// vectors (amount of columns) with (amount of rows) indices each
		int n = M1.length;

		int result = -1;

		// if vector solves the knapsack return true 
		for (int i = 0; i < n; i++) {
			if (vectorInRange(M1[i])) {
				result = i;
				break;
			}
		}

		if (result < 0)
			return null;

		return M1[result];
	}
	
	public static boolean vectorInRange(Vector v) {

		for (int i = 0; i < originalM; i++) {
			String value = v.get(i).toString();			
			value = value.replace(".0", "");
			int n = Integer.parseInt(value);

			if ((n != 0) && (n != 1))
					return false;
		}
		
		for (int i = originalM; i < v.size(); i++) {
			String value = v.get(i).toString();
			value = value.replace(".0", "");
			int n = Integer.parseInt(value);

			if (n != 0)
				return false;
		}
		
		return true;
	}
}
