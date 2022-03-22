// import File Modules
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
// packages
import LexicalAnalyser.lexer;
import LexicalAnalyser.Tokenizer;

public class Main {
    public static void main(String[] args) {  
        String data = "";
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
        System.out.println(lexemesList);
        for (int i = 0; i < lexemesList.size(); i++) {
            String lexeme = lexemesList.get(i);
            System.out.print(lexeme);
            Tokenizer.Tokenizers(lexeme);   
        }
      }
}
