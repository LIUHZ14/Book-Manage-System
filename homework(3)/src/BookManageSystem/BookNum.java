package BookManageSystem;

import java.io.Serializable;

public class BookNum implements Serializable {//Serialization version ID for compatibility when saving and loading
    private static final long serialVersionUID = 1L;//Serialization version ID
    private String bookId;//Set Book ID
    private int bookTotalNum = 0;//Set total number of books
    private int bookBorrowedNum = 0;//Set number of borrowed books

    public BookNum() {}//Default constructor

    public BookNum(String bookId, int totalNum) {//Constructor
        this.bookId = bookId;
        this.bookTotalNum = totalNum;
    }

    //---Getter and Setter methods---

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getBookTotalNum() {
        return bookTotalNum;
    }

    public void setBookTotalNum(int bookTotalNum) {
        if (bookTotalNum >= 0) {//Quantity cannot be negative
            this.bookTotalNum = bookTotalNum;//Set total number of books
        } else {//Quantity cannot be negative
            System.out.println("\u001B[31mWarning: Book quantity cannot be negative\u001B[0m");//Print a warning message
        }
    }

    public int getBookBorrowedNum() {
        return bookBorrowedNum;
    }//Get number of borrowed books

    public void setBookBorrowedNum(int bookBorrowedNum) {//Set number of borrowed books
        if (bookBorrowedNum >= 0 && bookBorrowedNum <= bookTotalNum) {//Quantity cannot be negative
            this.bookBorrowedNum = bookBorrowedNum;//Set number of borrowed books
        } else {//Quantity cannot be negative
            System.out.println("\u001B[31mWarning: Invalid borrowed quantity\u001B[0m");//Print a warning message
        }
    }

    public int getAvailableNum() {
        return bookTotalNum - bookBorrowedNum;
    }//Get number of available books

    public boolean borrowBook() {//Borrow a book
        if (getAvailableNum() > 0) {//Check if there are available books
            bookBorrowedNum++;//Increase the number of borrowed books
            return true;//Borrow successful
        }
        return false;//Borrow failed
    }

    public boolean returnBook() {//Return a book
        if (bookBorrowedNum > 0) {//Check if there are borrowed books
            bookBorrowedNum--;//Decrease the number of borrowed books
            return true;//Return successful
        }
        return false;//Return failed
    }

    @Override
    public String toString() {//Return a string representation of the book
        return String.format("Total: %d | Borrowed: %d | Available: %d",
                bookTotalNum, bookBorrowedNum, getAvailableNum());//Return a string representation of the book
    }

}
