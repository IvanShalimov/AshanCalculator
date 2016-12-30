package game.ivan.ashancalculator.start;

import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.R;

/**
 * Created by ivan on 27.12.16.
 */

public class PicassoCallback implements Callback {

    ImageView view;
    int resources;

    public PicassoCallback(ImageView imageView, int drawable){
        view = imageView;
        resources = drawable;
    }
    @Override
    public void onSuccess() {
        Picasso.with(AshanApplication.getInstante())
                .load(resources)
                .resize(view.getMeasuredWidth(), view.getMeasuredHeight())
                .into(view);
    }

    @Override
    public void onError() {

    }
}
