package LexicalAnalyser;

import java.util.regex.*;

public class Tokenizer {

	// Tokenizer Function to identify each lexeme
	public static Token Tokenizers(String lexeme, int lineNumber) {

		// Initialize Token Class as arr
		Token arr;
		// Check if lexeme is keyword
		if (isKeyword(lexeme)) {
			arr = new Token(lineNumber, "KEYWORD", lexeme);
			arr.display();
		} else if (isBoolean(lexeme)) {
			arr = new Token(lineNumber, "BOOLEAN", lexeme);
			arr.display();
		}
		// Check if lexeme is identifier
		else if (isIdentifier(lexeme)) {
			arr = new Token(lineNumber, "ID", lexeme);
			arr.display();
		}
		// Check if lexeme is integer
		else if (isInteger(lexeme)) {
			arr = new Token(lineNumber, "INT", lexeme);
			arr.display();
		}
		// Check if lexeme is character
		else if (isCharacter(lexeme)) {
			arr = new Token(lineNumber, "CHAR", lexeme);
			arr.display();
		}
		// Check if lexeme is operator
		else if (isOperator(lexeme)) {
			arr = new Token(lineNumber, "OPERATOR", lexeme);
			arr.display();
		}
		// Check if lexeme is assignment
		else if (isAssignment(lexeme)) {
			arr = new Token(lineNumber, "ASSIGNMENT", lexeme);
			arr.display();
		}
		// Check if lexeme is punctuation
		else if (isPunctuation(lexeme)) {
			arr = new Token(lineNumber, "PUNCTUATION", lexeme);
			arr.display();
		}
		// Check if lexeme is string
		else if (isString(lexeme)) {
			arr = new Token(lineNumber, "STRING", lexeme);
			arr.display();
		}
		// Check if lexeme is undefined
		else {
			arr = new Token(lineNumber, "UNDEFINED", lexeme);
			arr.display();
		}
		return arr;
	}

	// Token Class
	static class Token {
		// Token class variables
		public String type;
		public String value;
		public int line;

		// Token class constructor
		Token(int line, String type, String value) {
			this.line = line;
			this.type = type;
			this.value = value;
		}

		// display() method to display
		// the Token data
		public void display() {
			System.out.println("Token (type:" + type
					+ ", value: " + value
					+ ", line:" + line + ");");
			// System.out.println();
		}
	}

	// Function to validate the identifier.
	public static boolean isIdentifier(String identifier) {

		// Regex to check valid identifier.
		// String regex = "^([a-zA-Z_]?[a-zA-Z\\d_]+)$";
		String regex = "^([a-zA-Z]+)$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the identifier is empty
		// return false
		if (identifier.equals(null)) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given identifier
		// and regular expression.
		Matcher m = p.matcher(identifier);

		// Return if the identifier
		// matched the ReGex
		return m.matches();
	}

	// Function to validate the integer.
	public static boolean isInteger(String integer) {

		// Regex to check valid integer.
		String regex = "[+-]?[0-9]+";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the integer is empty
		// return false
		if (integer.equals(null)) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given integer
		// and regular expression.
		Matcher m = p.matcher(integer);

		// Return if the integer
		// matched the ReGex
		return m.matches();
	}

	// Function to validate the character.
	public static boolean isCharacter(String character) {
		// Regex to check valid character.
		String regex = "^'[\\w\\W]'$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the character is empty
		// return false
		if (character.equals(null)) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given character
		// and regular expression.
		Matcher m = p.matcher(character);

		// Return if the character
		// matched the ReGex

		// System.out.println(character);
		return m.matches();
	}

	// Function to validate the string.
	public static boolean isString(String string) {

		// Regex to check valid string.
		String regex = "[\\w\\W]*";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the string is empty
		// return false
		if (string.equals(null)) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given string
		// and regular expression.
		Matcher m = p.matcher(string);

		// Return if the string
		// matched the ReGex
		return m.matches();
	}

	// Function to validate the boolean.
	public static boolean isFloat(String floatNo) {

		// Regex to check valid floatNo.
		String regex = "^([+-]?\\d+\\.?\\d+)$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the string is empty
		// return false
		if (floatNo.equals(null)) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given floatNo
		// and regular expression.
		Matcher m = p.matcher(floatNo);

		// Return if the float
		// matched the ReGex
		return m.matches();
	}

	// Function to validate the keyword.
	public static boolean isKeyword(String keyword) {

		// List to check valid keyword.
		String[] regex = { "if", "else", "char", "int", "string",
				"this", "boolean", "stop", "grab", "throw", "character",
				"class", "resume", "case", "default", "extends", "do", "float",
				"for", "insert", "new", "pri", "pro", "uni", "return", "static",
				"super", "test", "while", "public", "private", "void", "define", "begin", "end", "print" };

		// Condition to check if keyword exists in the list
		boolean exists = false;
		for (String element : regex) {
			if (element.equals(keyword)) {
				exists = true;
				break;
			}
		}
		return exists;
	}

	// Function to validate the operator.
	public static boolean isOperator(String operator) {

		// List to check valid operator.
		String[] regex = { "+", "-", "/", "*", "^", "++", "--",
				">", "<", "<=", ">=", "==", "!=" };

		// Condition to check if operator exists in the list
		boolean exists = false;
		for (String element : regex) {
			if (element.equals(operator)) {
				exists = true;
				break;
			}
		}

		return exists;
	}

	public static boolean isPunctuation(String operator) {

		// List to check valid operator.
		String[] punc = { ":", ";", "(", ")", "{", "}","," };

		// Condition to check if operator exists in the list
		boolean exists = false;
		for (String element : punc) {
			if (element.equals(operator)) {
				exists = true;
				break;
			}
		}

		return exists;
	}

	// Function to validate the assignment.
	public static boolean isAssignment(String assignment) {

		// List to check valid assignment.
		String[] regex = { "=", "+=", "-=", "/=", "*=", "^=" };

		// Condition to check if assignment exists in the list
		boolean exists = false;
		for (String element : regex) {
			if (element.equals(assignment)) {
				exists = true;
				break;
			}
		}
		return exists;
	}

	// Function to validate the assignment.
	public static boolean isBoolean(String assignment) {

		// List to check valid assignment.
		String[] values = { "True", "False" };

		// Condition to check if assignment exists in the list
		boolean exists = false;
		for (String element : values) {
			if (element.equals(assignment)) {
				exists = true;
				break;
			}
		}
		return exists;
	}

	public static void main(String args[]) {
		// Tokenizers("int");
		// Tokenizers("a");
		// Tokenizers("+");
		// Tokenizers("b");
		// Tokenizers(";");
		// Tokenizers("'b'");
		// System.out.println(Tokenizers("\"hello\""));
		// String[] lexemesList = {"int", "a", "=", "5", ";", "string", "b", "=",
		// "'Hello'", ";", "boolean", "c", "=", "true", ";", "char", "d", "=", "'c'",
		// ";"};
		// List<String> opList = new ArrayList<>(Arrays.asList(lexemesList));

		// for (String string : opList) {
		// Tokenizers(string);
		// }
		// System.out.println("");
		// for (String string : lexemesList) {
		// Tokenizers(string);
		// }
	}
}