package BookManageSystem;

import java.util.Scanner;

 public class Main {
        public static void menu() {
            System.out.println("Welcome to the Library Management System");//welcome words(欢迎语)
            System.out.println("1、the list of books(图书列表)");
            System.out.println("2、add book(添加图书)");
            System.out.println("3、book name query(按书名查询)");
            System.out.println("4、author query(按作者查询)");
            System.out.println("0、close the System");
            System.out.println("please input the operation that the user want to do");
        }
        static void main(String[] args) {
            BookManager manager=new BookManager();// create a BookManager object(创建一个BookManager对象)
            manager.init();// initialize the book list(初始化图书列表)
            menu();// show menu(显示菜单)
            Scanner input=new Scanner(System.in);// create a Scanner object(创建一个Scanner对象)
            int num= input.nextInt();// get the next operation
            while(num!=0) {
                if (num == 1) {
                    manager.list();// list books(列出图书)
                    menu();// show menu(显示菜单)
                    num = input.nextInt();// get the next operation
                } else if (num == 2) {
                    System.out.println("This is the Book Addition Interface");
                    Book book = new Book();
                    BookNum bookNum = new BookNum();// create a Book object(创建一个Book对象)
                    System.out.println("input book name");
                    book.bookname = input.next();// input book name
                    System.out.println("input book author");
                    book.bookAuthor = input.next();// input book author
                    System.out.println("input book price");
                    book.bookPrice = input.nextInt();// input book price
                    System.out.println("input book total number");
                    bookNum.bookTotalNum = input.nextInt();// input book total number
                    manager.addBook(book, bookNum);//add book
                    menu();// show menu(显示菜单)
                    num = input.nextInt();// get the next operation
                }else if (num == 3){// if statement(判断)
                    Book book = new Book();// create a Book object(创建一个Book对象)
                    System.out.println("input book name");
                    book.bookname = input.next();// input book name
                    manager.bookSeekName(book);;// book name query(按书名查询)
                    menu();// show menu(显示菜单)
                    num = input.nextInt();// get the next operation
                }else if (num == 4){// if statement(判断)
                Book book = new Book();// create a Book object(创建一个Book对象)
                System.out.println("input author name");
                book.bookAuthor = input.next();// input author name
                manager.bookSeekAuthor(book);// author query(按作者查询)
                menu();// show menu(显示菜单)
                num = input.nextInt();
                }else {
                    menu();// show menu(显示菜单)
                    num=input.nextInt();// get the next operation
                }
            }
        }
 }
