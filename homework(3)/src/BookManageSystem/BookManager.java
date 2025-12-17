package BookManageSystem;

import java.io.*;
import java.util.*;

public class BookManager {

    //---Date Storage---
    private Book[] books = new Book[1000];//Stores up  to 1000 books
    private BookNum[] bookNumbers = new BookNum[1000];//Book numbers/inventory
    private Category[] categories = new Category[10];//Only 10 categories allowed
    private Borrower[] borrowers = new Borrower[100];//Up to 100 borrowers

    //---Counts---
    private int bookCount = 0;//Current number of books
    private int categoryCount = 0;//Current number of categories
    private int borrowerCount = 0;//Current number of borrowers

    //---File Names---
    private static final String DATA_FILE = "library_data.dat";//Save/load data here
    private static final String REPORT_FILE = "library_report.txt";//Report go here

    // ANSI color codes for console output
    public static final String RESET = "\u001B[0m";//Reset to normal test
    public static final String RED = "\u001B[31m";//For errors
    public static final String GREEN = "\u001B[32m";//For success  messages
    public static final String YELLOW = "\u001B[33m";//For warnings
    public static final String BLUE = "\u001B[34m";//Blue test
    public static final String PURPLE = "\u001B[35m";//Not used
    public static final String CYAN = "\u001B[36m";//Cyan  test
    public static final String BOLD = "\u001B[1m";//Bold text

    public void init() {
        // Initialize categories
        categories[categoryCount++] = new Category("CAT001", "Fiction", "Fictional literature");//Add 1 default categories
        categories[categoryCount++] = new Category("CAT002", "Non-Fiction", "Factual literature");//Add 1 default categories
        categories[categoryCount++] = new Category("CAT003", "Science", "Scientific books");//Add 1 default categories
        categories[categoryCount++] = new Category("CAT004", "Technology", "Technology and programming");//Add 1 default categories

        // Initialize sample books
        // Add 4 sample books with their inventory counts
        addBook(new Book("Ateler's Corner", "Juan Li", 45, "Fiction", "2020-01-15"), new BookNum("BK001", 5));//5 copies available
        addBook(new Book("The Ordinary World", "Yao Lu", 55, "Fiction", "2015-03-20"), new BookNum("BK002", 3));//3 copies available
        addBook(new Book("Java Programming", "John Doe", 85, "Technology", "2023-05-10"), new BookNum("BK003", 8));//3 copies available
        addBook(new Book("Data Science Basics", "Jane Smith", 75, "Science", "2022-08-15"), new BookNum("BK004", 6));//6 copies available

        // Initialize sample borrowers
        //Add 2 sample borrowers
        borrowers[borrowerCount++] = new Borrower("BR001", "Alice Johnson", "1234567890", "alice@email.com");//Add 1 borrower
        borrowers[borrowerCount++] = new Borrower("BR002", "Bob Smith", "9876543210", "bob@email.com");//Add 1 borrower
    }

    public void saveData() {//Try-with-resources ensures the stream is automatically
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {//Create a LibraryData object containing all system data
            LibraryData data = new LibraryData(books, bookNumbers, categories, borrowers,
                    bookCount, categoryCount, borrowerCount);
            oos.writeObject(data);//Serialize and write the object to the file
            System.out.println(GREEN + "✓ Data saved successfully!" + RESET);//Print success message
        } catch (IOException e) {//Handle IO exceptions
            System.out.println(RED + "✗ Error saving data: " + e.getMessage() + RESET);//Print error message
        }
    }

    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {//Read and deserialize the LibraryData object from the file
            LibraryData data = (LibraryData) ois.readObject();

            //Restore all data from the Loaded object
            books = data.getBooks();
            bookNumbers = data.getBookNumbers();
            categories = data.getCategories();
            borrowers = data.getBorrowers();
            bookCount = data.getBookCount();
            categoryCount = data.getCategoryCount();
            borrowerCount = data.getBorrowerCount();

            System.out.println(GREEN + "✓ Data loaded successfully!" + RESET);
        } catch (FileNotFoundException e) {
            //No saved data file exists - start fresh with default data
            System.out.println(YELLOW + "No saved data found. Starting with default data." + RESET);
            init();//Initialize with sample data
        } catch (IOException | ClassNotFoundException e) {
            //Handle file corruption or version compatibility issues
            System.out.println(RED + "✗ Error loading data: " + e.getMessage() + RESET);//Print error message
            init();
        }
    }

    public void generateReport() {//Try-with-resources ensures the PrintWriter is closed automatically
        try (PrintWriter writer = new PrintWriter(new FileWriter(REPORT_FILE))) {

            // Report header
            writer.println("==========================================");
            writer.println("         LIBRARY MANAGEMENT SYSTEM REPORT");
            writer.println("==========================================");
            writer.println("\nGenerated on: " + new Date());

            // Books inventory Section
            writer.println("\n--- BOOKS INVENTORY ---");
            //Create formatted table header for books
            writer.println(String.format("%-10s | %-25s | %-15s | %-6s | %-10s | %-10s | Inventory",
                    "ID", "Title", "Author", "Price", "Category", "Publish Date"));
            writer.println("-".repeat(100));//Separator line

            //List all books with their inventory counts
            for (int i = 0; i < bookCount; i++) {
                if (books[i] != null && bookNumbers[i] != null) {
                    writer.println(books[i].toString() + " | " + bookNumbers[i].toString());
                }
            }

            // Borrowers Section
            writer.println("\n--- BORROWERS ---");
            //Create formatted table header for borrowers
            writer.println(String.format("%-10s | %-20s | %-15s | %-25s | Books Borrowed",
                    "ID", "Name", "Phone", "Email"));
            writer.println("-".repeat(90));//Separator line

            //List all borrowers
            for (int i = 0; i < borrowerCount; i++) {
                if (borrowers[i] != null) {
                    writer.println(borrowers[i].toString());
                }
            }
            // Statistics Section - Summary of library data
            writer.println("\n--- STATISTICS ---");
            writer.println("Total Books: " + bookCount);
            writer.println("Total Categories: " + categoryCount);
            writer.println("Total Borrowers: " + borrowerCount);
            writer.println("==========================================");

            //Footer completed message
            System.out.println(GREEN + "✓ Report generated: " + REPORT_FILE + RESET);
        } catch (IOException e) {
            //Handle file writing errors
            System.out.println(RED + "✗ Error generating report: " + e.getMessage() + RESET);
        }
    }

    public void list() {
        //Print section header with formatted table in the console
        printHeader("BOOK LIST");

        //Print table header with bold formatting
        System.out.println(BOLD + String.format("%-10s | %-25s | %-15s | %-6s | %-10s | %-10s | %-20s",
                "ID", "Title", "Author", "Price", "Category", "Publish Date", "Inventory Status") + RESET);

        //Separator line
        System.out.println("-".repeat(110));

        //List all books with color-coded availability status
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && bookNumbers[i] != null) {
                //Determine color: Green for available, RED if out of stock
                String statusColor = bookNumbers[i].getAvailableNum() > 0 ? GREEN : RED;

                //Print book details with colored inventory status
                System.out.println(books[i].toString() + " | " +
                        statusColor + bookNumbers[i].toString() + RESET);
            }
        }
        System.out.println(CYAN + "\nTotal books: " + bookCount + RESET);//Print total book count
    }

    public void addBook(Book book, BookNum bookNum) {
        //Check if book storage is full
        if (bookCount >= books.length) {
            System.out.println(RED + "Warning: Book storage is full!" + RESET);
            return;
        }

        boolean exists = false;

        //Check if book already exists in the system
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getBookname().equalsIgnoreCase(book.getBookname()) &&
                    books[i].getBookAuthor().equalsIgnoreCase(book.getBookAuthor())) {

                //Book exists - update inventory count
                int currentNum = bookNumbers[i].getBookTotalNum();
                int newNum = bookNum.getBookTotalNum();
                bookNumbers[i].setBookTotalNum(currentNum + newNum);

                System.out.println(YELLOW + "⚠ Same book exists. Increased inventory by " + newNum + RESET);
                exists = true;
                break;
            }
        }

        //If book doesn't exist, add it to a new system
        if (!exists) {
            books[bookCount] = book;//Add book to the array

            //Set book ID from the book object to the book number object
            bookNum.setBookId(book.getBookId());
            bookNumbers[bookCount] = bookNum;

            bookCount++;//Increment book counter

            System.out.println(GREEN + "✓ Book added successfully! ID: " + book.getBookId() + RESET);
        }
    }

    public void fuzzySearchByName(String keyword) {
        //Display search header
        printHeader("SEARCH RESULTS BY TITLE");

        //Print table header
        System.out.println(BOLD + String.format("%-10s | %-25s | %-15s | %-6s | %-10s",
                "ID", "Title", "Author", "Price", "Available") + RESET);

        //Separator line
        System.out.println("-".repeat(80));

        boolean found = false;

        //Search for books matching the keyword
        for (int i = 0; i < bookCount; i++) {
            //Check if book exists
            if (books[i] != null && books[i].getBookname().toLowerCase().contains(keyword.toLowerCase())) {
                //Print book details
                System.out.println(String.format("%-10s | %-25s | %-15s | %-6d | %-10d",
                        books[i].getBookId(),
                        books[i].getBookname(),
                        books[i].getBookAuthor(),
                        books[i].getBookPrice(),
                        bookNumbers[i].getAvailableNum()));

                found = true;//At least one book found
            }
        }

        printSearchResult(found, keyword, "books");
    }

    public void fuzzySearchByAuthor(String keyword) {
        printHeader("SEARCH RESULTS BY AUTHOR");
        System.out.println(BOLD + String.format("%-10s | %-25s | %-15s | %-6s | %-10s",
                "ID", "Title", "Author", "Price", "Available") + RESET);
        System.out.println("-".repeat(80));

        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getBookAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(String.format("%-10s | %-25s | %-15s | %-6d | %-10d",
                        books[i].getBookId(),
                        books[i].getBookname(),
                        books[i].getBookAuthor(),
                        books[i].getBookPrice(),
                        bookNumbers[i].getAvailableNum()));
                found = true;
            }
        }

        //Show appropriate message based on search results
        printSearchResult(found, keyword, "authors");
    }

    public void listCategories() {
        //Display section header
        printHeader("BOOK CATEGORIES");

        //Print table header with bold formatting
        System.out.println(BOLD + String.format("%-10s | %-20s | %-30s", "ID", "Name", "Description") + RESET);

        //Separator line
        System.out.println("-".repeat(70));

        for (int i = 0; i < categoryCount; i++) {
            //Check if category exists
            if (categories[i] != null) {//Category exists
                System.out.println(categories[i].toString());//Print category details
            }
        }
    }

    public void listBorrowers() {//List all borrowers
        printHeader("BORROWERS LIST");//Display section header
        System.out.println(BOLD + String.format("%-10s | %-20s | %-15s | %-25s | Books",
                "ID", "Name", "Phone", "Email") + RESET);//Print table header
        System.out.println("-".repeat(90));//Separator line

        for (int i = 0; i < borrowerCount; i++) {//Loop through borrowers
            if (borrowers[i] != null) {//Check if borrower exists
                System.out.println(borrowers[i].toString());//Print borrower details
            }
        }
    }

    public void addBorrower(Scanner input) {//Add a new borrower
        System.out.print("Enter borrower name: ");//Prompt for borrower's name
        String name = input.nextLine();//Get borrower details
        System.out.print("Enter phone: ");//Prompt for borrower's phone
        String phone = input.nextLine();//Get borrower details
        System.out.print("Enter email: ");//Prompt for borrower's email
        String email = input.nextLine();//Get borrower details

        String borrowerId = "BR" + String.format("%03d", borrowerCount + 1);//Generate borrower ID
        Borrower borrower = new Borrower(borrowerId, name, phone, email);//Create a new borrower object
        borrowers[borrowerCount++] = borrower;//Add borrower to the array

        System.out.println(GREEN + "✓ Borrower added successfully! ID: " + borrowerId + RESET);//Show success message
    }

    public void borrowBook(Scanner input) {//Borrow a book
        System.out.print("Enter borrower ID: ");//Prompt for borrower ID
        String borrowerId = input.nextLine();//Get borrower ID
        System.out.print("Enter book ID: ");//Prompt for book ID
        String bookId = input.nextLine();//Get book ID

        Borrower borrower = findBorrowerById(borrowerId);//Find borrower by ID
        BookNum bookNum = findBookNumById(bookId);//Find book number by ID

        if (borrower != null && bookNum != null) {//Check if borrower and book exist
            if (bookNum.borrowBook() && borrower.borrowBook(bookId)) { //Borrow book
                System.out.println(GREEN + "✓ Book borrowed successfully!" + RESET);//Show success message
            } else {//Book cannot be borrowed
                System.out.println(RED + "✗ Cannot borrow book. Check availability or borrower limit." + RESET);//Show error message
            }
        } else {//If book or borrower does not exist
            System.out.println(RED + "✗ Invalid borrower ID or book ID." + RESET);//Show error message
        }
    }

    private Borrower findBorrowerById(String id) {//Find borrower by ID
        for (int i = 0; i < borrowerCount; i++) {//Loop through borrowers
            if (borrowers[i] != null && borrowers[i].getBorrowerId().equals(id)) {//Check if borrower exists
                return borrowers[i];//Return borrower
            }
        }
        return null;//Borrower does not exist
    }

    private BookNum findBookNumById(String id) {//Find book number by ID
        for (int i = 0; i < bookCount; i++) {//Loop through book numbers
            if (books[i] != null && books[i].getBookId().equals(id) && bookNumbers[i] != null) {//Check if book number exists
                return bookNumbers[i];//Return book number
            }
        }
        return null;//Book number does not exist
    }

    private void printHeader(String title) {//Print section header
        System.out.println("\n" + BLUE + "=".repeat(50) + RESET);//Separator line
        System.out.println(BOLD + BLUE + "          " + title + RESET);//Section title
        System.out.println(BLUE + "=".repeat(50) + RESET);//Separator line
    }

    private void printSearchResult(boolean found, String keyword, String type) {//Print search result message
        if (!found) {//If no results were found
            System.out.println(RED + "✗ No " + type + " containing \"" + keyword + "\" were found" + RESET);//Show error message
        } else {//Results were found
            System.out.println(GREEN + "✓ Search completed." + RESET);//Show success message
        }
    }

    public int getBookCount() {
        return bookCount;
    }//Get total number of books

    public int getBorrowerCount() {
        return borrowerCount;
    }//Get total number of borrowers

    public int getCategoryCount() {
        return categoryCount;
    }//Get total number of categories

}
