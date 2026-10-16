package edu.su.john.lms.model;

import java.util.Scanner;

public class Book {
    private int id;
    private String title; 
    private String author; 
    private String category;
    private Double price;
    private int totalCopies;
    private int availableCopies;
    private Scanner scan;
        
    static int nextId = 1001;

    public Book() {
        this.id = nextId++;
    }
  
    public Book(int id, String title, String author, String category, Double price, int totalCopies,
            int availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public Book(String title, String author, String category, Double price, int totalCopies, int availableCopies) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    public int getAvailableCopies() {
        return availableCopies;
    }
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    @Override
    public String toString() {
        return String.format("%-10d%-30s%-20s%-15s%-15.2f%-20d%-20d", 
        this.id,this.title,this.author,this.category,this.price,this.totalCopies,this.availableCopies);
    }
    public static void printHeading(){
        System.out.printf("%-10s%-30s%-20s%-15s%-15s%-20s%-20s\n","Id","Title","Author","Category","Price","Total Copies","Available Copies");
    }

    public void scanBook(){
        scan = new Scanner(System.in);
        System.out.println("Enter Title:");
        this.title = scan.nextLine();
        System.out.println("Enter Author:");
        this.author = scan.nextLine();
        System.out.println("Enter Category:");
        this.category = scan.nextLine();
        System.out.println("Enter Price:");
        this.price = scan.nextDouble();scan.nextLine();
        System.out.println("Enter Total Copies:");
        this.totalCopies = scan.nextInt();scan.nextLine();
        System.out.println("Enter Available Copies:");
        this.availableCopies = scan.nextInt();scan.nextLine();
    }

    public void returnCopy(){
        if(this.availableCopies >= this.totalCopies)
            System.out.println("All Books are already available!");
        else
            this.availableCopies++;
    }
    
}

