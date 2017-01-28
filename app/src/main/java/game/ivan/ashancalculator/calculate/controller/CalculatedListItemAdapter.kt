package game.ivan.ashancalculator.calculate.controller

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import game.ivan.ashancalculator.R
import game.ivan.ashancalculator.database.models.Item

/**
 * Created by ivan on 28.01.2017.
 */
class  CalculatedListItemAdapter(): Adapter<ViewHolder>() {

    internal var items: List<Item>? = listOf()
    set(value) {
        items = value
        notifyDataSetChanged()
    }


    constructor(list:List<Item> ) : this() {
        items = list
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.getContext())
                .inflate(R.layout.calculated_list_item, parent, false)
        return ViewHolder(view)}

    override fun getItemCount(): Int = items!!.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.name?.setText(items?.get(position)?.name)
        holder?.count?.setText(items?.get(position)?.count.toString())
        val a:Double = items?.get(position)?.count as Double
        val b:Double = items?.get(position)?.price as Double
        val result = a*b
        holder?.price?.setText("$result")
    }

}

class ViewHolder: RecyclerView.ViewHolder{

    var name: TextView? = null
    var count: TextView? = null
    var price: TextView? = null

    constructor(itemView: View):super(itemView){
        name = itemView.findViewById(R.id.name_calculated_item) as TextView
        count = itemView.findViewById(R.id.count_calculated_item) as TextView
        price  = itemView.findViewById(R.id.price_calculated_item) as TextView
    }

}
