import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MazeRunner {
    private static File roomFile = new File("rooms.txt");
    private static File descFile = new File ("descriptions.txt");
    private static List<String> rooms;
    private static List<String> descs;
    private static boolean gameActive;
    
    public static void main(String[] args) throws Exception {
        FileSys files = new FileSys(roomFile, descFile);
        rooms = files.getRooms();
        descs = files.getDescriptions();
        // Get my files
        Maze m = new Maze(rooms, descs);
        Player p = new Player(m);
        gameActive = true;
        Input input = new Input(new Scanner(System.in));
        game(m, p, input);
        input.get().close();

    }

    private static void game(Maze m, Player p, Input i) {
        p.sendMsg("You wake up dazed and confused...");
        p.sendMsg("You are inside what appears to be a labrynth of sorts, expanding in both directions");
        String input = i.getTrueStr("Type in a direction to go: Left or Right");
        // Transforms players input into 1 or -1 to move the player, or returns 0 otherwise
        int firstDirection = i.transformDirection(input);

        // Intro just checks for the first direction
        while (firstDirection == 0|| firstDirection == -1) {
            String newDir = correctInput(p, "[ Left  Right ]", i);
            firstDirection = i.transformDirection(newDir);
        }
        // p = 1

        p.goTo(firstDirection, m);
        runLoop(m, p, i);
    }
    private static String correctInput(Player p, String validIn, Input s) {
        p.sendMsg("Invalid input!");
        return s.getTrueStr("Valid inputs include: " + validIn);
    }

    private static void runLoop(Maze m, Player p, Input i) {
        // if there are 5 rooms, the loop should end when path has 4 rooms
        String validIn = "[Left, Right, Back]";
        int roomCount = rooms.size();
        System.out.println("Room count is " + roomCount);

        // Room at 1
        Room room = m.createRandomRoom(p.getLocation());

        while (gameActive) {
            sendDebug();
            // Players new location will be their current location +/- 1 depending on tehir input
            p.sendMsg("You enter into " + room.name + "");
            p.sendMsg("Where will you go next?");
            String input = i.getTrueStr();
            int increment =i.transformDirection(input);
            p.goTo(increment, m);
            room = m.createRandomRoom(p.getLocation());
            // 
            // Path is 5 rooms
            // Rooms is 3 long

            int pathCount = m.getPath().size();

            p.sendMsg(roomCount + " : " + pathCount);

            if ((pathCount - roomCount == roomCount)) {
                p.sendMsg("Now");
                gameActive = false;
            }

            
            

            
            
        }
        // if (rooms.isEmpty()) {
        //     p.sendMsg("You enter the last room that is empty except for a latch which keeps closed a door - behind it shines a glimmer of the sun you once knew");
        //     p.sendMsg("Do you open the lever and escape, or become part of the labrynth, forver.");
        //     p.sendMsg("Choose the Blue Pill: Freedom from the labrynth - faith in mandkind");
        //     p.sendMsg("Choose the Red Pill: Freedom from mankind - faith in the labrynth");
        //     String answer = i.getTrueStr("Which pill do you take, blue or red?");
        //     validIn = "[Blue, Red]";
        //     while (!answer.equals("blue") && !answer.equals("red")) {
        //         String newAnswer = correctInput(p, validIn, i);
        //         answer = newAnswer;
        //     }
        //     p.sendMsg("Very well, I hope you are happy with your decision");

            
        // }
    }
    public static void sendDebug(Maze m) {
        System.out.print("Rooms List: ");
        for (String s: rooms) {
            System.out.print(s + ",");
        }
        System.out.println(" L = " + rooms.size());

        System.out.print("Path List: ");
        for (Room r: m.getPath()) {
            System.out.print(r.name + ",");
        }
        System.out.println(" L = " + m.getPath().size());
        System.out.println("");
    }
}