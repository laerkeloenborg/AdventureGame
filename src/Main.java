import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map gameMap = new Map();
        AdventureGame adventureGame1 = new AdventureGame(gameMap);
        UserInterface userInterface = new UserInterface(adventureGame1); //makes a new userinterface with our adventuregame
        userInterface.startGame(); //starts the game
        }
    }


