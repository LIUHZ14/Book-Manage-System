package BookManageSystem;

import java.util.Scanner;

                public class Main {
                    public static void menu() {
                        System.out.println("Welcome to the Library Management System");
                        System.out.println("1、the list of books(图书列表)");
                        System.out.println("2、add book(添加图书)");
                        System.out.println("3、book name query(按书名查询)");
                        System.out.println("4、author query(按作者查询)");
                        System.out.println("5、fuzzy search(模糊搜索)");
                        System.out.println("0、close the System");
                        System.out.println("please input the operation that the user want to do");
                    }

                    public static void main(String[] args) {
                        BookManager manager = new BookManager();
                        manager.init();
                        menu();
                        Scanner input = new Scanner(System.in);
                        int num = input.nextInt();

                        while(num != 0) {
                            if (num == 1) {
                                manager.list();
                                menu();
                                num = input.nextInt();
                            } else if (num == 2) {
                                System.out.println("This is the Book Addition Interface");
                                Book book = new Book();
                                BookNum bookNum = new BookNum();

                                System.out.println("input book name");
                                String name = input.next();
                                book.setBookname(name);

                                System.out.println("input book author");
                                String author = input.next();
                                book.setBookAuthor(author);

                                System.out.println("input book price");
                                int price = input.nextInt();
                                book.setBookPrice(price);

                                System.out.println("input book total number");
                                int totalNum = input.nextInt();
                                bookNum.setBookTotalNum(totalNum);

                                manager.addBook(book, bookNum);
                                menu();
                                num = input.nextInt();

                            } else if (num == 3) {
                                Book book = new Book();
                                System.out.println("input book name");
                                String name = input.next();
                                book.setBookname(name);
                                manager.bookSeekName(book);
                                menu();
                                num = input.nextInt();

                            } else if (num == 4) {
                                Book book = new Book();
                                System.out.println("input author name");
                                String author = input.next();
                                book.setBookAuthor(author);
                                manager.bookSeekAuthor(book);
                                menu();
                                num = input.nextInt();

                            } else if (num == 5) {
                                System.out.println("1、Fuzzy search by book name");
                                System.out.println("2、Fuzzy search by author name");
                                System.out.println("Please choose search type:");
                                int searchType = input.nextInt();

                                if (searchType == 1) {
                                    System.out.println("input keyword for book name:");
                                    String keyword = input.next();
                                    manager.fuzzySearchByName(keyword);
                                } else if (searchType == 2) {
                                    System.out.println("input keyword for author name:");
                                    String keyword = input.next();
                                    manager.fuzzySearchByAuthor(keyword);
                                } else {
                                    System.out.println("Invalid choice!");
                                }

                                menu();
                                num = input.nextInt();

                            } else {
                                System.out.println("Invalid option! Please try again.");
                                menu();
                                num = input.nextInt();
                            }
                        }
                    }
                }