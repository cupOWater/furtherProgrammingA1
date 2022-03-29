public class Student {
    private String id;
    private String name;
    private String birthDate;

    public Student(String id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student ID= '" + id + '\'' +
                ", Student Name= '" + name + '\'' +
                ", Birth Date= '" + birthDate + '\'' +
                '}';
    }
}
