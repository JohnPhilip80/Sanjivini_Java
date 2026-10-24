package edu.su.john.lms;

import edu.su.john.lms.model.Book;

public class Demo {
    public static void main(String[] args){
        Book bk1 = new Book();
        Book bk2 = new Book();
        Book bk3 = new Book();

        bk1.id = 1001;
        bk1.title = "Effective Java";
        bk1.author = "Joshua Bloch";
        bk1.category = "Programming";
        bk1.price = 4500.00;
        bk1.totalCopies = 40;
        bk1.availableCopies = 40;

        bk2.id = 1002;
        bk2.title = "Clean Code";
        bk2.author = "Robert C. Martin";
        bk2.category = "Programming";
        bk2.price = 2500.00;
        bk2.totalCopies = 30;
        bk2.availableCopies = 30;

        bk3.id = 1003;
        bk3.title = "Database System Concepts";
        bk3.author = "Silberschatz";
        bk3.category = "Database";
        bk3.price = 7800.00;
        bk3.totalCopies = 15;
        bk3.availableCopies = 15;

        System.out.println("Id --- Title --- Author --- Category --- Price --- Total Copies --- Available Copies");
        System.out.println(bk1.id + " --- " + bk1.title + " --- " + bk1.author + " --- " + bk1.category + " --- " + bk1.price + " --- " + bk1.totalCopies + " --- " + bk1.availableCopies);
        System.out.println(bk2.id + " --- " + bk2.title + " --- " + bk2.author + " --- " + bk2.category + " --- " + bk2.price + " --- " + bk2.totalCopies + " --- " + bk2.availableCopies);
        System.out.println(bk3.id + " --- " + bk3.title + " --- " + bk3.author + " --- " + bk3.category + " --- " + bk3.price + " --- " + bk3.totalCopies + " --- " + bk3.availableCopies);
    }
}
