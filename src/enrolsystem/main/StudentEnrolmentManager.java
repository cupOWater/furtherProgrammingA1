package enrolsystem.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentEnrolmentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<StudentEnrolment> studentEnrolments = new ArrayList<>();

    public StudentEnrolmentManager(String filePath) throws IOException {
        fillData(filePath);
    }

    public boolean addEnrolment(String sid, String cid, String semester){
        Student student = isStudentPresent(sid);
        Course course = isCoursePresent(cid);
        if(student != null && course != null && isValidSemester(semester)){
            for (StudentEnrolment enrolment: studentEnrolments) {
                if(student.equals(enrolment.getStudent()) && course.equals(enrolment.getCourse()) && semester.equalsIgnoreCase(enrolment.getSemester())){
                    System.out.println("Enrolment already exists in system...");
                    return false;
                }
            }
            studentEnrolments.add(new StudentEnrolment(student, course, semester));
            return true;
        }
        if(student == null){
            System.out.println("Student not found...");
        }
        if(course == null){
            System.out.println("This course is not in the system...");
        }
        if(!isValidSemester(semester)){
            System.out.println("Invalid semester value...");
        }
        return false;
    }

    void updateEnrolment(){}

    public boolean deleteEnrolment(String sid, String cid, String semester){
        StudentEnrolment enrolment = getOneEnrolment(sid, cid, semester);
        if (enrolment != null){
            studentEnrolments.remove(enrolment);
            return true;
        }
        return false;
    }

    public StudentEnrolment getOneEnrolment(String sid, String cid, String semester){
        Student student = isStudentPresent(sid);
        Course course = isCoursePresent(cid);
        if(student != null && course != null && isValidSemester(semester)){
            for (StudentEnrolment enrolment: studentEnrolments) {
                if(student.equals(enrolment.getStudent()) && course.equals(enrolment.getCourse()) && semester.equalsIgnoreCase(enrolment.getSemester())){
                    return enrolment;
                }
            }
        }

        if(student == null){
            System.out.println("Student not found...");
        }
        if(course == null){
            System.out.println("This course is not in the system...");
        }
        if(!isValidSemester(semester)){
            System.out.println("Invalid semester value...");
        }else{
            System.out.println("Enrolment does not exist...");
        }
        return null;
    }
    public ArrayList<StudentEnrolment> getAllEnrolment(){
        return studentEnrolments;
    }


    public Student isStudentPresent(String sid){
        for (Student student: students) {
            if(student.getId().equals(sid.toUpperCase())){
                return student;
            }
        }
        return null;
    }

    public Course isCoursePresent(String cid){
        for (Course course : courses){
            if(course.getId().equals(cid.toUpperCase())){
                return course;
            }
        }
        return null;
    }

    public boolean isValidSemester(String semester){
        Pattern regPattern = Pattern.compile("\\d{4}[ABCabc]");
        Matcher check = regPattern.matcher(semester);
        return check.matches();
    }

    private void fillData(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        while(line != null){
            String[] data = line.split(",");
            if(isStudentPresent(data[0]) == null){
                // Add student into students array if not present
                students.add(new Student(data[0], data[1], data[2]));
            }
            if(isCoursePresent(data[3]) == null){
                // Add course into courses array if not present
                courses.add(new Course(data[3], data[4], data[5]));
            }
            // Add enrollment from default.csv
            addEnrolment(data[0], data[3], data[6]);
            line = reader.readLine();
        }
    }
}
