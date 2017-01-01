package game.ivan.ashancalculator.database.models;

/**
 * Created by ivan on 31.12.16.
 */

public class Item {
    public Long _id;
    public String name;
    public String photoPath;
    public long tagId;
    public int count;
    public double price;

    public Item(){

    }

    public Item(String name, String photoPath, long tagId, int count, double price){
        this.name = name;
        this.photoPath = photoPath;
        this.tagId = tagId;
        this.count = count;
        this.price = price;
    }
}
