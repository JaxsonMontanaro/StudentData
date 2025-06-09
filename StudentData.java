/*
 * Write a Java program that incorporates a loop that prompts the user for student data. 
 * 
 * Student data are private fields in a student class including:
 * String name
 * String address
 * double GPA
 * 
 * Each student object is stored in a linked list.
 * 
 * After the user completes the data entry, output the contents of the linked list in ascending sorted order 
 * by name to a regular text file 
 * that can be opened and viewed using a simple plain-text editor such as notepad.
 * 
 * Validate numeric data for Grade Point Average (GPA).
 */


import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;
//import packages

class Students {
    private String name;
    private String address;
    private double gpa;
    // Students class with private objects

    Students(String name, String address, double gpa) {
        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }
    // Constructor which takes in objects as arguments and initializes them

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getGpa() {
        return gpa;
    }
    // getters are needed for access because objects are private

@Override
public String toString() {
    return "Name: " + name + "\nAddress: " + address + "\nGPA: " + gpa;
}
// Overides toString Method and prints student information
}


public class StudentData {
    public static void main(String[] args) {
        LinkedList<Students> studentList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String response;
        // main method with scanner for user input and linked list to hold each student object
        //response holds user response

        do {
            System.out.print("What is the students name? ");
            String name = scanner.nextLine();
            // stores user input in name and goes to next line
            System.out.print("What is the students adress? ");
            String address = scanner.nextLine();
            // stores user input in address and goes to next line
            double gpa;
            // gpa value needed so that data can be stored

            while (true) {
                System.out.print("What is the students gpa? ");
                try {
                    gpa = Double.parseDouble(scanner.nextLine());
                    // cant use scanner.nextLine as it saves value to string, will need
                    // double.parseDouble for gpa
                    if (gpa >= 0.0 && gpa <= 5.0) {
                        break;
                        // if gpa is between 0 and 5, exits the while loop
                    } else {
                        System.out.println("Students gpa must be between 0.0 and 5.0");
                    }
                    // stays in while loop until gpa condition is met
                } catch (NumberFormatException e) {
                    System.out.println("Please enter students gpa in the listed format");
                    // originally wanted just if else however if user entes three, the program will crash
                    // try catch catches values other than doubles
                }
            }
            studentList.add(new Students(name, address, gpa));
            // add new student object with entered information and add to student list
            
            System.out.print("Do you want to add another student? (y or n): ");
            response = scanner.nextLine();
        } while (response.equals("y"));
        //checks if user wants to add another student and keeps looping if user enters y

        Collections.sort(studentList, new Comparator<Students>() {
            public int compare(Students a, Students b) {
                return a.getName().compareToIgnoreCase(b.getName());
            }
        });
        //Sorts student list with comparator
        //sorts calues into -1,0, or one in order to compare them with negative being smaller and being placed first

        try {
            FileWriter writer = new FileWriter("orderedStudents.txt");
            //Filewriter creates a file for students.txt
            for (Students s : studentList) {
                writer.write(s.toString() + " ");
            }
            //For every students s in studentList write value toString (calls toString)
            writer.close();
            //close writer
            System.out.println("Students data saved!");
            //alerts user that data has been printes
        } catch (Exception e) {
            System.out.println("Error saving students information");
        }

        scanner.close();
        //closes scanner
    }
}


/*
 * Recourses:
 * Module 6 Critical thinking
 * https://www.w3schools.com/java/java_encapsulation.asp
 * Module 3 critical thinking
 * https://www.geeksforgeeks.org/linked-list-in-java/
 * https://www.w3schools.com/java/java_try_catch.asp
 * https://www.w3schools.com/java/java_while_loop_do.asp
 * https://www.geeksforgeeks.org/convert-string-to-double-in-java/
 * https://www.w3schools.com/java/java_files_create.asp
 * https://www.geeksforgeeks.org/collections-sort-java-examples/
 */