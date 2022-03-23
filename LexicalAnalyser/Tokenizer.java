package LexicalAnalyser;
import java.util.*;

import java.util.regex.*;
import LexicalAnalyser.*;

public class Tokenizer {
     
 
    // Tokenizer Function to identify each lexeme
    public static Token Tokenizers(String lexeme) {
        
    // re RE = new re ( );
        int line = 1;
        if (lexeme == ";"){
            line += 1;
        }
        // Initialize Token Class as arr
        Token arr;
        // Check if lexeme is keyword
        if (isKeyword(lexeme)) {
            arr = new Token(line, "KEYWORD", lexeme);
            arr.display();
        }
        // Check if lexeme is identifier
         else if (isIdentifier(lexeme)) {
            arr = new Token(line, "ID", lexeme);
            arr.display();
        }
        // Check if lexeme is integer
         else if (isInteger(lexeme)) {
            arr = new Token(line, "INT", lexeme);
            arr.display();
        }
        // Check if lexeme is character
         else if (isCharacter(lexeme)) {
            arr = new Token(line, "CHAR", lexeme);
            arr.display();
        }
        // Check if lexeme is assignment
         else if (lexeme == "=") {
            arr = new Token(line, "ASSIGNMENT", lexeme);
            arr.display();
        }
        // Check if lexeme is assignment
        else if (lexeme == ";") {
            arr = new Token(line, "PUNCTUATION", lexeme);
            arr.display();
        }
        // Check if lexeme is string
         else if (isString(lexeme)) {
            arr = new Token(line, "STRING", lexeme);
            arr.display();
        }
        // Check if lexeme is undefined
         else {
            arr = new Token(line, "UNDEFINED", lexeme);
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
	public static boolean
	isIdentifier(String identifier)
	{

		// Regex to check valid identifier.
		// String regex = "^([a-zA-Z_]?[a-zA-Z\\d_]+)$";
		String regex = "^([a-zA-Z]+)$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the identifier is empty
		// return false
		if (identifier == null) {
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
    public static boolean
	isInteger(String identifier)
	{

		// Regex to check valid identifier.
		String regex = "[+-]?[0-9]+";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the identifier is empty
		// return false
		if (identifier == null) {
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

	// Function to validate the character.
    public static boolean
	isCharacter(String identifier)
	{
		// boolean start = identifier.startsWith("'") && identifier.endsWith("'");

		// Regex to check valid identifier.
		String regex = "^'[\\w\\W]'$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the identifier is empty
		// return false
		if (identifier == null) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given identifier
		// and regular expression.
		Matcher m = p.matcher(identifier);

		// Return if the identifier
		// matched the ReGex
		
		// System.out.println(identifier);
		return m.matches();
	}

	// Function to validate the string.
    public static boolean
	isString(String identifier)
	{

		// Regex to check valid identifier.
		String regex = "[\\w\\W]*";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the identifier is empty
		// return false
		if (identifier == null) {
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

	// Function to validate the boolean.
    public static boolean
	isFloat(String identifier)
	{

		// Regex to check valid identifier.
		String regex = "^([+-]?\\d+\\.?\\d+)$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the identifier is empty
		// return false
		if (identifier == null) {
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

	// Function to validate the keyword.
	public static boolean
	isKeyword(String identifier)
	{

		// Regex to check valid identifier.
		String[] regex = {"if", "else", "int", "string", "this", "boolean", "stop", "grab", "throw", "character", "class", "resume", "case",
		"default","extends","do","float","for","insert","new","pri","pro","uni","return","static","super","test"};

		// Compile the ReGex
//		Pattern p = Pattern.compile(regex);

		boolean exists = false;
		for (String element : regex) {
			if (element == identifier) {
				exists = true;
				break;
			}
		}
		if(exists == true){
			return true;
		} else{
			return false;
		}
//
//
//		// If the identifier is empty
//		// return false
//		if (identifier == null) {
//			return false;
//		}
//
//		// Pattern class contains matcher() method
//		// to find matching between given identifier
//		// and regular expression.
//		Matcher m = p.matcher(identifier);
//
//		// Return if the identifier
//		// matched the ReGex
//		return m.matches();
	}
    
	// Function to validate the operator.
    public static boolean 
	isOperator(String identifier) {

        // Regex to check valid identifier.
        String[] regex = { "+", "-", "/", "*", ";","=" };

        // Compile the ReGex
        // Pattern p = Pattern.compile(regex);

        boolean exists = false;
        for (String element : regex) {
            if (element == identifier) {
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
        String[] lexemesList = {"int", "a", "=", "5", ";", "string", "b", "=", "'Hello'", ";", "boolean", "c", "=", "true", ";", "char", "d", "=", "'c'", ";"};
        List<String> opList = new ArrayList<>(Arrays.asList(lexemesList));
    
        for (String string : opList) {
            Tokenizers(string);
        }
		System.out.println("");
        for (String string : lexemesList) {
            Tokenizers(string);
        }
    }
}