// File: BookNum.java
package BookManageSystem;

import java.io.Serializable;

public class BookNum implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bookId;
    private int bookTotalNum = 0;
    private int bookBorrowedNum = 0;

    public BookNum() {}

    public BookNum(String bookId, int totalNum) {
        this.bookId = bookId;
        this.bookTotalNum = totalNum;
    }

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
        if (bookTotalNum >= 0) {
            this.bookTotalNum = bookTotalNum;
        } else {
            System.out.println("\u001B[31mWarning: Book quantity cannot be negative\u001B[0m");
        }
    }

    public int getBookBorrowedNum() {
        return bookBorrowedNum;
    }

    public void setBookBorrowedNum(int bookBorrowedNum) {
        if (bookBorrowedNum >= 0 && bookBorrowedNum <= bookTotalNum) {
            this.bookBorrowedNum = bookBorrowedNum;
        } else {
            System.out.println("\u001B[31mWarning: Invalid borrowed quantity\u001B[0m");
        }
    }

    public int getAvailableNum() {
        return bookTotalNum - bookBorrowedNum;
    }

    public boolean borrowBook() {
        if (getAvailableNum() > 0) {
            bookBorrowedNum++;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (bookBorrowedNum > 0) {
            bookBorrowedNum--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Total: %d | Borrowed: %d | Available: %d",
                bookTotalNum, bookBorrowedNum, getAvailableNum());
    }
}