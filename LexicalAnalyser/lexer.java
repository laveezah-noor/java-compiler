package LexicalAnalyser;

// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class lexer {

    public static String[] op = { "+", "-", "/", "*" };
    public static String[] punctuation = { ";", ",", ":", "{", "}", "?" };
    public static String[] logOp = { ">", "<", "=" };
    public static String[] assOp = { "+=", "-=" };

    public static String[] opArray = new String[] { "+", "-", "/", "*", ">", "<", "=" };
    public static String[] puncArray = new String[] { ";", ",", ":", "?" };

    public static List<String> opList = new ArrayList<>(Arrays.asList(opArray));
    public static List<String> puncList = new ArrayList<>(Arrays.asList(puncArray));

    public static boolean isAlpha(String ch) {
        return ch.matches("[a-zA-Z]+");
    }

    public static boolean isSpaceOrEmptyString(String ch) {
        return !ch.equals(" ") & !ch.equals("");
    }

    public static List<String> lexeme(String input) {

        // Creating a List of type String using ArrayList
        List<String> list = new ArrayList<String>();
        String lexeme = "";
        String prev = "";
        String next = "";

        for (int i = 0; i < input.length(); i++) {

            // access each character
            String ch = String.valueOf(input.charAt(i));
            prev = i != 0 ? String.valueOf(input.charAt(i - 1)) : "";
            next = i != input.length() - 1 ? String.valueOf(input.charAt(i + 1)) : "";
            boolean areAlphas = isAlpha(next) & isAlpha(ch) & isAlpha(prev);

            // Condtion to check if the character is not space or operator or punctuation
            if (!ch.contains(" ") & !opList.contains(ch) & !puncList.contains(ch)) {
                lexeme += ch;
                // System.out.println(lexeme);

            }

            // Condtion to check if the character is not space and contains operator or
            // punctuation
            // and lexeme is not empty
            else if (!ch.contains(" ") & lexeme != "" & lexeme != " " & (opList.contains(ch) | puncList.contains(ch))) {
                // System.out.println("First Else " + ch + lexeme);

                list.add(lexeme);
                lexeme = "";

                if (isSpaceOrEmptyString(ch) & isSpaceOrEmptyString(next) &
                         !areAlphas) {
                    if (opList.contains(ch) & opList.contains(next)) {
                        lexeme += ch;
                        lexeme += next;
                        list.add(lexeme);
                        lexeme = "";
                        i++;
                    } else {

                        list.add(ch);

                    }
                } else if (isSpaceOrEmptyString(ch)) {

                    list.add(ch);

                    // lexeme += ch;
                }
                // if (ch.equals("\'")) {
                // int nextQuoteIndex = i;
                // for (int j = i + 1; j < input.length(); j++) {
                // char characterNext = input.charAt(j);
                // if (characterNext == '\'') {
                // break;
                // } else {
                // nextQuoteIndex += 1;
                // }
                // }
                // list.add("\'" + input.substring(i + 1, nextQuoteIndex + 1) + "\'");
                // i += nextQuoteIndex - i + 1;
                // }
                // if (!isAlpha(prev)) {
                // System.out.println("OPERATOR " + prev);
                // }
                // if (!isAlpha(next)) {
                // System.out.println("OPERATOR " + next);
                // }
                // if (!isAlpha(ch)) {
                // System.out.println("OPERATOR " + ch);
                // }
                // if (prev.equals("'") & next.equals("'")) {
                // list.add(prev + ch + next);
                // i += 1;
                // }
                // if (isAlpha(prev) & isAlpha(ch)) {
                // list.add(ch);
                // }

            }

            // Condtion to check if the character is not space or operator or punctuation
            else if (opList.contains(ch) | puncList.contains(ch) & !ch.contains(" ")) {
                // System.out.println("Second Else " + ch + lexeme);
                
                if (isSpaceOrEmptyString(ch) & isSpaceOrEmptyString(next) & !areAlphas) {
                    if (opList.contains(ch) & opList.contains(next)) {
                        lexeme += ch;
                        lexeme += next;
                        list.add(lexeme);
                        lexeme = "";
                        i++;
                    } else {

                        list.add(ch);

                    }
                } else if (isSpaceOrEmptyString(ch)) {

                    list.add(ch);

                    // lexeme += ch;
                }
                // list.add(ch);
                // lexeme = "";
            }

            // Condtion to check if the character is space and lexeme is not empty
            else if (lexeme != "" & lexeme != " " & ch.contains(" ")) {
                // System.out.println("Third Else " + lexeme);
                list.add(lexeme);
                lexeme = "";
            }

        }

        return list;

    }

    public static void main(String args[]) {
        System.out.println(lexeme("int 1+2=5; string 3+5=5.3;"));
        System.out.println(lexeme("int a=5; a++; a--; a+=1; a-=1;"));
        System.out.println(lexeme("int a = 0;"));
        System.out.println(lexeme("a++;"));
        System.out.println(lexeme("--a;"));
        System.out.println(lexeme("int b = ++a + --a;"));
        System.out.println(lexeme("int b += a;"));
        System.out.println(lexeme("int c = b*a;"));
    }
}
