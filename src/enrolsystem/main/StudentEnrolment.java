package enrolsystem.main;

public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester.toUpperCase();
    }

    @Override
    public String toString() {
        return "Student ID = " + student.getId() +
                " | Course ID = " + course.getId() +
                " | Semester = " + semester ;
    }
}
