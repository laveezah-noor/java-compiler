// import File Modules
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import LexicalAnalyser.lexer;
import LexicalAnalyser.Tokenizer;

public class MainClass {
  
    public static void main(String[] args) {  
        String data = "";
        System.out.println("Input");
        try {
          File myObj = new File("code.txt");
          Scanner myReader = new Scanner(myObj);  
          while (myReader.hasNextLine()) {
            String dataline = myReader.nextLine();
            System.out.println(dataline);
            data += dataline;
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        
        List<String> lexemesList = lexer.lexeme(data); 
        String[] lexemeArray = new String[lexemesList.size()];
        lexemesList.toArray(lexemeArray);
        System.out.println("Lexeme List");
        System.out.println(lexemesList);
        // System.out.println("Lexeme Array");
        // System.out.println(lexemeArray);
        System.out.println("Tokens");
        String[] opArray = {"int", "a", "=", "5", ";", "string", "b", "=", "'Hello'", ";", "boolean", "c", "=", "true", ";", "char", "d", "=", "'c'", ";"};
        List<String> opList = new ArrayList<>(Arrays.asList(opArray));
    
        // for (String string : opList) {
        //     Tokenizer.Tokenizers(string);
        // }
        // for(String str: lexemeArray) {
        //   // System.out.println(str);
        //   Tokenizer.Tokenizers(str);
        // };
        
        for (int i = 0; i < lexemesList.size(); i++) {
            String lexeme = lexemesList.get(i);
            String op = opList.get(i);
            // System.out.print(lexeme==op);
            Tokenizer.Tokenizers(lexeme);   
            Tokenizer.Tokenizers(op);   

          }

      }
}
