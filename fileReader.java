import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class fileReader {
    ArrayList<ExpenseType> expenseTypes = new ArrayList<ExpenseType>();
        
        void createList() {
            System.out.println("------------------Creating Expense Types and adding to List------------------\n...\n...\n...");
            ExpenseType1 travel = new ExpenseType1(2368, "travel", 1000, 10, "kms");
            expenseTypes.add(travel);
            ExpenseType1 food = new ExpenseType1(1542, "food", 500, 50, "meal");
            expenseTypes.add(food);
            ExpenseType1 rec = new ExpenseType1(6900, "recreation", 400, 100, "activity");
            expenseTypes.add(rec);
            ExpenseType2 health = new ExpenseType2(6589, "healthcare", 700, 50);
            expenseTypes.add(health);
            ExpenseType2 ins = new ExpenseType2(1543, "insurance", 350, 60);
            expenseTypes.add(ins);
            ExpenseType2 wfh = new ExpenseType2(5658, "working from home", 1000, 80);
            expenseTypes.add(wfh);
        }

        void printList() {
            System.out.println("------------------Printing List------------------");
            for (ExpenseType expenseType : expenseTypes) {
                System.out.println(expenseType);
            }
        }





    public static void main(String[] args) throws Exception {
        fileReader myFileReader = new fileReader();
        myFileReader.createList();
        myFileReader.printList();
        


        // FileReader Reader = new FileReader("expenseTypes");
        // BufferedReader bReader = new BufferedReader(Reader);

        // String line = null;
        // if (bReader != null) {
        //     line = bReader.readLine();
        // }

        // while (line != null) {
        //     System.out.println(line);
		// 	line = bReader.readLine();
        // }

        // if (bReader != null) {
        //     bReader.close();;
        // }

        // if (Reader != null) {
        //     Reader.close();
        // }


        // File myFile = new File("expenseTypes");
        // File myFile2 = new File("employees");
        // Scanner scanner = new Scanner(myFile);
        // while (scanner.hasNextLine()) {
        //     System.out.println(scanner.nextLine());
        // }
    }
}
