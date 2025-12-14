// File: Book.java
package BookManageSystem;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bookId;
    private String bookname;
    private String bookAuthor;
    private int bookPrice;
    private String category;
    private String publishDate;

    public Book() {
        this.bookId = generateBookId();
    }

    public Book(String bookname, String bookAuthor, int bookPrice, String category, String publishDate) {
        this.bookId = generateBookId();
        this.bookname = bookname;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.category = category;
        this.publishDate = publishDate;
    }

    private String generateBookId() {
        return "BK" + System.currentTimeMillis() % 10000;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        if (bookname != null && !bookname.trim().isEmpty()) {
            this.bookname = bookname;
        } else {
            System.out.println("\u001B[31mWarning: Book title cannot be empty\u001B[0m");
        }
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        if (bookAuthor != null && !bookAuthor.trim().isEmpty()) {
            this.bookAuthor = bookAuthor;
        } else {
            System.out.println("\u001B[31mWarning: Book Author cannot be empty\u001B[0m");
        }
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        if (bookPrice >= 0) {
            this.bookPrice = bookPrice;
        } else {
            System.out.println("\u001B[31mWarning: Book price cannot be negative\u001B[0m");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-25s | %-15s | %-6d | %-10s | %-10s",
                bookId, bookname, bookAuthor, bookPrice, category, publishDate);
    }
}