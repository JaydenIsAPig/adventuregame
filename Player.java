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
    public Room goToRoom(int amnt, Maze m) {
        position += amnt;
        int futurePos = position - 1;
        if (m.roomExist(futurePos)) {
            return m.path.get(futurePos);
        }
        return m.createRandomRoom(futurePos);
    }
    public Room getRoom() {
        return m.path.get(position - 1);
    }

    public int getLocation() {
        return position;
    }

    public void sendMsg(String s) {
        System.out.println(s);
    }
    
}
