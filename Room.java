import java.util.ArrayList;
import java.util.List;

abstract class Room{
    String name;
    int loc;

    Room(String nombre, int location) {
        name = nombre;
        loc = location;
    }

    public int getLocation() {
        return loc;
    }


    
    
}
    interface InteractableRoom {
        public static final String output = "";

    }

    class PuzzleRoom extends Room implements InteractableRoom {

        PuzzleRoom(String nombre, int location) {
            super(nombre, location);
            //TODO Auto-generated constructor stub
        }
        
    }
    class CombatRoom extends Room implements InteractableRoom {

        CombatRoom(String nombre, int location) {
            super(nombre, location);
            //TODO Auto-generated constructor stub
        }
        
    }
    class KillRoom extends Room {

        KillRoom(String nombre, int location) {
            super(nombre, location);
            //TODO Auto-generated constructor stub
        }
        
    }
    class SceneRoom extends Room {

        SceneRoom(String nombre, int location) {
            super(nombre, location);
            //TODO Auto-generated constructor stub
        }
        
    }