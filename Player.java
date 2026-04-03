public class Player {
    private int health = 5;
    private int position = 0;
    private Maze m;
    // private Weapon tool;
    Player(Maze maze) {
        m = maze;
    }
    
    public void goTo(int amnt, Maze m) {
        position += amnt;

        
    }

    public int getLocation() {
        return position;
    }

    public void sendMsg(String s) {
        System.out.println(s);
    }
    
}
