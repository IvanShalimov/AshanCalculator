package game.ivan.ashancalculator.start

import android.widget.ImageView

import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import game.ivan.ashancalculator.AshanApplication

/**
 * Created by ivan on 27.12.16.
 */

class PicassoCallback(internal var view: ImageView, internal var resources: Int) : Callback {
    override fun onSuccess() {
        Picasso.with(AshanApplication.instance)
                .load(resources)
                .resize(view.measuredWidth, view.measuredHeight)
                .into(view)
    }

    override fun onError() {

    }
}
