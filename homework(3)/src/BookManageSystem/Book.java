package BookManageSystem;

import java.io.Serializable;

public class Book implements Serializable {// Serialization version identifier to ensure compatibility during deserialization
    private static final long serialVersionUID = 1L;// Unique identifier for the book
    private String bookId; // Title of the book
    private String bookname;//Name of the book
    private String bookAuthor;// Author of the book
    private int bookPrice;// Price of the book
    private String category;// Category of the book
    private String publishDate;// Publish date of the book

    public Book() {
        this.bookId = generateBookId();
    }// The use of constructor

    public Book(String bookname, String bookAuthor, int bookPrice, String category, String publishDate) {//The definition of the constructor
        this.bookId = generateBookId();//Generate a unique book ID
        this.bookname = bookname;//Set the book title
        this.bookAuthor = bookAuthor;//Set the book author
        this.bookPrice = bookPrice;//Set the book price
        this.category = category;//Set the book category
        this.publishDate = publishDate;//Set the book publish date
    }

    private String generateBookId() {
        return "BK" + System.currentTimeMillis() % 10000;
    }//Return the unique book ID

    public String getBookId() {
        return bookId;
    }//Return the book ID

    public String getBookname() {
        return bookname;
    }//Return the book title

    public void setBookname(String bookname) {//Set the book title
        if (bookname != null && !bookname.trim().isEmpty()) {//Check if the book title is not empty
            this.bookname = bookname;//Set the book title
        } else {//If the book title is empty
            System.out.println("\u001B[31mWarning: Book title cannot be empty\u001B[0m");//Print a warning message
        }
    }

    public String getBookAuthor() {
        return bookAuthor;
    }//Set the book author with input validation

    public void setBookAuthor(String bookAuthor) {//Input validation: author must not be empty or null
        if (bookAuthor != null && !bookAuthor.trim().isEmpty()) {//Check if the book author is not empty or null
            this.bookAuthor = bookAuthor;//Set the book author
        } else {//If the book author is empty or null
            System.out.println("\u001B[31mWarning: Book Author cannot be empty\u001B[0m");//Print a warning message
        }
    }

    public int getBookPrice() {
        return bookPrice;
    }//Set the book price with input validation

    public void setBookPrice(int bookPrice) {//Input validation: price must be non-negative
        if (bookPrice >= 0) {//Check if the book price is non-negative
            this.bookPrice = bookPrice;//Set the book price
        } else {//If the book price is negative
            System.out.println("\u001B[31mWarning: Book price cannot be negative\u001B[0m");//Print a warning message
        }
    }

    public String getCategory() {
        return category;
    }//Set the book category

    public void setCategory(String category) {
        this.category = category;
    }//Get the publish date

    public String getPublishDate() {
        return publishDate;
    }//Set the publish date

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }//Override the toString method

    @Override
    public String toString() {//Return a string representation of the book
        return String.format("%-10s | %-25s | %-15s | %-6d | %-10s | %-10s",
                bookId, bookname, bookAuthor, bookPrice, category, publishDate);//Return a string representation of the book
    }

}
