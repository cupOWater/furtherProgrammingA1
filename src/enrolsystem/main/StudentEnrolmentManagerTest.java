package enrolsystem.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentEnrolmentManagerTest {
    StudentEnrolmentManager sem;

    @BeforeEach
    void setUp() throws IOException {
        sem = new StudentEnrolmentManager("default.csv");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fillData() {
        // If wrong file is chosen, throw IOException
        assertThrows(IOException.class, () -> new StudentEnrolmentManager("test.csv"));
    }

    @Test
    void addEnrolment() {
        boolean status = sem.addEnrolment("s101312", "phys1230", "2022a");
        StudentEnrolment addedEnrol = sem.getAllEnrolments().get(sem.getAllEnrolments().size() - 1);

        assertTrue(status);
        assertEquals(sem.getAllEnrolments().size(), 16);
        assertEquals(addedEnrol.getStudent().getId(), "S101312");
        assertEquals(addedEnrol.getCourse().getId(), "PHYS1230");
        assertEquals(addedEnrol.getSemester(), "2022A");

        assertFalse(sem.addEnrolment("s101312", "noCourse", "2021a"));
    }

    @Test
    void deleteEnrolment() {
        boolean status = sem.deleteEnrolment("s101312", "cosc4030", "2020c");

        assertTrue(status);
        assertEquals(sem.getAllEnrolments().size(), 14);

        assertFalse(sem.deleteEnrolment("s101312", "phys1230", "2020c"));
    }

    @Test
    void getStudentEnrolments() {
        ArrayList<StudentEnrolment> studentEnrolments = sem.getStudentEnrolments("s101312");
        assertEquals(studentEnrolments.size(), 3);
        assertNull(sem.getStudentEnrolments("s133222"));
    }

    @Test
    void getOneEnrolment() {
        StudentEnrolment studentEnrolment = sem.getOneEnrolment("s101312", "cosc4030", "2020c");

        assertEquals(studentEnrolment.getStudent(), sem.getStudents().get(0));
        assertEquals(studentEnrolment.getCourse(), sem.getCourses().get(0));

        assertNull(sem.getOneEnrolment("s101312", "phys1230", "2021c"));
    }

    @Test
    void isStudentPresent() {
        Student student = sem.isStudentPresent("s101312");
        assertEquals(student, sem.getStudents().get(0));
        assertNull(sem.isStudentPresent("s3884734"));
    }

    @Test
    void isCoursePresent() {
        Course course = sem.isCoursePresent("cosc4030");
        assertEquals(course, sem.getCourses().get(0));
        assertNull(sem.isCoursePresent("COSC3204"));
    }

    @Test
    void isValidSemester() {
        String validSem = "2045A";
        String invalidSem1 = "2013D";
        String invalidSem2 = "202DD";

        assertTrue(sem.isValidSemester(validSem));
        assertFalse(sem.isValidSemester(invalidSem1));
        assertFalse(sem.isValidSemester(invalidSem2));
    }
}