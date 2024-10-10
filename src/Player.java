import java.util.ArrayList;

//Holder styr på spillerens position og håndtere bevægelser
public class Player {
    private Room currentRoom;
    private String lastAttemptedDirection;
    private ArrayList<Item> inventoryList;//makes an ArrayList, shows which items the player has
    private int health;
    private Weapon equippedWeapon = null;

    //constructor
    public Player(Room startingRoom) {
        currentRoom = startingRoom;
        inventoryList = new ArrayList<Item>();
        health = 100; //sets player health to 100 from the start
    }

    //get method to get the current room the player is in
    public Room getCurrentRoom() {
        return currentRoom;
    }

    //set method to set currentRoom
    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    //getter method to get name of current room
    public String getName() {
        return currentRoom.getName();
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

        getCurrentRoom().printListOfItemsRoom(); //output: rooms items
        System.out.println();
        getCurrentRoom().printListOfEnemiesRoom(); //output: enemies
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
    public String unlockDoor() {
        String direction = getLastAttemptedDirection();
        Item item = findItem("key"); //looks through players inventory to see if there's a key

        if (direction != null && showInventory().contains("key")) { //if direction isn't null and players inventory contains a key
            Room currentRoom = getCurrentRoom();
            currentRoom.unlockDoor(direction); //unlocks the door, from the currentroom, in the direction the user has typed
            setLastAttemptedDirection(null); //resets the direction user has typed
            removeItemInventorylist(item);//removes the key after it's been used
            return "The door to the " + direction + " is now unlocked!";
        } else if (!showInventory().contains("key")) { //if players inventory doesn't have a key
            return "You need a key to open the door! Maybe you can find one in another room?";
        } else {
            return "There is no locked door to unlock in that direction";
        }
    }

    //method to add an item to the ArrayList
    public void addInventoryList(Item item) {
        inventoryList.add(item);
    }

    //methode to remove an item from the ArrayList
    public void removeItemInventorylist(Item item) {
        inventoryList.remove(item);
    }


    //method to drop an item in a room
    public String dropItem(String itemToDrop) {
        Item item = findItem(itemToDrop);

        if (item != null) {
            removeItemInventorylist(item); //remover item from your inventory
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
        if (inventoryList.isEmpty() && getEquippedWeapon() == null) {
            return "Your inventory is empty and you haven't equipped any weapon...";
        } else if (inventoryList.isEmpty()) {
            return "Your inventory is empty... \n Current equipped weapon: " + getEquippedWeapon();
        } else {
            for (Item item : inventoryList) {
                showItems += (item.getLongName()) + ", "; //finds the item in inventory and adds to the empty string
            }
            showItems = showItems.substring(0, showItems.length() - 2) + "."; //removes the last comma
            //substring cuts the showItems string, starts from index 0, ends at the Strings length, -2 because then we want get the space and comma
        }
        return "You have collected: " + showItems + "\n Current equipped weapon: " + getEquippedWeapon();
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

    public int getHealth() {
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

    //method so health can not be more tha 100
    public void increaseHealth(int amount) {
        health = Math.min(health + amount, 100); //Ensure max health is 100
    }

    //method so health can not be under 0
    public void decreaseHealth(int amount) {
        health = Math.max(health + amount, 0); //Ensure min health is 0
        if (health <= 0) {
            System.out.println("You died.");
            System.exit(0); //ending program because player died
        }
    }

    public String eatFood(String foodName) {
        Item item = findItem(foodName); //find item (food) in players inventory

        if (item == null) {
            item = currentRoom.searchItem(foodName); //if the item isn't in players inventory, we look in the currentroom
        }

        if (item == null) { //if the item isn't to be found in current room
            return "There's no " + foodName + " here."; //returns output and stop the method
        }

        if (getHealth() >= 100) { //if health is 100 or over you cannot eat more food which ha positive healthpoints
            if (item instanceof Food) {
                Food food = (Food) item; //explicit down cast /typecasting
                int foodHealth = food.getHealthPoints();
                if (foodHealth > 0) { //if food health points is positive (over 0)
                    return "Your health is already full";
                }
            }
        }

        //we have to check if the item is food or a thing
        if (item instanceof Food) { //checks if the item is a food
            Food food = (Food) item; //typecaster item to food, so I can get specific methods which is located in our Food class
            int foodValue = food.getHealthPoints();

            if (foodValue > 0) {
                increaseHealth(foodValue); //increase players health
                String result = "Good choice by eating " + foodName + ", your health is now up at " + getHealth() + ".";

                //removes the food from current room or players inventory
                if (inventoryList.contains(item)) {
                    removeItemInventorylist(item); //removes from inventory
                } else {
                    currentRoom.removeItem(item); //removes from current room
                }
                return result;
            } else {
                decreaseHealth(foodValue);
                String result = "Oh no... After eating " + foodName + ", your health is now down at " + getHealth() + ".";

                //removes the food from current room or players inventory
                if (inventoryList.contains(item)) {
                    removeItemInventorylist(item); //removes from inventory
                } else {
                    currentRoom.removeItem(item); //removes from current room
                }
                return result;
            }

        } else {
            return "You cannot eat " + foodName + "!";
        }
    }

    //get current equipped weapon
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public String equipWeapon(String weaponName) {
        Item item = findItem(weaponName); //finds item (weapon) in players inventory

        if (item == null) {
            return "You don't have a " + weaponName + " in your inventory."; //return this output and stop the method
        } else if (!(item instanceof Weapon)) { //checks if the item is a weapon
            return "This item is not a weapon.";
        } else {
            Weapon newWeapon = (Weapon) item; // typecasting, down cast item object, so I can get the methods from Weapon class

            //if we already equipped a weapon, we need to put it back in inventory when we want to equip a new weapon.
            //then we can use the weapon again at another time else it will be lost...
            if (equippedWeapon != null) {
                inventoryList.add(equippedWeapon); //add current equippedweapon to inventory
                String previousWeaponName = equippedWeapon.getShortName();
                equippedWeapon = newWeapon;
                inventoryList.remove(newWeapon); // removes new equipped weapon from inventory
                return previousWeaponName + " is now in your inventory.\nYou have equipped " + newWeapon.getShortName() + ".";
            }

            // If there isn't an equipped weapon already.
            equippedWeapon = newWeapon;
            inventoryList.remove(newWeapon); // removes equipped from inventory
            return "You have equipped " + newWeapon.getShortName() + ".";
        }
    }

    public String attackEnemy(String enemyName) {
        Weapon weapon = getEquippedWeapon();

        Enemy enemy = currentRoom.searchEnemies(enemyName); //search after the enemy in room


        if (getHealth() < 15) { //if players health is below 15
            return "Your health is too low to fight... Go find some food.";
        }

        if (currentRoom.getEnemiesInRoom().isEmpty()) {
            return "Your fighting the air. There's no enemies in here.";
        }else if (weapon == null) { //if player haven't equipped a weapon
            return "You don't have any equipped weapon to use.";
        } else if (!weapon.canUse()) { //if weapon can not be used bc lack of ammo
            return "Your weapon needs more ammunition!";
        } else {
            getEquippedWeapon().use(); //player use equipped weapon
            int playersWeaponDamage = getEquippedWeapon().getDamage(); //damage from players weapon

            int enermyHealth = enemy.hitByPlayer(playersWeaponDamage);//enemy's health reduced by that damage from players weapon
            enemy.attackPlayer(this); //enemy attacks player

            String enemyHealthMessage = "You have damaged the " + enemy.getLongName() + " with your " + getEquippedWeapon() + ".\n" + enemy.getLongName() + "'s health: " + enermyHealth + ".\n";
            String playerHealthMessage = "You've been attacked by " + enemy.getLongName() + " with " + enemy.getWeapon() + ".\nYour health: " + getHealth() + ".";
            Weapon enemyWeapon = enemy.getWeapon(); //gets the weapon the enemy has

            if (enemy.getHealth() == 0) {
                getCurrentRoom().removeEnemy(enemy); //removes enemy from room
                getCurrentRoom().addItemRoom(enemyWeapon); //add weapon to current room
                return "You have defeated the " + enemy.getLongName() + ", " + enemyWeapon + " has been dropped into the room.";
            } else {
                return enemyHealthMessage + playerHealthMessage;
            }
        }
    }
}
