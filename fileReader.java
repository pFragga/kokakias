import java.io.*;
import java.util.Scanner;

public class fileReader {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("expenseTypes");
        File myFile2 = new File("employees");
        Scanner scanner = new Scanner(myFile);
        Scanner scanner2 = new Scanner(myFile2);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        
        while (scanner2.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}