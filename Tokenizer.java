import java.util.regex.*;

public class Tokenizer {
    public static boolean isIdentifier(String identifier) {

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

    public static boolean isKeyword(String identifier) {

        // Regex to check valid identifier.
        String[] regex = { "if", "else", "int", "str", "this", "boolean", "stop", "grab", "throw", "character", "class",
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

    public static boolean isOperator(String identifier) {

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

    public static void Tokenizers(String input) {
        Token arr;
        // arr = new Token();

        if (isKeyword(input)) {
            // return "KEYWORD";
            arr = new Token(1, "KEYWORD", input);

            arr.display();
        } else if (isIdentifier(input)) {
            // return "ID";
            arr = new Token(1, "ID", input);

            arr.display();
        } else if (isInteger(input)) {
            // return "INT";
            arr = new Token(1, "INT", input);

            arr.display();
        } else if (isCharacter(input)) {
            // return "CHAR";
            arr = new Token(1, "CHAR", input);

            arr.display();
        } else if (isString(input)) {
            // return "STRING";
            arr = new Token(1, "STRING", input);

            arr.display();
        } else if (isOperator(input)) {
            // return "STRING";
            arr = new Token(1, "OPERATOR", input);

            arr.display();
        } else {
            arr = new Token(1, "UNDEFINED", input);

            arr.display();

        }

    }

    static class Token {

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
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Tokenizers("int");
        Tokenizers("a");
        System.out.println(isIdentifier("a"));
        Tokenizers("+");
        Tokenizers("b");
        Tokenizers(";");
        Tokenizers("'b'");
        Tokenizers("\"hello\"");

    }
}