import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentEnrolmentManager {
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<StudentEnrolment> studentEnrolments = new ArrayList<>();
    Scanner s = new Scanner(System.in);

    public StudentEnrolmentManager(String filePath) throws IOException {
        fillData(filePath);
    }

    void add(String sid, String cid, String semester){
        Student student = isStudentPresent(sid);
        Course course = isCoursePresent(cid);
        if(student != null && course != null){
            studentEnrolments.add(new StudentEnrolment(student, course, semester));
        }
        if(student == null){
            System.out.println("Student not found...");
        }
        if(course == null){
            System.out.println("This course is not in the system...");
        }
    }

    void update(){}
    void delete(){}
    void getOne(){}
    void getAll(){}

    private Student isStudentPresent(String sid){
        for (Student student: students) {
            if(student.getId().equals(sid)){
                return student;
            }
        }
        return null;
    }

    private Course isCoursePresent(String cid){
        for (Course course : courses){
            if(course.getId().equals(cid)){
                return course;
            }
        }
        return null;
    }

    void fillData(String filePath) throws IOException {
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
            add(data[0], data[3], data[6]);
            line = reader.readLine();
        }
    }
}
