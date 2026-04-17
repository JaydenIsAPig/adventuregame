import java.io.File;
import java.util.List;
import java.util.Scanner;

public class GameRunner {
    private static File roomFile = new File("rooms.txt");
    private static File descFile = new File ("descriptions.txt");
    private static List<String> rooms;
    private static List<String> descs;
    private static boolean gameActive;
    private static int roomCount;
    
    public static void main(String[] args) throws Exception {
        // Use file system to create array lists of rooms and descriptions
        FileSys files = new FileSys(roomFile, descFile);
        rooms = files.getRooms();
        descs = files.getDescriptions();
        
        // instantiate my classes with the array lists
        Maze m = new Maze(rooms, descs);
        Player p = new Player(m);
        Input input = new Input(new Scanner(System.in));

        // Set game active
        gameActive = true;
        roomCount = rooms.size();

        // Game start
        game(m, p, input);
        input.get().close();
    }


    private static void game(Maze m, Player p, Input i) {
        p.sendMsg("You wake up dazed and confused...");
        p.sendMsg("You are inside what appears to be a labrynth of sorts, expanding in both directions");
        String input = i.getTrueStr("Type in a direction to go: Left or Right");
        // Transforms players input into 1 or -1 to move the player, or returns 0 otherwise
        int firstDirection = i.transformDirection(input);

        // Intro just checks for the first direction, cant be 0 (wouldn't go anywhere) or -1 (would be going backwards)
        while (firstDirection == 0|| firstDirection == -1) {
            String newDir = correctInput(p, "[ Left  Right ]", i);
            firstDirection = i.transformDirection(newDir);
        }
        // p = 1
        // p.goTo(firstDirection, m);
        // runLoop(m, p, i);
        gameLoop(p, m, firstDirection, i);
        endGame(m, p, i);

    }


    private static void gameLoop(Player p, Maze m, int direction, Input i) {
        String validIn = "[Left, Right, Back]";
        while (gameActive) {
            Room room = p.goToRoom(direction, m);
            if (direction == -1) {
                p.sendMsg("You return to " + room.name + "");
                p.sendMsg("You once again see " + room.getDescription());
            }
            else {
                p.sendMsg("You enter into " + room.name + "");
                p.sendMsg("You see " + room.getDescription());
            }
            // Checks if you have any rooms left from the list, if not then break out the game loop
            if (roomCount == (m.getPath().size() + 1)) {
                break;
            }
            String input = i.getTrueStr("Where will you go next?  ");
            direction = i.transformDirection(input);
            while (direction < 1) {
                if (direction == 0) {
                    direction = i.transformDirection(correctInput(p, validIn, i));
                    continue;
                }
                else if (direction == -1 && p.getLocation() <= 0) {
                    p.sendMsg("You can't go back any further!");
                    direction = i.transformDirection(i.getTrueStr("Valid inputs include: [Left, Right]"));
                }
                else break;
            }
        }
    }

    private static void endGame(Maze m, Player p, Input i) {
            p.sendMsg("but it is empty except for a latch which keeps closed a door - behind it shines a glimmer of the sun you once knew");
            p.sendMsg("Do you open the lever and escape, or become part of the labrynth, forver.");
            p.sendMsg("Choose the Blue Pill: Freedom from the labrynth - faith in mandkind");
            p.sendMsg("Choose the Red Pill: Freedom from mankind - faith in the labrynth");
            String answer = i.getTrueStr("Which pill do you take, blue or red?");
            String validIn = "[Blue, Red]";
            while (!answer.equals("blue") && !answer.equals("red")) {
                String newAnswer = correctInput(p, validIn, i);
                answer = newAnswer;
            }
            p.sendMsg("Very well, I hope you are happy with your decision");
    }








    private static String correctInput(Player p, String validIn, Input s) {
        p.sendMsg("Invalid input!");
        return s.getTrueStr("Valid inputs include: " + validIn);
    }


    public static void sendDebug(Maze m, Player p, int offSet) {
        System.out.println("");
        System.out.print("Rooms List: ");
        for (String s: rooms) {
            System.out.print(s + ",");
        }
        System.out.println(" L = " + rooms.size());
        System.out.println("");
        System.out.print("Descriptions List: ");
        for (String s: descs) {
            System.out.print(s + ",");
        }
        System.out.println(" L = " + descs.size());
        System.out.println("");
        System.out.print("Path List: ");
        for (Room r: m.getPath()) {
            System.out.print(r.name + ",");
        }
        System.out.println(" L = " + m.getPath().size());
        int offSize = (m.getPath().size() + offSet);
        System.out.println("Path: "+offSize + " Room: " + roomCount + " Desc: " + descs.size());


        System.out.println("Player Room:" + p.getRoom().name + " Path[pos@" + (p.getLocation())+"]");
        System.out.println("");
    }
    
}
