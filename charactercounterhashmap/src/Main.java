import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        FileReader reader = new FileReader("C:\\Users\\Tiger Zhao\\IdeaProjects\\charactercounterhashmap\\src\\text.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        HashMap<Character, Integer> characterMap = new HashMap<>();
        for (int i = 97; i < 123; i++){
            characterMap.put((char) i, 0);
        }
        int character;
        while ((character = bufferedReader.read()) != -1) {
            if (Character.isAlphabetic(character)){
                character = Character.toLowerCase(character);
                characterMap.put((char) character, (characterMap.get((char) character) + 1));
            }
        }
        System.out.println(characterMap);

    }
}
