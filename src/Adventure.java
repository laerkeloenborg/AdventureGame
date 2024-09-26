import java.util.Random;

public class Adventure {
    private Room currentRoom; //instansvariabel for det rum man befinder sig i
    private String lasAttemptedDirection; //instansvariabel for den retning man har skrevet man vil gå mod

    //metode til at få fat på det rum man befinder sig i
    public Room getCurrentRoom() {
        return currentRoom;
    }

    //metode til at forbinde rummene
    public void setUpRoom() {
        Room room1 = new Room("Room 1: The Overgrown Entrance", "This room is the entrance to the abandoned temple. Walls of ancient stone are almost completely covered by vines and moss. \n" +
                "The air is humid, and a sense of something hidden lingers in the atmosphere.\n" +
                "There's two doors, one to east and one to south. \n", "You're now in: The Overgrown Entrance");
        Room room2 = new Room("Room 2: The Forgotten Library", "This room was once the temple's center of learning, but now dust and plants cover the half-rotten bookshelves.\n" +
                "The walls are filled with ancient inscriptions and puzzles. \n" + "There are to passages, one to the west and one to the east.\n", "You're now in: The Forgotten Library.");
        Room room3 = new Room("Room 3: The Cursed Altar", "This is the temple's darkest corner.\n" +
                "A weathered altar in the center is covered in ancient bloodstains from rituals performed thousands of years ago. \n" + "From here you can move to the west or head south.\n", "You're now in: The Cursed Altar");
        Room room4 = new Room("Room 4: The King's Fallen Throne", "This room was once the magnificent throne room where the temple's ruler sat.\n " +
                "Now the floor lies in ruins, and the throne is nearly toppled by the ravages of time.\n" + "The walls are covered in growth, and vines twist across the ceiling. \n" + "Now you can choose if you want to go to the north or south. \n", "You're now in: The King's Fallen Throne.");
        Room room5 = new Room("Room 5: The Sacred Tomb - The Final", "This room is the heart of the abandoned temple.\n" +
                "The room is filled with ancient frescoes that tell the story of the temple.\n" + "This is where the temple's secrets are hidden, and the player faces their greatest challenge here.\n" + "Only one way to get out of here, go south.","");
        Room room6 = new Room("Room 6: The Burial Chamber", "Room 6 is an ancient underground tomb filled with broken stones and hidden treasures. \n" +
                "Some graves have been looted, while others remain untouched, but danger lurks.\n" + "You can either head to the north or to the south from here.", "");
        Room room7 = new Room("Room 7: The Hall Of The Guards", "Here lived the temple's ancient guards, but now the room is filled with rusty weapons and armor. \n" +
                "The former guardians may awaken if the player is not careful. \n" + "From here you can choose between two paths, one which goes north or one which goes east.", "");
        Room room8 = new Room("Room 8: The Ruined Marketplace", "The temple's marketplace, now desolate and filled with toppled statues and decaying stalls. \n" +
                "The jungle has overtaken much of the space, but traces of ancient rituals are still visible.\n" + "From here you can choose between three ways, one to the west, east or north.", "");
        Room room9 = new Room("Room 9: The Temple's Dark Labyrinth", "The deepest and most sinister place in the temple. \n" +
                "Here, the corridors are narrow, and the darkness is thick. \n" + "Many explorers have been lost here. Dangerous traps are hidden in every corner.\n" + "To get out of this darkness, you can choose between going west or north.", "");


        currentRoom = room1; //sætter room 1 til at være der hvor vi starter

        room1.lockDoor("east"); //låser døren øst for room 1 (indgang til room 2)
        room6.lockDoor("south"); //låser døren syd for room 6 (indgang til room 9)
        room8.lockDoor("north"); //låser døren nord for room 8 (indgang til room 5)

        room1.setEast(room2); //sætter rummet øst for room 1 til room 2, dvs. når man går mod øst inde i rum 1 kommer man gennem døren ind til rum 2
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
    }

    //metode til at rykke videre ind i det næste rum
    public void move(String direction){
        Room nextRoom = null; //lokalvariabel

        //tjekker om døren er låst
        if (currentRoom.isDoorLocked(direction)){ //hvis døren er låst i den retning vil vi gå videre fra vores currentRoom gøres:
            lasAttemptedDirection = direction; //Dette gør at jeg kan åbne døren ellers kommer outputtet "There's no doors to unlock" (det som står på linje 101 i unlockLastAttemptedDoor metode).
            System.out.println("The door is locked");
            return; //afslut metoden hvis døren er låst
        }


        switch (direction){ //hvis brugeren skriver f.eks. north er det næste rum man kommer ind i nord for currentRoom
            case "north" : nextRoom = currentRoom.getNorth(); break;
            case "south" : nextRoom = currentRoom.getSouth(); break;
            case "east" : nextRoom = currentRoom.getEast(); break;
            case "west" : nextRoom = currentRoom.getWest(); break;
        }

        if (nextRoom != null) { //hvis det næste rum man går ind i ikke er lige med null, sætter den currentRoom til næste rum og udføre lookAround metode
                currentRoom = nextRoom; //currentRoom opdateres/defineres nu til nextRoom, dvs. det næste rum vi er gået ind i
                lookAround(); //gør at der udskrives rummets beskrivelse
        }else { //hvis nextRoom er lige med null printer den dette ud
            System.out.println("You cannot go this way!");
        }

    }

    //metode til at låse døren op i den direction spilleren har skrevet
    public void unlockLastAttemptedDoor(){
        if (lasAttemptedDirection != null){ //hvis lastAttemptedDirection ikke er null
            currentRoom.unlockDoor(lasAttemptedDirection); //låser døren op i den retning vi har skrevet fra det nuværende rum vi befinder os i
            System.out.println("The door to the " + lasAttemptedDirection + " is now unlocked!");
            lasAttemptedDirection = null; //resetter den direction vi lige har skrevet (nulstiller retningen)
        } else{
            System.out.println("There is no locked door to unlock in that direction");
        }
    }

    //metode til at udskrive hvilket rum man nu er i
    private void lookAround() {
        System.out.println("You're now in: " + currentRoom.getName());//udskriver navn på rummet man befinder sig i
        /* at der står to sout i stedet for én gør egentlig bare at navnet på rummet udskrives ovenover beskrivelsen (det pænere) */
        System.out.println(currentRoom.getDescription());//udskriver den opdaterede beskrivelse (om den skal være kort eller lang)
    }

    //toString metoden gør at vi får room 1 udskrevet som det rum vi først befinder os i
   @Override
    public String toString(){
        return "You're now in " + currentRoom.getName() + ", " + currentRoom.getDescription();
    }


}

