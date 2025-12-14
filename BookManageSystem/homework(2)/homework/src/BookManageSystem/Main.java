// File: Main.java
package BookManageSystem;

import java.util.Scanner;

public class Main {
    private static BookManager manager = new BookManager();
    private static Scanner input = new Scanner(System.in);

    private static void printMainMenu() {
        System.out.println(BookManager.CYAN + "=".repeat(60) + BookManager.RESET);
        System.out.println(BookManager.BOLD + BookManager.YELLOW +
                "       LIBRARY MANAGEMENT SYSTEM" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "=".repeat(60) + BookManager.RESET);
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
        System.out.println(BookManager.CYAN + "-".repeat(60) + BookManager.RESET);
        System.out.print(BookManager.BOLD + "Enter your choice: " + BookManager.RESET);
    }

    private static void printSearchMenu() {
        System.out.println("\n" + BookManager.BLUE + "SEARCH OPTIONS" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(30) + BookManager.RESET);
        System.out.println("1. Search by Book Title");
        System.out.println("2. Search by Author Name");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose option: ");
    }

    private static void printBorrowerMenu() {
        System.out.println("\n" + BookManager.BLUE + "BORROWER MANAGEMENT" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(30) + BookManager.RESET);
        System.out.println("1. List All Borrowers");
        System.out.println("2. Add New Borrower");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose option: ");
    }

    private static void printTransactionMenu() {
        System.out.println("\n" + BookManager.BLUE + "BOOK TRANSACTIONS" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(30) + BookManager.RESET);
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose option: ");
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(BookManager.RED + "Invalid input! Please enter a valid number." + BookManager.RESET);
                System.out.print("Try again: ");
            }
        }
    }

    public static void main(String[] args) {
        // Load saved data or initialize
        manager.loadData();

        System.out.println(BookManager.GREEN + "\u2728Welcome to the Library Management System! \u2728" + BookManager.RESET);

        int choice;
        do {
            printMainMenu();
            choice = getIntInput();

            switch (choice) {
                case 1:
                    manager.list();
                    break;

                case 2:
                    addBookMenu();
                    break;

                case 3:
                    searchMenu();
                    break;

                case 4:
                    manager.listCategories();
                    break;

                case 5:
                    borrowerMenu();
                    break;

                case 6:
                    transactionMenu();
                    break;

                case 7:
                    manager.saveData();
                    break;

                case 8:
                    manager.generateReport();
                    break;

                case 9:
                    showSystemInfo();
                    break;

                case 0:
                    System.out.println(BookManager.YELLOW + "\n\uD83D\uDCCE Saving data before exit..." + BookManager.RESET);
                    manager.saveData();
                    System.out.println(BookManager.GREEN + "Thank you for using the Library Management System!" + BookManager.RESET);
                    break;

                default:
                    System.out.println(BookManager.RED + "Invalid choice! Please try again." + BookManager.RESET);
            }

            if (choice != 0) {
                System.out.println("\n" + BookManager.CYAN + "Press Enter to continue..." + BookManager.RESET);
                input.nextLine();
            }

        } while (choice != 0);

        input.close();
    }

    private static void addBookMenu() {
        System.out.println("\n" + BookManager.BLUE + "➕ ADD NEW BOOK" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(30) + BookManager.RESET);

        Book book = new Book();

        System.out.print("Enter book title: ");
        book.setBookname(input.nextLine());

        System.out.print("Enter author: ");
        book.setBookAuthor(input.nextLine());

        System.out.print("Enter price: ");
        book.setBookPrice(getIntInput());

        input.nextLine(); // Clear buffer

        System.out.print("Enter category: ");
        book.setCategory(input.nextLine());

        System.out.print("Enter publish date (YYYY-MM-DD): ");
        book.setPublishDate(input.nextLine());

        System.out.print("Enter quantity: ");
        int quantity = getIntInput();

        BookNum bookNum = new BookNum();
        bookNum.setBookTotalNum(quantity);

        manager.addBook(book, bookNum);
    }

    private static void searchMenu() {
        int choice;
        do {
            printSearchMenu();
            choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter search keyword for title: ");
                    String keyword1 = input.nextLine();
                    manager.fuzzySearchByName(keyword1);
                    break;

                case 2:
                    System.out.print("Enter search keyword for author: ");
                    String keyword2 = input.nextLine();
                    manager.fuzzySearchByAuthor(keyword2);
                    break;

                case 3:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println(BookManager.RED + "Invalid choice!" + BookManager.RESET);
            }

            if (choice != 3) {
                System.out.println("\n" + BookManager.CYAN + "Press Enter to continue..." + BookManager.RESET);
                input.nextLine();
            }

        } while (choice != 3);
    }

    private static void borrowerMenu() {
        int choice;
        do {
            printBorrowerMenu();
            choice = getIntInput();

            switch (choice) {
                case 1:
                    manager.listBorrowers();
                    break;

                case 2:
                    manager.addBorrower(input);
                    break;

                case 3:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println(BookManager.RED + "Invalid choice!" + BookManager.RESET);
            }

            if (choice != 3) {
                System.out.println("\n" + BookManager.CYAN + "Press Enter to continue..." + BookManager.RESET);
                input.nextLine();
            }

        } while (choice != 3);
    }

    private static void transactionMenu() {
        int choice;
        do {
            printTransactionMenu();
            choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.println("Borrow Book feature coming soon!");
                    // manager.borrowBook(input);
                    break;

                case 2:
                    System.out.println("Return Book feature coming soon!");
                    break;

                case 3:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println(BookManager.RED + "Invalid choice!" + BookManager.RESET);
            }

            if (choice != 3) {
                System.out.println("\n" + BookManager.CYAN + "Press Enter to continue..." + BookManager.RESET);
                input.nextLine();
            }

        } while (choice != 3);
    }

    private static void showSystemInfo() {
        System.out.println("\n" + BookManager.BLUE + "SYSTEM INFORMATION" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(40) + BookManager.RESET);
        System.out.println("Total Books: " + BookManager.GREEN + manager.getBookCount() + BookManager.RESET);
        System.out.println("Total Categories: " + BookManager.GREEN + manager.getCategoryCount() + BookManager.RESET);
        System.out.println("Total Borrowers: " + BookManager.GREEN + manager.getBorrowerCount() + BookManager.RESET);
        System.out.println("Data File: " + BookManager.YELLOW + "library_data.dat" + BookManager.RESET);
        System.out.println("Report File: " + BookManager.YELLOW + "library_report.txt" + BookManager.RESET);
        System.out.println(BookManager.CYAN + "-".repeat(40) + BookManager.RESET);
    }
}