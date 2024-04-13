public class Student implements Comparable<Student>{

    int age;

    public Student(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Student other) {
        if (this.age == other.age) return 0;
        if (this.age > other.age) return 1;
        else return -1;
    }
}
