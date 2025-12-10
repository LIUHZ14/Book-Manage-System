package BookManageSystem;

public class BookManager {// Defines a public class named BookManager to manage books in the library system
    Book[] books=new Book[50];// An array to store up to 50 books
    public void init(){// Initializes the library with two books
        Book b1=new Book();// Creates a new Book object
        b1.bookname="阿泰勒的角落";// Sets the name of the book
        b1.bookauthor="李娟";// Sets the author of the book
        b1.bookprice=45;// Sets the price of the book

        Book b2=new Book();// Creates a new Book object
        b2.bookname="平凡的世界";// Sets the name of the book
        b2.bookauthor="路遥";// Sets the author of the book
        b2.bookprice=55;// Sets the price of the book

        books[0]=b1;// Adds the first book to the library
        books[1]=b2;// Adds the second book to the library
    }
    public void list(){// Displays the list of books in the library
        System.out.println("这里是图书列表");// Displays the title of the book list
        System.out.println("书名\t\t\t\t作者\t\t价格");// Displays the title of the book list
        for(int i=0;i<=books.length-1;i++){// Loops through each book in the library
            if (books[i]!=null){// Checks if the book is not null
                System.out.println(books[i].bookname+"\t\t"+books[i].bookauthor+"\t\t"+books[i].bookprice);// Displays the details of the book
            }
        }
    }
    public void addBook(Book book){//Referencing function.
        for(int i=0;i<books.length-1;i++){
            if (books[i]==null){//the cycle index
                books[i]=book;
                break;
            }
        }
    }

}
