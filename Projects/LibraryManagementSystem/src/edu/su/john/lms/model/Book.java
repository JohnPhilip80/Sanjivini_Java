package edu.su.john.lms.model;

public class Book {
    private int id;
    private String title; 
    private String author; 
    private String category;
    private int totalCopies;
    private int availableCopies;
    
    public Book(int id, String title, String author,  String category, int totalCopies) {
        if (totalCopies <= 0) {
            throw new IllegalArgumentException("Total copies must be greater than zero.");
        }
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public int getAvailableCopies() {
        return availableCopies;
    }
    public void issueCopy() {
        if (availableCopies <= 0) {
            throw new IllegalStateException("No copies available.");
        }
        availableCopies--;
    }
    public void returnCopy() {
        if (availableCopies >= totalCopies) {
            throw new IllegalStateException("All copies are already available.");
        }
        availableCopies++;
    }
    @Override
    public String toString() {
        return String.format( "ID: %d | Title: %s | Author: %s | Category: %s | Available: %d/%d",
            id, title, author, category, availableCopies, totalCopies);
    }
   
}
