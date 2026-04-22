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
    private Scanner mobScan;
    private Map<String, String> puzzles;

    public FileSys(File roomFile, File descFile, File puzzleFile, File mobFile) throws Exception {
        roomScan = new Scanner(roomFile);
        descScan = new Scanner(descFile);
        puzzleScan = new Scanner(puzzleFile);
        puzzles = new HashMap<>();
        mobScan = new Scanner(mobFile);

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
    public List<String> getMobs() {
        int i = 0;
        List<String> allMobs = new ArrayList<>();
         while (mobScan.hasNextLine()) {
            String s = mobScan.nextLine();
            allMobs.add(i, s);
            i++;
        }
        mobScan.close();
        return shuffleList(allMobs);
        
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
            if (parts.length == 2) {
                allQuestions.add(i, parts[0]);
                putPuzzles(parts);
            }
            i++;
        }
        puzzleScan.close();
        return shuffleList(allQuestions);
    }

    private void putPuzzles(String[] args) {
            puzzles.put(args[0], args[1]);
    }

    public Map<String, String> getPuzzles() {
        return puzzles;
    }
    
    private static List<String> shuffleList(List<String> list) {
        List<String> array = list;
        Collections.shuffle(array);
        return array;
    }
}