package enrolsystem.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentEnrolmentManager {
    // Instantiate the required array lists for the enrolment system
    private final ArrayList<Student> students = new ArrayList<>();
    private final ArrayList<Course> courses = new ArrayList<>();
    private final ArrayList<StudentEnrolment> studentEnrolments = new ArrayList<>();

    public StudentEnrolmentManager(String filePath) throws IOException {
        // fill the above arrays on instantiation
        fillData(filePath);
    }

    public boolean addEnrolment(String sid, String cid, String semester){
        Student student = isStudentPresent(sid);
        Course course = isCoursePresent(cid);
        if(student != null && course != null && isValidSemester(semester)){ // Checking for data validity
            for (StudentEnrolment enrolment: studentEnrolments) { // Look through enrolments to see if there are duplicates
                if(student.equals(enrolment.getStudent()) && course.equals(enrolment.getCourse()) && semester.equalsIgnoreCase(enrolment.getSemester())){
                    System.out.println("Enrolment already exists in system...");
                    return false;
                }
            }
            // If everything is good, then add enrolment to array
            // Else display missing or invalid entry
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

    public boolean deleteEnrolment(String sid, String cid, String semester){
        StudentEnrolment enrolment = getOneEnrolment(sid, cid, semester); // Check to see if enrolment is present
        if (enrolment != null){
            studentEnrolments.remove(enrolment);
            return true;
        }
        return false;
    }

    public ArrayList<StudentEnrolment> getStudentEnrolments(String sid){
        // Return all enrolments that are belonged to 1 student
        Student student = isStudentPresent(sid);
        if(student == null){
            System.out.println("Student not found...");
            return null;
        }
        ArrayList<StudentEnrolment> enrolments = new ArrayList<>();
        for (StudentEnrolment enrolment: studentEnrolments) {
            if(enrolment.getStudent().equals(student)){
                enrolments.add(enrolment);
            }
        }
        return enrolments;
    }

    public StudentEnrolment getOneEnrolment(String sid, String cid, String semester){
        Student student = isStudentPresent(sid);
        Course course = isCoursePresent(cid);
        if(student != null && course != null && isValidSemester(semester)){
            for (StudentEnrolment enrolment: studentEnrolments) {
                if(student.equals(enrolment.getStudent()) && course.equals(enrolment.getCourse()) && semester.equalsIgnoreCase(enrolment.getSemester())){
                    return enrolment; // If all entries are valid, return that StudentEnrolment object
                }
            }
        }
        // Else display error messages
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
        // Return Student object if present in students arraylist
        for (Student student: students) {
            if(student.getId().equals(sid.toUpperCase())){
                return student;
            }
        }
        return null;
    }

    public Course isCoursePresent(String cid){
        // Return Course object if present in courses arraylist
        for (Course course : courses){
            if(course.getId().equals(cid.toUpperCase())){
                return course;
            }
        }
        return null;
    }

    public boolean isValidSemester(String semester){
        // Use regex to make sure semester value is 4 digits follow by a, b or c case-insensitive
        Pattern regPattern = Pattern.compile("\\d{4}[ABCabc]");
        Matcher check = regPattern.matcher(semester);
        return check.matches();
    }

    private void fillData(String filePath) throws IOException {
        // Read data from provided file and fill them in to arrays
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

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
