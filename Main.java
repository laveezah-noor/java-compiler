import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        
      System.out.println(data); 
      }
}
