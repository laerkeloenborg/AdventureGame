public class Enemy {
    private String longName;
    private String shortName;
    private Weapon weapon;
    private int health;

    //constructor
    public Enemy(String longName, String shortName, int health, Weapon weapon) {
        this.longName = longName;
        this.shortName = shortName;
        this.health = health;
        this.weapon = weapon;
    }

    //getter methods
    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getHealth() {
        return health;
    }

    //set method to set health
    public void setHealth(int health) {
        this.health = health;
    }

    //be hit by a player
    public int hitByPlayer(int damage) {
        health += damage; //+ bc weapons damage are written with negative numbers

        if (health <= 0) {
            setHealth(0);
        }

        return health;
    }

    //attack player back after being hit
    public void attackPlayer(Player player){
        int enemyWeaponDamage = getWeapon().getDamage(); //local variabel, how much damage will enemy's weapon give
        player.decreaseHealth(enemyWeaponDamage); //reduce players health
    }

}
