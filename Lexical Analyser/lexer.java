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

    static void lexeme(String input) {
        String lexeme = "";
        for (int i = 0; i < input.length(); i++){
            // access each character
            String ch = String.valueOf(input.charAt(i));
            // System.out.println(ch);
            // System.out.println(opList.contains(ch));
            // System.out.println(puncList.contains(ch));
            if (!ch.contains(" ") & !opList.contains(ch) & !puncList.contains(ch)) {
                lexeme += ch; 
                System.out.println("Lexeme: " + lexeme);
                System.out.println(" char: " + ch );
            } else if(opList.contains(ch) | !puncList.contains(ch) & !ch.contains(" ")){
                System.out.print("Lex:"+lexeme);
                System.out.println("Char: " + ch);
                Tokenizers(ch);
                lexeme = "";
            } else if (ch.contains(" ") | puncList.contains(ch) & lexeme != ""){
                System.out.println("Space Lexeme: " + lexeme);
                Tokenizers(lexeme);
                lexeme = "";
            }
            
            // if (lexeme != ""){
            //     Tokenizer(lexeme);
            // }
            // else if (opList.contains(ch) | puncList.contains(ch)) {
            //     lexeme = "";
            // }
        }
        // System.out.println("Full Lexeme: " + lexeme);
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
        lexeme("int 1 + 2 = 5; string 3+5;");
    }
}
