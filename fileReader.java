    //FileReader
    void readFile() throws IOException {
        BufferedReader reader = null;
        ExpenseType expenseType = null;
        String line;
        System.out.println("\n>>>>>>>>>>>>>> Adding Objects (ExpenseType) from File to List ...");

        try {
            reader = new BufferedReader(new FileReader(new File("expenseTypes")));
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
                                        line = reader.readLine(); //maybe not needed
                                        int id = 0;
                                        if (line.trim().startsWith("ID ")) {
                                            id = Integer.parseInt(line.trim().substring(3).trim());
                                        }
                                        line = reader.readLine(); //maybe not needed
                                        String desc = null;
                                        if (line.trim().startsWith("DESC ")) {
                                            desc = line.trim().substring(5).trim();
                                        }
                                        line = reader.readLine(); //idk bro
                                        double max = 0;
                                        if (line.trim().startsWith("MAX_MONTHLY_EXPENSE ")) {
                                            max = Double.parseDouble(line.trim().substring(20).trim());
                                        }
                                        line = reader.readLine(); //bababooey
                                        double price = 0;
                                        if (line.trim().startsWith("PRICE ")) {
                                            price = Double.parseDouble(line.trim().substring(6).trim());
                                        }
                                        line = reader.readLine(); //drink piss and live
                                        String unit = null;
                                        if (line.trim().startsWith("UNIT_OF_MEASUREMENT ")) {
                                            unit = line.trim().substring(20).trim();
                                        }
                                        if (line.trim().equals("}")) {
                                            expenseType = new ExpenseType1(id, desc, max, price, unit);
                                            expenseTypes.add(expenseType);
                                        }//ExpenseType1
                                    } else if (line.trim().substring(5).trim().equals("2")) {
                                        line = reader.readLine(); //maybe not needed
                                        int id = 0;
                                        if (line.trim().startsWith("ID ")) {
                                            id = Integer.parseInt(line.trim().substring(3).trim());
                                        }
                                        line = reader.readLine(); //maybe not needed
                                        String desc = null;
                                        if (line.trim().startsWith("DESC ")) {
                                            desc = line.trim().substring(5).trim();
                                        }
                                        line = reader.readLine(); //idk bro
                                        double max = 0;
                                        if (line.trim().startsWith("MAX_MONTHLY_EXPENSE ")) {
                                            max = Double.parseDouble(line.trim().substring(20).trim());
                                        }
                                        line = reader.readLine(); //lesgooooo
                                        double rate = 0;
                                        if (line.trim().startsWith("RATE ")) {
                                            rate = Double.parseDouble(line.trim().substring(5).trim());
                                        }
                                        line = reader.readLine();
                                        if (line.trim().equals("}")) {
                                            expenseType = new ExpenseType2(id, desc, max, rate);
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
    }

    void printList() {
        System.out.println("\n >>>>>>>>>>>> Printing List \n");
        for (ExpenseType expenseType : expenseTypes) {
            System.out.println(expenseType);
        }
    }

    //main function
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to MainApp!");
        MainApp myapp = new MainApp();
        myapp.readFile();
        myapp.printList();
        // myapp.mainMenu();
        // myapp.loadData();
    }
}
