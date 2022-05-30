/**
 * This program converts GBP, British Pounds, in to 4 different currencies, US Dollars, Euros, Yen and Australian Dollars.
 * @author Vicky Lumb
 * @version 1.0 28/04/2020
 */
// Javadoc comment including tags

import java.util.*; //accesses all pre-compiled classes in the Eclipse IDE package

public class CurrencyConverter { //class declared

	/**
	 * The method firstly displays the title Currency Converter and asks the user to input an amount in pounds.
	 * @param args
	 * @author Vicky Lumb
	 * @version 1.0 28/04/2020
	 */
	// Javadoc comment including tags
	
	public static void main(String[] args) { //main method declared
		
		double us_dollar, euro, yen, australian_dollar, conversion;
		double pound = 0; //pound variable set to 0 initially
		char restart; //variables declared
		
	do { //place code in loop
		Scanner keyboard = new Scanner(System.in); //create keyboard, new Scanner object 
		System.out.println("******Currency Converter******"); //display program name
		
	try {
		System.out.println("Welcome. Please enter an amount in pounds:"); //ask user to input pounds
		pound = keyboard.nextDouble(); //call input method
		
		System.out.println("Which currency would you like to convert to?"); //separate lines to make easy to read
		System.out.println("Please type: 1 for us_dollars, 2 for euros, 3 for yen, or 4 for australian_dollars:"); //ask user to input an option
		conversion = keyboard.nextDouble(); //call input method
		
		if (conversion == 1) { //condition 1
		us_dollar = pound * 1.24; //calculate conversion 1
		System.out.println("us_dollar = " + us_dollar); //display result
		}
		
		else if (conversion == 2) { //condition 2
		euro = pound * 1.15; //calculate conversion 2
		System.out.println("euros = " + euro); //display result
		}
		
		else if (conversion == 3) { //condition 3
		yen = pound * 133.22; //calculate conversion 3
		System.out.println("yen = " + yen); //display result
		}
		
		else if (conversion == 4) { //condition 4
		australian_dollar = pound * 1.93; //calculate conversion 4
		System.out.println("australian dollars = " + australian_dollar); //display result
		}
		
		else {
		System.out.println("Invalid Input Choice"); //display if no conditions met
		}
	}
		
	catch(Exception e) { //catch any exception
		System.out.println("Invalid Input. You must enter an amount in pounds as a real number"); //exception error message, not a real number
	}
	
	finally {
		keyboard.nextLine(); //reset keyboard after invalid input
		System.out.println("Would you like to restart the program? Please type Y or N: "); //ask user to input yes or no to restart
		restart = keyboard.next() .charAt(0); //call input method
	}
	} while (restart == 'Y' || restart == 'y'); //test. restart or end program
	}
}

