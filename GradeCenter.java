import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

/**
 * A program that reads test scores, averages them, processes a number grade, 
 * and neatly outputs information in rows and columns either on the command line
 * or in a text file. 
 * @author Nick Kammerer
 * @version 1.0
 */
public class GradeCenter {

	// Declaration of constants
	final static int GRADE_REPORT = 1;
	final static int GRADE_REPORT_IN_FILE = 2;
	final static int EXIT = 3;
	final static int STUDENT_AMOUNT = 4; // Edit this constant to scale student amount.
	final static int STUDENT_AMOUNT_APPENDED = STUDENT_AMOUNT + 1;
	
	/**
	 * Prompts input of test 1 and test 2 scores, returns 
	 * a grade report either in terminal or text file. 
	 * @param args A reference to a string array to store
     *             command-line arguments
	 */
	public static void main(String[] args) {
		// Declaration of variables
		boolean running = true;
		int choice;  
		int[] testOne = new int[STUDENT_AMOUNT_APPENDED], testTwo = new int[STUDENT_AMOUNT_APPENDED];
		double[] average = new double[STUDENT_AMOUNT_APPENDED];
		char[] letterGrade = new char[STUDENT_AMOUNT_APPENDED];
		String fileName;
		File reportFile;
		PrintWriter reportWriter;
		Scanner input = new Scanner(System.in);
		DecimalFormat scoreFormat = new DecimalFormat("000");
		DecimalFormat averageFormat = new DecimalFormat("000.0");

		// Main loop
		while (running == true) {
			// Prompt user menu choice & store choice
			System.out.println("Welcome to Grate Center!");
			System.out.println("Enter 1 to generate and display a grade report.");
			System.out.println("Enter 2 to generate a a grade report and save it into a file.");
			System.out.println("Enter 3 to quit.");
			System.out.print("Enter a choice: ");
			choice = input.nextInt();
			
			// Conditional structure to determine which menu action to take
			switch(choice) {
				case GRADE_REPORT:
					// Prompt grade input for test 1  
					System.out.println("");
					System.out.println("Enter grades for test 1 and test 2.");
					System.out.println("For test 1,");
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
						System.out.print("Enter score " + count + ": ");
						testOne[count] = input.nextInt();
					}
					
					// Prompt grade input for test 2
					System.out.println("");
					System.out.println("For test 2,");
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
						System.out.print("Enter score " + count + ": ");
						testTwo[count] = input.nextInt();
					}
					// Call to calculateAverage() method
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
							average[count] = calculateAverage(testOne[count], testTwo[count]);
						
					}
					// Call to calculateLetterGrade() method
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
						letterGrade[count] = calculateLetterGrade(average[count]);
					}
					// Output report
					System.out.println("");
					System.out.println("Test 1\tTest 2\tAverage\tGrade");
					System.out.println("------\t------\t-------\t-----");
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
						System.out.println(scoreFormat.format(testOne[count]) + "\t" + scoreFormat.format(testTwo[count]) + "\t" + averageFormat.format(average[count]) + "\t" + letterGrade[count]);
					}
					System.out.println("");
					
					break;
					
				case GRADE_REPORT_IN_FILE:
					// Prompt grade input for test 1  
					System.out.println("");
					System.out.println("Enter grades for test 1 and test 2.");
					System.out.println("For test 1,");
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
						System.out.print("Enter score " + count + ": ");
						testOne[count] = input.nextInt();
					}
					
					// Prompt grade input for test 2
					System.out.println("");
					System.out.println("For test 2,");
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
						System.out.print("Enter score " + count + ": ");
						testTwo[count] = input.nextInt();
					}
					// Call to calculateAverage() method
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
							average[count] = calculateAverage(testOne[count], testTwo[count]);
						
					}
					// Call to calculateLetterGrade() method
					for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
						letterGrade[count] = calculateLetterGrade(average[count]);
					}
					
					// Prompt & accept file name
					System.out.println("");
					System.out.println("A new file will be created for the report.");
					System.out.println("Enter the new file name(For example, MyReport.txt");
					fileName = input.next();
					reportFile = new File(fileName);
					
					// Output report to file
					try {
						reportFile.createNewFile();
						} catch (IOException e) {
							// Print file-creation error message
							System.out.println("ERROR: file could not be created.");
							e.printStackTrace();
						}
					try {
						reportWriter = new PrintWriter(reportFile);
						reportWriter.println("\n");
						reportWriter.println("Test 1 \t Test 2 \t Average \t Grade \n");
						reportWriter.println("------ \t ------ \t ------- \t ----- \n");
						for(int count = 1; count < STUDENT_AMOUNT_APPENDED; count++) {
							reportWriter.println(scoreFormat.format(testOne[count]) + " \t " + scoreFormat.format(testTwo[count]) + " \t \t " + averageFormat.format(average[count]) + " \t \t " + letterGrade[count] + "\n");
						}
						reportWriter.close();
					} catch (IOException e) {
						System.out.println("ERROR: file could not be written to.");
						e.printStackTrace();
					}
					
					// Inform user of file write completion
					System.out.println("Report written into a file: " + fileName + "\n");
					
					break;
					
				case EXIT:
					// Exit main loop.
					running = false;
					
					break;
			}

		}
		// Close scanner.
		input.close();
	}
	
	/**
	 * Accepts input of test 1 and test 2 scores. Averages
	 * both arrays, and returns the averages as doubles. 
	 * @param testOne[] A reference to an instance of the first
	 * 					array of user-input test scores.  
	 * @param testTwo[] A reference to an instance of the second
	 * 					array of user-input test scores.
	 */
	public static double calculateAverage(int testOne, int testTwo) {
		// Declaration of average variable 
		double average;
		
		// Calculate average of two numbers
		average = (double) (testOne + testTwo)/2;
		
		// Return average
		return average;
	}
	/**
	 * Accepts input of average. Returns an array containing the letter
	 * grade for each student. 
	 * @param average A reference to an instance of the array storing 
	 * 				   test average scores. 		   
	 */
	public static char calculateLetterGrade(double average) {
		// Declaration of variables
		char grade;
		
		// Calculate letterGrade
		if (average >= 90) {
			grade = 'A';
		} else if (average < 90  && average >= 80) {
			grade = 'B';
		} else if (average < 80 && average >= 70) {
			grade = 'C';
		} else {
			grade = 'F';
		}
			
		// Return letterGrade
		return grade; 
	}
}
