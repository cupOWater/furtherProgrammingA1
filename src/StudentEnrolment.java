public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Student ID = " + student.getId() +
                ", Course ID = " + course.getId() +
                ", Semester = '" + semester + '\'' +
                '}';
    }
}
