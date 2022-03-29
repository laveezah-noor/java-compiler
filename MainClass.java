
// import File Modules
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import LexicalAnalyser.lexer;
import LexicalAnalyser.Tokenizer;

public class MainClass {

  // Function to read the lexemes line by line.
  public static List<String> readLexemes(String data) {

    List<String> lexemesList = lexer.lexeme(data);

    return lexemesList;

  }

  public static void main(String[] args) {

    String data = "";
    System.out.println("Input");

    // Reading the data from code.txt file.
    int lineNumber = 1;

    try {

      // Initializing File Reader
      File myObj = new File("code.txt");
      Scanner myReader = new Scanner(myObj);

      while (myReader.hasNextLine()) {
        String dataline = myReader.nextLine();
        // System.out.println(dataline);
        
        // Dealing with Multi Line Comments 
        if (dataline.contains("!^")){
          while (myReader.hasNextLine()) {
            if (!dataline.contains("^!")) {
               System.out.println("here"+dataline);
               dataline += myReader.nextLine();
               dataline += "\t";
            } else {
              dataline += myReader.nextLine();
              break;
            }
          }
        }
        // Dealing with Single Line Comments
        else if (dataline.contains("\\")) {
          // System.out.println("I'm Comment");
        } 
        else {
          List<String> lexemeLine = readLexemes(dataline);
          System.out.println(lexemeLine);

          // Looping Through
          for (int i = 0; i < lexemeLine.size(); i++) {
            String lexeme = lexemeLine.get(i);
            Tokenizer.Tokenizers(lexeme, lineNumber);

          }
        }

        // Incrementing the line Number
        data += "\n" + dataline;
        lineNumber += 1;
      }

      // Printing all the code
      System.out.println("\n\n All Code: ");
      System.out.println(data);

      myReader.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }
}
