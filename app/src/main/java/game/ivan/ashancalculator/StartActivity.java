package game.ivan.ashancalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import game.ivan.ashancalculator.controller.StartController;
import game.ivan.ashancalculator.presenter.StartPresenter;
import game.ivan.ashancalculator.view.StartView;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    ViewGroup container;

    Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

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
