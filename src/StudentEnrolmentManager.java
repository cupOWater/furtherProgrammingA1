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
                students.add(new Student(data[0], data[1], data[2]));
            }
            if(isCoursePresent(data[3]) == null){
                courses.add(new Course(data[3], data[4], data[5]));
            }

            line = reader.readLine();
        }
    }
}
