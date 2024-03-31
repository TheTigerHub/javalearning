import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static int mostFrequent ( ArrayList arr, int n)
    {
        int maxcount = 0;
        int numbermax = 0;
        for (int i = 0; i < n; i++){
            int count = 0;
            for (int j = 0; j<n; j++) {
                if (arr.get(i) == arr.get(j)) {
                    count ++;
                }
            }

            if (count > maxcount){
                maxcount = count;
                numbermax = (int) arr.get(i);
            }
        }

        return numbermax;
    }

    public static int percentage (ArrayList arr, int countChar) {
        return ((Collections.frequency(arr, countChar))*100)/arr.size();
    }
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Integer> characterArray = new ArrayList<>();

        try {
            FileReader reader = new FileReader("C:\\Users\\Tiger Zhao\\IdeaProjects\\characterwordcounter\\src\\text.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            Scanner inputScanner = new Scanner(System.in);

            int character;
            while ((character = bufferedReader.read()) != -1) {
                if (character < 97) {
                    characterArray.add((character + 32));

                } else {
                    characterArray.add(character);
                }
                char ch = (char) character;
                //System.out.println(ch);
            }
            //System.out.println(characterArray + "array before removal");
            characterArray.removeAll(Arrays.asList(64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,123,124,125,126,127,128,155,156,157,158,159));
            int n = characterArray.size();
            int mostcommon = 0;
            mostcommon = mostFrequent(characterArray, n);


            System.out.println("most common character number is " + "'" + mostcommon + "'");
            System.out.println("most common character is " + "'" + (char) mostcommon + "'");
            //System.out.println(characterArray + "array after removal");
            System.out.println("What letter would you like to count");
            String findletter = inputScanner.nextLine();
            char characterOutput = findletter.charAt(0);
            if (Character.isAlphabetic(characterOutput)) {
                System.out.println(Character.toLowerCase(characterOutput) + " showed up: " + Collections.frequency(characterArray, (int) Character.toLowerCase(characterOutput)) + " times.");
            } else {
                System.out.println("Please enter a Latin alphabetic character.");
            }

            System.out.println("debug array" + characterArray);
            for (int i = 97; i<123; i++) {
                System.out.println((char)i + " showed up: " + percentage(characterArray, i) + "%");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
