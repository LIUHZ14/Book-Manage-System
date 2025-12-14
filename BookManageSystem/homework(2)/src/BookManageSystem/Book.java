package BookManageSystem;

public class Book {
    private String bookname;
    private String bookAuthor;
    private int bookPrice;

    public String getBookname() {
        return bookname;
    }// aquire getter

    public void setBookname(String bookname) {// [New] Automated Check: Book title cannot be empty
        if (bookname != null && !bookname.trim().isEmpty()) {  //judgement
            this.bookname = bookname;//set book name
        } else {//judgement
            System.out.println("Warning:Book title is empty");//print warning
        }
    }

    public String getBookAuthor() {
        return bookAuthor;
    }// aquire getter

    public void setBookAuthor(String bookAuthor) {// [New] Automated Check: Book author cannot be empty
        if (bookAuthor != null && !bookAuthor.trim().isEmpty()) {//Check if the author is empty
            this.bookAuthor = bookAuthor;//set book author
        } else {//judgement
            System.out.println("Warning:Book Author is empty");//print warning
        }
    }

    public int getBookPrice() {
        return bookPrice;
    }// aquire getter

    public void setBookPrice(int bookPrice) {// [New] Automated Check: Book price cannot be negative
        if (bookPrice >= 0) { //judgement
            this.bookPrice = bookPrice;//set book price
        } else {// judgement
            System.out.println("Warning:Book quantity cannot be negative");//print warning
        }
    }

}
