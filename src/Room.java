import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

//repræsentere et rum i spillet, så der kan dannes flere
public class Room {

    //div. instansvariabler
    private String name;
    private String description;
    private String shortDescription;
    private Room north, east, south, west;
    private boolean lockNorth, lockEast, lockSouth, lockWest; //lås for nord osv.
    private boolean visited;
    private ArrayList<Item> itemsRooms = new ArrayList<Item>();    //makes an ArrayList, shows which items there is in the rooms

    //constructor
    public Room(String name, String description, String shortDescription) {
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.lockNorth = false; //standarden er at dørene ikke er låste
        this.lockEast = false;
        this.lockSouth = false;
        this.lockWest = false;
        this.visited = false; //standarden er at ingen af rummene er besøgt inden det går igang
    }


    //metode til at låse døren
    public void lockDoor(String direction) {
        switch (direction.toLowerCase()) {
            case "north":
                lockNorth = true;
                break;//hvis brugere skriver north er døren låst osv.
            case "east":
                lockEast = true;
                break;
            case "south":
                lockSouth = true;
                break;
            case "west":
                lockWest = true;
                break;
        }
    }

    //metode til at låse døren op
    public void unlockDoor(String direction) {
        switch (direction.toLowerCase()) {
            case "north": //hvis brugere skriver north er døren låst op osv.
                lockNorth = false;
                break;
            case "east":
                lockEast = false;
                break;
            case "south":
                lockSouth = false;
                break;
            case "west":
                lockWest = false;
            default:
                System.out.println("invalid direction!");
        }
    }

    //Metode til at finde ud af om døren er låst
    public boolean isDoorLocked(String direction) {
        switch (direction.toLowerCase()) {
            case "north": //returns if the lock at e.g. north is true(locked) or false (unlocked)
                return lockNorth;
            case "east":
                return lockEast;
            case "south":
                return lockSouth;
            case "west":
                return lockWest;
            default:
                return false; //unknown direction
        }
    }

    // get metode til at få fat på de forskellige rooms navne
    public String getName() {
        return name;
    }

    //get metode til at få fat på de forskellige beskrivelser af rummene
    public String getDescription() {
        if (!visited) { //hvis ikke besøgt gør det nedestående
            visited = true; //rummet er nu besøgt
            return description; //udskriver lang beskrivelse
        } else {
            return shortDescription; //output: short description og the room (a room you have visited before) Udskriver kort beskrivelse når rummet besøges igen
        }
    }

    //set & get metode på North
    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    //set & get metode på East
    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    //set & get metode på South
    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    //set & get metode på West
    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    //method to add item to a room
    public void addItemRoom(Item item) {
        itemsRooms.add(item);
    }

    //method to remove an item in a room
    public Item removeItem(Item item) {
        itemsRooms.remove(item);
        return item;
    }

    //method to search after an item in a room
    public Item searchItem(String itemDescription) {
        for (Item item : itemsRooms) { //looks through the arrayList itemsRoom for the item
            if (item.getShortName().equalsIgnoreCase(itemDescription)) { //if the item is at the arrayList
                return item; //returns item
            }
        }
        return null;
    }

    //method to list the items in the room
    public void listArrayRooms() {
        if (itemsRooms.isEmpty()) {
            System.out.println("There's no items in this room.");
        } else {
            System.out.println("Items in this room: ");
            for (Item i : itemsRooms) {
                System.out.println("- " + i.getLongName());
            }
        }

    }
}
