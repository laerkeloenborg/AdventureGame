import java.util.Scanner;

public class UserInterface {
    public void displayMenu() {
        Scanner userInput = new Scanner(System.in);
        Adventure adventure1 = new Adventure();
        adventure1.setUpRoom();

        System.out.println("WELCOME TO THE ABANDONED TEMPLE! \n\n" +
                "Deep within the jungle stands a forgotten temple that has not been visited for centuries. \n" +
                "Once the center of a mighty civilization, it is now overtaken by the forces of the jungle and dark powers.\n" +
                "You are an explorer drawn by legends of a powerful artifact that can change destinies. \n" +
                "Many have attempted to find this treasure before you, but none have returned. \n" +
                "Before you lies a labyrinth of nine rooms, filled with dangers, puzzles, and hidden truths. \n" +
                "Can you find what you seek, or will the temple claim you as well? \n \n" +
                "HOW TO PLAY: \n" +
                "If you want to enter a new room, type: go, followed by the direction: north, east, south, or west. Alternatively, you can simply type the directions as n, e, s, or w without the go. \n" +
                "Upon entering a room, you can type look, and you will receive a description of the room. \n" +
                "If you need help, type help, and a list of commands will be shown. \n" +
                "Maybe some of the passages are locked. If you would like to come through, you have to type unlock!\n" +
                "To exit the game at any point, type exit, and the game will end. \n \n" +
                "Step forward, into the depths of the temple. Your choices here will determine your fate.\n" +
                "Will you find what you seek, or become yet another victim of the temple’s many mysteries?\n");

        System.out.println(adventure1); //får udskrevet hvilket rum man starter med at befinde sig i, både navn og beskrivelse
        boolean done = false;

        while (!done) {
            System.out.println("Now type which direction you want to go: ");
            String usersChoice = userInput.nextLine();

            if (usersChoice.equalsIgnoreCase("go north") || usersChoice.equalsIgnoreCase("north") || usersChoice.equalsIgnoreCase("n")) {
                System.out.println("going north!");
                adventure1.move("north");
            } else if (usersChoice.equalsIgnoreCase("go east") || usersChoice.equalsIgnoreCase("east") || usersChoice.equalsIgnoreCase("e")) {
                System.out.println("going east!");
                adventure1.move("east");
            } else if (usersChoice.equalsIgnoreCase("go south") || usersChoice.equalsIgnoreCase("south") || usersChoice.equalsIgnoreCase("s")) {
                System.out.println("going south!");
                adventure1.move("south");
            } else if (usersChoice.equalsIgnoreCase("go west") || usersChoice.equalsIgnoreCase("west") || usersChoice.equalsIgnoreCase("w")) {
                System.out.println("going west!");
                adventure1.move("west");
            } else if (usersChoice.equalsIgnoreCase("help")) {
                System.out.println("See in the description of the room which ways you can go, type look if you wanna see the description again.");
                System.out.println("type one of the directions, look for the rooms description, unlock to unlock a door or exit for ending the game.");
            } else if (usersChoice.equalsIgnoreCase("look")) { //hvis useren taster look, bliver dette printet ud
                System.out.println(adventure1.getCurrentRoom().getDescription());
            } else if (usersChoice.equalsIgnoreCase("exit")) { //hvis useren taster exit ind bliver dette printet ud
                System.out.println("The game will now end, thank you for playing!");
                done = true;
            } else if (usersChoice.equalsIgnoreCase("unlock")) {
                adventure1.unlockLastAttemptedDoor(); //låser døren op i den retning man har skrevet, hvis døren er låst
            } else {
                System.out.println("Type one of the opportunities you got in the introduction, if you need to see how to play again, type help - try again.");
            }
        }


    }
}
/*
   "Are you ready for the game, yes or no?"
 while(gameStart){
            if (userStartChoice.equalsIgnoreCase("yes")) {



            } else if (userStartChoice.equalsIgnoreCase("no")){
                System.out.println("Are you actually sure? Maybe try typing yes:)");
                userInput.next();
            } else {
                System.out.println("try typing something that makes sense:)");
                userInput.next();
            }

        }

        Hvis man vil have spilleren skal kunne svare yes eller no til at begynde spillet
        lav en metode runGame?
 */



