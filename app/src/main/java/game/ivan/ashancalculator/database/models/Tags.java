package game.ivan.ashancalculator.database.models;

/**
 * Created by ivan on 21.12.16.
 */
public class Tags {

    public Long _id;
    public String nameTags;
    public int divisionFactor;

    public Tags(){}

    public Tags(String nameTags, int divisionFactor){
        this.nameTags = nameTags;
        this.divisionFactor = divisionFactor;
    }
}
