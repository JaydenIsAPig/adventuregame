import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileSys {
    private Scanner roomScan;
    private Scanner descScan;

    public FileSys(File roomFile, File descFile) throws Exception {
        roomScan = new Scanner(roomFile);
        descScan = new Scanner(descFile);
    }
    public List<String> getRooms() {
        int i = 0;
        List<String> allRooms = new ArrayList<String>();
         //colin - set length like this might lead to problems if you have more than 5 rooms, since the following code block will keep appending until no more room files left, even if it overflows
         while (roomScan.hasNextLine()) {
            String s = roomScan.nextLine();
            allRooms.add(i, s);
             // colin - i think this is why it keeps listing random numbers, see following code comment
            i++;
        }
        roomScan.close();
        return shuffleList(allRooms);
        
    }

    public List<String> getDescriptions() {
        int i = 0;
        List<String> allDescriptions = new ArrayList<String>();
         //colin - set length like this might lead to problems if you have more than 5 rooms, since the following code block will keep appending until no more room files left, even if it overflows
         while (descScan.hasNextLine()) {
            String s = descScan.nextLine();
            allDescriptions.add(i, s);
             // colin - i think this is why it keeps listing random numbers, see following code comment
            i++;
        }
        descScan.close();
        return shuffleList(allDescriptions);
        
    }
    
    public static List<String> shuffleList(List<String> list) {
        List<String> array = list;
        Collections.shuffle(array);
        return array;
    }
}