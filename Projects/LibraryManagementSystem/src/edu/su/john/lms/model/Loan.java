package edu.su.john.lms.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private final int id;
    private final Book book;
    private final Member member;
    private final LocalDate issueDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(int id, Book book, Member member, LocalDate issueDate, int loanDays) {
        if (loanDays <= 0) {
            throw new IllegalArgumentException("Loan period must be positive.");
        }
        this.id = id;
        this.book = book;
        this.member = member;
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
    public Member getMember() {
        return member;
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
    public boolean isReturned() {
        return returnDate != null;
    }
    public void markReturned(LocalDate date) {
        if (isReturned()) {
            throw new IllegalStateException("This loan has already been returned.");
        }
        if (date.isBefore(issueDate)) {
            throw new IllegalArgumentException("Return date cannot precede issue date.");
        }
        returnDate = date;
        book.returnCopy();
    }
    public long getOverdueDays(LocalDate date) {
        if (isReturned()) {
            return 0;
        }
        if (!date.isAfter(dueDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, date);
    }
    @Override
    public String toString() {
        return String.format( "Loan ID: %d | Book: %s | Member: %s | Issued: %s | Due: %s | Status: %s",
            id, book.getTitle(), member.getName(), issueDate, dueDate, isReturned() ? "RETURNED" : "ISSUED");
    }
}