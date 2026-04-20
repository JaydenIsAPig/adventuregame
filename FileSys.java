import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileSys {
    private Scanner roomScan;
    private Scanner descScan;
    private Scanner puzzleScan;

    public FileSys(File roomFile, File descFile, File puzzleFile) throws Exception {
        roomScan = new Scanner(roomFile);
        descScan = new Scanner(descFile);
        puzzleScan = new Scanner(puzzleFile);
    }

    public List<String> getRooms() {
        int i = 0;
        List<String> allRooms = new ArrayList<>();
         while (roomScan.hasNextLine()) {
            String s = roomScan.nextLine();
            allRooms.add(i, s);
            i++;
        }
        roomScan.close();
        return shuffleList(allRooms);
        
    }


    public List<String> getDescriptions() {
        int i = 0;
        List<String> allDescriptions = new ArrayList<>();
         while (descScan.hasNextLine()) {
            String s = descScan.nextLine();
            allDescriptions.add(i, s);
            i++;
        }
        descScan.close();
        return shuffleList(allDescriptions);
        
    }

    public List<String> getQuestions() {
        int i = 0;
        List<String> allQuestions= new ArrayList<>();
         while (puzzleScan.hasNextLine()) {
            String s = puzzleScan.nextLine().trim();
            String[] parts = s.split(":", 2);
            
            allQuestions.add(i, s);
            i++;
        }
        puzzleScan.close();
        return shuffleList(allQuestions);

        // [0, line0]
        // [1, line1]
        // [2, line2]

        // [0, line2]
        // [1, line0]
        // [2, line1]
    }

    public Map<String, String> getPuzzles(List<String> shuffledList) {
        Map<String,String> puzzles = new HashMap<>();
         for (String line: shuffledList) {
            String s = line;
            String[] parts = s.split(":", 2);
            // line -> Question:Key
            // Hash map -> {Question, Key}
            if (parts.length == 2) {
                puzzles.put(parts[0], parts[1]);
            }
        }
        return puzzles;
        
    }
    
    public static List<String> shuffleList(List<String> list) {
        List<String> array = list;
        Collections.shuffle(array);
        return array;
    }
}