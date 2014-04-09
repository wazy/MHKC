package edu.uncfsu.csc490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Vector;

public class BreakMHKCConsole {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IOException {
		String line = null;

		//String filename = "in/example85.txt";
		String filename = "in/public_key.txt";

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

		// populate vector array
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

		System.out.print("The public key is: ");
		for (int i = 0; i < A[0].size(); i++) {
			System.out.print(A[0].get(i) + " ");
		}

		System.out.println();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = A.length;
		int m = A[0].size();
		
		boolean done = false;
		while (!done) {

			System.out.println("\nEnter the encrypted letters separated by spaces.");
			System.out.print(">>> ");

			String[] strArr = br.readLine().split(" ");

			String ans = "";

			for (int i = 0; i < strArr.length; i++) {
				Vector B = new Vector(n);

				for (int j = 0; j < n; j++) {
					B.add(Integer.parseInt(strArr[i]));					
				}

				Vector result = IntegerEquationsSolver.solve(A, B);
				
				ans += printBinaryToASCIIResult(m, result);
			}

			System.out.println("\n\nThe decrypted text is: " + ans);
			
			System.out.println("\nWould you like to continue y/n? ");
			System.out.print(">>> ");

			if (br.readLine().charAt(0) == 'n') {
				System.out.println("\nGoodbye!\n");
				done = true;
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static String printBinaryToASCIIResult(int m, Vector result) {

		if (result == null)
			return "*";

		String res = "";
		String ans = "";

		// put binary array to string
		for (int i = 0; i < m; i++) {
			res += result.get(i);
		}
		
		int charCode = Integer.parseInt(res, 2);
	
		ans += new Character((char)charCode).toString();
		
		return ans;
	}
}
