import java.io.IOException;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        String choice;
        StudentEnrolmentManager enrolmentManager = new StudentEnrolmentManager("default.csv");

        decision: while(true){
            System.out.println("""
                1. Add Enrolment
                2. Delete Enrolment
                3. Report
                4. Exit""");
            choice = userInput.nextLine();
            sep();
            switch (choice){
                default:
                    System.out.println("Invalid option, please enter again...\n");
                    break;
                case "1":
                    System.out.print("Enter student ID: ");
                    String sid = userInput.nextLine();
                    System.out.print("Enter course ID: ");
                    String cid = userInput.nextLine();
                    System.out.print("Enter semester: ");
                    String sem = userInput.nextLine();
                    System.out.println();

                    if(enrolmentManager.addEnrolment(sid, cid, sem)){
                        System.out.println("Enrolled successfully...");
                    }
                    sep();
                    break;
                case "2":
                    sep();
                    break;
                case"3":
                    sep();
                    break;
                case "4":
                    break decision;
            }
        }
    }

    static void sep(){
        System.out.println("-------------------------------------");
    }
}
