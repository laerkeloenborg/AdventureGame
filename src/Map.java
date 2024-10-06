//vores creator, som opretter og forbinder rummene
public class Map {
    private Room startingRoom;

    public Map() {
        createMap();
    }


    //method to connect the rooms
    public void createMap() {
        Room room1 = new Room("Room 1: The Overgrown Entrance", "This room is the entrance to the abandoned temple. Walls of ancient stone are almost completely covered by vines and moss. \n" +
                "The air is humid, and a sense of something hidden lingers in the atmosphere. \n" +
                "There's two doors, one to east and one to south. \n", "You're now in: The Overgrown Entrance");
        Room room2 = new Room("Room 2: The Forgotten Library", "This room was once the temple's center of learning, but now dust and plants cover the half-rotten bookshelves. \n" +
                "The walls are filled with ancient inscriptions and puzzles. \n" + "There are to passages, one to the west and one to the east. \n", "You're now in: The Forgotten Library.");
        Room room3 = new Room("Room 3: The Cursed Altar", "This is the temple's darkest corner. \n" +
                "A weathered altar in the center is covered in ancient bloodstains from rituals performed thousands of years ago. \n" + "From here you can move to the west or head south. \n", "You're now in: The Cursed Altar");
        Room room4 = new Room("Room 4: The King's Fallen Throne", "This room was once the magnificent throne room where the temple's ruler sat. \n " +
                "Now the floor lies in ruins, and the throne is nearly toppled by the ravages of time. \n" + "The walls are covered in growth, and vines twist across the ceiling. \n" + "Now you can choose if you want to go to the north or south. \n", "You're now in: The King's Fallen Throne.");
        Room room5 = new Room("Room 5: The Sacred Tomb - The Final", "This room is the heart of the abandoned temple. \n" +
                "The room is filled with ancient frescoes that tell the story of the temple. \n" + "This is where the temple's secrets are hidden, and the player faces their greatest challenge here. \n" + "Only one way to get out of here, go south.", "You're now in: The Sacred Tomb.");
        Room room6 = new Room("Room 6: The Burial Chamber", "Room 6 is an ancient underground tomb filled with broken stones and hidden treasures. \n" +
                "Some graves have been looted, while others remain untouched, but danger lurks. \n" + "You can either head to the north or to the south from here.", "You're now in: The Burial Chamber.");
        Room room7 = new Room("Room 7: The Hall Of The Guards", "Here lived the temple's ancient guards, but now the room is filled with rusty weapons and armor. \n" +
                "The former guardians may awaken if the player is not careful. \n" + "From here you can choose between two paths, one which goes north or one which goes east.", "You're now in: The Hall Of Guards.");
        Room room8 = new Room("Room 8: The Ruined Marketplace", "The temple's marketplace, now desolate and filled with toppled statues and decaying stalls. \n" +
                "The jungle has overtaken much of the space, but traces of ancient rituals are still visible. \n" + "From here you can choose between three ways, one to the west, east or north.", "You're now in: The Ruined Marketplace.");
        Room room9 = new Room("Room 9: The Temple's Dark Labyrinth", "The deepest and most sinister place in the temple. \n" +
                "Here, the corridors are narrow, and the darkness is thick. \n" + "Many explorers have been lost here. Dangerous traps are hidden in every corner. \n" + "To get out of this darkness, you can choose between going west or north.", "You're now in: The Temple's Dark Labyrinth.");

        //combines the rooms

        room1.setEast(room2); //sets the room east for room 1 to room 2
        room1.setSouth(room4);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setWest(room7);
        room8.setNorth(room5);
        room8.setEast(room9);

        room9.setWest(room8);
        room9.setNorth(room6);

        //locks door in a specific direction
        room1.lockDoor("east"); //e.g. lock the door east for room 1 (entrance to room 2)
        room6.lockDoor("south");
        room8.lockDoor("north"); //LAV SÅ DET ER EN SPECIFIC KODE ELLER NØGLE MAN SKAL HAVE FOR AT KOMME IND


        //Add room items
        room1.addItemRoom(new Item("The holy shield", "shield"));
        room2.addItemRoom(new Item("Holy scriptures", "scriptures"));
        room3.addItemRoom(new Item("Enchanting compas", "compas"));
        room7.addItemRoom(new Item("The old key", "key")); //adds item to room4 ArrayList
        room5.addItemRoom(new Item("A mysterious amulet", "amulet"));
        room6.addItemRoom(new Item("The shining gem", "gem"));
        room4.addItemRoom(new Item("A dusty map", "map"));
        room8.addItemRoom(new Item("An old coin", "coin"));
        room9.addItemRoom(new Item("The glowing torch", "torch"));
        room9.addItemRoom(new Item("The old key", "key"));

        //add food
        room1.addItemRoom(new Food("An apple", "apple", 10));
        room2.addItemRoom(new Food("Mystical herb", "herb", 25));
        room3.addItemRoom(new Food("Monster meat", "meat", -25));
        room4.addItemRoom(new Food("Mushroom soup", "soup", -15));
        room4.addItemRoom(new Food("An apple", "apple", 10));
        room6.addItemRoom(new Food("Healing tempel fruit", "fruit", 30));
        room6.addItemRoom(new Food("Mushroom soup", "soup", -15));
        room7.addItemRoom(new Food("Unidentified fruit", "fruit", -10));
        room8.addItemRoom(new Food("Glowig pie", "pie", 15));
        room9.addItemRoom(new Food("Templar crackers", "crackers", -5));
        room9.addItemRoom(new Food("Monster meat", "meat", -25));


        //defines the starting room to room 1
        startingRoom = room1;

    }

    //get method to get the room you're starting at
    public Room getStartingRoom() {
        return startingRoom;
    }

}
