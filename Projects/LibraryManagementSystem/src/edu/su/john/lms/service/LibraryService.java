package edu.su.john.lms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import edu.su.john.lms.model.Book;
import edu.su.john.lms.model.Loan;
import edu.su.john.lms.model.Member;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    private int nextBookId = 1;
    private int nextMemberId = 1;
    private int nextLoanId = 1;

    public LibraryService() {
        addBook("Effective Java", "Joshua Bloch", "Programming", 3);
        addBook("Clean Code", "Robert C. Martin", "Programming", 2);
        addBook("Database System Concepts", "Silberschatz", "Database", 4);
        registerMember("Arun", "arun@college.edu");
        registerMember("Priya", "priya@college.edu");
    }
    public Book addBook(String title, String author, String category, int copies) {
        Book book = new Book(nextBookId++, title, author, category, copies);
        books.add(book);
        return book;
    }
    public Member registerMember(String name, String email) {
        Member member = new Member(nextMemberId++, name, email);
        members.add(member);
        return member;
    }
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }
    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }
    public Optional<Book> findBookById(int id) {
        return books.stream()
            .filter(book -> book.getId() == id)
            .findFirst();
    }
    public Optional<Member> findMemberById(int id) {
        return members.stream()
            .filter(member -> member.getId() == id)
            .findFirst();
    }
    // Lambda + Predicate + Stream API
    public List<Book> searchBooks(String keyword) {
        String key = keyword.toLowerCase().trim();
        Predicate<Book> matches =
            book -> book.getTitle().toLowerCase().contains(key)
                || book.getAuthor().toLowerCase().contains(key)
                || book.getCategory().toLowerCase().contains(key);

        return books.stream()
            .filter(matches)
            .sorted(Comparator.comparing(Book::getTitle))
            .collect(Collectors.toList());
    }
    public List<Book> getBooksSortedByTitle() {
        return books.stream()
            .sorted(Comparator.comparing(Book::getTitle))
            .collect(Collectors.toList());
    }
    public List<Loan> getActiveLoans() {
        return loans.stream()
            .filter(loan -> !loan.isReturned())
            .collect(Collectors.toList());
    }
    public List<Loan> getOverdueLoans(LocalDate date) {
        return loans.stream()
            .filter(loan -> loan.getOverdueDays(date) > 0)
            .collect(Collectors.toList());
    }
    public Loan issueBook(int bookId, int memberId) {
        Book book = findBookById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book not found."));

        Member member = findMemberById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member not found."));

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No copies available for this book.");
        }
        boolean alreadyBorrowed = loans.stream()
            .anyMatch(loan ->  
                !loan.isReturned() && loan.getBook().getId() == bookId && loan.getMember().getId() == memberId);

        if (alreadyBorrowed) {
            throw new IllegalStateException("Member already has this book issued.");
        }

        LocalDate today = LocalDate.now();
        Loan loan = new Loan(nextLoanId++, book, member, today, 14);

        book.issueCopy();
        loans.add(loan);
        return loan;
    }

    public Loan returnBook(int loanId) {
        Loan loan = loans.stream()
            .filter(item -> item.getId() == loanId)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Loan not found."));
        loan.markReturned(LocalDate.now());
        return loan;
    }
}