package LexicalAnalyser;
// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.Arrays;


public class lexer {
    
    public static String[] op={"+","-","/","*"};
    public static String[] punctuation={";",",",":","{","}","?"};
    public static String[] logOp = {">","<","="};
    public static String[] assOp= {"+=","-="};

    public static String[] opArray = new String[]{"+","-","/","*",">","<"};
    public static String[] puncArray = new String[]{";",",",":","?"};

    public static List<String> opList = new ArrayList<>(Arrays.asList(opArray));
    public static List<String> puncList = new ArrayList<>(Arrays.asList(puncArray));

    public static List<String> lexeme(String input) {
        //Creating a List of type String using ArrayList  
        List<String> list=new ArrayList<String>(); 
        String lexeme = "";
        for (int i = 0; i < input.length(); i++){
            // access each character
            String ch = String.valueOf(input.charAt(i));
            // Condtion  to check if the character is not space or operator or punctuation
            if (!ch.contains(" ") & !opList.contains(ch) & !puncList.contains(ch)) {
                lexeme += ch; 
            } 
            // Condtion  to check if the character is not space and contains operator or punctuation
            // and lexeme is not empty
            else if(!ch.contains(" ") & lexeme != "" & lexeme != " " & (opList.contains(ch) | puncList.contains(ch))){
                // System.out.println("First Else");
                list.add(lexeme);
                list.add(ch);
                lexeme = "";
            }
            // Condtion  to check if the character is not space or operator or punctuation
            else if(opList.contains(ch) | puncList.contains(ch) & !ch.contains(" ")){
                // System.out.println("Second Else");
                list.add(ch);
                lexeme = "";
            } 
            // Condtion  to check if the character is space and lexeme is not empty
            else if (lexeme != "" & lexeme != " " & ch.contains(" ")){
                // System.out.println("Third Else");
                list.add(lexeme);
                lexeme = "";
            }
        }
        return list;
      }
      
      // All the functions to check the lexemes
    //  Identifier Constant
    public static boolean isIdentifier(String identifier) {

        // Regex to check valid identifier.
        String regex = "^([a-zA-Z_][a-zA-Z\\d_]+)$";

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

    //  Integer Constant
    public static boolean isInteger(String identifier) {

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

    //  Character Constant
    public static boolean isCharacter(String identifier) {

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
        return m.matches();
    }

    //  String Constant
    public static boolean isString(String identifier) {

        // Regex to check valid identifier.
        String regex = "^\"[\\w\\W]*\"$";

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

    //  Float Constant
    public static boolean isFloat(String identifier) {

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

    //  Keyword Constant
    public static boolean isKeyword(String identifier) {

        // Regex to check valid identifier.
        String[] regex = { "if", "else", "int", "string", "this", "boolean", "stop", "grab", "throw", "character", "class",
                "resume", "case",
                "default", "extends", "do", "float", "for", "insert", "new", "pri", "pro", "uni", "return", "static",
                "super", "test" };

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

    
//  Operator Constant
    public static boolean isOperator(String identifier) {

        // Regex to check valid identifier.
        String[] regex = {"+", "-", "/", "*", ";", "=" };

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
    
    //  Operator Constant
    public static boolean isAssignment(String identifier) {

        // Regex to check valid identifier.
        String[] regex = { "+", "-", "/", "*", ";" };

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

    //  Operator Constant
    public static boolean isPunctuator(String identifier) {

        // Regex to check valid identifier.
        String[] regex = { ";" };

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
    // Tokenizer Function to identify each lexeme
    public static void Tokenizers(String lexeme) {
        // Initialize Token Class as arr
        Token arr;
        // Check if lexeme is keyword
        if (isKeyword(lexeme)) {
            arr = new Token(1, "KEYWORD", lexeme);
            arr.display();
        }
        // Check if lexeme is identifier
         else if (isIdentifier(lexeme)) {
            arr = new Token(1, "ID", lexeme);
            arr.display();
        }
        // Check if lexeme is integer
         else if (isInteger(lexeme)) {
            arr = new Token(1, "INT", lexeme);
            arr.display();
        }
        // Check if lexeme is character
         else if (isCharacter(lexeme)) {
            arr = new Token(1, "CHAR", lexeme);
            arr.display();
        }
        // Check if lexeme is string
         else if (isString(lexeme)) {
            arr = new Token(1, "STRING", lexeme);
            arr.display();
        }
        // Check if lexeme is operator
         else if (isOperator(lexeme)) {
            arr = new Token(1, "OPERATOR", lexeme);
            arr.display();
        }
        // Check if lexeme is undefined
         else {
            arr = new Token(1, "UNDEFINED", lexeme);
            arr.display();
        }
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
                    + ", value:" + value
                    + ", line:" + line + ");");
            System.out.println();
        }
    }
    public static void main(String args[])
	{
        System.out.println(lexeme("int 1 + 2 = 5; string 3+5 = 5.3;"));
    }
}
