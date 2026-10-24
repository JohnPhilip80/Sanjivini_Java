package edu.su.john.lms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.su.john.lms.model.Book;

public class Demo {
    public static void main(String[] args){

        Map<Integer,Book> books = new HashMap<>();

        books.put(1,new Book("Effective Java","Joshua Bloch","Programming",45000.00,40,40));
        books.put(2,new Book("Clean Code","Robert C. Martin","Programming",25000.00,30,30));
        books.put(3,new Book("Database System Concepts","Silberschatz","Database",45000.00,40,40));
        books.put(4,new Book("C Programming","Balagurusamy", "Programming",4200.00,17,17));
        books.put(5,new Book("Network Essentials","Yeshwant Kanitkar", "Networking",8900.00,27,27));

        books.get(3).setPrice(3900.00);
        //books.get(4).scanBook();
        books.remove(2);

        Book.printHeading();
        
        for(Book bk:books.values()){
            System.out.println(bk);
        }
    }
}
