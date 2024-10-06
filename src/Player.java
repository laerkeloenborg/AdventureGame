import java.util.ArrayList;

//Holder styr på spillerens position og håndtere bevægelser
public class Player {
    private Room currentRoom;
    private String lastAttemptedDirection;
    private ArrayList<Item> inventoryList;//makes an ArrayList, shows which items the player has
    private int health;

    //constructor
    public Player(Room startingRoom) {
        currentRoom = startingRoom;
        inventoryList = new ArrayList<Item>();
        health = 100;
    }

    //get method to get the current room the player is in
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getName(){
        return currentRoom.getName();
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
        String roomDescription = getCurrentRoom().getDescriptionIfVisited();
        System.out.println(roomDescription);//output: the rooms description (whether it should be short or long)

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
            lookAround(); //output: the rooms description, else the output will only be going xx
        } else {
            System.out.println("You cannot go this way!");
        }
    }


    //method to unlock the door in the direction the player has wished for
    public void unlockLastAttemptedDoor() {
        String direction = getLastAttemptedDirection();
        Item item = findItem("key"); //looks through players inventory to see if there's a key

        if (direction != null && showInventory().contains("key")) { //if direction isn't null and players inventory contains a key
            Room currentRoom = getCurrentRoom();
            currentRoom.unlockDoor(direction); //unlocks the door, from the currentroom, in the direction the user has typed
            System.out.println("The door to the " + direction + " is now unlocked!");
            setLastAttemptedDirection(null); //resets the direction user has typed
            removeItem(item);//removes the key after it's been used
        } else if(!showInventory().contains("key")) { //if players inventory doesn't have a key
            System.out.println("You need a key to open the door! Maybe you can find one in another room?");
        } else{
            System.out.println("There is no locked door to unlock in that direction");
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
    public ArrayList<Item> getInventoryList() {
        return inventoryList;
    }

    //method to drop an item in a room
    public String dropItem(String itemToDrop) {
        Item item = findItem(itemToDrop);

        if (item != null) {
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
    public String showInventory() {
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

    public int getHealth(){
        return health;
    }

    //method to show healthpoints, ---ÆNDRE SÅ POINTET KOMMER UD SÅLEDES CURRENT HEALTH: XX OGSÅ FORKLARING EFTERFØLGENDE
    public String showHealth() {
        System.out.println("Current health: " + health + ". ");
        if (health >= 80) {
            System.out.println("You're fit for fight!");
        } else if (health >= 50) {
            System.out.println("You're in decent health, but be careful.");
        } else if (health >= 25) {
            System.out.println("You're in bad health, be cautious.");
        } else {
            System.out.println("You're in critical health.");
        }
        return "";
    }

    //method so health can be more tha 100
    public void increaseHealth(int amount) {
        health = Math.min(health + amount, 100); //Ensure max health is 100
    }

    //method so health can be under 0
    public void decreaseHealth(int amount) {
        health = Math.max(health + amount, 0); //Ensure min health is 0
        if (health <= 0) {
            System.out.println("You died.");
            System.exit(0); //ending program because player died
        }
    }

    //FIKS SÅ MAN IKKE KAN FÅ MERE END 100 I HEALTH
    public void eatFood(String foodName) {
            Item item = findItem(foodName); //find item in players inventory

            if (item == null) {
                item = currentRoom.searchItem(foodName); //if the item isn't in players inventory, we look in the currentroom
            }

            if (item == null) { //if the item isn't to be found in current room
                System.out.println("There's no " + foodName + " here.");
                return; //stops the method
            }

        if(getHealth() >= 100) { //if health is 100 or over you cannot eat more food which ha positive healthpoints
            if (item instanceof Food) {
                Food food = (Food) item; //explicit down cast /typecasting
                int foodHealth = food.getHealthPoints();
                if (foodHealth > 0) { //if food health points is positive (over 0)
                    System.out.println("Your health is already full");
                    return; //stops the method
                }
            }
        }

            //we have to check if the item is food or a thing
            if (item instanceof Food) { //checks if the item is a food
                Food food = (Food) item; //typecaster item to food, so I can get specific methods which is located in our Food class
                int foodValue = food.getHealthPoints();

                if(foodValue > 0){
                    increaseHealth(foodValue); //increase players health
                    System.out.println("Good choice by eating " + foodName + ", your health is now up at " + getHealth() + "." );
                } else{
                    decreaseHealth(foodValue);
                    System.out.println("Oh no... After eating " + foodName + ", your health is now down at " + getHealth() + ".");
                }

                //removes the food from current room or players inventory
                if (inventoryList.contains(item)) {
                    removeItem(item); //removes from inventory
                } else {
                    currentRoom.removeItem(item); //removes from current room
                }
            } else {
                System.out.println("You cannot eat " + foodName + "!");
            }
        }
}
