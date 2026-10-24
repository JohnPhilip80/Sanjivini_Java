package edu.su.john.basics;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        //studentProfile();
        //simpleCalculator();
        gradeCalculator();
    }
    
    public static void gradeCalculator(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter mark (0-100): ");
        int mark = sc.nextInt();

        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark");
        } else if (mark >= 90) {
            System.out.println("Grade: A");
        } else if (mark >= 80) {
            System.out.println("Grade: B");
        } else if (mark >= 70) {
            System.out.println("Grade: C");
        } else if (mark >= 50) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F - Fail");
        }

        sc.close();
    }
    public static void simpleCalculator(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();

        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));

        if (b != 0) {
            System.out.println("Division: " + (a / b));
            System.out.println("Remainder: " + (a % b));
        } else {
            System.out.println("Division by zero is not allowed.");
        }

        sc.close();
    }
    public static void studentProfile(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Enter mark: ");
        double mark = sc.nextDouble();

        System.out.print("Enter grade: ");
        char grade = sc.next().charAt(0);

        System.out.println("\n--- Student Profile ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Mark: " + mark);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}