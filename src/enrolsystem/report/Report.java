package enrolsystem.report;

import enrolsystem.main.*;

import java.util.ArrayList;

public abstract class Report {
    StudentEnrolmentManager enrolmentManager;
    protected Report(StudentEnrolmentManager enrolmentManager) {
        this.enrolmentManager = enrolmentManager;
    }

    public abstract void display();
    abstract void saveCsv();

}

class CourseReport extends Report {
    private Student student;
    private String semester;
    private ArrayList<Course> courses = new ArrayList<>();

    protected CourseReport(StudentEnrolmentManager enrolmentManager, Student student, String semester) {
        super(enrolmentManager);
        this.student = student;
        this.semester = semester.toUpperCase();
        for (StudentEnrolment enrolment: enrolmentManager.getAllEnrolment()) {
            if(enrolment.getStudent().equals(student) && enrolment.getSemester().equalsIgnoreCase(semester)){
                courses.add(enrolment.getCourse());
            }
        }
    }

    @Override
    public void display() {
        for (Course course: courses) {
            System.out.println(course);
        }
    }

    @Override
    void saveCsv() {

    }
}

class StudentReport extends Report {
    Course course;
    String semester;
    ArrayList<Student> students = new ArrayList<>();

    protected StudentReport(StudentEnrolmentManager enrolmentManager, Course course, String semester) {
        super(enrolmentManager);
        this.course = course;
        this.semester = semester;
        for (StudentEnrolment enrolment: enrolmentManager.getAllEnrolment()) {
            if(enrolment.getCourse().equals(course) && enrolment.getSemester().equalsIgnoreCase(semester)){
                students.add(enrolment.getStudent());
            }
        }
    }

    @Override
    public void display() {
        for(Student student: students){
            System.out.println(student);
        }
    }

    @Override
    void saveCsv() {

    }
}

class CourseSemesterReport extends Report {
    String semester;
    ArrayList<Course> courses = new ArrayList<>();

    protected CourseSemesterReport(StudentEnrolmentManager enrolmentManager, String semester) {
        super(enrolmentManager);
        this.semester = semester.toUpperCase();
        for (StudentEnrolment enrolment: enrolmentManager.getAllEnrolment()) {
            if(enrolment.getSemester().equalsIgnoreCase(semester) && !courses.contains(enrolment.getCourse())){
                courses.add(enrolment.getCourse());
            }
        }
    }

    @Override
    public void display() {
        for (Course course: courses) {
            System.out.println(course);
        }
    }

    @Override
    void saveCsv() {

    }
}