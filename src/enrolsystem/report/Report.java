package enrolsystem.report;

import enrolsystem.main.*;

import java.util.ArrayList;

public abstract class Report {
    StudentEnrolmentManager enrolmentManager;
    protected Report(StudentEnrolmentManager enrolmentManager) {
        this.enrolmentManager = enrolmentManager;
    }

    public abstract void display();
    abstract void printCsv();

}

class CourseReport extends Report {
    private Student student;
    private String semester;
    protected CourseReport(StudentEnrolmentManager enrolmentManager, Student student, String semester) {
        super(enrolmentManager);
        this.student = student;
        this.semester = semester.toUpperCase();
    }

    @Override
    public void display() {
        ArrayList<StudentEnrolment> enrolments= enrolmentManager.getAllEnrolment();
        for (StudentEnrolment enrolment: enrolments) {
            if(enrolment.getStudent().equals(student) && enrolment.getSemester().equals(semester)){
                System.out.println(enrolment.getCourse());
            }
        }
    }

    @Override
    void printCsv() {

    }
}

class StudentReport extends Report {
    protected StudentReport(StudentEnrolmentManager enrolmentManager) {
        super(enrolmentManager);
    }

    @Override
    public void display() {

    }

    @Override
    void printCsv() {

    }
}

class CourseSemesterReport extends Report {
    protected CourseSemesterReport(StudentEnrolmentManager enrolmentManager) {
        super(enrolmentManager);
    }

    @Override
    public void display() {

    }

    @Override
    void printCsv() {

    }
}