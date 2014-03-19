package edu.uncfsu.csc490;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Vector;

// TODO: Expand this class and implement 8.4

@SuppressWarnings({ "rawtypes", "unchecked" })
public class IntegerEquationsSolver extends LLL {

	public static void main(String[] args) throws IOException {

		String line = null;
		String filename = "in/3by3.txt";

		int count = 0;
		int lines = 0;

		// for file I/O
		RandomAccessFile in = new RandomAccessFile(filename, "r");
		
		// count number of lines
		while (in.readLine() != null) {
			lines++;
		}

		// allocate enough memory dynamically
		Vector[] M = new Vector[lines];

		// reset to beginning of file
		in.seek(0);

		// populate vectory array
		while((line = in.readLine()) != null) {
			String[] strArr = line.split(" ");
			M[count] = new Vector(strArr.length);
			Collections.addAll(M[count], strArr);
			count++;
		}
		
		in.close();

		if (M[0] == null) {
			System.out.println("Fatal: b is null");
			System.exit(1);
		}

		// transpose M and calculate M'
		Vector[] M1 = M.clone();
		M = transpose(M);
		M1 = reduce(M.clone());

		// showcase the results
		System.out.println("**************************************************\n");
		print("M is:", M);
		print("M' is:", M1);
		System.out.println("**************************************************\n");

		// redundant --> LLL should not terminate unless it is reduced
		if (reducedBasis)
			System.out.println("M' is reduced.");
	}
}
