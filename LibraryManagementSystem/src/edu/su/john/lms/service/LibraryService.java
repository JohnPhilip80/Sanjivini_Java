package edu.su.john.lms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.su.john.lms.model.Book;
import edu.su.john.lms.model.Loan;
import edu.su.john.lms.model.Student;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

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
        Book.printHeading();
        for(Book bk:books){
            System.out.println(bk);
        }
    }
    public void addBook(){
        Book bk = new Book();
        bk.scanBook();
        books.add(bk);
    }
    public void searchBooks(){
        List<Book> resultBooks = new ArrayList<>();
        System.out.println("Enter Title or Author or Category to search");
        String key = scan.nextLine();
        for(Book bk:books){
            if(bk.getTitle().contains(key) || bk.getAuthor().contains(key) || bk.getCategory().contains(key)){
                resultBooks.add(bk);
            }
        }
        if(resultBooks.size() == 0){
            System.out.println("No books found!");
        }
        else{
            Book.printHeading();
            for(Book bk:resultBooks)
                System.out.println(bk);
        }
    }
    public void displayStudents(){
        Student.printHeading();
        for(Student st:students){
            System.out.println(st);
        }
    }
    public void registerStudent(){
        Student st = new Student();
        st.scanStudent();
        students.add(st);
    }
    public Book findBookById(int bookId){
        for(Book bk:books){
            if(bk.getId() == bookId){
                return bk;
            }
        }
        return null;
    }
    public Student findStudentById(int studentId){
        for(Student st:students){
            if(st.getId() == studentId){
                return st;
            }
        }
        return null;
    }
    public void issueBook(){
        System.out.println("\n--- ISSUE BOOK ---");
        displayBooks();
        System.out.println("Enter Book ID:");
        int bookId = scan.nextInt();scan.nextLine();
        displayStudents();
        System.out.println("Enter Student ID:");
        int studentId = scan.nextInt();scan.nextLine();

        Book bk = findBookById(bookId);
        Student st = findStudentById(studentId);
        if(bk == null)
        {
            System.out.println("Book Not Found");
            return;
        }
        if(st == null)
        {
            System.out.println("Student Not Found");
            return;
        }
        if(bk.getAvailableCopies() <=0){
            System.out.println("No Copies Available for this book!");
            return;
        }
        LocalDate today = LocalDate.now();
        Loan loan = new Loan(bk, st, today, 14);
        bk.setAvailableCopies(bk.getAvailableCopies() - 1);
        loans.add(loan);
        System.out.println("Book Issued Successfully!");
    }
    public void returnBook(){
        System.out.println("\n--- RETURN BOOK ---");
        displayActiveLoans();
        System.out.println("Enter Loan ID:");
        int loanID = scan.nextInt();scan.nextLine();
        
        System.out.println("Book returned successfully.");
    }
    public void displayActiveLoans(){
        System.out.println("Method will display all Active Loans");
    }
    public void displayOverdueLoans(){
        System.out.println("Method will Display All Overdue Loans");
    }
}
