import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        SortedSet<Student> studentSet = new TreeSet<>();

        studentSet.add(new Student("Jill", "Anderson", 4, 5));
        studentSet.add(new Student("Tiger", "Zhao", 1, 4));
        studentSet.add(new Student("Peng", "Zhao", 2, 7));
        studentSet.add(new Student("Molly", "Yang", 3, 6));
        studentSet.add(new Student("Bill", "Smith", 5, 2));


        System.out.println("by last name");
        for (Student student : studentSet) {
            System.out.println(student);
        }
    }
}
