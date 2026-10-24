package edu.su.john.lms.service;

import java.util.ArrayList;
import java.util.List;
import edu.su.john.lms.model.Book;
import edu.su.john.lms.model.Student;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public LibraryService() {
        books.add(new Book("Effective Java", "Joshua Bloch", "Programming",4200.00, 3,3));
        books.add(new Book("Clean Code", "Robert C. Martin", "Programming",5100.00, 2,2));
        books.add(new Book("Database System Concepts", "Silberschatz", "Database",3600.00, 4,4));

        students.add(new Student("Arun", "arun@college.edu"));
        students.add(new Student("Priya", "priya@college.edu"));
        students.add(new Student("Sakshi", "sakshi@college.edu"));
        students.add(new Student("Prashant", "prashant@college.edu"));
    }
    public void showMenu() {
        System.out.println("\n--------- MAIN MENU ---------");
        System.out.println("1. Display all books");
        System.out.println("2. Add a new book");
        System.out.println("3. Search books");
        System.out.println("4. Display all students");
        System.out.println("5. Register a student");
        System.out.println("6. Issue a book");
        System.out.println("7. Return a book");
        System.out.println("8. Display active loans");
        System.out.println("9. Display overdue loans");
        System.out.println("0. Exit");
    }

    public void displayBooks(){
        System.out.println("Method will display all the books");
    }
    public void addBook(){
        System.out.println("Method will add a new Book");
    }
    public void searchBook(){
        System.out.println("Method will search a book");
    }
    public void displayStudents(){
        System.out.println("Method will display all the students");
    }
    public void registerStudent(){
        System.out.println("Method will register a new student");
    }
    public void issueBook(){
        System.out.println("Method will issue a book to student");
    }
    public void returnBook(){
        System.out.println("Method will return the book");
    }
    public void displayActiveLoans(){
        System.out.println("Method will display all Active Loans");
    }
    public void displayOverdueLoans(){
        System.out.println("Method will Display All Overdue Loans");
    }
}
