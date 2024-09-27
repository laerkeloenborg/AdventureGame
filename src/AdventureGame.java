//Controller, styrer spillets flow og koordinere mellem klasserne
public class AdventureGame {
    private String lastAttemptedDirection;
    private Player player;

    //Gamemap generates
    public AdventureGame(Map gameMap){
        this.player = new Player(gameMap.getStartingRoom());
    }

    //method to get a name and description for the room the player is in
    public void lookAround() {
        Room currentRoom = player.getCurrentRoom(); //this line is in many methods else the output will fail and say that the currentRoom is null
        System.out.println("You're now in: " + player.getCurrentRoom().getName());//output: the current rooms name
        System.out.println(player.getCurrentRoom().getDescription());//output: the rooms description (whether it should be short or long) udskriver den opdaterede beskrivelse (om den skal være kort eller lang)
    }

    //metod to get the description of the current room
    public void gameRoom(){
        System.out.println(player.getCurrentRoom().getDescription());
    }

    //method to move the player in a direction
    public void movePlayer(String direction){
       Room currentRoom = player.getCurrentRoom();
        Room nextRoom = null; //lokalvariabel

        //tjekker om døren er låst
        if (currentRoom.isDoorLocked(direction)){ //if the door is locked in the direction the user write the output will be as shown in line 31
            lastAttemptedDirection = direction; //sets the lastAttemptedDirection to direction
            System.out.println("The door is locked"); //this will come as output if the door is locked
            return; //end the method if the door is locked
        }


        switch (direction.toLowerCase()){ //if user writes e.g. north the next room will be north for the current room
            case "north" : nextRoom = currentRoom.getNorth(); break;
            case "south" : nextRoom = currentRoom.getSouth(); break;
            case "east" : nextRoom = currentRoom.getEast(); break;
            case "west" : nextRoom = currentRoom.getWest(); break;
        }

        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom); //sets currentRoom as next room (it will be the room we will enter)
            lookAround(); //output: the rooms description
        }else {
            System.out.println("You cannot go this way!");
        }

    }

    //method to unlock the door in the direction the player has wished for
    public void unlockLastAttemptedDoor(){
        if (lastAttemptedDirection != null){
            Room currentRoom = player.getCurrentRoom();
            currentRoom.unlockDoor(lastAttemptedDirection); //unlocks the door, from the currentroom, in the direction the user has typed
            System.out.println("The door to the " + lastAttemptedDirection + " is now unlocked!");
            lastAttemptedDirection = null; //resets the direction user has typed
        } else{
            System.out.println("There is no locked door to unlock in that direction");
        }
    }

}





