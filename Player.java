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

        if (!(m.roomExist(position))) {
            Room r = m.createRandomRoom(position);
            return r;
        }
        return m.path.get(position);
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
