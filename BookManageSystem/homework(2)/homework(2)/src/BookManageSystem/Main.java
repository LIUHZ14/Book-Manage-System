package BookManageSystem;

import java.util.Scanner;

                public class Main {
                    public static void menu() {// menu
                        System.out.println("Welcome to the Library Management System");//print welcome
                        System.out.println("1、the list of books(图书列表)");//print menu
                        System.out.println("2、add book(添加图书)");//print book addition
                        System.out.println("3、book name query(按书名查询)");//print book query
                        System.out.println("4、author query(按作者查询)");//print author query
                        System.out.println("5、fuzzy search(模糊搜索)");//print fuzzy search
                        System.out.println("0、close the System");//print close
                        System.out.println("please input the operation that the user want to do");//print input
                    }

                    public static void main(String[] args) {// main
                        BookManager manager = new BookManager();// create BookManager object
                        manager.init();// initialize
                        menu();// print menu
                        Scanner input = new Scanner(System.in);// create Scanner object
                        int num =getInt(input);// input

                        while(num != 0) {// while loop
                            if (num == 1) {// if num == 1
                                manager.list();// print book list
                                menu();//menu method
                                num = input.nextInt();// input
                            } else if (num == 2) {// if num == 2
                                System.out.println("This is the Book Addition Interface");//print book addition
                                Book book = new Book();// create Book object
                                BookNum bookNum = new BookNum();// create BookNum object

                                System.out.println("input book name");//print book name
                                String name = input.next();// input
                                book.setBookname(name);// set book name

                                System.out.println("input book author");//print book author
                                String author = input.next();// input
                                book.setBookAuthor(author);// set book author

                                System.out.println("input book price");//print book price
                                int price = input.nextInt();// input
                                book.setBookPrice(price);// set book price

                                System.out.println("input book total number");//print book total number
                                int totalNum = input.nextInt();// input
                                bookNum.setBookTotalNum(totalNum);// set book total number

                                manager.addBook(book, bookNum);// add book
                                menu();// menu method
                                num =getInt(input);// input

                            } else if (num == 3) {// if num == 3
                                Book book = new Book();// create Book object
                                System.out.println("input book name");//print book name
                                String name = input.next();// input
                                book.setBookname(name);// set book name
                                manager.bookSeekName(book);// book seek name
                                menu();// menu method
                                num =getInt(input);// input

                            } else if (num == 4) {// if num == 4
                                Book book = new Book();// create Book object
                                System.out.println("input author name");//print author name
                                String author = input.next();// input
                                book.setBookAuthor(author);// set book author
                                manager.bookSeekAuthor(book);// book seek author
                                menu();// menu method
                                num =getInt(input);// input

                            } else if (num == 5) {// if num == 5
                                System.out.println("1、Fuzzy search by book name");//print fuzzy search
                                System.out.println("2、Fuzzy search by author name");//print fuzzy search
                                System.out.println("Please choose search type:");//print search type
                                int searchType = input.nextInt();// input

                                if (searchType == 1) {// if searchType == 1
                                    System.out.println("input keyword for book name:");//print keyword
                                    String keyword = input.next();// input
                                    manager.fuzzySearchByName(keyword);// fuzzy search by name
                                } else if (searchType == 2) {// if searchType == 2
                                    System.out.println("input keyword for author name:");//print keyword
                                    String keyword = input.next();// input
                                    manager.fuzzySearchByAuthor(keyword);// fuzzy search by author
                                } else {//judgement
                                    System.out.println("Invalid choice!");//print invalid choice
                                }

                                menu();// menu method
                                num =getInt(input);// input

                            } else {// judgement
                                System.out.println("Invalid option! Please try again.");//print invalid option
                                menu();// menu method
                                num =getInt(input);// input
                            }
                        }
                    }
                    private static int getInt(Scanner input) {
                        while (true) {
                            try {
                                return input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input! Please enter a valid integer.");
                                input.nextLine();
                                menu();
                            }
                        }
                    }
                }