package fracCalc;

import java.util.*;

public class FracCalc {

    public static void main(String[] args) {
    	Scanner userInput = new Scanner(System.in);
    	
    	System.out.println("Welcome to Fraction Calculator!\n"
    			+ "Enter your equations below.\n");
    	boolean toContinue = true;
        while (toContinue) {
        	String equation = userInput.nextLine();
        	toContinue = toContinue(equation);
        	if (toContinue) {
        		String output = produceAnswer(equation);
        		System.out.println(output);
        	}
        }
        System.out.println("Exiting fraction calculator.");        
        userInput.close();
    }

    public static String produceAnswer(String equation) {
//    	if there are spaces left to do (meaning that the equation is fully
//    	parsed and solved, it will exit the while loop
    	while (equation.indexOf(' ') != -1) {
//    		finds first operand by taking a substring of all chars before
//    		first space
    		String operand1 = equation.substring(0, equation.indexOf(' '));
        	
//    		deletes the first operand from equation
        	equation = equation.substring(equation.indexOf(' ') + 1);
        	
//        	finds operator, which will be single character at the beginning
//        	of string
        	char operator = equation.charAt(0);
        	
//        	deletes the operator from the equation
        	equation = equation.substring(2);
        	String operand2;
        	
//        	if there are still more operators left to parse out, it will
//        	find the next operand by taking a substring of all chars
//        	before the next space. if there are no more operators left to
//        	parse, the second operand will be the rest of the equation
//        	the second operand is then deleted from the equation
        	if (equation.indexOf(' ') != -1) {
        		operand2 = equation.substring(0, equation.indexOf(' '));
        		equation = equation.substring(equation.indexOf(' ')); 
        	} else {
        		operand2 = equation;
        		equation = "";
        	}
        	
        	return operand2;
        	
//        	the result calculated by operand 1 and 2, and put back at the
//        	beginning of equation; if another operation still exists,
//        	while loop will repeat 
//        	equation = operate(operand1, operator, operand2) + equation;
    	}
    	
    	return equation;
        
//    	return "whole:" + stringToWhole(operand2)
//             + " numer:" + stringToNumer(operand2)
//             + " denom:" + stringToDenom(operand2);
    	
    }

    public static boolean toContinue(String equation) {
    	equation = equation.toLowerCase();
    	if (equation.charAt(0) == 'q' && equation.charAt(1) == 'u' &&
    		equation.charAt(2) == 'i' && equation.charAt(3) == 't') {
    		return false;
    	} else {
    		return true;
    	}
    }
}
