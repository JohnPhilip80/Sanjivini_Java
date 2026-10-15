package edu.su.john.lms.model;

import java.time.LocalDate;

public class Loan {
    private int id;
    private Book book;
    private Student student;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    static int nextId = 3001;

    public Loan(Book book, Student student, LocalDate issueDate, int loanDays) {
        this.id = nextId++;
        this.book = book;
        this.student = student;
        this.issueDate = issueDate;
        this.dueDate = issueDate.plusDays(loanDays);
        this.returnDate = null;
    }
    public int getId() {
        return id;
    }
    public Book getBook() {
        return book;
    }
    public Student getStudent() {
        return student;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public boolean isReturned(){
        return returnDate !=null;
    }
    @Override
    public String toString() {
        return String.format("%-10d%-30s%-30s%-20s%-20s%-15s\n", 
        this.id,this.book.getTitle(),this.student.getName(),this.issueDate,this.dueDate,this.isReutrned() ? "RETURNED": "ISSUED");
    }
    public static void printHeading(){
        System.out.printf("%-10s%-30s%-30s%-20s%-20s%-15s\n","Loan Id","Book","Student","Issued Date","Due Date","Status");
    }
}
