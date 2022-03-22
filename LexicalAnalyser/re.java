package LexicalAnalyser;
// Java program to validate the
// identifiers using Regular Expression.

import java.util.regex.*;

class GFG {

	// Function to validate the identifier.
	public static boolean
	isIdentifier(String identifier)
	{

		// Regex to check valid identifier.
		String regex = "^([a-zA-Z_]?[a-zA-Z\\d_]+)$";

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
		
		System.out.println(identifier);
		return m.matches();
	}

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

	public static boolean
	isKeyword(String identifier)
	{

		// Regex to check valid identifier.
		String[] regex = {"if", "else", "integer", "this", "boolean", "stop", "grab", "throw", "character", "class", "resume", "case",
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

		return exists;
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
    

	// Driver Code.
	public static void main(String args[])
	{

		// Test Case 1:
//		String str1 = "geeks123";
//		System.out.println(isIdentifier(str1));

		// Test Case 6:
//		String str6 = "Geeks_123";
//		System.out.println(isIdentifier(str6));

		// Test Case 2:
//		String str2 = "gee ks123";
//		System.out.println(isIdentifier(str2));

		// Test Case 3:
//		String str3 = "1geeks";
//		System.out.println(isIdentifier(str3));
		System.out.println(isCharacter("\\"));
		System.out.println(isCharacter("\n"));
//		System.out.println(isCharacter("'JAVA_JUNIOR'"));
//		System.out.println(isInteger("w"));
//		System.out.println(isInteger("2.08"));
//		System.out.println(isString("w"));
//		System.out.println(isFloat("2.08"));
//		System.out.println(isFloat("5"));

		// Test Case 4:
//		String str4 = "insert";
//		System.out.println(isKeyword(str4));

		// Test Case 5:
//		String str5 = "and_this";
//		System.out.println(isKeyword(str5));
        // Test Case 9, 10, 11, 12 , 13:
//        String str9 = "_";
//        System.out.println(isIdentifier(str9));
//        String str10 = "_S";
//        System.out.println(isIdentifier(str10));
//        String str11 = "s_";
//        System.out.println(isIdentifier(str11));
//        String str12 = " ";
//        System.out.println(isString(str12));
//        String str13 = "INSERT";
//        System.out.println(isKeyword(str13));

	}
}
// op={"+","-","/","*"};
// punctuation={";",",",":","{","}","?"};
// logOp = {">","<","=",">=","<="};
// assOp= {"+=","-="};


// public static void
// lexer(loop);
// array [] = lexum [];
// 		for (int ch = 0, ch+=input{
// 		System.out.println();
// 		}