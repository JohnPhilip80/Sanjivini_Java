package edu.su.john.lms;

import java.util.ArrayList;
import java.util.List;
import edu.su.john.lms.model.Student;

public class Demo {
    public static void main(String[] args){

        List<Student> students = new ArrayList<>();
        students.add(new Student("John Philip","john@gmail.com"));
        students.add(new Student("Sakshi","sakshi@gmail.com"));
        students.add(new Student("Arun","arun@gmail.com"));
        students.add(new Student("Priya","priya@gmail.com"));
        students.add(new Student("Nishanthi","nisha@gmail.com"));

        students.get(3).setEmail("PRIYA@gmail.com");
        //students.get(4).scanStudent();
        students.remove(2);

        Student.printHeading();
        for(Student st:students){
            System.out.println(st);
        }
    }
}
