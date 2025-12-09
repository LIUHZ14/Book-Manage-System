package BookManageSystem;

import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("欢迎来到图书管理系统");
        System.out.println("1、图书列表");
        System.out.println("2、添加图书");
        System.out.println("请输入对应操作");
    }
    static void main(String[] args) {
        BookManager manager=new BookManager();
        manager.init();
        menu();
        Scanner input=new Scanner(System.in);
        int num= input.nextInt();
        while(num!=0) {
            if (num == 1) {
                manager.list();
                menu();
                num = input.nextInt();
            } else if (num == 2) {
                System.out.println("这里是添加图书界面");
                Book b1=new Book();
                System.out.println("请输入图书名称");
                b1.bookname=input.next();
                System.out.println("请输入图书作者");
                b1.bookauthor= input.next();
                System.out.println("请输入图书价格");
                b1.bookprice= input.nextInt();
                manager.addBook(b1);
                menu();
                num=input.nextInt();
            }
        }
    }
}
