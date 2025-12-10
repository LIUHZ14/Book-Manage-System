package BookManageSystem;

public class Book {
    private String bookname;      // 改为私有变量
    private String bookAuthor;    // 改为私有变量
    private int bookPrice;        // 改为私有变量

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        if (bookname != null && !bookname.trim().isEmpty()) {  // [新增] 自动化检测：书名不能为空
            this.bookname = bookname;
        } else {
            System.out.println("Warning:Book title is empty");
        }
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        if (bookAuthor != null && !bookAuthor.trim().isEmpty()) {  // [新增] 自动化检测：作者不能为空
            this.bookAuthor = bookAuthor;
        } else {
            System.out.println("Warning:Book Author is empty");
        }
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        if (bookPrice >= 0) {  // [新增] 自动化检测：价格不能为负数
            this.bookPrice = bookPrice;
        } else {
            System.out.println("Warning:Book quantity cannot be negative");
        }
    }
}