package edu.uncfsu.csc490;
import java.util.Scanner;


public class Encryption {

	public static void main(String[] args) {
		//user inputs message as string
		System.out.println("Enter your message");
		Scanner input = new Scanner (System.in);
		String message = input.nextLine();
		String message1 = message.toLowerCase();
		
		
		//put message into character array
		char [] array1 = message1.toCharArray();
		
		if ((array1.length & 1) == 1){
			System.out.println("odd");
			char temp = array1[array1.length - 1];
		}	
	
		for (int i = 0; i < array1.length; i++) {

			char c = array1[i];
			
			System.out.println(c % 26);
		}
		
		
		//put message into groups of 2 chars, duplicate last char if odd # of chars in message
		
		//switch chars in each group to their int value and store in 2X1 vectors
		
		//multiply each vector individually with a matrix A and store in new 2X1 vector
		
		//use modulo 26 to reduce each vectors values to ints between 0 and 26
		
		//switch the new vector values to their char value
		
		//combine the new char groups into one string
		
		//print the new encrypted message
		
			
		
		//}
		input.close();
	}
	

}
