package edu.su.john.lms;

import edu.su.john.lms.model.Book;

public class Demo {
    public static void main(String[] args){
        Book bk1 = new Book(1001,"Effective Java","Joshua Bloch","Programming",45000.00,40,40);
        Book bk2 = new Book(1002,"Clean Code","Robert C. Martin","Programming",25000.00,30,30);
        Book bk3 = new Book(1003,"Database System Concepts","Silberschatz","Database",45000.00,40,40);
        Book bk4 = new Book();

        bk4.scanBook();

        Book.printHeading();
        System.out.println(bk1);
        System.out.println(bk2);
        System.out.println(bk3);
        System.out.println(bk4);
    }
}
