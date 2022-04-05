package enrolsystem.report;

import enrolsystem.main.StudentEnrolmentManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReportFactoryTest {
    StudentEnrolmentManager sem;
    ReportFactory factory;
    String userInputValid = "s101312\r2021c";
    String userInputInvalid = "s3884734\r2020c";

    @BeforeEach
    void setUp() throws IOException {
        sem = new StudentEnrolmentManager("default.csv");
        factory = new ReportFactory(sem);
    }

    @AfterEach
    void tearDown() {


    }

    // Test initialization of reports and their function to save as csv
    @Test
    void createCourseReport() {
        // Simulate user's input
        String userInputValid = "s101312\r2020c\r";
        String userInputInvalid = "s3884734\r2020c\r";

        System.setIn(new ByteArrayInputStream(userInputValid.getBytes()));
        Report courseReport = factory.createCourseReport();
        assertTrue(courseReport.display());

        courseReport.saveCsv(); // Save file with name sid_Course_semester.csv
        assertNotNull(courseReport);
        assertTrue(new File("S101312_Courses_2020C.csv").exists());
        new File("S101312_Courses_2020C.csv").delete();

        System.setIn(new ByteArrayInputStream(userInputInvalid.getBytes()));
        courseReport = factory.createCourseReport();
        assertNull(courseReport);
    }

    @Test
    void createStudentReport() {
        String userInputValid = "cosc4030\r2020c\r";
        String userInputInvalid = "cosc2222\r2020c\r";

        System.setIn(new ByteArrayInputStream(userInputValid.getBytes()));
        Report studentReport = factory.createStudentReport();
        assertNotNull(studentReport);
        assertTrue(studentReport.display());
        studentReport.saveCsv(); // Save file with name cid_Student_semester.csv
        assertTrue(new File("COSC4030_Students_2020C.csv").exists());
        new File("COSC4030_Students_2020C.csv").delete();

        System.setIn(new ByteArrayInputStream(userInputInvalid.getBytes()));
        studentReport = factory.createStudentReport();
        assertNull(studentReport);
    }

    @Test
    void createCourseSemesterReport() {
        String userInputValid = "2020c\r";
        String userInputInvalid = "2021d\r";

        System.setIn(new ByteArrayInputStream(userInputValid.getBytes()));
        Report courseReport = factory.createCourseSemesterReport();
        assertTrue(courseReport.display());
        assertNotNull(courseReport);
        courseReport.saveCsv(); // Save file with name semester_Course.csv
        assertTrue(new File("2020C_Courses.csv").exists());
        new File("2020C_Courses.csv").delete();

        System.setIn(new ByteArrayInputStream(userInputInvalid.getBytes()));
        courseReport = factory.createCourseSemesterReport();
        assertNull(courseReport);
    }
}