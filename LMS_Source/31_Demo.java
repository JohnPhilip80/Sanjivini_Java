package edu.su.john.lms;

import edu.su.john.lms.model.Book;

public class Demo {
    public static void main(String[] args){
        Book bk1 = new Book();
        Book bk2 = new Book();
        Book bk3 = new Book();

        bk1.setId(1001);
        bk1.setTitle("Effective Java");
        bk1.setAuthor("Joshua Bloch");
        bk1.setCategory("Programming");
        bk1.setPrice(4500.00);
        bk1.setTotalCopies(40);
        bk1.setAvailableCopies(40);

        bk2.setId(1002);
        bk2.setTitle("Clean Code");
        bk2.setAuthor("Robert C. Martin");
        bk2.setCategory("Programming");
        bk2.setPrice(2500.00);
        bk2.setTotalCopies(30);
        bk2.setAvailableCopies(30);

        bk3.setId(1003);
        bk3.setTitle("Database System Concepts");
        bk3.setAuthor("Silberschatz");
        bk3.setCategory("Database");
        bk3.setPrice(7800.00);
        bk3.setTotalCopies(15);
        bk3.setAvailableCopies(15);

        System.out.printf("%-10s%-30s%-20s%-15s%-15s%-20s%-20s","Id","Title","Author","Category","Price","Total Copies","Available Copies");
        System.out.printf("\n%-10d%-30s%-20s%-15s%-15.2f%-20d%-20d",bk1.getId(), bk1.getTitle(), bk1.getAuthor(), bk1.getCategory(), bk1.getPrice(), bk1.getTotalCopies(), bk1.getAvailableCopies());
        System.out.printf("\n%-10d%-30s%-20s%-15s%-15.2f%-20d%-20d",bk2.getId(), bk2.getTitle(), bk2.getAuthor(), bk2.getCategory(), bk2.getPrice(), bk2.getTotalCopies(), bk2.getAvailableCopies());
        System.out.printf("\n%-10d%-30s%-20s%-15s%-15.2f%-20d%-20d",bk3.getId(), bk3.getTitle(), bk3.getAuthor(), bk3.getCategory(), bk3.getPrice(), bk3.getTotalCopies(), bk3.getAvailableCopies());
    }
}
