import java.util.ArrayList;

//Holder styr på spillerens position og håndtere bevægelser
public class Player {
    private Room currentRoom;
    private String lastAttemptedDirection;
    private ArrayList<Item> inventoryList;//makes an ArrayList, shows which items the player has

    //constructor
    public Player(Room startingRoom) {
        currentRoom = startingRoom;
        inventoryList = new ArrayList<Item>();
    }

    //get method to get the current room the player is in
    public Room getCurrentRoom() {
        return currentRoom;
    }

//set method to set currentRoom
    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    // Getter for lastAttemptedDirection
    public String getLastAttemptedDirection() {
        return lastAttemptedDirection;
    }

    // Setter for lastAttemptedDirection
    public void setLastAttemptedDirection(String direction) {
        this.lastAttemptedDirection = direction;
    }
    //method to look around in a room and get description and items printed out
    public void lookAround() {
        Room currentRoom = getCurrentRoom(); //this line is in many methods else the output will fail and say that the currentRoom is null
        System.out.println("You're now in: " + getCurrentRoom().getName());//output: the current rooms name
        System.out.println(getCurrentRoom().getDescription());//output: the rooms description (whether it should be short or long) udskriver den opdaterede beskrivelse (om den skal være kort eller lang)
        getCurrentRoom().listArrayRooms(); //output: rooms items
    }

    //method to move player from room to room
    public void movePlayer(String direction) {
        Room currentRoom = getCurrentRoom();
        Room nextRoom = null; //lokalvariabel

        //checks if the door is locked
        if (currentRoom.isDoorLocked(direction)) { //if the door is locked in the direction the user write the output will be as shown in line 31
            lastAttemptedDirection = direction; //sets the lastAttemptedDirection to direction
            System.out.println("The door is locked"); //this will come as output if the door is locked
            return; //end the method if the door is locked, else it will automatically open itself
        }

        switch (direction.toLowerCase()) { //if user writes e.g. north the next room will be north for the current room
            case "north":
                nextRoom = currentRoom.getNorth();
                break;
            case "south":
                nextRoom = currentRoom.getSouth();
                break;
            case "east":
                nextRoom = currentRoom.getEast();
                break;
            case "west":
                nextRoom = currentRoom.getWest();
                break;
        }

        if (nextRoom != null) {
            setCurrentRoom(nextRoom); //sets currentRoom as next room (it will be the room we will enter)
            lookAround(); //output: the rooms description
        } else {
            System.out.println("You cannot go this way!");
        }
    }

    //method to add an item to the ArrayList
    public void addInventoryList(Item item) {
        inventoryList.add(item);
    }

    //methode to remove an item from the ArrayList
    public void removeItem(Item item) {
        inventoryList.remove(item);
    }


    //method to get the Arraylist
//    public ArrayList<Item> getInventoryList() {
//        return inventoryList;
//    }

    //method to drop an item in a room
    public String dropItem(String itemToDrop) {
        Item item = findItem(itemToDrop);

        if(item != null){
            removeItem(item); //remover item from your inventory
            currentRoom.addItemRoom(item); //add'er the item you removed from inventory to the room
            return "You have dropped " + item.getShortName();
        } else {
            return "You don't have that item in your inventory.";
        }
    }

    //method to take an item from a room and put it in your inventory
    public String takeItem(String itemDescription) {
        Item item1 = currentRoom.searchItem(itemDescription);
        {
            if (item1.getShortName().equalsIgnoreCase(itemDescription)) { //if item your searching for is in the room
                Item collectedItem = currentRoom.removeItem(item1); //remove item from currentroom
                addInventoryList(collectedItem); //add removed item to your inventorylist
                return "The " + collectedItem.getShortName() + " is now part of your inventory";
            } else {
                return "The item you are looking for is not in this room";
            }
        }

    }

    //method to show the inventory
    public String showInventory(){
        String showItems = ""; //an empty String
        if (inventoryList.isEmpty()) {
            return ("Your inventory is empty...");
        } else {
            for (Item item : inventoryList) {
                showItems += (item.getLongName()) + ", "; //finds the item in inventory and adds to the empty string
            }
            showItems = showItems.substring(0, showItems.length() - 2) + "."; //removes the last comma
            //substring cuts the showItems string, starts from index 0, ends at the Strings length, -2 because then we want get the space and comma
        }
        return "You have collected: " + showItems;
    }

    //method to find an item in the inventorylist
    public Item findItem(String item) {
        for (Item itemName : inventoryList) {
            if (itemName.getShortName().equalsIgnoreCase(item)) {
                return itemName;
            }
        }
        return null;
    }


    //toString metoden gør at vi får room 1 udskrevet som det rum vi først befinder os i
    @Override
    public String toString() {
        return "You're now in " + currentRoom.getName() + ", " + currentRoom.getDescription();
    }


}
