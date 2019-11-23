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
        	toContinue = equation.equals("quit");
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
        	
//        	finds operator, which will now be at the beginning of string
        	String operator = equation.substring(0, equation.indexOf(' '));
        	
//        	deletes the operator from the equation
        	equation = equation.substring(equation.indexOf(' ') + 1);
        	String operand2;
        	
//        	if there are still more operators left to parse out, it will
//        	find the next operand by taking a substring of all chars
//        	before the next space. if there are no more operators left to
//        	parse, the second operand will be the rest of equation, and
//        	is then deleted from the equation
        	if (equation.indexOf(' ') != -1) {
        		operand2 = equation.substring(0, equation.indexOf(' '));
        		equation = equation.substring(equation.indexOf(' ')); 
        	} else {
        		operand2 = equation;
        		equation = "";
        	}
        	
//        	the result calculated by operand 1 and 2, and put back at the
//        	beginning of equation; if another operation still exists,
//        	while loop will repeat 
        	equation = operate(operand1, operator, operand2) + equation;
    	}
    	
    	return equation;
        
//    	return "whole:" + stringToWhole(operand2)
//             + " numer:" + stringToNumer(operand2)
//             + " denom:" + stringToDenom(operand2);
    	
    }
    
//  method for parsing whole value from string
    public static int stringToWhole(String input) {
    	String whole;
    	
//    	if there is an underscore, the whole number will be a substring between
//    	index 0 and the index of the underscore
    	if (input.indexOf('_') > 0) {
    		whole = input.substring(0, input.indexOf('_'));
    	
//    		if there is no underscore and also no slash, the whole value is
//    		the input
    	} else if (input.indexOf('/') == -1) {
    		whole = input;

//    		if there is no underscore but there is a slash, the whole value
//    		is zero
    	} else {
    		whole = "0";
    	}
    	
    	return Integer.parseInt(whole);
    }
    
//  method for parsing numerator from string
    public static int stringToNumer(String input) {
    	String numer;
    	
//    	if there is a slash, the numerator is the substring between the
//    	underscore and the slash
    	if (input.indexOf('/') >= 0) {
    		numer = input.substring(input.indexOf('_') + 1, input.indexOf('/'));
    	
//    		if there is no slash, the numerator is 0
    	} else {
    		numer = "0";
    	}
    	
//    	if the whole value is negative, the numerator must also be negative
//    	too because the negative will distribute to the fraction part of the
//    	mixed number
    	if (stringToWhole(input) >= 0) {
    		return Integer.parseInt(numer);
    	} else {
    		return -1 * Integer.parseInt(numer);
    	}
    }
    
    public static int stringToDenom(String input) {
    	String denom;
    	
//    	if there is a slash, the denominator is the substring from the slash
//    	to the end
    	if (input.indexOf('/') >= 0) {
    		denom = input.substring(input.indexOf('/') + 1);
    	
//    		if there is no slash, the denominator is 1
    	} else {
    		denom = "1";
    	}
    	
    	return Integer.parseInt(denom);
    }
    
//  method for converting mixed number to improper fraction
    public static int toImproper(int whole, int numer, int denom) {
    	int excess = whole * denom;
    	numer += excess;
    	return numer;
    }
    
//  method for converting improper fractions to mixed numbers
    public static int toMixed(int numer, int denom) {
    	int whole = numer / denom;
    	return whole;
    }
    
//  method for calculating the greatest common multiple between a given
//  numerator and denominator
    public static int gcm(int numer, int denom) {
    	
//    	numerator and denominator receive absolute value because it won't
//    	affect the gcm, but all positive numbers is easier to deal with
    	numer = Math.abs(numer);
    	denom = Math.abs(denom);
    	int dividend = 2;
    	int gcm = 1;
    	
//    	for every dividend, it will check to see if the dividend is divisible
//    	evenly by both the numerator and the denominator. if it divides, the
//    	gcm multiples by the dividend, while the numerator and denominator divide
//    	the dividend. only when the dividend does not pass the test to ensure
//    	that values that need to be factored multiple times can do so
    	while (dividend <= denom) {
    		if (numer % dividend == 0 && denom % dividend == 0) {
    			gcm *= dividend;
    			numer /= dividend;
    			denom /= dividend;	
    		} else {
    			dividend++;
    		}
    	}
    	
    	return gcm;
    }
    
//  method takes a whole number, the numerator, and the denominator
    public static String intsToOperand(int whole, int numer, int denom) {
//    	if the numerator is zero, you only need to return like 1 assignment
    	if (numer == 0) {
    		return whole + "";
    	
//    		if the whole number is zero, you can return the whole number.
//    		if the denominator is negative, the negative is moved up to zach.
    	} else if (whole == 0) {
    		if (denom > 0) {
    			return numer + "/" + denom;
    		} else {
    			return (-1 * numer) + "/" + Math.abs(denom);
    		}
    	} else {
    		return whole + "_" + (Math.abs(numer))+ "/" + (Math.abs(denom));
    	}
    }
    
    public static String operate(String operand1, String operator, String operand2) {
    	int whole1 = stringToWhole(operand1);
    	int numer1 = stringToNumer(operand1);
    	int denom1 = stringToDenom(operand1);
    	int whole2 = stringToWhole(operand2);
    	int numer2 = stringToNumer(operand2);
    	int denom2 = stringToDenom(operand2);
    	
    	numer1 = toImproper(whole1, numer1, denom1);
    	numer2 = toImproper(whole2, numer2, denom2);
    	
    	int numerResult;
    	int denomResult;
    	
    	if (operator.equals("+")) {
    		numerResult = (numer1 * denom2) + (numer2 * denom1);
        	denomResult = denom1 * denom2;
        	
    	} else if (operator.equals("-")) {
    		numerResult = (numer1 * denom2) - (numer2 * denom1);
        	denomResult = denom1 * denom2;
        	
    	} else if (operator.equals("*")) {
    		numerResult = numer1 * numer2;
        	denomResult = denom1 * denom2;
        	
    	} else if (operator.equals("/")) {
    		numerResult = numer1 * denom2;
        	denomResult = numer2 * denom1;
        	
    	} else {
    		return "ERROR: Invalid operator.";
    	}
    	
    	int gcm = gcm(numerResult, denomResult);
    	numerResult /= gcm;
    	denomResult /= gcm;
    	
    	int wholeResult = toMixed(numerResult, denomResult);
    	int excessNumer = wholeResult * denomResult;
    	numerResult = numerResult - excessNumer;
    	
    	return intsToOperand(wholeResult, numerResult, denomResult);
    }
}
