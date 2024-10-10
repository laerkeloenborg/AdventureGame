public abstract class Weapon extends Item {
    int damage;

    //constructor
    public Weapon(String longName, String shortName, int damage) {
        super(longName, shortName);
        this.damage = damage;
    }

    //getter method for damage
    public int getDamage(){
        return damage;
    }

    //Abstracts method which can be overrided in the subclasses
    abstract boolean canUse();

    abstract String use();

    //toString method the name of our weapon, so the output doesn't come out gibberish
    @Override
    public String toString() {
        return getLongName();
    }

}
