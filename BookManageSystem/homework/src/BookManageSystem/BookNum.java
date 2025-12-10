package BookManageSystem;

public class BookNum {
    private int bookTotalNum = 0;

    public int getBookTotalNum() {
        return bookTotalNum;
    }

    public void setBookTotalNum(int bookTotalNum) {
        if (bookTotalNum >= 0) {
            this.bookTotalNum = bookTotalNum;
        } else {
            System.out.println("Warning:Book quantity cannot be negative");
        }
    }
}
