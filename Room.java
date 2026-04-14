import java.util.ArrayList;
import java.util.List;

abstract class Room{
    String name;
    String desc;
    int loc;

    Room(String nombre, int location, String description) {
        name = nombre;
        loc = location;
        desc = description;
    }

    public int getLocation() {
        return loc;
    }
    public String getDescription() {
        return desc;
    }


    
    
}
    interface InteractableRoom {
        public static final String output = "";

    }

    class PuzzleRoom extends Room implements InteractableRoom {

        PuzzleRoom(String nombre, int location, String desc) {
            super(nombre, location, desc);
            //TODO Auto-generated constructor stub
        }
        
    }
    class CombatRoom extends Room implements InteractableRoom {

        CombatRoom(String nombre, int location, String desc) {
            super(nombre, location, desc);
            //TODO Auto-generated constructor stub
        }
        
    }
    class KillRoom extends Room {

        KillRoom(String nombre, int location, String desc) {
            super(nombre, location, desc);
            //TODO Auto-generated constructor stub
        }
        
    }
    class SceneRoom extends Room {

        SceneRoom(String nombre, int location, String desc) {
            super(nombre, location, desc);
            //TODO Auto-generated constructor stub
        }
        
    }