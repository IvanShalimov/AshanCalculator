package game.ivan.ashancalculator.start;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.service.ActionBarProvider;
import game.ivan.ashancalculator.start.controller.StartController;

public class StartActivity extends AppCompatActivity implements ActionBarProvider {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    ViewGroup container;

    @BindColor(R.color.colorAccent) int colorAccent;

    Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(colorAccent);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new StartController()));
        }

    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

}
