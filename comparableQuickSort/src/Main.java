import java.util.Arrays;

public class Main {
    public static void sort(Student[] arr, int low, int high) {
        if (high <= low) return;

        int pivot = partition(arr, low, high);
        sort(arr, 0, pivot - 1);
        sort(arr, pivot + 1, high);
    }

    private static int partition(Student[] arr, int low, int high) {

        Student pivot = arr[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j].compareTo(pivot) > 0) {
                i++;

                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        Student temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i;
    }

    public static void main(String[] args) {

        Student[] arr = new Student[10];
        arr[0] = new Student(10);
        arr[1] = new Student(3);
        arr[2] = new Student(51);
        arr[3] = new Student(4);
        arr[4] = new Student(23);
        arr[5] = new Student(6);
        arr[6] = new Student(7);
        arr[7] = new Student(12);
        arr[8] = new Student(1);
        arr[9] = new Student(15);
        sort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i].age + " age");
        }
    }

}
