import java.io.IOException;

public class MainProgram {
    public static void main(String[] args) throws IOException {
        StudentEnrolmentManager enrolmentManager = new StudentEnrolmentManager("default.csv");
        System.out.println(enrolmentManager.students);
    }
}
