
// import File Modules
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import LexicalAnalyser.lexer;
import LexicalAnalyser.Tokenizer;

public class MainClass {

  // Function to read the lexemes line by line.
  public static List<String> readLexemes(String data) {
    String lexemeData = data+" ";
    System.out.println(lexemeData);
    List<String> lexemesList = lexer.lexeme(lexemeData);
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
        if (dataline.contains("!^")) {
          String newDataLine = "";
          for (int i = 0; i < dataline.length(); i++) {
            if (dataline.charAt(i) != '!' & dataline.charAt(i + 1) != '^') {
              newDataLine += dataline.charAt(i);
              // System.out.println(newDataLine);
            } else {
              break;
            }
          }
          // Make Lexemes and Tokens of Line before the Comment
          List<String> lexemeLine = readLexemes(newDataLine);
          System.out.println(newDataLine);
          System.out.println(lexemeLine);

          // Looping Through
          for (int i = 0; i < lexemeLine.size(); i++) {
            String lexeme = lexemeLine.get(i);
            Tokenizer.Tokenizers(lexeme, lineNumber);

            Tokenizer.Tokenizers(lexeme, lineNumber).display();
            
          }
          // Check if the current line does not have closing comment tag
          // and there is a next line present
          if (!dataline.contains("^!") & myReader.hasNextLine()) {
            while (myReader.hasNextLine()) {
              // Check if the line has closing tag
              // if not add next line to dataline
              if (!dataline.contains("^!")) {
                dataline += "\n";
                dataline += myReader.nextLine();
                // System.out.println(dataline);
              }
              // if the dataline has closing tag
              else if (dataline.contains("^!")) {
                newDataLine = "";
                // iterate through the dataline
                // check where is the closing tag
                for (int i = 0; i < dataline.length(); i++) {
                  String next2char = String.valueOf(dataline.charAt(i)) + String.valueOf(dataline.charAt(i + 1));
                  if (!next2char.equals("^!")) {
                    newDataLine += dataline.charAt(i);
                    // System.out.println(newDataLine);
                  }
                  // if closing tag is found
                  // make lexemes of the code after the closing tag
                  else {
                    i += 2;
                    newDataLine = "";
                    while (i < dataline.length()) {
                      newDataLine += dataline.charAt(i);
                      // System.out.println(newDataLine);
                      i++;
                    }

                    // Make Lexemes and Tokens of Line before the Comment
                    lexemeLine = readLexemes(newDataLine);
                    System.out.println(newDataLine);
                    System.out.println(lexemeLine);

                    // Looping Through
                    for (int j = 0; j < lexemeLine.size(); j++) {
                      String lexeme = lexemeLine.get(j);
                      Tokenizer.Tokenizers(lexeme, lineNumber);
                      
                    }

                    break;
                  }
                }
              }
            }
            if (!dataline.contains("^!") & !myReader.hasNextLine()) {
              System.out.println("Lexical Error");
            }
          }
        }

        // Dealing with Single Line Comments
        else if (dataline.contains("\\")) {
          // Iterate through the line and check where is Comment
          String newDataLine = "";
          for (int j = 0; j < dataline.length(); j++) {
            if (dataline.charAt(j) == '\\' & dataline.charAt(j + 1) == '\\') {
              System.out.println("I'm SingleLine Comment");
              break;
            } else {
              newDataLine += dataline.charAt(j);
            }
          }
          // Make Lexemes and Tokens of Line before the Comment
          List<String> lexemeLine = readLexemes(newDataLine);
          System.out.println(newDataLine);
          System.out.println(lexemeLine);

          // Looping Through
          for (int i = 0; i < lexemeLine.size(); i++) {
            String lexeme = lexemeLine.get(i);
            Tokenizer.Tokenizers(lexeme, lineNumber);
            

          }

        } else {
          List<String> lexemeLine = readLexemes(dataline);
          System.out.println(dataline);
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
