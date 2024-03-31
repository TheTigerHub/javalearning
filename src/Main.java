import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        FileReader reader = new FileReader("C:\\Users\\Tiger Zhao\\IdeaProjects\\charactercounternoarray\\src\\test.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        int[] characterArray;
        characterArray = new int[26];

        int character;
        while ((character = bufferedReader.read()) != -1) {
            if (Character.isAlphabetic(character)){
                character = Character.toLowerCase(character);
                characterArray[character-'a'] += 1;
            }
        }
        System.out.println("[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]");
        System.out.println(Arrays.toString(characterArray));
    }
}

