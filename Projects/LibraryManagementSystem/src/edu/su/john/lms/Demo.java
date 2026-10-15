package edu.su.john.lms;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import edu.su.john.lms.model.Book;
import edu.su.john.lms.model.Loan;
import edu.su.john.lms.model.Member;
import edu.su.john.lms.service.LibraryService;

public class Demo {
    private static Scanner sc = new Scanner(System.in);
    private static LibraryService library = new LibraryService();
    public static void main(String[] args){
        boolean running = true;
        System.out.println("==================================");
        System.out.println("SANJIVANI UNIVERSITY LIBRARY MANAGEMENT");
        System.out.println("==================================");

        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> displayBooks();
                    case 2 -> addBook();
                    case 3 -> searchBooks();
                    case 4 -> displayMembers();
                    case 5 -> registerMember();
                    case 6 -> issueBook();
                    case 7 -> returnBook();
                    case 8 -> displayActiveLoans();
                    case 9 -> displayOverdueLoans();
                    case 0 -> {
                        running = false;
                        System.out.println("Thank you. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Unexpected error: " + ex.getMessage());
            }
            System.out.println();
        }
        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n--------- MAIN MENU ---------");
        System.out.println("1. Display all books");
        System.out.println("2. Add a new book");
        System.out.println("3. Search books");
        System.out.println("4. Display all members");
        System.out.println("5. Register a member");
        System.out.println("6. Issue a book");
        System.out.println("7. Return a book");
        System.out.println("8. Display active loans");
        System.out.println("9. Display overdue loans");
        System.out.println("0. Exit");
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Value cannot be empty.");
        }
    }

    // Consumer + method reference
    private static <T> void displayList(
        List<T> items, String emptyMessage) {
        if (items.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        Consumer<T> printer = System.out::println;
        items.forEach(printer);
    }

    private static void displayBooks() {
        System.out.println("\n--- BOOK CATALOGUE ---");
        displayList(library.getBooksSortedByTitle(),"No books available.");
    }

    private static void addBook() {
        System.out.println("\n--- ADD BOOK ---");
        String title = readText("Book title: ");
        String author = readText("Author: ");
        String category = readText("Category: ");
        int copies = readInt("Number of copies: ");
        Book book = library.addBook(title, author, category, copies);
        System.out.println("Book added successfully.");
        System.out.println(book);
    }

    private static void searchBooks() {
        System.out.println("\n--- SEARCH BOOKS ---");
        String keyword = readText("Enter title, author or category: ");
        displayList(library.searchBooks(keyword),"No matching books found.");
    }

    private static void displayMembers() {
        System.out.println("\n--- REGISTERED MEMBERS ---");
        displayList(library.getAllMembers(), "No members registered.");
    }

    private static void registerMember() {
        System.out.println("\n--- REGISTER MEMBER ---");
        String name = readText("Member name: ");
        String email = readText("Email address: ");
        Member member = library.registerMember(name, email);
        System.out.println("Member registered successfully.");
        System.out.println(member);
    }

    private static void issueBook() {
        System.out.println("\n--- ISSUE BOOK ---");
        displayBooks();
        int bookId = readInt("Enter book ID: ");
        displayMembers();
        int memberId = readInt("Enter member ID: ");
        Loan loan = library.issueBook(bookId, memberId);
        System.out.println("Book issued successfully.");
        System.out.println(loan);
    }

    private static void returnBook() {
        System.out.println("\n--- RETURN BOOK ---");
        displayActiveLoans();
        int loanId = readInt("Enter loan ID: ");
        Loan loan = library.returnBook(loanId);
        System.out.println("Book returned successfully.");
        System.out.println(loan);
    }

    private static void displayActiveLoans() {
        System.out.println("\n--- ACTIVE LOANS ---");
        displayList( library.getActiveLoans(), "No active loans.");
    }

    private static void displayOverdueLoans() {
        System.out.println("\n--- OVERDUE LOANS ---");
        LocalDate today = LocalDate.now();
        List<Loan> overdue = library.getOverdueLoans(today);
        if (overdue.isEmpty()) {
            System.out.println("No overdue loans.");
            return;
        }
        overdue.forEach(loan -> {
            System.out.println(loan);
            System.out.println("Overdue days: " + loan.getOverdueDays(today));
        });
    }
}
