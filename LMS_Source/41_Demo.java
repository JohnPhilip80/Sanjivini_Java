package edu.su.john.lms;

import java.util.HashSet;
import java.util.Set;

import edu.su.john.lms.model.Book;

public class Demo {
    public static void main(String[] args){

        Set<Book> books = new HashSet<>();

        books.add(new Book("Effective Java","Joshua Bloch","Programming",45000.00,40,40));
        books.add(new Book("Clean Code","Robert C. Martin","Programming",25000.00,30,30));
        books.add(new Book("Database System Concepts","Silberschatz","Database",45000.00,40,40));
        books.add(new Book("C Programming","Balagurusamy", "Programming",4200.00,17,17));
        books.add(new Book("Network Essentials","Yeshwant Kanitkar", "Networking",8900.00,27,27));

        for(Book bk:books){
            if(bk.getId() == 1003){
                bk.setPrice(3900.00);
                break;
            }
        }

        Book delBook = null;
        for(Book bk:books){
            if(bk.getId() == 1004){
                delBook = bk;
                break;
            }
        }
        if (delBook != null)
            books.remove(delBook);

        Book.printHeading();
        for(Book bk:books){
            System.out.println(bk);
        }
    }
}
