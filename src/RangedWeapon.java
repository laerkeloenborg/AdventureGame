public class RangedWeapon extends Weapon{
    private int ammunition;

    public RangedWeapon(String longName, String shortName, int damage, int ammunition){
        super(longName,shortName,damage);
        this.ammunition = ammunition;
    }

    public int remainingUses(){
        return ammunition;
    }

    @Override
    public boolean canUse(){
        return ammunition > 0; //weapon can be used as long as the ammunition is over 0
    }

    @Override
    public String use(){
        if(canUse()){
            ammunition--;
            return "You fired with the " + getShortName() + ". You have " + remainingUses() + " uses left.";
        } else {
            return "You don't have any ammunition left...";
        }
    }
}
