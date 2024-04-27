public class Main {

    public static void main(String[] args) {

       /* int i = 1;
        double d = i;

        System.out.println("double d auto casted from int i " + d);

        double d2 = 1.0;
        int i2 = (int) d2;

        System.out.println("int i2 explicitly casted from double d2 " + i2);

        int i3 = 1;
        long l = i3;

        System.out.println("long l auto casted from int i3 " + l);

        long l2 = 1000000000;
        int i4 = (int) l2;

        System.out.println("int i4 explicitly casted from long l2 " + i4);*/

        Person p = new Student();
        p.talk();
        ((Student) p).speak();

        Student s = (Student) p;
        s.talk();
        s.speak();
        System.out.println(p.getClass());
    }
}
