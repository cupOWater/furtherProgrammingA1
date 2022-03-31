package enrolsystem.report;

import enrolsystem.main.*;

import java.util.Scanner;

public class ReportFactory {
    // This class will create different types of the Report instance
    // Allow validation before instantiating the object
    StudentEnrolmentManager enrolmentManager;

    public ReportFactory(StudentEnrolmentManager enrolmentManager) {
        this.enrolmentManager = enrolmentManager;
    }

    public CourseReport createCourseReport(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
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

    public StudentReport createStudentReport(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Course ID: ");
        String cid = scanner.nextLine();
        Course course = enrolmentManager.isCoursePresent(cid);
        if(course == null){
            System.out.println("Course is not in system...");
            return null;
        }

        System.out.print("Enter Semester: ");
        String semester = scanner.nextLine();
        if(!enrolmentManager.isValidSemester(semester)) {
            System.out.println("Invalid semester value...");
            return null;
        }
        return new StudentReport(enrolmentManager, course, semester);
    }

    public CourseReport createCourseSemesterReport(){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Semester: ");
        String semester = s.nextLine();
        if(enrolmentManager.isValidSemester(semester)){
            return new CourseReport(enrolmentManager, semester);
        }
        System.out.println("Invalid semester...");
        return null;
    }
}

