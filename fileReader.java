/* Omada: 117
Suggrafeis:
1100130 - Theodoros Balas
3200234 - Peter Frangatzis


Το κομμάτι του πρόγραμματος της 1ης εργασίας μεταφορτώθηκε δύο φορές
(με την πρώτη να είναι εμπρόθεσμη και τη δεύτερη με ολιγοήμερη καθυστέρηση).
Αν υπάρχει η δυνατότητα (και δεν έχει γίνει ήδη), παρακαλούμε,
λάβετε υπόψιν την ανανεωμένη έκδοση - καταβάλαμε εξαιρετική προσπάθεια για το update.
*/
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Employee class
class Employee {
    private String name, surname;
    private double maxMonthlyTotal;

    public Employee(String n, String s, double max) {
        name = n;
        surname = s;
        maxMonthlyTotal = max;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public double getMaxMonthlyTotal() {
        return this.maxMonthlyTotal;
    }

    public String toString() {
        return name + " " + surname + "\nEmployee's maximum monthly total: " + maxMonthlyTotal;
    }
}

// ExpenseType class
abstract class ExpenseType {
    private int ID;
    private String desc;
    private double maxMonthlyExpense;

    public ExpenseType(int ID, String desc, double maxMonthlyExpense) {
        this.ID = ID;
        this.desc = desc;
        this.maxMonthlyExpense = maxMonthlyExpense;
    }

    public int getID() {
        return this.ID;
    }

    public String getDescription() {
        return this.desc;
    }

    public double getMaxMonthlyExpense() {
        return this.maxMonthlyExpense;
    }

    public String toString() {
        return "ID: " + getID() + "\nDescription: " + getDescription() + "\nMaximum monthly expense limit: " + getMaxMonthlyExpense();
    }
}

// ExpenseType1 class
class ExpenseType1 extends ExpenseType {
    private double price;
    private String unitOfMeasurement;

    public ExpenseType1(int ID, String desc, double maxMonthlyExpense, double p, String unit) {
        super(ID, desc, maxMonthlyExpense);
        price = p;
        unitOfMeasurement = unit;
    }

    public double getPrice() {
        return this.price;
    }

    public String getUnitMeasurement() {
        return this.unitOfMeasurement;
    }

    @Override
    public String toString() {
        return "ID: " + getID() + "\nDescription: " + getDescription() + "\nMaximum monthly expense limit: " + getMaxMonthlyExpense()
                + "\nPrice per unit: " + this.price + "/" + this.unitOfMeasurement;
    }
}

// ExpenseType2 class
class ExpenseType2 extends ExpenseType {
    private double rate;

    public ExpenseType2(int ID, String desc, double maxMonthlyExpense, double rate) {
        super(ID, desc, maxMonthlyExpense);
        this.rate = rate;
    }

    public double getRate() {
        return this.rate;
    }

    @Override
    public String toString() {
        return "ID: " + getID() + "\nDescription: " + getDescription() + "\nMaximum monthly expense limit: " + getMaxMonthlyExpense()
                + "\nTo be reimbursed by: " + this.rate + "%";
    }
}

// Expense class
class Expense {
    private Employee employee;
    private ExpenseType expenseType;
    private double amount;
    private String reason;

    public Expense(Employee emp, ExpenseType type, double amount, String reason) {
        this.employee = emp;
        this.expenseType = type;
        this.amount = amount;
        this.reason = reason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public ExpenseType getExpenseType() {
        return this.expenseType;
    }

    public double getAmount() {
        return this.amount;
    }

    public String toString() {
        return employee + ": " + amount + ". " + expenseType + ". The reason being: " + reason;
    }
}

// Transaction class
abstract class Transaction {
    private Employee employee;
    private double value;

    public Transaction(Employee employee, double value) {
        this.employee = employee;
        this.value = value;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public double getValue() {
        return this.value;
    }

    public String toString() {
        return "Transaction of " + this.getValue() + " made by " + this.getEmployee();
    }
}

// Downpayment class
class  Downpayment extends Transaction {
    public Downpayment(Employee employee, double value) {
        super(employee, value);
    }

    @Override
    public String toString() {
        return this.getEmployee() + " made a down payment of " + this.getValue();
    }
}

// Difference class
class Difference extends Transaction {
    public Difference(Employee employee, double value) {
        super(employee, value);
    }

    @Override
    public String toString() {
        return this.getEmployee() + " spent " + this.getValue() + " over his maximum monthly expense limit";
    }
}

// Compensation class
class Compensation extends Transaction {
    private ExpenseType expenseType;

    public Compensation(Employee employee, double value, ExpenseType type) {
        super(employee, value);
        this.expenseType = type;
    }

    public ExpenseType getExpenseType() {
        return this.expenseType;
    }

    @Override
    public String toString() {
        return this.getValue() + " Compensation of " + this.getExpenseType() + " by " + this.getEmployee();
    }
}

// Finalised class
class Finalised extends Transaction {
    public Finalised(Employee employee, double value) {
        super(employee, value);
    }

    @Override
    public String toString() {
        return "Final compensation for " + this.getEmployee() + " : " + this.getValue();
    }
}

// MainApp class containing main function
public class MainApp {
    Scanner input;
    ArrayList<Employee> employees;
    ArrayList<Expense> expenses;
    ArrayList<ExpenseType> expenseTypes;
    ArrayList<Transaction> transactions;
    public static int index;

    //Load data to create database
    void loadData() {
        Employee petros = new Employee("Peter", "Frangatzis", 1000);
        employees.add(petros);
        Employee theodore = new Employee("Theodore", "Balas", 600);
        employees.add(theodore);
        Employee karl = new Employee("Karl", "Marx", 200);
        employees.add(karl);
        Employee john = new Employee("John", "Lenon", 800);
        employees.add(john);
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
        Downpayment d1 = new Downpayment(karl, 100);
        transactions.add(d1);
        Downpayment d2 = new Downpayment(theodore, 500);
        transactions.add(d2);
        Downpayment d3 = new Downpayment(petros, 200);
        transactions.add(d3);
        Downpayment d4 = new Downpayment(john, 800);
        transactions.add(d4);
        /**
         * We created 2 Finalised objects to test if printAll() worked properly
         * 
         * Finalised objects normally created through clearExpenses()
         */
        Finalised f1 = new Finalised(john, 800);
        transactions.add(f1);
        Finalised f2 = new Finalised(karl, 300);
        transactions.add(f2);
        Expense e1 = new Expense(theodore, travel, 30, "Edinburgh - Livingston");
        expenses.add(e1);
        Expense e2 = new Expense(theodore, food, 5, "meals");
        expenses.add(e2);
        Expense e3 = new Expense(theodore, ins, 500, "car");
        expenses.add(e3);
        Expense e4 = new Expense(karl, wfh, 500, "PC screen");
        expenses.add(e4);
        Expense e5 = new Expense(karl, health, 400, "check-up");
        expenses.add(e5);
        Expense e6 = new Expense(karl, rec, 3, "museum visits");
        expenses.add(e6);
        Expense e7 = new Expense(petros, health, 400, "doctor");
        expenses.add(e7);
        Expense e8 = new Expense(petros, ins, 1000, "liability");
        expenses.add(e8);
        Expense e9 = new Expense(petros, travel, 20, "cab ride");
        expenses.add(e9);
        Expense e10 = new Expense(john, wfh, 400, "bed");
        expenses.add(e10);
        Expense e11 = new Expense(john, wfh, 600, "guitar");
        expenses.add(e11);
        Expense e12 = new Expense(john, rec, 1, "day tripper");
        expenses.add(e12);
    }

    // MainApp function
    public MainApp() {
        input = new Scanner(System.in);
        employees = new ArrayList<Employee>();
        expenses = new ArrayList<Expense>();
        expenseTypes = new ArrayList<ExpenseType>();
        transactions = new ArrayList<Transaction>();
        loadData();
    }

    // 1st Menu function: newExpenseType
    void newExpenseType() {
        System.out.print("Enter ID: ");
        int a = input.nextInt();
        input.nextLine();// skip new line
        System.out.print("Enter description of expense type: ");
        String b = input.nextLine();
        System.out.print("Enter maximum monthly expense of expense type: ");
        Double c = input.nextDouble();
        input.nextLine();// skip new line
        int type = selectExpenseType();
    
        if (type == 1) {
            System.out.print("Enter price: ");
            double p = input.nextDouble();
            input.nextLine();// skip new line
            System.out.print("Enter unit of measurement: ");
            String u = input.nextLine();
            expenseTypes.add(new ExpenseType1(a, b, c, p, u));
            System.out.println(new ExpenseType1(a, b, c, p, u).toString());
            } else {
            System.out.print("Enter rate: ");
            double r = input.nextDouble();
            input.nextLine();// skip new line
            expenseTypes.add(new ExpenseType2(a, b, c, r));
            System.out.println(new ExpenseType2(a, b, c, r).toString());
        }
    }

    // 2nd Menu function: newEmployee
    void newEmployee() {
        System.out.print("Enter employee's last name: ");
        String a = input.nextLine();
        System.out.print("Enter employee's first name: ");
        String b = input.nextLine();
        System.out.print("Enter employee's max monthly compensation: ");
        double c = input.nextDouble();
        input.nextLine();
        employees.add(new Employee(a, b, c));
    }

    // 3rd Menu function: newExpense
    void newExpense() {
        boolean flag = true;
        int quant;
        Employee emp = selectEmployee();
        ExpenseType et = selectExpType();
        
        System.out.print("Enter expense quantinty or value to continue (or 0 to return to main menu): ");
            do {
                quant = input.nextInt();
                input.nextLine(); // skip newline
                if (quant == 0) {
                    mainMenu();
                } else if (quant < 0) {
                    System.out.print(
                        "Invalid number - value/quantinty must be positive!\nPlease enter a positive number (or 0 to return to main menu): "
                    );
                    flag = false;
                }
            } while (!flag);

        System.out.print("Enter expense description: ");
        String reason = input.nextLine();
        expenses.add(new Expense(emp, et, quant, reason));
    }

    // 4th Menu function: newDownpayment
    void newDownpayment() {
        boolean flag = true;
        int value;
        Employee emp = selectEmployee();
    
        System.out.print("Please enter value of downpayment (or 0 to return to main menu): ");
        do {
            value = input.nextInt();
            input.nextLine(); // skip newline
            if (value == 0) {
                mainMenu();
            } else if (value < 0) {
                System.out.print(
                    "Invalid number - value must be positive!\nPlease enter value of downpayment (or 0 to return to main menu): "
                );
                flag = false;
            }
        } while (!flag);
    
        transactions.add(new Downpayment(emp, value));
    }

    // 5th Menu function: printExpenses
    void printExpenses() {
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    // 6th Menu function: clearExpenses
    void clearExpenses() {

        Employee employee = selectEmployee();
        List<Expense> employeeExpenses = getExpensesForEmployee(employee);
        List<ExpenseType> employeeExpenseTypes = new ArrayList<ExpenseType>();
        for (Expense expense : expenses) {
            employeeExpenseTypes.add(expense.getExpenseType());
        }

        boolean flag;
        List<Double> amountsToSum = new ArrayList<Double>();
        for (int i = 0; i < employeeExpenses.size(); i++) {
            flag = true;
            int j = 0;
            do {
                if (employeeExpenses.get(i).getExpenseType() == employeeExpenseTypes.get(j)) {
                    amountsToSum.set(j, amountsToSum.get(j) + employeeExpenses.get(i).getAmount());
                    flag = false;
                }
                j++;
            } while (flag);
        }

        List<Double> valueOfExpenses = new ArrayList<Double>();
        int i = 0;
        for (ExpenseType expType : employeeExpenseTypes) {
            if (expType instanceof ExpenseType1) {
                ExpenseType1 exp1 = (ExpenseType1) expType;
                valueOfExpenses.set(i, exp1.getPrice() * amountsToSum.get(i));
            } else if (expType instanceof ExpenseType2) {
                ExpenseType2 exp2 = (ExpenseType2) expType;
                valueOfExpenses.set(i, exp2.getRate() * amountsToSum.get(i));
            }
            i++;
        }

        i = 0;
        for (double value : valueOfExpenses) {
            if (valueOfExpenses.get(i) > employeeExpenseTypes.get(i).getMaxMonthlyExpense()) {
                valueOfExpenses.set(i, employeeExpenseTypes.get(i).getMaxMonthlyExpense());
            }
            i++;
        }

        double sum = 0;
        i = 0;
        for (double value : valueOfExpenses) {
            if (valueOfExpenses.get(i) != 0) {
                Compensation comp = new Compensation(employee, valueOfExpenses.get(i), employeeExpenseTypes.get(i));
                sum = sum + valueOfExpenses.get(i);
            }
            i++;
        }

        if (sum > employee.getMaxMonthlyTotal()) {
            double dif = sum - employee.getMaxMonthlyTotal();
            Difference diference = new Difference(employee, dif);
            sum = sum - dif;
        }

        i = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof Downpayment) {
                if (transaction.getEmployee() == employee) {
                    sum = sum - transaction.getValue();
                }
            }
            i++;
        }

        Finalised fin = new Finalised(employee, sum);
        System.out.print(employee.toString() + " has his/her transaction finalised");

    }

    // 7th Menu function: printTransactions
    void printTransactions() {
        System.out.print("Select an employee:\n");
        System.out.println(getTransactionsForEmployee(selectEmployee()));
    }

    //8th Menu function: clearAll
    void clearAll() {
        System.out.println("Oops! It seems this operation in not ready yet!");
    }

    // 9th Menu function: printAll
    void printAll() {
        boolean flag = false;
        for (Transaction transaction : transactions) {
            if (transaction instanceof Finalised) {
                System.out.println(transaction.getEmployee() + "\n" + transaction.getValue());
                flag = true;
            }
        }

        List<Double> finalisedTransactions = new ArrayList<Double>();
        for (Transaction transaction : transactions) {
            if (transaction instanceof Finalised) {
                finalisedTransactions.add(transaction.getValue());
                flag = true;
            }
        }

        double sumFinalisedTransactions = 0;
        for (Double value : finalisedTransactions) {
            sumFinalisedTransactions += value;
            flag = true;
        }

        System.out.println("Total sum of finalised transactions: " + sumFinalisedTransactions);
        if (flag == false) {
            System.out.println("No transactions have been finalised yet!");
        }
    }

    // Menu
    public void mainMenu() {
        int menu;

        do {
            System.out.println("Main Menu" + "\n1: Add new expense type" + "\n2: Add new employee"
                    + "\n3: Add new employee expense" + "\n4: Add new downpayment transaction"
                    + "\n5: Print employees' expenses" + "\n6: Clear employee's expenses"
                    + "\n7: Print employees' transactions" + "\n8: Clear all company's employees' expenses"
                    + "\n9: Print final monthly sum of all employees" + "\n0: exit");
            System.out.print("Enter selection to continue [0-9]: ");
            menu = input.nextInt();
            input.nextLine(); // skip new line
            switch (menu) {
                case 1:
                    newExpenseType();
                    break;
                case 2:
                    newEmployee();
                    break;
                case 3:
                    newExpense();
                    break;
                case 4:
                    newDownpayment();
                    break;
                case 5:
                    printExpenses();
                    break;
                case 6:
                    clearExpenses();
                    break;
                case 7:
                    printTransactions();
                    break;
                case 8:
                    clearAll();
                    break;
                case 9:
                    printAll();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid number!");
            }
        } while (menu != 0);
    }

    //FileReader
    void readFile() throws IOException {
        /**
         * !!!IMPORTANT!!!
         * O reader αντιμετωπίζει ένα πρόβλημα το οποίο δεν καταφέραμε να επιλύσουμε.
         * Διαβάσει και αρχικοποιεί (σωστά!) μόνο το πρώτο αντικείμενο του κάθε text file.
         * Κατά συνέπεια, για να μπορέσετε να τρέξετε σωστά το πρόγραμμα,
         * έχουμε κρατήσει το loadData της πρώτης άσκησης το οποίομπορείτε να κάνετε comment out
         * για να δοκιμάσετε τη λειτουρία του file reader (αφού αφαιρέσετε to comment από την αντίστοιχη μέθοδο).
         */
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(new File("EXPENSETYPES.txt")));
            line = reader.readLine();
            while (line != null) {
                if (line.trim().equals("EXPENSE_TYPE_LIST")) {
                    line = reader.readLine();
                    if (line.trim().equals("{")) {
                        line = reader.readLine();
                        if (line.trim().equals("EXPENSE_TYPE")) {
                            line = reader.readLine();
                            if (line.trim().equals("{")) {
                                line = reader.readLine();
                                if (line.trim().startsWith("TYPE ")) {
                                    if (line.trim().substring(5).trim().equals("1")) {
                                        line = reader.readLine();
                                        int id = 0;
                                        if (line.trim().startsWith("ID ")) {
                                            id = Integer.parseInt(line.trim().substring(3).trim());
                                        }
                                        line = reader.readLine();
                                        String desc = null;
                                        if (line.trim().startsWith("DESC ")) {
                                            desc = line.trim().substring(5).trim();
                                        }
                                        line = reader.readLine();
                                        double max = 0;
                                        if (line.trim().startsWith("MAX_MONTHLY_EXPENSE ")) {
                                            max = Double.parseDouble(line.trim().substring(20).trim());
                                        }
                                        line = reader.readLine();
                                        double price = 0;
                                        if (line.trim().startsWith("PRICE ")) {
                                            price = Double.parseDouble(line.trim().substring(6).trim());
                                        }
                                        line = reader.readLine();
                                        String unit = null;
                                        if (line.trim().startsWith("UNIT_OF_MEASUREMENT ")) {
                                            unit = line.trim().substring(20).trim();
                                        }
                                        line = reader.readLine();
                                        ExpenseType1 expenseType = new ExpenseType1(id, desc, max, price, unit);
                                        if (line.trim().equals("}")) {
                                            expenseTypes.add(expenseType);
                                        }//ExpenseType1
                                    } else if (line.trim().substring(5).trim().equals("2")) {
                                        line = reader.readLine();
                                        int id = 0;
                                        if (line.trim().startsWith("ID ")) {
                                            id = Integer.parseInt(line.trim().substring(3).trim());
                                        }
                                        line = reader.readLine();
                                        String desc = null;
                                        if (line.trim().startsWith("DESC ")) {
                                            desc = line.trim().substring(5).trim();
                                        }
                                        line = reader.readLine();
                                        double max = 0;
                                        if (line.trim().startsWith("MAX_MONTHLY_EXPENSE ")) {
                                            max = Double.parseDouble(line.trim().substring(20).trim());
                                        }
                                        line = reader.readLine();
                                        double rate = 0;
                                        if (line.trim().startsWith("RATE ")) {
                                            rate = Double.parseDouble(line.trim().substring(5).trim());
                                        }
                                        line = reader.readLine();
                                        ExpenseType2 expenseType = new ExpenseType2(id, desc, max, rate);
                                        if (line.trim().equals("}")) {
                                            expenseTypes.add(expenseType);
                                        }//ExpenseType2
                                    }
                                }//TYPE
                            }
                        }//EXPENSETYPE
                    }
                }//EXPENSE_TYPE_LIST
                line = reader.readLine();
            }//while
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }

        try {
            reader = new BufferedReader(new FileReader(new File("EMPLOYEES.txt")));
            line = reader.readLine();
            while (line != null) {
                if (line.trim().equals("EMPLOYEE_LIST")) {
                    line = reader.readLine();
                    if (line.trim().equals("{")) {
                        line = reader.readLine();
                        if (line.trim().equals("EMPLOYEE")) {
                            line = reader.readLine();
                            if (line.trim().equals("{")) {
                                line = reader.readLine();
                                String n = null;
                                if (line.trim().startsWith("FIRST_NAME ")) {
                                    n = line.trim().substring(11).trim();
                                }
                                line = reader.readLine();
                                String s = null;
                                if (line.trim().startsWith("LAST_NAME ")) {
                                    s = line.trim().substring(10).trim();
                                }
                                line = reader.readLine();
                                double maxMonthlyTotal = 0;
                                if (line.trim().startsWith("MAX_MONTHLY_EXPENSE ")) {
                                    maxMonthlyTotal = Double.parseDouble(line.trim().substring(20).trim());
                                }
                                line = reader.readLine();
                                Employee employee = new Employee(n, s, maxMonthlyTotal);
                                if (line.trim().equals("}")) {
                                    employees.add(employee);
                                }
                            }
                        }//EMPLOYEE
                    }
                }//EMPLOYEE_LIST
                line = reader.readLine();
            }//while
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }

        try {
            reader = new BufferedReader(new FileReader(new File("EXPENSES.txt")));
            line = reader.readLine();
            while (line != null) {
                if (line.trim().equals("EXPENSE_LIST")) {
                    line = reader.readLine();
                    if (line.trim().equals("{")) {
                        line = reader.readLine();
                        if (line.trim().equals("EXPENSE")) {
                            line = reader.readLine();
                            if (line.trim().equals("{")) {
                                line = reader.readLine();
                                Employee emp = null;
                                if (line.trim().startsWith("EMPLOYEE_CODE ")) {
                                    emp = findEmployeeFromName(line.trim().substring(14).trim());
                                }
                                line = reader.readLine();
                                ExpenseType type = null;
                                if (line.trim().startsWith("EXPENSE_CODE ")) {
                                    type = findExpenseTypeFromDesc(line.trim().substring(13).trim());
                                }
                                line = reader.readLine();
                                double amount = 0;
                                if (line.trim().startsWith("VAL ")) {
                                    amount = Double.parseDouble(line.trim().substring(4).trim());
                                }
                                line = reader.readLine();
                                String reason = null;
                                if (line.trim().startsWith("REASON ")) {
                                    reason = line.trim().substring(7).trim();
                                }
                                line = reader.readLine();
                                Expense expense = new Expense(emp, type, amount, reason);
                                if (line.trim().equals("}")) {
                                    expenses.add(expense);
                                }
                            }
                        }//EXPENSE
                    }
                }//EXPENSE_LIST
                line = reader.readLine();
            }//while
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }

        try {
            reader = new BufferedReader(new FileReader(new File("TRANSACTIONS.txt")));
            line = reader.readLine();
            while (line != null) {
                if (line.trim().equals("TRN_LIST")) {
                    line = reader.readLine();
                    if (line.trim().equals("{")) {
                        line = reader.readLine();
                        if (line.trim().equals("TRN")) {
                            line = reader.readLine();
                            if (line.trim().equals("{")) {
                                line = reader.readLine();
                                Employee emp = null;
                                if (line.trim().startsWith("EMPLOYEE_CODE ")) {
                                    emp = findEmployeeFromName(line.trim().substring(14).trim());
                                }
                                line = reader.readLine();
                                if (line.trim().startsWith("TYPE ")) {
                                    if (line.trim().substring(5).trim().equals("Downpayment")) {
                                        line = reader.readLine();
                                        double value = 0;
                                        if (line.trim().startsWith("VAL ")) {
                                            value = Double.parseDouble(line.trim().substring(4).trim());
                                        }
                                        line = reader.readLine();
                                        Transaction downpayment = new Downpayment(emp, value);
                                        if (line.trim().equals("}")) {
                                            transactions.add(downpayment);
                                        }//Downpayment
                                    } else if (line.trim().substring(5).trim().equals("Compensation")) {
                                        line = reader.readLine();
                                        ExpenseType type =null;
                                        if (line.trim().startsWith("EXPENSE_CODE ")) {
                                            type = findExpenseTypeFromDesc(line.trim().substring(13).trim());
                                        }
                                        line = reader.readLine();
                                        double value = 0;
                                        if (line.trim().startsWith("VAL ")) {
                                            value = Double.parseDouble(line.trim().substring(5).trim());
                                        }
                                        line = reader.readLine();
                                        Transaction compensation = new Compensation(emp, value, type);
                                        if (line.trim().equals("}")) {
                                            transactions.add(compensation);
                                        }//Compensation
                                    } else if (line.trim().substring(5).trim().equals("Finalised")) {
                                        line = reader.readLine();
                                        double value = 0;
                                        if (line.trim().startsWith("VAL ")) {
                                            value = Double.parseDouble(line.trim().substring(4).trim());
                                        }
                                        line = reader.readLine();
                                        Transaction finalised = new Finalised(emp, value);
                                        if (line.trim().equals("}")) {
                                            transactions.add(finalised);
                                        }//Finalised
                                    }
                                }//TYPE
                            }
                        }//TRN
                    }
                }//TRN_LIST
                line = reader.readLine();
            }//while
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
    }

    List<Expense> getExpensesForEmployee(Employee employee) {
        List<Expense> employeeExpenses = new ArrayList<Expense>();
        for (Expense expense : expenses) {
            if (expense.getEmployee() == employee) {
                employeeExpenses.add(expense);
            }
        }
        return employeeExpenses;
    }

    List<Transaction> getTransactionsForEmployee(Employee employee) {
        List<Transaction> employeeTransactions = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (transaction.getEmployee() == employee) {
                employeeTransactions.add(transaction);
            }
        }
        return employeeTransactions;
    }

    Employee selectEmployee() {
        int i = 1;
        boolean flag = true;
        int menu = 0;

        for (Employee employee : employees) {
            System.out.println(i + ". " + employee.toString());
            i++;
        }

        Employee employee;

        do {
            System.out.print("Enter number to select employee (or 0 to return to main menu): ");
            menu = input.nextInt();
            input.nextLine(); // skip newline
            if (menu == 0) {
                mainMenu();
            } else if (menu - 1 > employees.size() | menu < 1) {
                System.out.print("Invalid number!");
                flag = false;
            }
        } while (!flag);

        MainApp.index = menu;

        return employee = employees.get(index - 1);
    }

    int selectExpenseType() {
        boolean flag = true;
        int menu;
        System.out.println("Expense Type" + "\n1: expense defined by quantinty" + "\n2: expense defined by value"
                + "\n0: return to main menu");
        do {
            System.out.print("Enter selection to continue [0-2]: ");
            menu = input.nextInt();
            input.nextLine(); // skip newline
            if (menu == 0) {
                mainMenu();
            } else if (menu != 1 & menu != 2) {
                flag = false;
            }
        } while (!flag);

        return menu;
    }

    ExpenseType selectExpType() {
        int i = 1;
        boolean flag = true;
        int menu = 0;

        for (ExpenseType expenseType : expenseTypes) {
            System.out.println(i + ". " + expenseType.getDescription());
            i++;
        }

        ExpenseType expenseType;

        do {
            System.out.print("Enter number to select expense type (or 0 to return to main menu): ");
            menu = input.nextInt();
            input.nextLine(); // skip newline
            if (menu == 0) {
                mainMenu();
            } else if (menu - 1 > expenseTypes.size() | menu < 1) {
                System.out.print("Invalid number!");
                flag = false;
            }
        } while (!flag);

        MainApp.index = menu;

        return expenseType = expenseTypes.get(index - 1);
    }

    ExpenseType findExpenseTypeFromDesc(String description) {
        ExpenseType expType = null;
        for (ExpenseType expenseType : expenseTypes) {
            String desc = expenseType.getDescription();
            if (desc == description) {
                expType = expenseType;
                break;
            }
        }
        return expType;
    }

    Employee findEmployeeFromName(String surname) {
        Employee emp = null;
        for (Employee employee : employees) {
            String s = employee.getSurname();
            if (surname == s) {
                emp = employee;
                break;
            }
        }
        return emp;
    }

    int findExpenseType(ExpenseType exp) {
        int type;
        
        if (exp instanceof ExpenseType1) {
            type = 1;
        }
        else {
            type = 2;
        }
        return type;
    }
    
    String findTransactionType (Transaction trn) {
        String trnType;
        
        if (trn instanceof Finalised) {
            trnType = "Finsalised";
        }
        else if (trn instanceof Compensation) {
            trnType = "Compensation";
        }
        else if (trn instanceof Difference) {
            trnType = "Difference";
        }
        else {
            trnType = "Downpayment";
        }
        
        return trnType;
    }

    public void printExpenseList() {
        
        System.out.println(" Priting expense list...");

        FileWriter writer = null;

        try {
            writer = new FileWriter(new File("EXPENSES.txt"));

            for (Expense expense : expenses) {
                    writer.write ("EXPENSE"+"\n"+"{"+"\n"
                                + "\n"+"\t"+"EMPLOYEE_CODE " + expense.getEmployee().getSurname()
                                + "\n"+"\t"+"EXPENSE_TYPE "    + findExpenseType(expense.getExpenseType())
                                //+ "\n"+"\t"+"EXPENSE_CODE " + expense.getID()
                                + "\n"+"\t"+"VAL " + expense.getAmount()
                                + "\n"+"}"+"\n");
                }
                writer.close();
            }

        catch (IOException e) {
            System.err.println("Error writing file.");
        }
    }//CreateFile
    
    public void printTransactionList() {
        
        System.out.println("Priting transaction list...");
        
        FileWriter writer = null;

        try    {
            writer = new FileWriter(new File("TRANSACTIONS.txt"));
        
            for (Transaction transaction : transactions) {
                    writer.write ("TRN"+"\n"+"{"+"\n"
                                + "\n"+"\t"+"EMPLOYEE_CODE " + transaction.getEmployee().getSurname()
                                + "\n"+"\t"+"TYPE " + findTransactionType(transaction));
                    if (transaction instanceof Compensation) {
                        Compensation compensation = (Compensation) transaction;
                        writer.write ("\n"+"\t"+"EXPENSE_TYPE " + findExpenseType(compensation.getExpenseType())
                                + "\n"+"\t"+"EXPENSE_CODE " + compensation.getExpenseType().getID());
                    }
                    writer.write ("\n"+"\t"+"VAL " + transaction.getValue());
            }
            writer.close();
        }
            
        catch (IOException e) {
            System.err.println("Error writing file.");
        }
    }//CreateFile
    
    //main function
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to MainApp!");
        MainApp myapp = new MainApp();
        // myapp.readFile();
        myapp.mainMenu();
        myapp.loadData();
    }
}
