// File: Main.java
package BookManageSystem;

import java.util.Scanner;

public class Main {//Share instances used throughout the application
    private static BookManager manager = new BookManager();//Main system controller
    private static Scanner input = new Scanner(System.in);//For reading user input

    private static void printMainMenu() {
        //Top border with cyan line
        System.out.println(BookManager.CYAN + "=".repeat(60) + BookManager.RESET);

        //System title in bold bold yellow
        System.out.println(BookManager.BOLD + BookManager.YELLOW +
                "       LIBRARY MANAGEMENT SYSTEM" + BookManager.RESET);

        //Middle border with cyan line
        System.out.println(BookManager.CYAN + "=".repeat(60) + BookManager.RESET);

        //Menu options
        System.out.println(BookManager.GREEN + "1. \uD83D\uDCDA View Book List" + BookManager.RESET);
        System.out.println(BookManager.GREEN + "2. \u2795 Add New Book" + BookManager.RESET);
        System.out.println(BookManager.GREEN + "3. \uD83D\uDD0D Fuzzy Search" + BookManager.RESET);
        System.out.println(BookManager.GREEN + "4. \uD83D\uDCC2 Manage Categories" + BookManager.RESET);
        System.out.println(BookManager.GREEN + "5. \uD83D\uDC64 Manage Borrowers" + BookManager.RESET);
        System.out.println(BookManager.GREEN + "6. \uD83D\uDCD6 Borrow/Return Books" + BookManager.RESET);
        System.out.println(BookManager.GREEN + "7. \uD83D\uDCCE Save Data" + BookManager.RESET);
        System.out.println(BookManager.GREEN + "8. \uD83D\uDCCA Generate Report" + BookManager.RESET);
        System.out.println(BookManager.GREEN + "9. \u2139\uFE0F️  System Information" + BookManager.RESET);
        System.out.println(BookManager.RED + "0. \u274C Exit System" + BookManager.RESET);

        //Bottom border with cyan line
        System.out.println(BookManager.CYAN + "-".repeat(60) + BookManager.RESET);

        //Separator line
        System.out.print(BookManager.BOLD + "Enter your choice: " + BookManager.RESET);
    }

    /**
     * Displays the search options sub-menu.
     * Allows users to choose between searching by book title or author name.
     */
    private static void printSearchMenu() {
        System.out.println("\n" + BookManager.BLUE + "SEARCH OPTIONS" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(30) + BookManager.RESET);
        System.out.println("1. Search by Book Title");
        System.out.println("2. Search by Author Name");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose option: ");
    }


    /**
     * Displays the borrower management sub-menu.
     * Provides options for viewing and adding library borrowers.
     */
    private static void printBorrowerMenu() {
        System.out.println("\n" + BookManager.BLUE + "BORROWER MANAGEMENT" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(30) + BookManager.RESET);
        System.out.println("1. List All Borrowers");
        System.out.println("2. Add New Borrower");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose option: ");
    }


    /**
     * Displays the book transactions sub-menu.
     * Handles borrowing and returning of books by library members.
     */
    private static void printTransactionMenu() {
        System.out.println("\n" + BookManager.BLUE + "BOOK TRANSACTIONS" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(30) + BookManager.RESET);
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose option: ");
    }

    private static int getIntInput() {//Get integer input
        while (true) {//Loop until valid input is provided
            try {//Try parsing input as an integer
                return Integer.parseInt(input.nextLine());//Return parsed integer
            } catch (NumberFormatException e) {//Catch exception
                System.out.println(BookManager.RED + "Invalid input! Please enter a valid number." + BookManager.RESET);//Print error message
                System.out.print("Try again: ");//Prompt for another input
            }
        }
    }

    public static void main(String[] args) {
        // Load saved data or initialize
        manager.loadData();//Load saved data

        System.out.println(BookManager.GREEN + "\u2728Welcome to the Library Management System! \u2728" + BookManager.RESET);//Display welcome message

        int choice;//Variable to store user's choice
        do {
            printMainMenu();//Display main menu
            choice = getIntInput();//Get user's choice as integer

            switch (choice) {
                case 1://View book list
                    manager.list();
                    break;

                case 2://Add new book
                    addBookMenu();
                    break;

                case 3://Fuzzy search
                    searchMenu();
                    break;

                case 4://Manage categories
                    manager.listCategories();
                    break;

                case 5://Manage borrowers
                    borrowerMenu();
                    break;

                case 6://Borrow/return books
                    transactionMenu();
                    break;

                case 7://Save data
                    manager.saveData();
                    break;

                case 8://Generate report
                    manager.generateReport();
                    break;

                case 9://System information
                    showSystemInfo();
                    break;

                case 0://Exit
                    System.out.println(BookManager.YELLOW + "\n\uD83D\uDCCE Saving data before exit..." + BookManager.RESET);
                    manager.saveData();//Auto-save before exiting
                    System.out.println(BookManager.GREEN + "Thank you for using the Library Management System!" + BookManager.RESET);
                    break;

                default://Invalid choice
                    System.out.println(BookManager.RED + "Invalid choice! Please try again." + BookManager.RESET);
            }

            if (choice != 0) {//If choice is not 0, prompt for another choice
                System.out.println("\n" + BookManager.CYAN + "Press Enter to continue..." + BookManager.RESET);//Prompt for another choice
                input.nextLine();//Wait for user to press Enter
            }

        } while (choice != 0);//Loop until choice is 0

        input.close();//Close scanner
    }

    /**
     * Handles the book addition process through user interaction
     * Guides the user through the process of adding a new book
     * Creates a new Book object and adds it to the library
     */
    private static void addBookMenu() {
        System.out.println("\n" + BookManager.BLUE + "➕ ADD NEW BOOK" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(30) + BookManager.RESET);

        // Create a new Book object
        Book book = new Book();


        //Step 1: Get book title
        System.out.print("Enter book title: ");
        book.setBookname(input.nextLine());

        //Step 2: Get author
        System.out.print("Enter author: ");
        book.setBookAuthor(input.nextLine());

        //Step 3: Get price
        System.out.print("Enter price: ");
        book.setBookPrice(getIntInput());

        input.nextLine(); // Clear the input buffer after reading the price

        //Step 4: Get category
        System.out.print("Enter category: ");
        book.setCategory(input.nextLine());

        //Step 5: Get publish date
        System.out.print("Enter publish date (YYYY-MM-DD): ");
        book.setPublishDate(input.nextLine());

        //Step 6: Get quantity
        System.out.print("Enter quantity: ");
        int quantity = getIntInput();

        //Create inventory object with the quantity
        BookNum bookNum = new BookNum();
        bookNum.setBookTotalNum(quantity);

        //Add book to the library
        manager.addBook(book, bookNum);
    }

    private static void searchMenu() {
        int choice;

        //Search menu loop
        do {
            printSearchMenu();//Display search op`
            choice = getIntInput();//Get user's choice

            switch (choice) {
                case 1://Search by title
                    System.out.print("Enter search keyword for title: ");
                    String keyword1 = input.nextLine();
                    manager.fuzzySearchByName(keyword1);
                    break;

                case 2://Search by author
                    System.out.print("Enter search keyword for author: ");
                    String keyword2 = input.nextLine();
                    manager.fuzzySearchByAuthor(keyword2);
                    break;

                case 3://Return to main menu
                    System.out.println("Returning to main menu...");
                    break;

                default://Invalid choice
                    System.out.println(BookManager.RED + "Invalid choice!" + BookManager.RESET);
            }

            if (choice != 3) {//Pause after search results
                System.out.println("\n" + BookManager.CYAN + "Press Enter to continue..." + BookManager.RESET);
                input.nextLine();//Wait for user to press Enter
            }
        } while (choice != 3);//Continue until user chooses to go back
    }

    /**
     * Handles the borrower management process through user interaction
     * Allows the user to view existing borrowers or add new ones
     */
    private static void borrowerMenu() {
        int choice;

        //Borrower menu loop
        do {
            printBorrowerMenu();//Display borrower options
            choice = getIntInput();//Get user's choice

            switch (choice) {
                case 1://List borrowers
                    manager.listBorrowers();
                    break;

                case 2://Add borrower
                    manager.addBorrower(input);
                    break;

                case 3://Return to main menu
                    System.out.println("Returning to main menu...");
                    break;

                default://Invalid choice
                    System.out.println(BookManager.RED + "Invalid choice!" + BookManager.RESET);
            }

            //Pause after borrower operations
            if (choice != 3) {
                System.out.println("\n" + BookManager.CYAN + "Press Enter to continue..." + BookManager.RESET);
                input.nextLine();//Wait for user to press Enter
            }

        } while (choice != 3);//Continue until user chooses to go back
    }

    /**
     * Handles book borrowing and returning transactions
     */
    private static void transactionMenu() {
        int choice;

        //Transaction menu loop
        do {
            printTransactionMenu();//Display transaction options
            choice = getIntInput();//Get user's choice

            switch (choice) {
                case 1://Borrow book
                    System.out.println("Borrow Book feature coming soon!");
                    // manager.borrowBook(input);
                    break;

                case 2://Return book
                    System.out.println("Return Book feature coming soon!");
                    break;

                case 3://Return to main menu
                    System.out.println("Returning to main menu...");
                    break;

                default://Invalid choice
                    System.out.println(BookManager.RED + "Invalid choice!" + BookManager.RESET);
            }

            //Pause after transaction operations
            if (choice != 3) {
                System.out.println("\n" + BookManager.CYAN + "Press Enter to continue..." + BookManager.RESET);
                input.nextLine();
            }

        } while (choice != 3);
    }

    /**
     * Displays system information and statistics
     * Shows current data counts and file locations
     */
    private static void showSystemInfo() {
        System.out.println("\n" + BookManager.BLUE + "SYSTEM INFORMATION" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(40) + BookManager.RESET);

        // Display current statistics with color coding
        System.out.println("Total Books: " + BookManager.GREEN + manager.getBookCount() + BookManager.RESET);
        System.out.println("Total Categories: " + BookManager.GREEN + manager.getCategoryCount() + BookManager.RESET);
        System.out.println("Total Borrowers: " + BookManager.GREEN + manager.getBorrowerCount() + BookManager.RESET);

        // Display file locations
        System.out.println("Data File: " + BookManager.YELLOW + "library_data.dat" + BookManager.RESET);
        System.out.println("Report File: " + BookManager.YELLOW + "library_report.txt" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(40) + BookManager.RESET);
    }
}