
// import File Modules
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import LexicalAnalyser.lexer;
import LexicalAnalyser.Tokenizer;
import LexicalAnalyser.TokenClass;
import SyntaxAnalyser.Syntax;
public class MainClass {
  static String T = "true";
  // Function to read the lexemes line by line.
  public static List<String> readLexemes(String data) {
    String lexemeData = data + " ";
    // System.out.println(lexemeData);
    List<String> lexemesList = lexer.lexeme(lexemeData);
    return lexemesList;

  }

  public static void main(String[] args) {
    List<Tokenizer.Token> tokenList = new ArrayList<Tokenizer.Token>();
    // Tokenizer.Token[] tokenList = new Tokenizer.Token[1];
    String data = "";
    // System.out.println("Input:");
    // Reading the data from code.txt file.
    int lineNumber = 1;

    try {

      // Initializing File Reader
      File myObj = new File("test.txt");
      Scanner myReader = new Scanner(myObj);

      outerLoop: while (myReader.hasNextLine()) {
        String dataline = myReader.nextLine();
      //  // System.out.println(dataline);

        // Dealing with Multi Line Comments
        if (dataline.contains("!^")) {
          String newDataLine = "";
          for (int i = 0; i < dataline.length(); i++) {
            if (dataline.charAt(i) != '!' & dataline.charAt(i + 1) != '^') {
              newDataLine += dataline.charAt(i);
            //  // System.out.println(newDataLine);
            } else {
              break;
            }
          }

          // Make Lexemes and Tokens of Line before the Comment
          List<String> lexemeLine = readLexemes(newDataLine);
        //  // System.out.println(newDataLine);
          // System.out.println(lexemeLine);

          // Looping Through
          for (int i = 0; i < lexemeLine.size(); i++) {

            String lexeme = lexemeLine.get(i);
            Tokenizer.Token item = Tokenizer.Tokenizers(lexeme, lineNumber);
            String type = Tokenizer.Tokenizers(lexeme, lineNumber).type;
            String value = Tokenizer.Tokenizers(lexeme, lineNumber).value;
            int line = Tokenizer.Tokenizers(lexeme, lineNumber).line;
            String token = Tokenizer.Tokenizers(lexeme, lineNumber).display();

            if (type.equals("UNDEFINED")) {
              T = "false";
              System.out.println("Lexical Error at line:" + line + " " + type + " " + value);
              break outerLoop;
            } else {
              // System.out.println(token);
              tokenList.add(item);
            }
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
                    // System.out.println(newDataLine);
                    // System.out.println(lexemeLine);

                    // Looping Through
                    for (int j = 0; j < lexemeLine.size(); j++) {

                      String lexeme = lexemeLine.get(j);
                      Tokenizer.Token item = Tokenizer.Tokenizers(lexeme, lineNumber);
                      String type = Tokenizer.Tokenizers(lexeme, lineNumber).type;
                      String value = Tokenizer.Tokenizers(lexeme, lineNumber).value;
                      int line = Tokenizer.Tokenizers(lexeme, lineNumber).line;
                      String token = Tokenizer.Tokenizers(lexeme, lineNumber).display();

                      if (type.equals("UNDEFINED")) {

                        T = "false";
                        System.out.println("Lexical Error at line:" + line + " " + type + " " + value);
                        break outerLoop;

                      } else {
                        // System.out.println(token);
                        tokenList.add(item);
                      }
                    }

                    break;
                  }
                }
              }
            }
            if (!dataline.contains("^!") & !myReader.hasNextLine()) {
              // System.out.println("Lexical Error at line:" + lineNumber);
              break outerLoop;
            }
          }
        }

        // Dealing with Single Line Comments
        else if (dataline.contains("\\")) {
          // Iterate through the line and check where is Comment
          String newDataLine = "";
          for (int j = 0; j < dataline.length(); j++) {
            if (dataline.charAt(j) == '\\' & dataline.charAt(j + 1) == '\\') {
              // System.out.println("I'm SingleLine Comment");
              break;
            } else {
              newDataLine += dataline.charAt(j);
            }
          }
          // Make Lexemes and Tokens of Line before the Comment
          List<String> lexemeLine = readLexemes(newDataLine);
          // System.out.println(newDataLine);
          // System.out.println(lexemeLine);

          // Looping Through
          for (int i = 0; i < lexemeLine.size(); i++) {
            String lexeme = lexemeLine.get(i);
            Tokenizer.Token item = Tokenizer.Tokenizers(lexeme, lineNumber);
            String type = Tokenizer.Tokenizers(lexeme, lineNumber).type;
            String value = Tokenizer.Tokenizers(lexeme, lineNumber).value;
            int line = Tokenizer.Tokenizers(lexeme, lineNumber).line;
            String token = Tokenizer.Tokenizers(lexeme, lineNumber).display();
            if (type.equals("UNDEFINED")) {
              T = "false";
              System.out.println("Lexical Error at line:" + line + " " + type + " " + value);
              break outerLoop;
            } else {
              // System.out.println(token);
              tokenList.add(item);
            }
          }

        } else {
          List<String> lexemeLine = readLexemes(dataline);
          // System.out.println(dataline);
          // System.out.println(lexemeLine);

          // Looping Through
          for (int i = 0; i < lexemeLine.size(); i++) {
            String lexeme = lexemeLine.get(i);
            Tokenizer.Token item = Tokenizer.Tokenizers(lexeme, lineNumber);
            String type = Tokenizer.Tokenizers(lexeme, lineNumber).type;
            String value = Tokenizer.Tokenizers(lexeme, lineNumber).value;
            int line = Tokenizer.Tokenizers(lexeme, lineNumber).line;
            String token = Tokenizer.Tokenizers(lexeme, lineNumber).display();
            if (type.equals("UNDEFINED")) {
              T = "false";
              System.out.println("Lexical Error at line:" + line + " " + type + " " + value);
              break outerLoop;
            } else {
              // System.out.println(token);
              tokenList.add(item);
            }
          }
        }

        // Incrementing the line Number
        data += "\n" + dataline;
        lineNumber += 1;
      }

      // Printing all the code
      System.out.println("\n\n All Code: ");
      System.out.println(data);
      // System.out.println(tokenList.size());
    //  // System.out.println(tokenList);
    // Tokenizer.Token arr = new Tokenizer.Token(tokenList.size()+1, "$", "$");
		if (T=="true"){
      Tokenizer.Token arr = Tokenizer.Tokenizers("$", tokenList.size()+1);
      tokenList.add(arr);  
      Syntax syntaxClass = new Syntax(tokenList);
        syntaxClass.run();
        
    }	
    myReader.close();

    } catch (FileNotFoundException e) {
      // System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }
}
