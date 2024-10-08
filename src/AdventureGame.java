//Controller
public class AdventureGame {
    private Player player;

    //Gamemap generates
    public AdventureGame(Map gameMap) {
        player = new Player(gameMap.getStartingRoom());
    }

    //method to get a name and description for the room the player is in
    public void lookAround() {
        player.lookAround();
    }

    //method to move the player in a direction
    public void movePlayer(String direction) {
        player.movePlayer(direction);
    }

    //method to unlock door
    public String unlockDoor() {
        return player.unlockDoor();
    }

    /*method to get the name and description of the current room, useful when I go back to a room I have visited before,
    because if I use lookAround the output will be room name + description and below the short description will be printed
    */
    public String getName() {
        return player.getName();
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

    //show players health
    public String showHealth() {
        return player.showHealth();
    }

    //method to eat food
    public String eatFood(String foodName) {
        return player.eatFood(foodName);
    }

    //method to equip weapon
    public String equipWeapon(String weaponName) {
        return player.equipWeapon(weaponName);
    }

    //method to attack enemy
    public String attackEnemy(String enemyName) {
        return player.attackEnemy(enemyName);
    }

}





