package BookManageSystem;

import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("欢迎来到图书管理系统");//welcome words(欢迎语)
        System.out.println("1、图书列表");
        System.out.println("2、添加图书");
        System.out.println("请输入对应操作");
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
                System.out.println("这里是添加图书界面");
                Book b1=new Book();// input book name
                System.out.println("请输入图书名称");
                b1.bookname=input.next();// input book author
                System.out.println("请输入图书作者");
                b1.bookauthor= input.next();// input book author
                System.out.println("请输入图书价格");
                b1.bookprice= input.nextInt();// input book price
                manager.addBook(b1);//add book
                menu();// show menu(显示菜单)
                num=input.nextInt();// get the next operation
            }
        }
    }
}
