package edu.su.john.basics;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        //studentProfile();
        //simpleCalculator();
        //gradeCalculator();
        //menuCalculator();
        //multiplicationTable();
        //findSum();
        //numberInput();
        studentResult();
        
    }
    public static void studentResult(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        int total = 0;

        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter mark for subject " + i + ": ");
            int mark = sc.nextInt();

            while (mark < 0 || mark > 100) {
                System.out.println("Mark must be between 0 and 100.");
                System.out.print("Re-enter mark: ");
                mark = sc.nextInt();
            }

            total += mark;
        }

        double average = total / 3.0;
        String grade;

        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("\n--- Student Result ---");
        System.out.println("Name: " + name);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);

        sc.close();
    }
    public static void numberInput(){
        Scanner sc = new Scanner(System.in);

        int number;

        do {
            System.out.print("Enter a positive number: ");
            number = sc.nextInt();
        } while (number <= 0);

        System.out.println("Accepted number: " + number);
        sc.close();
    }
    public static void findSum(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a positive integer: ");
        int n = sc.nextInt();

        int i = 1;
        int sum = 0;

        while (i <= n) {
            sum += i;
            i++;
        }

        System.out.println("Sum = " + sum);
        sc.close();
    }
    public static void multiplicationTable(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }

        sc.close();
    }
    public static void menuCalculator(){
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");

        System.out.print("Choose an option: ");
        int choice = sc.nextInt();

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();

        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        switch (choice) {
            case 1:
                System.out.println("Result: " + (a + b));
                break;
            case 2:
                System.out.println("Result: " + (a - b));
                break;
            case 3:
                System.out.println("Result: " + (a * b));
                break;
            case 4:
                if (b != 0) {
                    System.out.println("Result: " + (a / b));
                } else {
                    System.out.println("Cannot divide by zero");
                }
                break;
            default:
                System.out.println("Invalid choice");
        }
        sc.close();
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