import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        List<String> allRooms = new ArrayList<String>();
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
        List<String> allDescriptions = new ArrayList<String>();
         while (descScan.hasNextLine()) {
            String s = descScan.nextLine();
            allDescriptions.add(i, s);
            i++;
        }
        descScan.close();
        return shuffleList(allDescriptions);
        
    }
    private List<String> getQuestions() {
        int i = 0;
        List<String> allQuestions = new ArrayList<String>();
         while (puzzleScan.hasNextLine()) {
            String s = descScan.nextLine().trim();

            String[] parts = s.split(":", 2);

            allQuestions.add(i, s);
            i++;
        }
        descScan.close();
        return shuffleList(allQuestions);
        
    }
    
    public static List<String> shuffleList(List<String> list) {
        List<String> array = list;
        Collections.shuffle(array);
        return array;
    }
}