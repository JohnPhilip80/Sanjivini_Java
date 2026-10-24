package edu.su.john.basics;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        studentProfile();
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