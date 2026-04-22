import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameRunner {
    private static File roomFile = new File("rooms.txt");
    private static File descFile = new File ("descriptions.txt");
    private static File puzzleFile = new File ("puzzles.txt");
    private static File mobFile = new File ("mobs.txt");
    private static List<String> rooms;
    private static List<String> descs;
    private static List<String> questions;
    private static List<String> mobs;
    private static Map<String,String> puzzles;
    private static boolean gameActive;
    private static int roomCount;
    
    public static void main(String[] args) throws Exception {
        // Use file system to create array lists of rooms and descriptions, puzzles, and questions
        FileSys files = new FileSys(roomFile, descFile, puzzleFile, mobFile);
        rooms = files.getRooms();
        descs = files.getDescriptions();
        questions = files.getQuestions();
        puzzles = files.getPuzzles();
        mobs = files.getMobs();
        

        // instantiate my classes with the array lists
        Input input = new Input(new Scanner(System.in));
        Maze m = new Maze(rooms, descs, input);
        Player p = new Player(m);


        // Set game active
        gameActive = true;
        roomCount = rooms.size();

        // Game start
        game(m, p, input);
        input.get().close();
    }


    private static void game(Maze m, Player p, Input i) {
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("You wake up dazed and confused...");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("You are inside what appears to be a labrynth of sorts, expanding in both directions...");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("");
        p.sendMsg("To start, type in the first direction to move");
        String input = i.getTrueStr("Left, Right, or Back (to go back you must go forward first):   ");
        // Transforms players input into 1 or -1 to move the player, or returns 0 otherwise
        int firstDirection = i.transformDirection(input);

        // Intro just checks for the first direction, cant be 0 (wouldn't go anywhere) or -1 (would be going backwards)
        while (firstDirection == 0|| firstDirection == -1) {
            String newDir = i.correctInput("[ Left  Right ]");
            firstDirection = i.transformDirection(newDir);
        }
        gameLoop(p, m, firstDirection, i);


    }


    private static void gameLoop(Player p, Maze m, int direction, Input i) {
        String validIn = "[Left, Right, Back]";
        int puzzleCount = puzzles.size();
        int mobCount = mobs.size();
        boolean isMonster;
        String monster;
        while (gameActive) {
            System.out.println("");
            Room room = p.goToRoom(direction, m);
            if (direction == -1) {
                p.sendMsg("You return to " + room.name + "");
                p.sendMsg("You once again see " + room.getDescription());
            }
            else {
                p.sendMsg("<-- You enter into " + room.name + " -->");
                System.out.print(" * You see " + room.getDescription());
                if (room instanceof InteractableRoom interactableRoom) {
                    boolean lost = false;
                    String result;
                    mobCount -= 1;

                    if (mobCount >= 0) {
                        isMonster = true;
                        monster = mobs.get(mobCount);
                        System.out.println(" with a " + monster + " nearby * ");
                    }
                    else {System.out.println(" * "); monster = ""; isMonster = false;}
                    
                    if (room instanceof PuzzleRoom) {
                        puzzleCount -= 1;
                        if (puzzleCount >= 0) {
                            String question = questions.get(puzzleCount);
                            if (isMonster) {
                                p.sendMsg(" * The " + monster + " wont let you leave until you answer his question: * ");
                            }
                            result = interactableRoom.processInput(question, puzzles.get(question));
                        }
                        else result = "Im supposed to ask you a riddle but I forgot it, you may pass";
                    }
                    else if (room instanceof CombatRoom) {
                        
                        if (isMonster) {
                        result = interactableRoom.processInput("!!! The " + monster + " tries to attack you !!!", monster);
                        if (result.contains("defeated")) {
                                p.loseHealth(1);
                                lost = true;
                            }

                        }
                        else result = "You see the remains of something deceased.";
                    }
                    else result = "";
                    
                    interactableRoom.result(result);
                    if (lost) {
                        p.sendMsg("You currently have "+ p.getHealth() +"/5 health points");
                    }
                    System.out.println();
                }
                else System.out.println(" * ");
            }
            if (p.getHealth() <= 0) {
                p.sendMsg("You lost all of your lives and were slain by the dungeon.");
                p.sendMsg("Bye bye.");
                break;
            }
            // Checks if you have any rooms left from the list, if not then break out the game loop
            if (roomCount == (m.getPath().size() + 1)) {
                endGame(m, p, i);
            }

            String input = i.getTrueStr("Where will you go next?  ");
            direction = i.transformDirection(input);
            while (direction < 1) {
                if (direction == 0) {
                    direction = i.transformDirection(i.correctInput(validIn));
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
                String newAnswer = i.correctInput(validIn);
                answer = newAnswer;
            }
            p.sendMsg("Very well, I hope you are happy with your decision");
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
