// File: LibraryData.java
package BookManageSystem;

import java.io.Serializable;

class LibraryData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Book[] books;
    private BookNum[] bookNumbers;
    private Category[] categories;
    private Borrower[] borrowers;
    private int bookCount;
    private int categoryCount;
    private int borrowerCount;

    public LibraryData(Book[] books, BookNum[] bookNumbers, Category[] categories,
                       Borrower[] borrowers, int bookCount, int categoryCount, int borrowerCount) {
        this.books = books;
        this.bookNumbers = bookNumbers;
        this.categories = categories;
        this.borrowers = borrowers;
        this.bookCount = bookCount;
        this.categoryCount = categoryCount;
        this.borrowerCount = borrowerCount;
    }

    // Getters
    public Book[] getBooks() { return books; }
    public BookNum[] getBookNumbers() { return bookNumbers; }
    public Category[] getCategories() { return categories; }
    public Borrower[] getBorrowers() { return borrowers; }
    public int getBookCount() { return bookCount; }
    public int getCategoryCount() { return categoryCount; }
    public int getBorrowerCount() { return borrowerCount; }
}
