package LexicalAnalyser;

public class TokenClass {
    // Token class variables
    public String type;
    public String value;
    public int line;

    // Token class constructor
    TokenClass(int line, String type, String value) {
        this.line = line;
        this.type = type;
        this.value = value;
    }

    // display() method to display
    // the Token data
    public String display() {
        return ("Token (type:" + type
                + ", value: " + value
                + ", line:" + line + ");");
        // System.out.println();
    }
}
