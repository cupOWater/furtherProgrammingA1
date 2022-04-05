import enrolsystem.main.*;
import enrolsystem.report.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        String choice;
        String filePath = "default.csv";
        try{
            StudentEnrolmentManager enrolmentManager = new StudentEnrolmentManager(filePath);
            System.out.println("All data from " + filePath + " has been added... \n");
            printArray(enrolmentManager.getAllEnrolments());
            sep();
            decision: while(true){
                System.out.println("""
                1. Add an enrolment
                2. Delete an enrolment
                3. Update enrolment for a student
                4. Report
                5. Display
                Enter any other keys to exit...""");
                System.out.print("> ");
                choice = userInput.nextLine();
                sep();
                switch (choice){
                    default:
                        break decision;
                    case "1":
                        System.out.print("Enter student ID: ");
                        String sid = userInput.nextLine();
                        System.out.print("Enter course ID: ");
                        String cid = userInput.nextLine();
                        System.out.print("Enter semester: ");
                        String sem = userInput.nextLine();
                        System.out.println();

                        if(enrolmentManager.addEnrolment(sid, cid, sem)){
                            System.out.println("Enrol successfully...");
                        }else{
                            System.out.println("Enrol fail...");
                        }
                        sep();
                        break;

                    case "2":
                        System.out.print("Enter student ID: ");
                        sid = userInput.nextLine();
                        System.out.print("Enter course ID: ");
                        cid = userInput.nextLine();
                        System.out.print("Enter semester: ");
                        sem = userInput.nextLine();
                        if (enrolmentManager.deleteEnrolment(sid, cid, sem)) {
                            System.out.println("Delete successfully...");
                        } else {
                            System.out.println("Delete fail...");
                        }
                        sep();
                        break;

                    case "3":
                        System.out.print("Enter student ID: ");
                        sid = userInput.nextLine();
                        if(enrolmentManager.getStudentEnrolments(sid) == null){
                            sep();
                            break;
                        }
                        printArray(enrolmentManager.getStudentEnrolments(sid));
                        System.out.println("""
                                1. Add enrolment
                                2. Delete enrolment
                                Other keys to return...""");
                        System.out.print("> ");
                        choice = userInput.nextLine();
                        sep();

                        switch (choice) {
                            default -> {
                            }
                            case "1" -> {
                                System.out.print("Enter course ID: ");
                                cid = userInput.nextLine();
                                System.out.print("Enter semester: ");
                                sem = userInput.nextLine();
                                if (enrolmentManager.addEnrolment(sid, cid, sem)) {
                                    System.out.println("Add successfully...");
                                } else {
                                    System.out.println("Add fail...");
                                }
                            }
                            case "2" -> {
                                System.out.print("Enter course ID: ");
                                cid = userInput.nextLine();
                                System.out.print("Enter semester: ");
                                sem = userInput.nextLine();
                                if (enrolmentManager.deleteEnrolment(sid, cid, sem)) {
                                    System.out.println("Delete successfully...");
                                } else {
                                    System.out.println("Delete fail...");
                                }
                            }
                        }
                        sep();
                        break;

                    case "4":
                        ReportFactory reportFactory = new ReportFactory(enrolmentManager);
                        Report report = null;
                        System.out.println("""
                            1. Print all courses for 1 student in 1 semester
                            2. Print all students of 1 course in 1 semester
                            3. Prints all courses offered in 1 semester
                            Other keys to return...""");
                        System.out.print("> ");
                        choice = userInput.nextLine();
                        sep();
                        switch (choice) {
                            default -> {
                            }
                            case "1" -> report = reportFactory.createCourseReport();
                            case "2" -> report = reportFactory.createStudentReport();
                            case "3" -> report = reportFactory.createCourseSemesterReport();
                        }
                        if(report != null){
                            if(report.display()){
                                System.out.print("Save as CSV?(Enter \"Y\" to confirm) ");
                                choice = userInput.nextLine().toUpperCase();
                                if(choice.equals("Y")){
                                    if(report.saveCsv()){
                                        System.out.println("Save successfully...");
                                    }else{
                                        System.out.println("Unable to save...");
                                    }
                                }
                            }
                        }
                        sep();
                        break;

                    case"5":
                        System.out.println("""
                            1. Display all enrolments
                            2. Display all students
                            3. Display all courses
                            Other keys to return...""");
                        System.out.print("> ");
                        choice = userInput.nextLine();
                        sep();

                        switch (choice) {
                            default -> {
                            }
                            case "1" -> printArray(enrolmentManager.getAllEnrolments());
                            case "2" -> printArray(enrolmentManager.getStudents());
                            case "3" -> printArray(enrolmentManager.getCourses());
                        }

                        sep();
                        break;
                }
            }
        }catch(IOException e){
            System.out.println("File not found...");
            System.exit(0);
        }


    }

    static void sep(){
        // print a separator line
        System.out.println("-------------------------------------");
    }

    static void printArray(ArrayList items){
        // Print each object in arraylist on different line
        // Must format toString() of object to fit the display
        for (Object item : items) {
            System.out.println(item);
        }
    }
}
