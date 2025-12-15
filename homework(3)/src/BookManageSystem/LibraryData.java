// File: LibraryData.java
package BookManageSystem;

import java.io.Serializable;

class LibraryData implements Serializable {
    // Serialization version ID for data compatibility
    private static final long serialVersionUID = 1L;

    //All data arrays from the BookManager
    private Book[] books;//Array of book objects
    private BookNum[] bookNumbers;//Array of inventory data for each book
    private Category[] categories;//Array of category objects
    private Borrower[] borrowers;//Array of borrower objects

    //Counters to track how many items
    private int bookCount;//Number of valid books in the books array
    private int categoryCount;//Number of valid categories in the categories array
    private int borrowerCount;//Number of valid borrowers in the borrowers array

    public LibraryData(Book[] books, BookNum[] bookNumbers, Category[] categories,
                       Borrower[] borrowers, int bookCount, int categoryCount, int borrowerCount) {//Constructor
        //Assign all parameters to instance variables
        this.books = books;
        this.bookNumbers = bookNumbers;
        this.categories = categories;
        this.borrowers = borrowers;
        this.bookCount = bookCount;
        this.categoryCount = categoryCount;
        this.borrowerCount = borrowerCount;
    }

    // ---Getter  methods---
    public Book[] getBooks() { return books; }
    public BookNum[] getBookNumbers() { return bookNumbers; }
    public Category[] getCategories() { return categories; }
    public Borrower[] getBorrowers() { return borrowers; }
    public int getBookCount() { return bookCount; }
    public int getCategoryCount() { return categoryCount; }
    public int getBorrowerCount() { return borrowerCount; }
}
