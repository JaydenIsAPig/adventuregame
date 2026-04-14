import java.util.ArrayList;
import java.util.List;

public class Maze {
    List<String> rooms;
    List<String> descs;
    List<Room> path;


    Maze(List<String> roomsList, List<String> descriptionsList) {
        rooms = roomsList;
        descs = descriptionsList;
        path = new ArrayList<Room>();
    }
    public List<String> getRooms() {
        return rooms;
    }
    public List<Room> getPath() {
        return path;
    }
    public void appendPath(Room r) {
        path.add(r);
        rooms.remove(r.name);
    }

    public Room createPuzzleRoom(int location) {
        // create the room
        Room r = new PuzzleRoom(rooms.get(location), location, descs.get(location-1));
        return r;
    }
    public Room createCombatRoom(int location) {
        // create the room
        Room r = new CombatRoom(rooms.get(location), location, descs.get(location-1));

        return r;
    }
    public Room createKillRoom(int location) {
        // create the room
        Room r = new KillRoom(rooms.get(location), location, descs.get(location-1));

        return r;
    }
    public Room createSceneRoom(int location) {
        // create the room
        Room r = new SceneRoom(rooms.get(location), location, descs.get(location-1));

        return r;
    }


    public Room createRandomRoom(int location) {
        Room newRoom;
        Double random = Math.floor(Math.random()*16) + 1;
        // generates a number 1-16

        // 5/16 chance that its combat, puzzle, or scene room
        // 1/16 chance that it is a kill room where the player must react quick to save their life

        if (5 <= random) {
            newRoom = createCombatRoom(location);
        }
        else if (10 <= random) {
            newRoom = createPuzzleRoom(location);
        }
        else if (15 <= random) {
            newRoom = createSceneRoom(location);
        }
        else { newRoom = createKillRoom(location); }

        appendPath(newRoom);
        
        return newRoom;
    }
    public void nextRoom() {
        
    }

    public boolean roomExist(int index) {
        if (0 <= index && index <= (path.size() - 1)) {
            return true;
    }
    return false;
}


    


    
}
