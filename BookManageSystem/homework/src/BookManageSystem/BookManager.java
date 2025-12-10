package BookManageSystem;

public class BookManager {// Defines a public class named BookManager to manage books in the library system
    Book[] books = new Book[1000];// An array to store up to 1000 books
    BookNum[] bookNumbers = new BookNum[1000];// An array to store the number of books in each book

    public void init() {// Initializes the library with two books
        Book b1 = new Book();// Creates a new Book object
        BookNum n1 = new BookNum();// Creates a new BookNum object
        b1.bookname = "Ateler'sCorner";// Sets the name of the book
        b1.bookAuthor = "JuanLi";// Sets the author of the book
        b1.bookPrice = 45;// Sets the price of the book
        n1.bookTotalNum = 1;// Sets the total number of books

        Book b2 = new Book();// Creates a new Book object
        BookNum n2 = new BookNum();// Creates a new BookNum object
        b2.bookname = "TheOrdinaryWorld";// Sets the name of the book
        b2.bookAuthor = "YaoLu";// Sets the author of the book
        b2.bookPrice = 55;// Sets the price of the book
        n2.bookTotalNum = 1;// Sets the total number of books

        books[0] = b1;// Adds the first book to the library
        books[1] = b2;// Adds the second book to the library
        bookNumbers[0] = n1;// Adds the first book's number of copies to the library
        bookNumbers[1] = n2;// Adds the second book's number of copies to the library
    }

    public void list() {// Displays the list of books in the library
        System.out.println("This is the title of the book list");// Displays the title of the book list
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");// Displays the title of the book list
        for (int i = 0; i <= books.length - 1; i++) {// Loops through each book in the library
            if (books[i] != null && bookNumbers[i] != null) {// Checks if the book is not null
                System.out.println(books[i].bookname + "\t\t" + books[i].bookAuthor + "\t\t"
                        + books[i].bookPrice + "\t\t" + bookNumbers[i].bookTotalNum);// Displays the details of the book
            }
        }
    }

    public void addBook(Book book, BookNum bookNum) {//Referencing function.
        boolean flag=true;//set the flag
        for (int i=0; i < books.length - 1; i++) {//the cycle index
            if(books[i]!=null){//judge whether the book is not null
                if (books[i].bookAuthor.equals(book.bookAuthor) && books[i].bookname.equals(book.bookname)
                        && books[i].bookPrice == book.bookPrice) {//judgement condition
                    bookNumbers[i] = bookNum;//write out the conclusion
                    flag = false;//the result is judged as false
                    break;//interrupt the routine
                }
            }
        }
        if (flag){//if the flag is false,break
            for (int j = 0; j < books.length - 1; j++) {//the cycle index
                if (books[j] == null) {//judge whether the books is null
                    books[j] = book;//turn the books into book
                    bookNumbers[j]= bookNum;//turn the bookNumbers into bookNum
                    break;//interrupt the routine
                }
            }
        }
    }
    public void bookSeekName(Book book){//Referencing function
        boolean flag=true;//set the flag
        for (int i=0; i < books.length - 1; i++) {//the cycle index
            if(books[i]!=null){//judge whether the books is not null
                if (books[i].bookname.equals(book.bookname)) {//judge whether the bookname is equal to the right bookname
                    System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");//print out the data
                    System.out.println(books[i].bookname + "\t\t" + books[i].bookAuthor + "\t\t"
                            + books[i].bookPrice + "\t\t" + bookNumbers[i].bookTotalNum);//print out the book data
                    flag = false;//turn the flag into false
                    break;//interrupt the routine
                }
            }
        }
        if (flag){//judgement
            for (int j = 0; j < books.length - 1; j++) {//the cycle index
                if (books[j] == null) {//judge whether the books is null
                    System.out.println("null");//print out null
                    break;//interrupt the routine
                }
            }
        }
    }
    public void bookSeekAuthor(Book book){//Referencing function
        boolean flag=true;//set the flag
        for (int i=0; i < books.length - 1; i++) {//the cycle index
            if(books[i]!=null){//judge whether the books is not null
                if (books[i].bookAuthor.equals(book.bookAuthor)) {//judgement
                    System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");//print out the data
                    System.out.println(books[i].bookname + "\t\t" + books[i].bookAuthor + "\t\t"
                            + books[i].bookPrice + "\t\t" + bookNumbers[i].bookTotalNum);//print out the book data
                    flag = false;//turn the flag into false
                    break;//interrupt the routine
                }
            }
        }
        if (flag){//judgement
            for (int j = 0; j < books.length - 1; j++) {//the cycle index
                if (books[j] == null) {//judge whether the books is null
                    System.out.println("null");//print out null
                    break;//interrupt the routine
                }
            }
        }
    }
}