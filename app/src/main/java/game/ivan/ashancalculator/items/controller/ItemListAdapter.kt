package game.ivan.ashancalculator.items.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import game.ivan.ashancalculator.AshanApplication
import game.ivan.ashancalculator.R
import game.ivan.ashancalculator.database.models.Item

/**
 * Created by ivan on 05.02.2017.
 */
class ItemListAdapter: RecyclerView.Adapter<ViewHolder>() {

    var items: List<Item> = listOf()
    lateinit internal var callback: ItemsListAdapterCallback

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.nameLabel?.text = items[position].name
        holder?.priceLabel?.text = (items[position].price * items[position].count).toString()

        val builder = Picasso.Builder(AshanApplication.instance)
        builder.listener { picasso, uri, exception -> exception.printStackTrace() }

        try {
            builder.build().load("file://" + items[position].photoPath)
                    .resize(100, 100)
                    .rotate(90f)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(holder?.itemIcon)

        } catch (exception: Exception) {
            exception.printStackTrace()
        }


        holder?.item_container?.setOnClickListener { view ->
            if (callback != null) {
                callback.onListItemSelect(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.getContext())
                .inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view) }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var priceLabel: TextView = itemView.findViewById(R.id.price_label) as TextView
    var nameLabel: TextView = itemView.findViewById(R.id.name_label) as TextView
    var item_container: RelativeLayout = itemView.findViewById(R.id.item_list_container) as RelativeLayout
    var itemIcon: ImageView = itemView.findViewById(R.id.item_icon) as ImageView

}

interface ItemsListAdapterCallback {
    fun onListItemSelect(item: Item)
}
