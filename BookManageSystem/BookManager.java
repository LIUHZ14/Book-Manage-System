package BookManageSystem;

public class BookManager {
    Book[] books=new Book[50];
    public void init(){
        Book b1=new Book();
        b1.bookname="阿泰勒的角落";
        b1.bookauthor="李娟";
        b1.bookprice=45;

        Book b2=new Book();
        b2.bookname="平凡的世界";
        b2.bookauthor="路遥";
        b2.bookprice=55;

        books[0]=b1;
        books[1]=b2;
    }
    public void list(){
        System.out.println("这里是图书列表");
        System.out.println("书名\t\t\t\t作者\t\t价格");
        for(int i=0;i<=books.length-1;i++){
            if (books[i]!=null){
                System.out.println(books[i].bookname+"\t\t"+books[i].bookauthor+"\t\t"+books[i].bookprice);
            }
        }
    }
    public void addBook(Book book){
        for(int i=0;i<books.length-1;i++){
            if (books[i]==null){
                books[i]=book;
                break;
            }
        }
    }

}
