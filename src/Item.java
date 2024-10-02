public class Item {
    private String longName;
    private String shortName;

    //constructor
    public Item(String longName, String shortName){
        this.longName = longName;
        this.shortName = shortName;
    }

    public String getLongName(){
        return longName;
    }


    public String getShortName(){
        return shortName;
    }

}
