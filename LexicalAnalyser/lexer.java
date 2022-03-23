package LexicalAnalyser;
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
    public static void main(String args[])
	{
        System.out.println(lexeme("int 1 + 2 = 5; string 3+5 = 5.3;"));
        // System.out.println(re.isKeyword("int"));
    }
}
