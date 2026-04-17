import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Room{
    String name;
    String desc;
    int loc;
    Input in;

    Room(String nombre, int location, String description, Input input) {
        name = nombre;
        loc = location;
        desc = description;
        in = input;
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
        public void processInput(String s1, String s2);
        public String result(String s);
    }
    class PuzzleRoom extends Room implements InteractableRoom {
        PuzzleRoom(String nombre, int location, String desc, Input i, String s) {
            super(nombre, location, desc, i);
            //TODO Auto-generated constructor stub
        }
        @Override
        public void processInput(String question, String key) {
            
            // Creates a new puzzle that can be used whenever
            boolean correct = false;
            String answer = in.getStr("? " + question + " ?");
            while (!correct) {
                if (!(answer.toLowerCase().equals(key))) {
                answer = in.getStr("Incorrect! Try again"); 
                }
                else correct = true;
            }
        }
        @Override
        public String result(String s) {
            // TODO Auto-generated method stub
            return null;
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