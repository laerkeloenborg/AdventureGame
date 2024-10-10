import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private String shortDescription;
    private Room north, east, south, west;
    private boolean lockNorth, lockEast, lockSouth, lockWest; //lås for nord osv.
    private boolean visited;
    private ArrayList<Item> itemsRooms = new ArrayList<Item>();    //makes an ArrayList, shows which items there is in the rooms
    private ArrayList<Enemy> enemiesInRoom = new ArrayList<Enemy>();

    //constructor
    public Room(String name, String description, String shortDescription) {
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.lockNorth = false; //Standard is that door is unlocked
        this.lockEast = false;
        this.lockSouth = false;
        this.lockWest = false;
        this.visited = false; //Standard is that no room has been visited
    }


    //method to lock the door
    public void lockDoor(String direction) {
        switch (direction.toLowerCase()) {
            case "north":
                lockNorth = true;
                break;//if user types north the door is locked
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

    //method to unlock a door
    public void unlockDoor(String direction) {
        switch (direction.toLowerCase()) {
            case "north":
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

    //Method to check if the door is locked
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

    // get method to get the rooms name
    public String getName() {
        return name;
    }

    //get metode to get rooms description
    public String getDescriptionIfVisited() {
        if (!visited) { //If room is not visited before
            visited = true; //rooms now visited
            return description; //output: long description
        } else {
            return "";
        }
    }

    //set & get methods
    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }


    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }


    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }


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
    public String printListOfItemsRoom() {
        if (itemsRooms.isEmpty()) {
            System.out.println("There's no items in this room.");
        } else {
            System.out.println("Items in this room: ");
            for (Item i : itemsRooms) {
                System.out.println("- " + i.getLongName());
            }
        }
        return "";
    }

    //method to get the list of enemies
    public ArrayList<Enemy> getEnemiesInRoom() {
        return enemiesInRoom;
    }

    //add enemy in room
    public void addEnemy(Enemy enemy) {
        enemiesInRoom.add(enemy);
    }

    //remove enemy from room
    public void removeEnemy(Enemy enemy) {
        enemiesInRoom.remove(enemy);
    }

    //method to search after an enemy
    public Enemy searchEnemies(String enemyDescription) {
        for (Enemy enemy : enemiesInRoom) { //looks through the arrayList enemiesInRoom for the enemy
            if (enemy.getShortName().equalsIgnoreCase(enemyDescription)) { //if the enemy is in the arrayList
                return enemy; //return enemy
            }
        }
        return null; //returns null if enemy isn't there
    }

    //method to print list of enemies
    public String printListOfEnemiesRoom() {
        String enemies = "";
        int i = 0;

        if (enemiesInRoom.isEmpty()) {
            return "Dont worry, there is no enemies in here...";
        } else {
            System.out.println("ENEMY ALERT -- Enemies in here: ");
            for (Enemy enemy : enemiesInRoom) {
                enemies += enemiesInRoom.get(i).getLongName();
                System.out.println("- " + enemy.getLongName());
            }

            return "";
        }
    }

}
