package edu.su.john.lms;

import java.util.Scanner;

import edu.su.john.lms.service.LibraryService;

public class Demo {
    private static Scanner sc = new Scanner(System.in);
    private static LibraryService ls = new LibraryService();
    public static void main(){
        boolean running = true;
        System.out.println("Welcome To Java Programming!");
        System.out.println("==================================");
        System.out.println("SANJIVANI UNIVERSITY LIBRARY MANAGEMENT");
        System.out.println("==================================");
        while (running) {
            ls.showMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> ls.displayBooks();
                case 2 -> ls.addBook();
                case 3 -> ls.searchBook();
                case 4 -> ls.displayStudents();
                case 5 -> ls.registerStudent();
                case 6 -> ls.issueBook();
                case 7 -> ls.returnBook();
                case 8 -> ls.displayActiveLoans();
                case 9 -> ls.displayOverdueLoans();
                case 0 -> {
                    running = false;
                    System.out.println("Thank you. Goodbye!");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
        sc.close();
        System.out.println("Program Ends Here!");
    }
}
