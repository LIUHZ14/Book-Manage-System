// File: Borrower.java
package BookManageSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Borrower implements Serializable {

    //Serialization version ID for data compatibility
    private static final long serialVersionUID = 1L;

    private String borrowerId;//Unique ID for the borrower
    private String name;//Full name of the borrower
    private String phone;//Contact phone number
    private String email;//Contact email address
    private List<String> borrowedBooks;//List of books currently borrowed by the borrower

    public Borrower(String borrowerId, String name, String phone, String email) {//Constructor
        this.borrowerId = borrowerId;//Set borrower ID
        this.name = name;//Set borrower name
        this.phone = phone;//Set borrower phone
        this.email = email;//Set borrower email
        this.borrowedBooks = new ArrayList<>();//Initialize list of borrowed books
    }

    //---Getter methods---

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
    }//Get list of borrowed books

    public boolean borrowBook(String bookId) {//Check if the borrower can borrow a book
        if (borrowedBooks.size() < 5) { // Maximum 5 books per borrower
            borrowedBooks.add(bookId);//
            return true;//Borrow successful
        }
        return false;//Borrow failed
    }

    public boolean returnBook(String bookId) {
        return borrowedBooks.remove(bookId);
    }//Return a book

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-15s | %-25s | Borrowed: %d",
                borrowerId, name, phone, email, borrowedBooks.size());//Return a string representation of the borrower
    }
}