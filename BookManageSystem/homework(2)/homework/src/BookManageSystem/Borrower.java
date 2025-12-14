// File: Borrower.java
package BookManageSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Borrower implements Serializable {
    private static final long serialVersionUID = 1L;
    private String borrowerId;
    private String name;
    private String phone;
    private String email;
    private List<String> borrowedBooks;

    public Borrower(String borrowerId, String name, String phone, String email) {
        this.borrowerId = borrowerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean borrowBook(String bookId) {
        if (borrowedBooks.size() < 5) { // Maximum 5 books per borrower
            borrowedBooks.add(bookId);
            return true;
        }
        return false;
    }

    public boolean returnBook(String bookId) {
        return borrowedBooks.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-15s | %-25s | Borrowed: %d",
                borrowerId, name, phone, email, borrowedBooks.size());
    }
}