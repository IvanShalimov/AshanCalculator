package game.ivan.ashancalculator.service

import game.ivan.ashancalculator.database.models.Item

/**
 * Created by ivan on 04.01.2017.
 */

class Calculator {

    fun sum(list: List<Item>): Double {
        val sum = list.sumByDouble { it.price * it.count }
        return sum
    }

    fun oneManSum(items: List<Item>, divider: Int): Double {
        return sum(items) / divider
    }
}
