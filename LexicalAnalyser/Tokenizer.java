package LexicalAnalyser;
// import LexicalAnalyser.GFG;

public class Tokenizer {

    // Tokenizer Function to identify each lexeme
    public static Token Tokenizers(String lexeme) {
        // Initialize Token Class as arr
        Token arr;
        // Check if lexeme is keyword
        if (re.isKeyword(lexeme)) {
            arr = new Token(1, "KEYWORD", lexeme);
            arr.display();
        }
        // Check if lexeme is identifier
         else if (re.isIdentifier(lexeme)) {
            arr = new Token(1, "ID", lexeme);
            arr.display();
        }
        // Check if lexeme is integer
         else if (re.isInteger(lexeme)) {
            arr = new Token(1, "INT", lexeme);
            arr.display();
        }
        // Check if lexeme is character
         else if (re.isCharacter(lexeme)) {
            arr = new Token(1, "CHAR", lexeme);
            arr.display();
        }
        // Check if lexeme is string
         else if (re.isString(lexeme)) {
            arr = new Token(1, "STRING", lexeme);
            arr.display();
        }
        // Check if lexeme is operator
         else if (re.isOperator(lexeme)) {
            arr = new Token(1, "OPERATOR", lexeme);
            arr.display();
        }
        // Check if lexeme is undefined
         else {
            arr = new Token(1, "UNDEFINED", lexeme);
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

    public static void main(String args[]) {
        // Tokenizers("int");
        // Tokenizers("a");
        // Tokenizers("+");
        // Tokenizers("b");
        // Tokenizers(";");
        // Tokenizers("'b'");
        // System.out.println(Tokenizers("\"hello\""));

    }
}