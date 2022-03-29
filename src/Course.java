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

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credNum='" + credNum + '\'' +
                '}';
    }
}
