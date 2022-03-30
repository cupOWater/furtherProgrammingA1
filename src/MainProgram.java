import enrolsystem.main.*;
import enrolsystem.report.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        String choice;
        try{
            StudentEnrolmentManager enrolmentManager = new StudentEnrolmentManager("default.csv");
            decision: while(true){
                System.out.println("""
                1. Add Enrolment
                2. Delete Enrolment
                3. Report
                4. Print All Enrolments
                0. Exit""");
                System.out.print("> ");
                choice = userInput.nextLine();
                sep();
                switch (choice){
                    default:
                        System.out.println("Invalid option, please enter again...\n");
                        break;
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
                        printArray(enrolmentManager.getAllEnrolment());
                        sep();
                        System.out.print("Enter student ID: ");
                        sid = userInput.nextLine();
                        System.out.print("Enter course ID: ");
                        cid = userInput.nextLine();
                        System.out.print("Enter semester: ");
                        sem = userInput.nextLine();

                        if(enrolmentManager.deleteEnrolment(sid, cid, sem)){
                            System.out.println("Delete successfully...");
                        }else{
                            System.out.println("Delete fail...");
                        }
                        sep();
                        break;
                    case "3":
                        ReportFactory reportFactory = new ReportFactory(enrolmentManager);
                        Report report = null;
                        System.out.println("""
                            1. Print all courses for 1 student in 1 semester
                            2. Print all students of 1 course in 1 semester
                            3. Prints all courses offered in 1 semester
                            Other keys to return""");
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
                            report.display();
                        }
                        sep();
                        break;
                    case"4":
                        printArray(enrolmentManager.getAllEnrolment());
                        sep();
                        break;
                    case "0":
                        break decision;
                }
            }
        }catch(IOException e){
            System.out.println("File not found...");
            System.exit(0);
        }


    }

    static void sep(){
        System.out.println("-------------------------------------");
    }

    static void printArray(ArrayList items){
        for (Object item : items) {
            System.out.println(item);
        }
    }
}
