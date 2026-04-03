import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSys {
    private File rooms;
    private File description;
    private Scanner roomScan;
    private Scanner descScan;

    public FileSys(File roomFile, File descFile) throws Exception {
        rooms = roomFile;
        roomScan = new Scanner(roomFile);
        description = descFile;
        descScan = new Scanner(descFile);
    }
    public void makefile() {


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
        return allRooms;
        
    }
    public List<String> getDescriptions() {
        int i = 0;
        List<String> allDescriptions = new ArrayList<String>();
         //colin - set length like this might lead to problems if you have more than 5 rooms, since the following code block will keep appending until no more room files left, even if it overflows
         while (roomScan.hasNextLine()) {
            String s = roomScan.nextLine();
            allDescriptions.add(i, s);
             // colin - i think this is why it keeps listing random numbers, see following code comment
            i++;
        }
        return allDescriptions;
        
    }
}