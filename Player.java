public class Player {
    private int health;
    private int position;
    private Maze m;
    // private Weapon tool;
    Player(Maze maze) {
        position = -1;
        health = 5;
        m = maze;
    }
    public Room goToRoom(int amnt, Maze m) {
        position += amnt;
        int futurePos = position;
        if (m.roomExist(futurePos)) {
            if (m.descExist(futurePos)) {
                return m.path.get(futurePos);
            }
        }
        return m.createRandomRoom(futurePos);
    }
    public Room getRoom() {
        return m.path.get(position);
    }

    public int getHealth() {
        return health;
    }
    public void loseHealth(int i) {
        health -= i;
    }

    public int getLocation() {
        return position;
    }

    public void sendMsg(String s) {
        System.out.println(s);
    }
    
}
