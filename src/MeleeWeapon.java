public class MeleeWeapon extends Weapon{

    public MeleeWeapon(String longName, String shortName, int damage){
        super(longName,shortName,damage);
    }

    @Override
    public boolean canUse(){
        return true; //this type of weapon can always be used, there's no limit...
    }

    @Override
    public String use(){
        return "You used the " + getShortName() + ".";
    }


}
