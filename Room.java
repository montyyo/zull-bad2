import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> nextRoom;
    
    private String item;
    private float weight;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description,String item,float weight) 
    {
        this.description = description;
        this.nextRoom = new HashMap<>();
        this.item = item;
        this.weight = weight;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east exit.
     * @param southeast The southeast exit.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east,Room southeast, Room south, Room west) 
    {
        nextRoom.put("nort", north);
        nextRoom.put("east", east);
        nextRoom.put("southeast", southeast);
        nextRoom.put("south", south);
        nextRoom.put("west", west);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    
    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String longDescription = "You are " + getDescription()+ "\n" + "item" + item + "weight" + weight  + "Exits: " ;
        longDescription += getExitString();

        return longDescription;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String exit = "";
        Iterator itNextRoom = nextRoom.keySet().iterator();
        while(itNextRoom.hasNext()){
            exit += itNextRoom.next() + " ";
        }

        return exit + "\n";
    }

    /**
     * retona la habitacion de salida en la coordenada indicada o null si no hay puerta en esa coordenada
     * @param coordenada coordenada de la que se quiere saber la salida
     * @return habitacion en la coordenada indicada o null en caso de no existir
     */
    public Room getExit(String coordenada){

        return nextRoom.get(coordenada);
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        nextRoom.put(direction, neighbor);
    }
}
