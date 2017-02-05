package game.ivan.ashancalculator.tags.controller

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import game.ivan.ashancalculator.R
import game.ivan.ashancalculator.database.models.Tags

/**
 * Created by ivan on 05.02.2017.
 */
class TagsListAdapter: RecyclerView.Adapter<ViewHolder>() {
    var tags: List<Tags> = listOf()
    internal lateinit var callback:TagsListAdapterCallback

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.tag_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemTagLabel?.text = "${tags.get(position).nameTags}"

        holder?.item_container?.setOnClickListener { view ->
            if (callback != null) {
                callback.onListItemSelect(tags[position])
            }
        }

    }

    fun setCallback(parameter: TagsListAdapterCallback) {
        callback = parameter
    }

}

class ViewHolder: RecyclerView.ViewHolder{

    var itemTagLabel: TextView
    var item_container: LinearLayout

    constructor(itemView: View):super(itemView){
        itemTagLabel = itemView.findViewById(R.id.item_tag_label) as TextView
        item_container = itemView.findViewById(R.id.tag_item_container)as LinearLayout

        itemTagLabel.setTextColor(Color.BLACK)
    }

}

interface TagsListAdapterCallback {
    fun onListItemSelect(tag: Tags)
}