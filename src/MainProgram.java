import java.io.IOException;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        StudentEnrolmentManager enrolmentManager = new StudentEnrolmentManager("default.csv");

        while(true){
            System.out.print("Enter student ID: ");
            String sid = userInput.next();
            System.out.print("Enter course ID: ");
            String cid = userInput.next();
            System.out.print("Enter semester: ");
            String sem = userInput.next();

            enrolmentManager.addEnrolment(sid, cid, sem);
            System.out.println();

        }
    }


}
