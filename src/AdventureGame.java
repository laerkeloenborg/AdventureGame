import java.util.ArrayList;

//Controller
public class AdventureGame {
    private Player player;
    // private String lastAttemptedDirection;

    //Gamemap generates
    public AdventureGame(Map gameMap) {
        player = new Player(gameMap.getStartingRoom());
    }

    //method to get a name and description for the room the player is in
    public void lookAround() {
        player.lookAround();
    }

    //method to move the player in a direction - MOVE TO PLAYER
    public void movePlayer(String direction) {
        player.movePlayer(direction);
    }

    //metod to get the description of the current room
    public void gameRoom() {
        System.out.println(player.getCurrentRoom().getDescription());
    }

    //take item method
    public String takeItem(String itemDescription) {
        return player.takeItem(itemDescription);
    }

    //drop item method
    public String dropItem(String itemDescription) {
        return player.dropItem(itemDescription);
    }

    //show inventory method
    public String showInventory() {
        return player.showInventory();
    }

    //method to unlock the door in the direction the player has wished for
    public void unlockLastAttemptedDoor() {
        String direction = player.getLastAttemptedDirection();
        Item item = player.findItem("key"); //looks through players inventory to see if there's a key

        if (direction != null && player.showInventory().contains("key")) { //if direction isn't null and players inventory contains a key
            Room currentRoom = player.getCurrentRoom();
            currentRoom.unlockDoor(direction); //unlocks the door, from the currentroom, in the direction the user has typed
            System.out.println("The door to the " + direction + " is now unlocked!");
            player.setLastAttemptedDirection(null); //resets the direction user has typed
            player.removeItem(item);//removes the key after it's been used
        } else if(!player.showInventory().contains("key")) { //if players inventory doesn't have a key
            System.out.println("You need a key to open the door! Maybe you can find one in another room?");
        } else{
            System.out.println("There is no locked door to unlock in that direction");
        }
    }

//    public ArrayList<Item> inventory() {
//        return player.getInventoryList();
//    }

}





