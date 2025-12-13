package BookManageSystem;

public class BookNum {//the number of books
    private int bookTotalNum = 0;//set the number of books

    public int getBookTotalNum() {
        return bookTotalNum;
    }//get the number of books

    public void setBookTotalNum(int bookTotalNum) {//set the number of books
        if (bookTotalNum >= 0) {//book number cannot be negative
            this.bookTotalNum = bookTotalNum;//set book number
        } else {// judgement
            System.out.println("Warning:Book quantity cannot be negative");//print warning
        }
    }
}
