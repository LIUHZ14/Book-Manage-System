package BookManageSystem;

public class BookManager {
    private Book[] books = new Book[1000];
    private BookNum[] bookNumbers = new BookNum[1000];
    private int bookCount = 0;

    public void init() {
        Book b1 = new Book();
        BookNum n1 = new BookNum();
        b1.setBookname("Ateler'sCorner");
        b1.setBookAuthor("JuanLi");
        b1.setBookPrice(45);
        n1.setBookTotalNum(1);

        Book b2 = new Book();
        BookNum n2 = new BookNum();
        b2.setBookname("TheOrdinaryWorld");
        b2.setBookAuthor("YaoLu");
        b2.setBookPrice(55);
        n2.setBookTotalNum(1);

        books[0] = b1;
        books[1] = b2;
        bookNumbers[0] = n1;
        bookNumbers[1] = n2;
        bookCount = 2;
    }

    public void list() {
        System.out.println("This is the title of the book list");
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && bookNumbers[i] != null) {
                System.out.println(books[i].getBookname() + "\t\t" +
                        books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" +
                        bookNumbers[i].getBookTotalNum());
            }
        }
    }

    public void addBook(Book book, BookNum bookNum) {
        if (bookCount >= books.length) {
            System.out.println("Warning:Book quantity cannot be negative");
            return;
        }

        boolean flag = true;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null) {
                if (books[i].getBookAuthor().equals(book.getBookAuthor()) &&
                        books[i].getBookname().equals(book.getBookname()) &&
                        books[i].getBookPrice() == book.getBookPrice()) {

                    int currentNum = bookNumbers[i].getBookTotalNum();
                    int newNum = bookNum.getBookTotalNum();
                    bookNumbers[i].setBookTotalNum(currentNum + newNum);
                    System.out.println("The same book already exists,and the inventory");
                    flag = false;
                    break;
                }
            }
        }

        if (flag) {
            books[bookCount] = book;
            bookNumbers[bookCount] = bookNum;
            bookCount++;
            System.out.println("Book added successfully!");
        }
    }

    public void bookSeekName(Book book) {
        boolean flag = false;
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null &&
                    books[i].getBookname().equalsIgnoreCase(book.getBookname())) {

                System.out.println(books[i].getBookname() + "\t\t" + books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" + bookNumbers[i].getBookTotalNum());
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("No relevant books found!");
        }
    }

    public void bookSeekAuthor(Book book) {
        boolean flag = false;
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null &&
                    books[i].getBookAuthor().equalsIgnoreCase(book.getBookAuthor())) {

                System.out.println(books[i].getBookname() + "\t\t" + books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" + bookNumbers[i].getBookTotalNum());
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("No relevant authors found");
        }
    }

    public void fuzzySearchByName(String keyword) {
        System.out.println("Fuzzy search results sorted by book title");
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");
        boolean flag = false;

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null &&
                    books[i].getBookname().toLowerCase().contains(keyword.toLowerCase())) {

                System.out.println(books[i].getBookname() + "\t\t" + books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" + bookNumbers[i].getBookTotalNum());
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("No books containing \"" + keyword + "\" were found");
        }
    }

    public void fuzzySearchByAuthor(String keyword) {
        System.out.println("Fuzzy search results sorted by book author");
        System.out.println("name\t\t\t\tauthor\t\tprice\t\tremaining quantity");
        boolean flag = false;

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null &&
                    books[i].getBookAuthor().toLowerCase().contains(keyword.toLowerCase())) {

                System.out.println(books[i].getBookname() + "\t\t" + books[i].getBookAuthor() + "\t\t" +
                        books[i].getBookPrice() + "\t\t" + bookNumbers[i].getBookTotalNum());
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("No author containing \"" + keyword + "\" were found");
        }
    }

    public int getBookCount() {
        return bookCount;
    }
}