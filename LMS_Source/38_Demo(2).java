package edu.su.john.lms;

import edu.su.john.lms.model.Book;

public class Demo {
    public static void main(String[] args){

        Book[] books = new Book[5];

        books[0] = new Book("Effective Java","Joshua Bloch","Programming",45000.00,40,40);
        books[1] = new Book("Clean Code","Robert C. Martin","Programming",25000.00,30,30);
        books[2] = new Book("Database System Concepts","Silberschatz","Database",45000.00,40,40);
        books[3] = new Book("C Programming","Balagurusamy", "Programming",4200.00,17,17);
        books[4] = new Book("Network Essentials","Yeshwant Kanitkar", "Networking",8900.00,27,27);

        books[3].setPrice(3900.00);
        //books[4].scanBook();

        Book.printHeading();
        
        /*for(int i=0;i<books.length;i++){
            System.out.println(books[i]);
        }*/
        
        for(Book bk:books){
            System.out.println(bk);
        }
    }
}
