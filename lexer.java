// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class lexer {
    
    public static String[] op={"+","-","/","*"};
    public static String[] punctuation={";",",",":","{","}","?"};
    public static String[] logOp = {">","<","="};
    public static String[] assOp= {"+=","-="};

    public static String[] opArray = new String[]{"+","-","/","*",">","<"};
    public static String[] puncArray = new String[]{" ",";",",",":","?"};

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
            if (!opList.contains(ch) & !puncList.contains(ch)) {
                lexeme += ch; 
                System.out.println("Lexeme: " + lexeme);
            }
            
            // if (lexeme != ""){
            //     Tokenizer(lexeme);
            // }
            // else if (opList.contains(ch) | puncList.contains(ch)) {
            //     lexeme = "";
            // }
        }
        System.out.println("Full Lexeme: " + lexeme);
      }
    // public static Object[] Tokenizer (String input){
    //     GFG RE = new GFG();
    //     Token[] arr;
    //     arr = new Token[1];
    //     if (RE.isCharacter(input)){
    //         // return "CHAR";
    //         arr[0] = new Token(1, "CHAR", input);

    //     }
    //     else if (RE.isString(input)){
    //         // return "STRING";
    //         arr[0] = new Token(1, "STRING", input);

    //     }
    //     else if (RE.isIdentifier(input)){
    //         // return "ID";
    //         arr[0] = new Token(1, "ID", input);

    //     }
    //     else if (RE.isInteger(input)){
    //         // return "INT";
    //         arr[0] = new Token(1, "INT", input);

    //     }
    //     else if (RE.isKeyword(input)){
    //         // return "KEYWORD";
    //         arr[0] = new Token(1, "KEYWORD", input);

    //     }
    //     return arr;
        
    // }
    // static class Token {
  
    //     public String type;
    //     public String value;
    //     public int line;
      
    //     // Token class constructor
    //     Token(int line, String type, String value)
    //     {
    //         this.line = line;
    //         this.type = type;
    //         this.value = value;
    //     }
      
    //     // display() method to display
    //     // the Token data
    //     public void display()
    //     {
    //         System.out.println("Token (type:" + type 
    //                            + ", value: " + value
    //                            + ", line:" + line + ");");
    //         System.out.println();
    //     }
    // }
    public static void main(String args[])
	{
        lexeme("int 1 + 2; string 3 + 5;");
        // System.out.println(lexeme.opList.contains("+"));
        // System.out.println(lexeme.puncList.contains(" "));
        // System.out.println(lexeme.puncList);
        // System.out.println(lexeme.opList);
    
    }
}
