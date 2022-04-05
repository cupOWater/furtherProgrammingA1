package enrolsystem.report;

import enrolsystem.main.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Report {
    StudentEnrolmentManager enrolmentManager;
    protected Report(StudentEnrolmentManager enrolmentManager) {
        this.enrolmentManager = enrolmentManager;
    }
    public abstract boolean display();
    public abstract boolean saveCsv();
}

class CourseReport extends Report {
    private Student student;
    private final String semester;
    private final String fileName;
    private final ArrayList<Course> courses = new ArrayList<>();

    protected CourseReport(StudentEnrolmentManager enrolmentManager, Student student, String semester) {
        super(enrolmentManager);
        this.student = student;
        this.semester = semester.toUpperCase();
        for (StudentEnrolment enrolment: enrolmentManager.getAllEnrolments()) {
            if(enrolment.getStudent().equals(student) && enrolment.getSemester().equalsIgnoreCase(semester)){
                courses.add(enrolment.getCourse());
            }
        }
        fileName = student.getId() + "_Courses_" + this.semester +".csv";
    }

    public String getSemester() {
        return semester;
    }

    public String getStudentID() {
        if(student != null){
            return student.getId();
        }
        return null;
    }

    protected CourseReport(StudentEnrolmentManager enrolmentManager, String semester) {
        super(enrolmentManager);
        this.semester = semester.toUpperCase();
        for (StudentEnrolment enrolment: enrolmentManager.getAllEnrolments()) {
            if(enrolment.getSemester().equalsIgnoreCase(semester) && !courses.contains(enrolment.getCourse())){
                courses.add(enrolment.getCourse());
            }
        }
        fileName = this.semester + "_Courses" + ".csv";
    }

    @Override
    public boolean display() {
        if(courses.size() == 0){
            System.out.println("There is no course in that fit criteria...");
            return false;
        }
        for (Course course: courses) {
            System.out.println(course);
        }
        return true;
    }

    @Override
    public boolean saveCsv() {
        if(courses.size() == 0){
            return false;
        }
        try{
            PrintWriter writer = new PrintWriter(fileName);
            StringBuilder builder = new StringBuilder();
            builder.append("Course ID,Course Name,Number Of Credits\n");
            for (Course course: courses) {
                builder.append(course.getId()).append(",")
                        .append(course.getName()).append(",")
                        .append(course.getCredNum()).append("\n");
            }
            writer.write(builder.toString());
            writer.flush();
            writer.close();
            return true;
        }
        catch(IOException e){
            System.out.println("Cannot create file...");
            return false;
        }
    }
}

class StudentReport extends Report {
    private final Course course;
    private final String semester;
    private final String fileName;
    private final ArrayList<Student> students = new ArrayList<>();

    public String getCourseID() {
        return course.getId();
    }

    public String getSemester() {
        return semester;
    }

    protected StudentReport(StudentEnrolmentManager enrolmentManager, Course course, String semester) {
        super(enrolmentManager);
        this.course = course;
        this.semester = semester.toUpperCase();
        for (StudentEnrolment enrolment: enrolmentManager.getAllEnrolments()) {
            if(enrolment.getCourse().equals(course) && enrolment.getSemester().equalsIgnoreCase(semester)){
                students.add(enrolment.getStudent());
            }
        }
        fileName = course.getId() + "_Students_" + this.semester + ".csv";
    }

    @Override
    public boolean display() {
        if(students.size() == 0){
            System.out.println("There is no student that fit criteria...");
            return false;
        }
        for(Student student: students){
            System.out.println(student);
        }
        return true;
    }

    @Override
    public boolean saveCsv() {
        if(students.size() == 0){
            return false;
        }
        try{
            PrintWriter writer = new PrintWriter(fileName);
            StringBuilder builder = new StringBuilder();
            builder.append("Student ID,Student Name,BirthDate\n");
            for (Student student: students) {
                builder.append(student.getId()).append(",")
                        .append(student.getName()).append(",")
                        .append(student.getBirthDate()).append("\n");
            }
            writer.write(builder.toString());
            writer.flush();
            writer.close();
            return true;
        }catch (IOException e){
            System.out.println("Cannot create file...");
            return false;
        }
    }
}
