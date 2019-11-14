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
//    	If there are spaces left to do (meaning that the equation is fully
//    	parsed and solved, it will exit the while loop
    	while (equation.indexOf(' ') != -1) {
    		String operand1 = equation.substring(0, equation.indexOf(' '));
        	
        	equation = equation.substring(equation.indexOf(' ') + 1);
        	
        	char operator = equation.charAt(0);
        	
        	equation = equation.substring(2);
        	String operand2;
        	
        	if (equation.indexOf(' ') != -1) {
        		operand2 = equation.substring(0, equation.indexOf(' '));
        		equation = equation.substring(equation.indexOf(' ')); 
        	} else {
        		operand2 = equation;
        		equation = "";
        	}
        	
        	equation = operate(operand1, operator, operand2) + equation;
    	}
    	
    	return equation;
    	//return operand2;
        
    	//return "whole:" + stringToWhole(operand2)
        //     + " numer:" + stringToNumer(operand2)
        //     + " denom:" + stringToDenom(operand2);
    	
    }
    
    public static int stringToWhole(String input) {
    	String whole;
    	if (input.indexOf('_') != -1) {
    		whole = input.substring(0, input.indexOf('_'));
    	} else if (input.indexOf('_') == -1 && input.indexOf('/') == -1) {
    		whole = input;
    	} else {
    		whole = "0";
    	}
    	
    	return Integer.parseInt(whole);
    	
    }
    
    public static int stringToNumer(String input) {
    	String numer;
    	if (input.indexOf('/') >= 0) {
    		numer = input.substring(input.indexOf('_') + 1, input.indexOf('/'));
    	} else {
    		numer = "0";
    	}
    	
    	if (stringToWhole(input) >= 0) {
    		return Integer.parseInt(numer);
    	} else {
    		return -1 * Integer.parseInt(numer);
    	}
    	
    }
    
    public static int stringToDenom(String input) {
    	String denom;    	
    	if (input.indexOf('/') >= 0) {
    		denom = input.substring(input.indexOf('/') + 1);
    	} else {
    		denom = "1";
    	}
    	
    	return Integer.parseInt(denom);
    }
    
    public static String add(String operand1, String operand2) {
		int whole1 = stringToWhole(operand1);
    	int numer1 = stringToNumer(operand1);
    	int denom1 = stringToDenom(operand1);
    	int whole2 = stringToWhole(operand2);
    	int numer2 = stringToNumer(operand2);
    	int denom2 = stringToDenom(operand2);
    	
    	numer1 = improperNumerResult(whole1, numer1, denom1);
    	numer2 = improperNumerResult(whole2, numer2, denom2);
    	
    	int numerResult = (numer1 * denom2) + (numer2 * denom1);
    	int denomResult = denom1 * denom2;
    	
    	int gcm = gcm(numerResult, denomResult);
    	
    	numerResult /= gcm;
    	denomResult /= gcm;
    	
    	int wholeResult = improperToMixedWholeResult(numerResult, denomResult);
    	int excessNumer = wholeResult * denomResult;
    	numerResult = numerResult - excessNumer;
    	
    	return intsToOperand(wholeResult, numerResult, denomResult);
	}
    
    public static String subtract(String operand1, String operand2) {
		int whole1 = stringToWhole(operand1);
    	int numer1 = stringToNumer(operand1);
    	int denom1 = stringToDenom(operand1);
    	int whole2 = stringToWhole(operand2);
    	int numer2 = stringToNumer(operand2);
    	int denom2 = stringToDenom(operand2);
    	
    	numer1 = improperNumerResult(whole1, numer1, denom1);
    	numer2 = improperNumerResult(whole2, numer2, denom2);
    	
    	int numerResult = (numer1 * denom2) - (numer2 * denom1);
    	int denomResult = denom1 * denom2;
    	
    	int gcm = gcm(numerResult, denomResult);
    	
    	numerResult /= gcm;
    	denomResult /= gcm;
    	
    	int wholeResult = improperToMixedWholeResult(numerResult, denomResult);
    	int excessNumer = wholeResult * denomResult;
    	numerResult = numerResult - excessNumer;
    	
    	return intsToOperand(wholeResult, numerResult, denomResult);
	}
    
    public static String multiply(String operand1, String operand2) {
		int whole1 = stringToWhole(operand1);
    	int numer1 = stringToNumer(operand1);
    	int denom1 = stringToDenom(operand1);
    	int whole2 = stringToWhole(operand2);
    	int numer2 = stringToNumer(operand2);
    	int denom2 = stringToDenom(operand2);
	
    	numer1 = improperNumerResult(whole1, numer1, denom1);
    	numer2 = improperNumerResult(whole2, numer2, denom2);
    	
    	int numerResult = numer1 * numer2;
    	int denomResult = denom1 * denom2;
    	
    	int gcm = gcm(numerResult, denomResult);
    	numerResult /= gcm;
    	denomResult /= gcm;
    	
    	int wholeResult = improperToMixedWholeResult(numerResult, denomResult);
    	int excessNumer = wholeResult * denomResult;
    	numerResult = numerResult - excessNumer;
    	
    	return intsToOperand(wholeResult, numerResult, denomResult);
	}
    
    public static String divide(String operand1, String operand2) {
		int whole1 = stringToWhole(operand1);
    	int numer1 = stringToNumer(operand1);
    	int denom1 = stringToDenom(operand1);
    	int whole2 = stringToWhole(operand2);
    	int numer2 = stringToNumer(operand2);
    	int denom2 = stringToDenom(operand2);
	
    	numer1 = improperNumerResult(whole1, numer1, denom1);
    	numer2 = improperNumerResult(whole2, numer2, denom2);
    	
    	int numerResult = numer1 * denom2;
    	int denomResult = numer2 * denom1;
    	
    	int gcm = gcm(numerResult, denomResult);
    	numerResult /= gcm;
    	denomResult /= gcm;
    	
    	int wholeResult = improperToMixedWholeResult(numerResult, denomResult);
    	int excessNumer = wholeResult * denomResult;
    	numerResult = numerResult - excessNumer;
    	
    	return intsToOperand(wholeResult, numerResult, denomResult);
	}
    
    public static int improperNumerResult(int whole, int numer, int denom) {
    	int excess = whole * denom;
    	numer += excess;
    	return numer;
    }
    
    public static int improperToMixedWholeResult(int numer, int denom) {
    	int whole = numer / denom;
    	return whole;
    }
    
    public static int gcm(int numer, int denom) {
    	numer = Math.abs(numer);
    	denom = Math.abs(denom);    	
    	int dividend = 2;
    	int gcm = 1;
    	while (dividend <= numer) {
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
    
    public static String intsToOperand(int whole, int numer, int denom) {
    	if (numer == 0) {
    		return whole + "";
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
    
    public static String operate(String operand1, char operator, String operand2) {
    	if (operator == '+') {
    		return (add(operand1, operand2));
    	} else if (operator == '-') {
    		return (subtract(operand1, operand2));
    	} else if (operator == '*') {
    		return (multiply(operand1, operand2));
    	} else if (operator == '/') {
    		return (divide(operand1, operand2));
    	} else {
    		return "ERROR: Invalid operator.";
    	}
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
