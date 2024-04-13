public class Student implements Comparable<Student>{

    String firstName;
    String lastName;
    int id;
    int gpa;

    public Student(String firstName, String lastName, int id, int gpa) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.gpa = gpa;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public int compareTo(Student other) {
        return this.lastName.compareTo(other.lastName);

        /*return (other.gpa - this.gpa);*/

    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", gpa=" + gpa +
                '}';
    }
}
