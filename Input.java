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
        System.out.print(s);
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
        input = input.toLowerCase();
        if (input.contains("right") || input.contains("left")) {
            return 1;
        }
        else if (input.contains("back")) {
            return -1;
        }
        else return 0;

    }


    
}
