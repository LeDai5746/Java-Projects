package project1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DaiLe_Project3 {

	public static void main(String[] args) {
		//We begin by declaring a boolean for the do while statements
		boolean tryAgain = true;
		//A string for the user's name is created
		String mName;
		//A string for the user input and the value the program uses for the operation is created
		String Operation;
		String mOperation;
		//The user is prompted to enter their name
		System.out.print("Enter your name: ");
		Scanner input = new Scanner(System.in);
		//A do while loop repeats the entry until the name fits the requirements
		do {
			//A dummy boolean is used to stop the loop if the name fits
			boolean stop = false;
			mName = input.nextLine();
			//We convert the inputted name into an array of characters
			char[] nameChar = mName.toCharArray();
			for (char c : nameChar) {
				//If the character is not a letter, a space, or a period we stop the for loop
				if (!Character.isLetter(c) && c != ' ' && c != '.') {
					//The dummy boolean keeps the loop resetting
					stop = true;
					System.out.println("Please reenter your name.");
					//We break the for loop at the first instance of a character not meeting the requirements
					break;
				}
			}
			//If the name fits the requirements, the dummy boolean stays false and the loop ends
			tryAgain = stop;
		} while(tryAgain);
		//We reset the boolean to true for the next do while statement
		tryAgain = true;
		//The system prompts the user outside of the loop to avoid clutter
		System.out.println("Choose the type of problems you wish to work.");
		System.out.print("Enter \"A\" for Addition, \"S\" for Subtraction, \"M\" for Multiplication, \"D\" for Division: ");
		//A do while loop to select the operation
		do {
			mOperation = input.nextLine();
			//A simple if else statement checks if the input is one of the selected characters regardless of case
			if (mOperation.equalsIgnoreCase("a") || mOperation.equalsIgnoreCase("s") || mOperation.equalsIgnoreCase("m") || mOperation.equalsIgnoreCase("d"))
				tryAgain = false;
			else
				//We prompt the user to enter the specified characters if they failed to do so
				System.out.println("Please enter A, S, M, or D.");
		} while(tryAgain);
		//The program reads the input as the upper case version
		Operation = mOperation.toUpperCase();
		//Here we define the variable for which the full word will be assigned for printing purposes
		String OperationType = null;
		//A switch statement chooses the word for the operation depending on the String fed to it
		switch(Operation) {
			case "A":
				OperationType = "Addition";
				break;
			case "S":
				OperationType = "Subtraction";
				break;
			case "M":
				OperationType = "Multiplication";
				break;
			case "D":
				OperationType = "Division";
				break;
		}
		//The boolean is reset
		tryAgain = true;
		//The user input variables are created and initialized so their scope isn't limited to the loop
		int mNumber = 0;
		int lRange = 0;
		int hRange = 0;
		//The do while loop catches exceptions for the number of problems
		do {
			//The catch checks if the input is an integer
			try {
				System.out.print("Enter the number of problems you wish to work: ");
				mNumber = input.nextInt();
				//A simple if else statement checks if the number of problems is below 1 and throws a message
				if (mNumber < 1)
					System.out.println("Please enter more than zero problems.");
				//If the number of problems is above 1, we set the boolean to false to end the loop and mNumber is set to the input
				else
				tryAgain = false;
				}
			//The InputMismatchException checks if the input is an integer and throws a message if it is not
			catch (InputMismatchException e1) {
				System.out.println("Please enter an integer.");
				input.nextLine();
			}
		} while (tryAgain);
		//We reset the boolean to accomodate the next do while loop
		tryAgain = true;
		do {
			//We use the same pattern for the number of problems
			try {
				System.out.print("Enter the low value for your problems: ");
				lRange = input.nextInt();
				//The main difference is the ranges can be equal to zero
				if (lRange < 0)
					System.out.println("Please enter a positive or zero factor.");
				else
					tryAgain = false;
				}
			catch (InputMismatchException e1) {
				System.out.println("Please enter an integer.");
				input.nextLine();
			}
		} while (tryAgain);
		//We reset the boolean and use the same pattern with the high value
		tryAgain = true;
		do {
			try {
				System.out.print("Enter the high value for your problems: ");
				hRange = input.nextInt();
				if (hRange < 0)
					System.out.println("Please enter a positive or zero factor.");
				else
					//Here we must also check that the high value is actually higher than the low value, or else the problems would be trivial
					if (hRange <= lRange)
						System.out.println("Please enter a number higher than the low factor.");
					else
					tryAgain = false;
				}
			catch (InputMismatchException e1) {
				System.out.println("Please enter an integer.");
				input.nextLine();
			}
		} while (tryAgain);
		//We reset the boolean for the problem inputs
		tryAgain = true;
		//A boolean used for the do while loop to create a new set of problems is created
		boolean restart;
		do {
			//By default we do not restart the loop
			restart = false;
			//Here we create a new array of objects
			Problems [] MProblems = new Problems[mNumber];
			//Here we create a new Session object with the user's inputs as the parameters
			Session thisSession = new Session(mName, OperationType, lRange, hRange);
			//We set the start time of the session to this moment before the problems are created in the for loop
			thisSession.setStart(System.currentTimeMillis());
			//This for loop creates a new Problem object for each index of the MProblems array
			for (int i=0; i < mNumber; i++) {
				//The Problem is fed the lower and upper range
				MProblems[i] = new Problems(lRange, hRange);
				//We use the object's method to create random factors in the range
				MProblems[i].makeFactors();
				//Factors in the object are assigned to the array factors in the driver class
				int[] factors = MProblems[i].makeFactors();
				//We begin the switch statement to create the problem
				switch(Operation) {
				case "A":
						//The integer for the  Addition answer is simply the sum
						int Aans = factors[0] + factors[1];
						//We use a setter to set the value of ans in the specific Problem object to what we calculated here
						MProblems[i].setAns(Aans);
						//The system prints the problem for the user
						System.out.print(factors[0] + " + " + factors[1] + " = ");
						//We update the display which will be used to print the problem at the end of the session
						MProblems[i].setDisplay(factors[0] + " + " + factors[1] + " = ");
						//The user inputs their response
						int responseA;
						//A do while loop checks the response
						do {
							try {
								responseA = input.nextInt();
								MProblems[i].setResponse(responseA);
								tryAgain = false;
								}
							//The first catch simply checks if the input is an integer
							catch (InputMismatchException e1) {
								System.out.println("Please enter an integer.");
								input.nextLine();
							}
							//The second catch checks if the input is less than zero and throws a message if it is
							catch (IllegalArgumentException e2) {
								System.out.println("Please enter a number greater than or equal to zero.");
								input.nextLine();
							}
						} while (tryAgain);
						//We use the score response method with the user's input as the argument
						MProblems[i].ScoreResponse(MProblems[i].getResponse());
						//The image is the display plus the response, correct answer, and whether or not it was correct
						MProblems[i].setImage(MProblems[i].getDisplay() + MProblems[i].getResponse() + " A: " + MProblems[i].getAns() + " " + MProblems[i].getCorrect());
						tryAgain = true;
					break;
				case "S":
						//The subtraction case proceeds in much the same way
						//The minuend is the sum of the factors
						int minu = factors[0] + factors[1];
						//The difference is the first factor
						int Sans = factors[0];
						//We proceed on an identical course to the first case
						MProblems[i].setAns(Sans);
						System.out.print(minu + " - " + factors[1] + " = ");
						MProblems[i].setDisplay(minu + " - " + factors[1] + " = ");
						int responseS;
						//The subsequent do while loops for the response are the same as the first
						do {
							try {
								responseS = input.nextInt();
								MProblems[i].setResponse(responseS);
								tryAgain = false;
								}
							catch (InputMismatchException e1) {
								System.out.println("Please enter an integer.");
								input.nextLine();
							}
							catch (IllegalArgumentException e2) {
								System.out.println("Please enter a number greater than or equal to zero.");
								input.nextLine();
							}
						} while (tryAgain);
						MProblems[i].ScoreResponse(MProblems[i].getResponse());
						MProblems[i].setImage(MProblems[i].getDisplay() + MProblems[i].getResponse() + " A: " + MProblems[i].getAns() + " " + MProblems[i].getCorrect());
						tryAgain = true;
					break;
				case "M":
						//This works much the same as the addition case with + swapped our for *
						int Mans = factors[0] * factors[1];
						MProblems[i].setAns(Mans);
						System.out.print(factors[0] + " * " + factors[1] + " = ");
						MProblems[i].setDisplay(factors[0] + " * " + factors[1] + " = ");
						int responseM;
						do {
							try {
								responseM = input.nextInt();
								MProblems[i].setResponse(responseM);
								tryAgain = false;
								}
							catch (InputMismatchException e1) {
								System.out.println("Please enter an integer.");
								input.nextLine();
							}
							catch (IllegalArgumentException e2) {
								System.out.println("Please enter a number greater than or equal to zero.");
								input.nextLine();
							}
						} while (tryAgain);
						MProblems[i].ScoreResponse(MProblems[i].getResponse());
						MProblems[i].setImage(MProblems[i].getDisplay() + MProblems[i].getResponse() + " A: " + MProblems[i].getAns() + " " + MProblems[i].getCorrect());
						tryAgain = true;
					break;
				case "D":
						//Here we must check if a factor is zero because division by zero is not possible
						if (factors[0] == 0 || factors[1] == 0) {
							//If one of the factors is a zero, we simply do nothing and repeat the loop with the same index
							i--;
						}
						else {
						//Otherwise, we proceed similarly to the subtraction case
						int quotient = factors[0] * factors[1];
						int Dans = factors[0];
						MProblems[i].setAns(Dans);
						System.out.print(quotient + " / " + factors[1] + " = ");
						MProblems[i].setDisplay(quotient + " / " + factors[1] + " = ");
						int responseD;
						do {
							try {
								responseD = input.nextInt();
								MProblems[i].setResponse(responseD);
								tryAgain = false;
								}
							catch (InputMismatchException e1) {
								System.out.println("Please enter an integer.");
								input.nextLine();
							}
							catch (IllegalArgumentException e2) {
								System.out.println("Please enter a number greater than or equal to zero.");
								input.nextLine();
							}
						} while (tryAgain);
						MProblems[i].ScoreResponse(MProblems[i].getResponse());
						MProblems[i].setImage(MProblems[i].getDisplay() + MProblems[i].getResponse() + " A: " + MProblems[i].getAns() + " " + MProblems[i].getCorrect());
						tryAgain = true;
						}
				}
			}
			//We set the end time after the problem loop finishes
			thisSession.setEnd(System.currentTimeMillis());
			//We calculate the elapsed time using the Session object's values then send the elapsed time to the object
			thisSession.setElapsed((int)(thisSession.getEnd()-thisSession.getStart())/1000);
			//Here we keep track of the score. It begins at zero
			int Score = 0;
			//For each Problems object with a correct score, we add 1
			//For each Problems object without a correct score, we add 0
			for (int j = 0; j < mNumber; j++) {
				Score = Score + MProblems[j].getScore();
			}
			//We update the running score to what we just calculated
			thisSession.setScore(Score);
			//We print out the session summaries
			System.out.println("Session Summary");
			System.out.println(mNumber + " problems, " + thisSession.getScore() + " correct, score is " + (int)((thisSession.getScore()/(double)mNumber)*100) + " , time is " + thisSession.getElapsed() + " sec");
			System.out.println(thisSession.getArray() + ", " + LocalDate.now() + ", " + LocalTime.now() + ", " + mNumber + ", " + thisSession.getScore() + ", " + (int)((thisSession.getScore()/(double)mNumber)*100) + ", " + thisSession.getElapsed());
			//Here we print the image of the Problems, that is the problem with the user's answers, the correct answer, and whether or not it is correct
			for (int k = 0; k < mNumber; k++) {
				System.out.println(MProblems[k].getImage());
			}
			tryAgain = true;
			System.out.println("Would you like to try another set of problems? Y/N? ");
			//Here we set up exception handling for the exit loop input
			do {
			String r = input.nextLine();
			//A simple if else statement suffices because the input can only be Y/y or N/n
			if (r.equalsIgnoreCase("y") || r.equalsIgnoreCase("n")) {
				tryAgain = false;
				//If the user enters Y or y, the boolean is set to true and the do while loop triggers again, creating a new set of problems
				if (r.equalsIgnoreCase("y"))
					restart = true;
				//If the user does not enter Y, by process of elimination they entered N and the loop ends
				else
					System.out.println("Thanks for playing!");
			}
			else
				System.out.println("Please enter Y or N.");
			
			} while(tryAgain);
		//Since the restart boolean is false by default, only entering Y triggers a second set of problems
		} while(restart);
	}
}
	//We create a new class for the problems
	class Problems {
		//The Problems object has the lower and upper range
		private int low;
		private int high;
		//The range is fed into the Object when it is created
		Problems(int lo, int hi) {
			low = lo;
			high = hi;
		}
		//Here are the other variables used in the driver class
		private String probImage;
		//The score is zero to begin with and only changes to 1 if the problem is answered correctly
		private int Score = 0;
		private String correct;
		private int ans;
		private String probDisplay;
		private int response;
		//We score the problem by changing score to 1 if it is correct, then we print whether it is correct or not then store the String for the image
		public int ScoreResponse(int a) {
			if (a == ans) {
				System.out.println("Correct");
				Score++;
				correct = "Correct";
			}
			else {
				System.out.println("Incorrect");
				correct = "Incorrect";
			}
			return Score;
		}
		//The first of many getter and setter methods
		public int getScore() {
			return Score;
		}
		//We create the array of factors using the object's parameters
		public int[] makeFactors() {
			int[] Factors = new int[2];
			int minFactor = low, maxFactor = high;
			Random rand1 = new Random();
			int one = rand1.nextInt(maxFactor - minFactor + 1) + minFactor;
			Random rand2 = new Random();
			int two = rand2.nextInt(maxFactor - minFactor + 1) + minFactor;
			Factors[0] = one;
			Factors[1] = two;
			return Factors;
		}
		//Th rest of these are getters and setters
		public void setAns(int a) {
			ans = a;
		}
		public String getCorrect() {
			return correct;
		}
		public int getAns() {
			return ans;
		}
		public void setImage(String I) {
			probImage = I;
		}
		public String getImage() {
			return probImage;
		}
		public void setDisplay(String D) {
			probDisplay = D;
		}
		public String getDisplay() {
			return probDisplay;
		}
		//Here we have the method to check if the reponse is below zero
		public void setResponse(int a) {
			if (a >= 0)
				response = a;
			//If the response is not equal to or greater than zero we throw a new exception
			else
				throw new IllegalArgumentException("Answer less than zero");
		}
		public int getResponse() {
			return response;
		}
	}
	//Here we have the Session object class, of which one will be created
	class Session {
		//We keep track of time as well as the score
		private double runScore;
		private long startTime;
		private long endTime;
		private long elapsedTime;
		//The problem array is used for the session summary and consists of the name, operation type, and range
		private String probArray;
		Session(String n, String t, int l, int h) {
			probArray = n + ", " + t + ", " + h;
		}
		//Numerous getters and setters for the private variables
		public void setStart(long s) {
			startTime = s;
		}
		public long getStart() {
			return startTime;
		}
		public void setEnd(long e) {
			endTime = e;
		}
		public long getEnd() {
			return endTime;
		}
		public void setScore(int r) {
			runScore = r;
		}
		//Score is initially a double but returned as an integer
		public int getScore() {
			return (int)runScore;
		}
		public void setElapsed(long e) {
			elapsedTime = e;
		}
		public long getElapsed() {
			return elapsedTime;
		}
		public String getArray() {
			return probArray;
		}
		
	}
