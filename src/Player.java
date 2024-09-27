//Holder styr på spillerens position og håndtere bevægelser
public class Player {
    private Room currentRoom;

    //constructor, spilleren starter i currentRoom
    public Player(Room startingRoom){
        currentRoom = startingRoom;
    }

    //get metode til at få current room, dvs. det rum playeren befinder sig i
    public Room getCurrentRoom(){
      return currentRoom;
    }

    public void setCurrentRoom(Room newRoom){
        this.currentRoom = newRoom;
    }

    //toString metoden gør at vi får room 1 udskrevet som det rum vi først befinder os i
    @Override
    public String toString(){
        return "You're now in " + currentRoom.getName() + ", " + currentRoom.getDescription();
    }


}
