package enrolsystem.main;

public class Course {
    private String id;
    private String name;
    private String credNum;

    public Course(String id, String name, String credNum) {
        this.id = id;
        this.name = name;
        this.credNum = credNum;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCredNum() {
        return credNum;
    }

    @Override
    public String toString() {
        return "Course ID= " + id +
                " | Course Name= " + name +
                " | Number of Credits= " + credNum;
    }
}
