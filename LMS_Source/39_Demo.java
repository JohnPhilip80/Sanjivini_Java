package edu.su.john.lms;

import java.util.ArrayList;
import java.util.List;
import edu.su.john.lms.model.Book;

public class Demo {
    public static void main(String[] args){

        List<Book> books = new ArrayList<>();

        books.add(new Book("Effective Java","Joshua Bloch","Programming",45000.00,40,40));
        books.add(new Book("Clean Code","Robert C. Martin","Programming",25000.00,30,30));
        books.add(new Book("Database System Concepts","Silberschatz","Database",45000.00,40,40));
        books.add(new Book("C Programming","Balagurusamy", "Programming",4200.00,17,17));
        books.add(new Book("Network Essentials","Yeshwant Kanitkar", "Networking",8900.00,27,27));

        books.get(3).setPrice(3900.00);
        //books.get(4).scanBook();
        books.remove(2);

        Book.printHeading();
        
        for(Book bk:books){
            System.out.println(bk);
        }
    }
}
