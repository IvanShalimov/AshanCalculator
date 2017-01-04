package game.ivan.ashancalculator.database.models;

import nl.qbusict.cupboard.annotation.Column;

/**
 * Created by ivan on 31.12.16.
 */

public class Item {
    public Long _id;
    public String name;
    public String photoPath;
    public long tag_id;
    public double count;
    public double price;

    public Item(){

    }

    public Item(String name, String photoPath, long tagId, double count, double price){
        this.name = name;
        this.photoPath = photoPath;
        this.tag_id = tagId;
        this.count = count;
        this.price = price;
    }
}
