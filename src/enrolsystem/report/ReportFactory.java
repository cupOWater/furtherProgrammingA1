package enrolsystem.report;

import enrolsystem.main.*;

import java.util.Scanner;

public class ReportFactory {
    StudentEnrolmentManager enrolmentManager;

    public ReportFactory(StudentEnrolmentManager enrolmentManager) {
        this.enrolmentManager = enrolmentManager;
    }

    public CourseReport createCourseReport(){
        System.out.print("Enter Student ID: ");
        Scanner scanner = new Scanner(System.in);
        String sid = scanner.nextLine();
        Student student = enrolmentManager.isStudentPresent(sid);
        if(student == null){
            System.out.println("Student not found...");
            return null;
        }

        System.out.print("Enter Semester: ");
        String semester = scanner.nextLine();
        if(!enrolmentManager.isValidSemester(semester)){
            System.out.println("Invalid semester value...");
            return null;
        }
        return new CourseReport(enrolmentManager, student, semester);
    }


}

