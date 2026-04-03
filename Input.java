import java.util.Scanner;

public class Input {
    private Scanner input;
    
    public Input(Scanner scan) {
        input = scan;
    }
    public String getStr() {
        return input.nextLine();
    }
    public String getTrueStr() {
        return input.nextLine().toLowerCase();
    }
    public int getInt() {
        return input.nextInt();
    }
    public String getStr(String s) {
        System.out.println(s);
        return input.nextLine();
    }
    public String getTrueStr(String s) {
        System.out.println(s);
        return input.nextLine().toLowerCase();
    }
    public int getInt(String s) {
        System.out.println(s);
        return input.nextInt();
    }
    public Scanner get() {
        return input;
    }
    public int transformDirection(String input) {
        if (input.equals("right") || input.equals("left")) {
            return 1;
        }
        else if (input.equals("backward")) {
            return -1;
        }
        return 0;

    }


    
}
