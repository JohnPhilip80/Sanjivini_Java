package edu.su.john.lms.model;

import java.util.Scanner;

public class Student {
    private int id; 
    private String name; 
    private String email;
    private Scanner scan;

    static int nextId = 2001;

    public Student() {
        this.id = nextId++;
    }
    
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Student(String name, String email) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%-10d%-30s%-30s", 
        this.id,this.name,this.email);
    }
    public static void printHeading(){
        System.out.printf("%-10s%-30s%-30s\n","Id","Name","Email");
    }    
    public void scanStudent(){
        scan = new Scanner(System.in);
        System.out.println("Enter Name:");
        this.name = scan.nextLine();
        System.out.println("Enter Email:");
        this.email = scan.nextLine();
    }   
}