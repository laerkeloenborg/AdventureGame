import java.util.Scanner;

//vores single responsibility principle
// håndtere alt input og output
public class UserInterface {
    private Scanner scanner;
    private AdventureGame adventureGame;

    public UserInterface(AdventureGame adventureGame) {
        this.adventureGame = adventureGame;
        scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("*******************************************************************************************\n" +
                "*                                                                                         *\n" +
                "*      ________         __                                 __                             *\n" +
                "*     |  |  |  |.-----.|  |.----.-----.--------.-----.    |  |_.-----.                    *\n" +
                "*     |  |  |  ||  -__||  ||  __|  _  |        |  -__|    |   _|  _  |                    *\n" +
                "*     |________||_____||__||____|_____|__|__|__|_____|    |____|_____|                    *\n" +
                "*                                                                                         *\n" +
                "*      __   __                      __                   __                       __      *\n" +
                "*     |  |_|  |--.-----.    .---.-.|  |--.---.-.-----.--|  |.-----.-----.-----.--|  |     *\n" +
                "*     |   _|     |  -__|    |  _  ||  _  |  _  |     |  _  ||  _  |     |  -__|  _  |     *\n" +
                "*     |____|__|__|_____|    |___._||_____|___._|__|__|_____||_____|__|__|_____|_____|     *\n" +
                "*                                                                                         *\n" +
                "*      __                         __         __                                           *\n" +
                "*     |  |_.-----.--------.-----.|  |.-----.|  |                                          *\n" +
                "*     |   _|  -__|        |  _  ||  ||  -__||__|                                          *\n" +
                "*     |____|_____|__|__|__|   __||__||_____||__|                                          *\n" +
                "*                         |__|                                                            *\n" +
                "*                                                                                         *\n" +
                "*******************************************************************************************\n\n");
    }

    public void backStory() {
        System.out.println("Deep within the jungle stands a forgotten temple that has not been visited for centuries. \n" +
                "Once the center of a mighty civilization, it is now overtaken by the forces of the jungle and dark powers.\n" +
                "You are an explorer drawn by legends of a powerful artifact that can change destinies. \n" +
                "Many have attempted to find this treasure before you, but none have returned. \n" +
                "Before you lies a labyrinth of nine rooms, filled with dangers, puzzles, and hidden truths. \n" +
                "Step forward, into the depths of the temple. Your choices here will determine your fate.\n\n" +
                "Will you find what you seek, or become yet another victim of the temple’s many mysteries?\n");
    }

    public void helpCommands() {
        System.out.println("********** Helping commands: ********** \n" +
                "- Type go <direction> you wanna go\n" +
                "- Type look for the current rooms description \n" +
                "- Type unlock to unlock a door \n" +
                "- Type take <item> to pick up an item \n" +
                "- Type inventory to see your inventory \n" +
                "- Type drop <item> to drop an item \n" +
                "- Type help to get access to the help commands\n " +
                "- Type exit for ending the game");
    }

    public void startGame() {
        displayWelcomeMessage(); //show the welcome message

        System.out.println("For displaying backstory type story else type next"); //the user can choose between letting the backstory be shown or not
        String userinputStart = scanner.nextLine().toLowerCase(); //makes a scanner for the user

        while (!userinputStart.equalsIgnoreCase("next")) { //while user does not type next
            if (userinputStart.equalsIgnoreCase("story")) { //if user types story
                backStory(); //the backstory will be shown
                break; //stops the loop when the story has shown
            } else { //if its another word than next or story
                System.out.println("Invalid entry. Try again, enter either story or next");
                userinputStart = scanner.nextLine().toLowerCase(); //the user can try again
            }
        }

        System.out.println("Are you ready for the game? / type yes or no:)"); //ask the user if it's ready to play
        userinputStart = scanner.nextLine().toLowerCase(); //makes a scanner

        if (userinputStart.equalsIgnoreCase("yes")) { //if user types yes, the games will start
            helpCommands(); //how to play, help commands shows

            System.out.println(); //looks prettier:)

            adventureGame.lookAround(); //The description of the first room we enter

            boolean gameIsRunning = true;

            while (gameIsRunning) {
                System.out.println("\nNow what do you want to do? ");
                String userinput = scanner.nextLine().toLowerCase();

                switch (userinput.split(" ")[0]) { //splits the userinput, starts from index 0
                    case "help" -> {//help commands shows
                        helpCommands();
                        break;
                    }
                    case "look" -> {//Description of the room
                        adventureGame.getName();
                        break;
                    }
                    case "unlock" -> {//Unlocks the door in the direction the user has written
                        adventureGame.unlockDoor();
                        break;
                    }
                    case "exit" -> { //ending game
                        System.out.println("Game ending....");
                        gameIsRunning = false;
                    }
                    case "health" -> { //health point shows
                        System.out.println(adventureGame.showHealth());
                    }
                    case "eat" -> {
                        String foodName = userinput.toLowerCase().substring(4);
                        adventureGame.eatFood(foodName);
                    }
                    case "take" -> {
                        String itemDescription = userinput.toLowerCase().substring(5); //userinputs output starts from index 5 (second word)
                        System.out.println(adventureGame.takeItem(itemDescription));
                    }
                    case "drop" -> {
                        String itemDescription = userinput.toLowerCase().substring(5);
                        System.out.println(adventureGame.dropItem(itemDescription));
                    }
                    case "inventory" -> {
                        System.out.println(adventureGame.showInventory());
                    }
                    case "go" -> {
                        String direction = userinput.split(" ", 2)[1]; //splits userinput (2 because it splits in 2, 1 because then it will take the second word user types)
                        switch (direction) {
                            case "north", "n" -> {
                                System.out.println("Going north!");
                                adventureGame.movePlayer("north");
                                break;
                            }

                            case "east", "e" -> {
                                System.out.println("Going east!");
                                adventureGame.movePlayer("east");
                                break;
                            }

                            case "south", "s" -> {
                                System.out.println("Going south!");
                                adventureGame.movePlayer("south");
                                break;
                            }

                            case "west", "w" -> {
                                System.out.println("Going west!");
                                adventureGame.movePlayer("west");
                                break;
                            }

                        }
                    }
                    default -> {
                        System.out.println("Unknown.... Try again!");
                        break;
                    }
                }
            }
        } else { //if user types something else than yes this will be the output and the game will end
            System.out.println("Don't worry, you can play it some other times!");
        }
        scanner.close(); //stops the scanner
    }

}