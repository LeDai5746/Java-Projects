package project1;

import java.time.LocalDate;
import java.util.Scanner;

public class Project1_001 {

	public static void main(String[] args) {
		// Dai Le, 001, project1
		//This program creates a flashcard session.
		//We tell the user to input values
		String name = getName();
		String operation = getOperation();
		//We use double for the number for purposes of calculating the score
		double number = getNumber();
		int[] range = getRange();
		//We store the starting system time s as a long value and the end time e as zero to begin with.
		long s = System.currentTimeMillis();
		long e = 0;
		//The number of correct answers starts out at 0 and is initialized as such
		double correct = 0;
		//The switches for the operations
		switch(operation) {
		case "A":
			for (int i=1; i <= number; i++) {
				//We pull the factors from getFactors then add them up to get ans
				int[] factors = getFactors(range);
				int ans = factors[0] + factors[1];
				//The user is prompted for a response
				System.out.print(factors[0] + "+" + factors[1] + "=");
				int response = getResponse();
				//We send the ans and the response and the current correct counter to scoreproblem
				correct = scoreproblem(ans, response, correct);
			}
			//We end by marking the time after the for loop and changing operation to the full name for display purposes.
			e = System.currentTimeMillis();
			operation = "Addition";
			break;
		case "S":
			//Subtraction proceeds much the same way except the user is presented with the difference and factor 2
			for (int i=1; i <= number; i++) {
				int[] factors = getFactors(range);
				int diff = factors[0] + factors[1];
				System.out.print(diff + "-" + factors[1] + "=");
				//We use factor 1 as the answer and factor 2 in the problem for uniformity
				int ans = factors[0];
				int response = getResponse();
				correct = scoreproblem(ans, response, correct);
			}
			e = System.currentTimeMillis();
			operation = "Subtraction";
			break;
		case "M":
			//Multiplication is practically the same as addition
			for (int i=1; i <= number; i++) {
				int[] factors = getFactors(range);
				int ans = factors[0] * factors[1];
				System.out.print(factors[0] + "*" + factors[1] + "=");
				int response = getResponse();
				correct = scoreproblem(ans, response, correct);
			}
			e = System.currentTimeMillis();
			operation = "Multiplication";
			break;
		case "D":
			//Division is similar to subtraction with one key difference
			for (int i=1; i <= number; i++) {
				int[] factors = getFactors(range);
				//We must check if the factor is zero, if it is then we cannot divide
				if (factors[0] == 0 || factors[1] == 0) {
					//This if statement checks if a factor is zero then deincriments i, skipping this factor set.
					i--;
				}
				else {
				//If no factors are zero, we proceed similar to the division case
				int quotient = factors[0] * factors[1];
				System.out.print(quotient + "/" + factors[1] + "=");
				int ans = factors[0];
				int response = getResponse();
				correct = scoreproblem(ans, response, correct);
				}
			}
			e = System.currentTimeMillis();
			operation = "Division";
			break;
		}
		//We display the session statistics for the user, marking the duration and the end time.
		System.out.println("Session Summary");
		System.out.println((int)number + " problems, " + (int)correct + " correct");
		//The score and time are calculated from doubles but converted to integers
		System.out.println("Score is " + (int)((correct/number)*100) + ", Time is " + ((int)(e-s)/1000) + " seconds");
		System.out.println("Session for " + name + " was " + operation + " on " + java.time.LocalDate.now() + " at " + java.time.LocalTime.now());
	}
	//This is the method to input the user's name
	public static String getName()	{
		//The user is asked to type in their name and the program accepts the next input and stores it as nName
		System.out.print("Enter your name: ");
		Scanner input = new Scanner(System.in);
		String mName = input.next();
		//The method returns the string mName
		return mName;
	}
	//This is the method for the operation input
	public static String getOperation()	{
		//We follow the same steps for inputting the name
		System.out.print("Enter \"A\" for Addition, \"S\" for Subtraction, \"M\" for Multiplication, \"D\" for Division: ");
		Scanner input = new Scanner(System.in);
		String mOperation = input.next();
		return mOperation;
	}
	//This is the method for the number of problems
	public static int getNumber() {
		//Similar steps for inputs
		System.out.print("Enter the number of problems you wish to work: ");
		Scanner input = new Scanner(System.in);
		//The number of problems must be an integer
		int mNumber = input.nextInt();
		return mNumber;
	}
	//This is the method for the range of factors
	public static int[] getRange()	{
		//The user enters the low value and the high value.
		System.out.print("Enter the low value for your problems: ");
		Scanner input = new Scanner(System.in);
		int lRange = input.nextInt();
		System.out.print("Enter the high value for your problems: ");
		Scanner input2 = new Scanner(System.in);
		int hRange = input2.nextInt();
		//The values are stored as integers and a new array is created
		int[] Range = new int[2];
		Range[0] = lRange;
		Range[1] = hRange;
		//The values of the array are changed to that of the integers and the array is returned
		return Range;
	}
	//This is the method to generate the factors from an array
	public static int[] getFactors(int[] arr) {
		//A new array is created
		int[] Factors = new int[2];
		//The two values from the input array are assigned to minimum factor and maximum factor
		int minFactor = arr[0], maxFactor = arr[1];
		//We generate a random value in the range by generating a value within the difference of the max and the min
		//This random value is converted to an integer and added to the min, giving us a value from min to max
		int one = (int)(Math.random()*((maxFactor - minFactor + 1))) + minFactor;
		int two = (int)(Math.random()*((maxFactor - minFactor + 1))) + minFactor;
		//The values are assigned to the array regardless of order and the array is returned
		Factors[0] = one;
		Factors[1] = two;
		return Factors;
	}
	//This is the method to get the user response
	public static int getResponse() {
		//The user enters an integer and it is recorded as response
		Scanner input = new Scanner(System.in);
		int response = input.nextInt();
		return response;
	}
	//This is the method to score the problem and keep the tally
	public static double scoreproblem(int a, int b, double c) {
		//If the answer matches the response, the score counter c is incremented up and the user is told Correct
		if (a == b) {
			c++;
			System.out.println("Correct");
		}
		//Otherwise the system tells the user the answer is incorrect and does not increment the tally.
		else {
			System.out.println("Incorrect");
		}	
		//The final correct tally is returned as c.
		return c;	
	}
}
