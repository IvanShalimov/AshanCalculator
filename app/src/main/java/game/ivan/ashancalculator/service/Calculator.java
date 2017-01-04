package game.ivan.ashancalculator.service;

import java.util.List;

import game.ivan.ashancalculator.database.models.Item;

/**
 * Created by ivan on 04.01.2017.
 */

public class Calculator{

    public double sum(List<Item> list) {
        double sum=0;
        for (Item item:list){
            sum+=(item.price*item.count);
        }
        return sum;
    }

    public double oneManSum(List<Item> items,int divider){
        return sum(items)/divider;
    }
}
