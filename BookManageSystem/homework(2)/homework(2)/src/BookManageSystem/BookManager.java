package BookManageSystem;

public class BookManager {//book manage system
    private Book[] books = new Book[1000];//book list
    private BookNum[] bookNumbers = new BookNum[1000];//book number
    private int bookCount = 0;//book count

    public void init() {//init book list
        Book b1 = new Book();//book1
        BookNum n1 = new BookNum();//book number
        b1.setBookname("Ateler's Corner");//book name
        b1.setBookAuthor("Juan Li");//book author
        b1.setBookPrice(45);//book price
        n1.setBookTotalNum(1);//book number

        Book b2 = new Book();//book2
        BookNum n2 = new BookNum();//book number
        b2.setBookname("The Ordinary World");//book name
        b2.setBookAuthor("Yao Lu");//book author
        b2.setBookPrice(55);//book price
        n2.setBookTotalNum(1);//book number

        books[0] = b1;//add book
        books[1] = b2;//add book
        bookNumbers[0] = n1;//add book number
        bookNumbers[1] = n2;//add book number
        bookCount = 2;//book count
    }

    public void list() {//book list
        System.out.println("This is the title of the book list");//print book list title
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");//print book list title

        for (int i = 0; i < bookCount; i++) {//book list
            if (books[i] != null && bookNumbers[i] != null) {//judgement
                System.out.println(books[i].getBookname() + "\t\t" +
                        books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" +
                        bookNumbers[i].getBookTotalNum());//book list
            }
        }
    }

    public void addBook(Book book, BookNum bookNum) {//add book
        if (bookCount >= books.length) {//book quantity cannot be negative
            System.out.println("Warning:Book quantity cannot be negative");//print warning
            return;// return
        }

        boolean flag = true;// flag
        for (int i = 0; i < bookCount; i++) {//cycle
            if (books[i] != null) {// judgement
                if (books[i].getBookAuthor().equals(book.getBookAuthor()) &&
                        books[i].getBookname().equals(book.getBookname()) &&
                        books[i].getBookPrice() == book.getBookPrice()) {// judgement

                    int currentNum = bookNumbers[i].getBookTotalNum();// get book number
                    int newNum = bookNum.getBookTotalNum();// get book number
                    bookNumbers[i].setBookTotalNum(currentNum + newNum);// add book number
                    System.out.println("The same book already exists,and the inventory");//print warning
                    flag = false;// flag
                    break;// break
                }
            }
        }

        if (flag) {// judgement
            books[bookCount] = book;// add book
            bookNumbers[bookCount] = bookNum;// add book number
            bookCount++;// book count
            System.out.println("Book added successfully!");//print success
        }
    }

    public void bookSeekName(Book book) {//book seek by name
        boolean flag = false;// flag
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");//print book list title

        for (int i = 0; i < bookCount; i++) {// cycle
            if (books[i] != null &&// judgement
                    books[i].getBookname().equalsIgnoreCase(book.getBookname())) {// judgement

                System.out.println(books[i].getBookname() + "\t\t" + books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" + bookNumbers[i].getBookTotalNum());//print book list
                flag = true;// flag
            }
        }

        if (!flag) {// judgement
            System.out.println("No relevant books found!");//print warning
        }
    }

    public void bookSeekAuthor(Book book) {//book seek by author
        boolean flag = false;// flag
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");//print book list title

        for (int i = 0; i < bookCount; i++) {// cycle
            if (books[i] != null &&// judgement
                    books[i].getBookAuthor().equalsIgnoreCase(book.getBookAuthor())) {// judgement

                System.out.println(books[i].getBookname() + "\t\t" + books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" + bookNumbers[i].getBookTotalNum());//print book list
                flag = true;// flag
            }
        }

        if (!flag) {// judgement
            System.out.println("No relevant authors found");//print warning
        }
    }

    public void fuzzySearchByName(String keyword) {//book seek by name
        System.out.println("Fuzzy search results sorted by book title");//print book list title
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");//print book list title
        boolean flag = false;// flag

        for (int i = 0; i < bookCount; i++) {// cycle
            if (books[i] != null &&// judgement
                    books[i].getBookname().toLowerCase().trim().contains(keyword.toLowerCase().trim())) {// judgement
                System.out.println(books[i].getBookname() + "\t\t" + books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" + bookNumbers[i].getBookTotalNum());//print book list
                flag = true;// flag
            }
        }

        if (!flag) {// judgement
            System.out.println("No books containing \"" + keyword + "\" were found");//print warning
        }
    }

    public void fuzzySearchByAuthor(String keyword) {//book seek by author
        System.out.println("Fuzzy search results sorted by book author");//print book list title
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");//print book list title
        boolean flag = false;// flag

        for (int i = 0; i < bookCount; i++) {// cycle
            if (books[i] != null &&books[i].getBookAuthor().toLowerCase().trim().contains(keyword.toLowerCase().trim())) {// judgement

                System.out.println(books[i].getBookname() + "\t\t" + books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" + bookNumbers[i].getBookTotalNum());//print book list
                flag = true;// flag
            }
        }

        if (!flag) {// judgement
            System.out.println("No author containing \"" + keyword + "\" were found");//print warning
        }
    }

    public int getBookCount() {
        return bookCount;
    }// get book count
}