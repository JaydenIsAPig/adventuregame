public class Player {
    private int health = 5;
    private int position;
    private Maze m;
    // private Weapon tool;
    Player(Maze maze) {
        position = 0;
        m = maze;
    }
    
    public void goTo(int amnt, Maze m) {
        position -= amnt;

        
    }
    public Room goToRoom(int amnt, Maze m) {
        position += amnt;
        int futurePos = position;
        if (m.roomExist(futurePos)) {
            return m.path.get(futurePos);
        }
        return m.createRandomRoom(futurePos);
    }
    public Room getRoom() {
        return m.path.get(position);
    }

    public int getLocation() {
        return position;
    }

    public void sendMsg(String s) {
        System.out.println(s);
    }
    
}
