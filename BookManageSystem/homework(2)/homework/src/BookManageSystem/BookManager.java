// File: BookManager.java
package BookManageSystem;

import java.io.*;
import java.util.*;

public class BookManager {
    private Book[] books = new Book[1000];
    private BookNum[] bookNumbers = new BookNum[1000];
    private Category[] categories = new Category[10];
    private Borrower[] borrowers = new Borrower[100];
    private int bookCount = 0;
    private int categoryCount = 0;
    private int borrowerCount = 0;
    private static final String DATA_FILE = "library_data.dat";
    private static final String REPORT_FILE = "library_report.txt";

    // ANSI color codes for console output
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public void init() {
        // Initialize categories
        categories[categoryCount++] = new Category("CAT001", "Fiction", "Fictional literature");
        categories[categoryCount++] = new Category("CAT002", "Non-Fiction", "Factual literature");
        categories[categoryCount++] = new Category("CAT003", "Science", "Scientific books");
        categories[categoryCount++] = new Category("CAT004", "Technology", "Technology and programming");

        // Initialize sample books
        addBook(new Book("Ateler's Corner", "Juan Li", 45, "Fiction", "2020-01-15"), new BookNum("BK001", 5));
        addBook(new Book("The Ordinary World", "Yao Lu", 55, "Fiction", "2015-03-20"), new BookNum("BK002", 3));
        addBook(new Book("Java Programming", "John Doe", 85, "Technology", "2023-05-10"), new BookNum("BK003", 8));
        addBook(new Book("Data Science Basics", "Jane Smith", 75, "Science", "2022-08-15"), new BookNum("BK004", 6));

        // Initialize sample borrowers
        borrowers[borrowerCount++] = new Borrower("BR001", "Alice Johnson", "1234567890", "alice@email.com");
        borrowers[borrowerCount++] = new Borrower("BR002", "Bob Smith", "9876543210", "bob@email.com");
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            LibraryData data = new LibraryData(books, bookNumbers, categories, borrowers,
                    bookCount, categoryCount, borrowerCount);
            oos.writeObject(data);
            System.out.println(GREEN + "✓ Data saved successfully!" + RESET);
        } catch (IOException e) {
            System.out.println(RED + "✗ Error saving data: " + e.getMessage() + RESET);
        }
    }

    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            LibraryData data = (LibraryData) ois.readObject();
            books = data.getBooks();
            bookNumbers = data.getBookNumbers();
            categories = data.getCategories();
            borrowers = data.getBorrowers();
            bookCount = data.getBookCount();
            categoryCount = data.getCategoryCount();
            borrowerCount = data.getBorrowerCount();
            System.out.println(GREEN + "✓ Data loaded successfully!" + RESET);
        } catch (FileNotFoundException e) {
            System.out.println(YELLOW + "No saved data found. Starting with default data." + RESET);
            init();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(RED + "✗ Error loading data: " + e.getMessage() + RESET);
            init();
        }
    }

    public void generateReport() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(REPORT_FILE))) {
            writer.println("==========================================");
            writer.println("         LIBRARY MANAGEMENT SYSTEM REPORT");
            writer.println("==========================================");
            writer.println("\nGenerated on: " + new Date());
            writer.println("\n--- BOOKS INVENTORY ---");
            writer.println(String.format("%-10s | %-25s | %-15s | %-6s | %-10s | %-10s | Inventory",
                    "ID", "Title", "Author", "Price", "Category", "Publish Date"));
            writer.println("-".repeat(100));

            for (int i = 0; i < bookCount; i++) {
                if (books[i] != null && bookNumbers[i] != null) {
                    writer.println(books[i].toString() + " | " + bookNumbers[i].toString());
                }
            }

            writer.println("\n--- BORROWERS ---");
            writer.println(String.format("%-10s | %-20s | %-15s | %-25s | Books Borrowed",
                    "ID", "Name", "Phone", "Email"));
            writer.println("-".repeat(90));

            for (int i = 0; i < borrowerCount; i++) {
                if (borrowers[i] != null) {
                    writer.println(borrowers[i].toString());
                }
            }

            writer.println("\n--- STATISTICS ---");
            writer.println("Total Books: " + bookCount);
            writer.println("Total Categories: " + categoryCount);
            writer.println("Total Borrowers: " + borrowerCount);
            writer.println("==========================================");

            System.out.println(GREEN + "✓ Report generated: " + REPORT_FILE + RESET);
        } catch (IOException e) {
            System.out.println(RED + "✗ Error generating report: " + e.getMessage() + RESET);
        }
    }

    public void list() {
        printHeader("BOOK LIST");
        System.out.println(BOLD + String.format("%-10s | %-25s | %-15s | %-6s | %-10s | %-10s | %-20s",
                "ID", "Title", "Author", "Price", "Category", "Publish Date", "Inventory Status") + RESET);
        System.out.println("-".repeat(110));

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && bookNumbers[i] != null) {
                String statusColor = bookNumbers[i].getAvailableNum() > 0 ? GREEN : RED;
                System.out.println(books[i].toString() + " | " +
                        statusColor + bookNumbers[i].toString() + RESET);
            }
        }
        System.out.println(CYAN + "\nTotal books: " + bookCount + RESET);
    }

    public void addBook(Book book, BookNum bookNum) {
        if (bookCount >= books.length) {
            System.out.println(RED + "Warning: Book storage is full!" + RESET);
            return;
        }

        boolean exists = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getBookname().equalsIgnoreCase(book.getBookname()) &&
                    books[i].getBookAuthor().equalsIgnoreCase(book.getBookAuthor())) {

                int currentNum = bookNumbers[i].getBookTotalNum();
                int newNum = bookNum.getBookTotalNum();
                bookNumbers[i].setBookTotalNum(currentNum + newNum);
                System.out.println(YELLOW + "⚠ Same book exists. Increased inventory by " + newNum + RESET);
                exists = true;
                break;
            }
        }

        if (!exists) {
            books[bookCount] = book;
            bookNum.setBookId(book.getBookId());
            bookNumbers[bookCount] = bookNum;
            bookCount++;
            System.out.println(GREEN + "✓ Book added successfully! ID: " + book.getBookId() + RESET);
        }
    }

    public void fuzzySearchByName(String keyword) {
        printHeader("SEARCH RESULTS BY TITLE");
        System.out.println(BOLD + String.format("%-10s | %-25s | %-15s | %-6s | %-10s",
                "ID", "Title", "Author", "Price", "Available") + RESET);
        System.out.println("-".repeat(80));

        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getBookname().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(String.format("%-10s | %-25s | %-15s | %-6d | %-10d",
                        books[i].getBookId(),
                        books[i].getBookname(),
                        books[i].getBookAuthor(),
                        books[i].getBookPrice(),
                        bookNumbers[i].getAvailableNum()));
                found = true;
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

        printSearchResult(found, keyword, "authors");
    }

    public void listCategories() {
        printHeader("BOOK CATEGORIES");
        System.out.println(BOLD + String.format("%-10s | %-20s | %-30s", "ID", "Name", "Description") + RESET);
        System.out.println("-".repeat(70));

        for (int i = 0; i < categoryCount; i++) {
            if (categories[i] != null) {
                System.out.println(categories[i].toString());
            }
        }
    }

    public void listBorrowers() {
        printHeader("BORROWERS LIST");
        System.out.println(BOLD + String.format("%-10s | %-20s | %-15s | %-25s | Books",
                "ID", "Name", "Phone", "Email") + RESET);
        System.out.println("-".repeat(90));

        for (int i = 0; i < borrowerCount; i++) {
            if (borrowers[i] != null) {
                System.out.println(borrowers[i].toString());
            }
        }
    }

    public void addBorrower(Scanner input) {
        System.out.print("Enter borrower name: ");
        String name = input.nextLine();
        System.out.print("Enter phone: ");
        String phone = input.nextLine();
        System.out.print("Enter email: ");
        String email = input.nextLine();

        String borrowerId = "BR" + String.format("%03d", borrowerCount + 1);
        Borrower borrower = new Borrower(borrowerId, name, phone, email);
        borrowers[borrowerCount++] = borrower;

        System.out.println(GREEN + "✓ Borrower added successfully! ID: " + borrowerId + RESET);
    }

    public void borrowBook(Scanner input) {
        System.out.print("Enter borrower ID: ");
        String borrowerId = input.nextLine();
        System.out.print("Enter book ID: ");
        String bookId = input.nextLine();

        Borrower borrower = findBorrowerById(borrowerId);
        BookNum bookNum = findBookNumById(bookId);

        if (borrower != null && bookNum != null) {
            if (bookNum.borrowBook() && borrower.borrowBook(bookId)) {
                System.out.println(GREEN + "✓ Book borrowed successfully!" + RESET);
            } else {
                System.out.println(RED + "✗ Cannot borrow book. Check availability or borrower limit." + RESET);
            }
        } else {
            System.out.println(RED + "✗ Invalid borrower ID or book ID." + RESET);
        }
    }

    private Borrower findBorrowerById(String id) {
        for (int i = 0; i < borrowerCount; i++) {
            if (borrowers[i] != null && borrowers[i].getBorrowerId().equals(id)) {
                return borrowers[i];
            }
        }
        return null;
    }

    private BookNum findBookNumById(String id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getBookId().equals(id) && bookNumbers[i] != null) {
                return bookNumbers[i];
            }
        }
        return null;
    }

    private void printHeader(String title) {
        System.out.println("\n" + BLUE + "=".repeat(50) + RESET);
        System.out.println(BOLD + BLUE + "          " + title + RESET);
        System.out.println(BLUE + "=".repeat(50) + RESET);
    }

    private void printSearchResult(boolean found, String keyword, String type) {
        if (!found) {
            System.out.println(RED + "✗ No " + type + " containing \"" + keyword + "\" were found" + RESET);
        } else {
            System.out.println(GREEN + "✓ Search completed." + RESET);
        }
    }

    public int getBookCount() {
        return bookCount;
    }

    public int getBorrowerCount() {
        return borrowerCount;
    }

    public int getCategoryCount() {
        return categoryCount;
    }
}