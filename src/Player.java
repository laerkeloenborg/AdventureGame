import java.util.ArrayList;

//Holder styr på spillerens position og håndtere bevægelser
public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventoryList;    //makes an ArrayList, shows which items the player has

    //constructor, spilleren starter i currentRoom
    public Player(Room startingRoom) {
        currentRoom = startingRoom;
        inventoryList = new ArrayList<Item>();
    }

    //get metode til at få current room, dvs. det rum playeren befinder sig i
    public Room getCurrentRoom() {
        return currentRoom;
    }


    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
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
    public ArrayList<Item> getInventoryList() {
        return inventoryList;
    }

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
                showItems += (item.getShortName()) + ", "; //finds the item in inventory and adds to the empty string
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
