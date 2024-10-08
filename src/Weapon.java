public abstract class Weapon extends Item {
    int damage;


    public Weapon(String longName, String shortName, int damage) {
        super(longName, shortName);
        this.damage = damage;
    }

    abstract boolean canUse();

    abstract String use();

//toString method the name of our weapon, so the output doesn't come out gibberish
    @Override
    public String toString() {
        return getLongName();
    }

}
