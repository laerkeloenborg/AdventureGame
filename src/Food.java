public class Food extends Item {
    private int healthPoints;

    //constructor
    public Food(String longName, String shortName, int healthPoints) {
        super(longName, shortName);
        this.healthPoints = healthPoints;
    }

    //method to get the health points
    public int getHealthPoints() {
        return healthPoints;
    }

}
