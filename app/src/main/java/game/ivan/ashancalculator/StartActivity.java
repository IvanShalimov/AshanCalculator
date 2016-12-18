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
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import game.ivan.ashancalculator.presenter.StartPresenter;
import game.ivan.ashancalculator.view.StartView;

public class StartActivity extends MvpActivity<StartView,StartPresenter> implements StartView {

    @BindView(R.id.add_item_button)
    Button addItemButton;
    @BindView(R.id.add_tag_button)
    Button addTagButton;
    @BindView(R.id.clear_bag_buton)
    Button clearItemsButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.test_status_click)
    TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

    }

    @NonNull
    @Override
    public StartPresenter createPresenter() {
        return new StartPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add_item_button)
    public void addItemButtonClick(View view){
        Log.d("Test","0");
        presenter.returnText(0);
    }

    @OnClick(R.id.add_tag_button)
    public void addTagButtonClick(View view){
        Log.d("Test","1");
        presenter.returnText(1);
    }

    @OnClick(R.id.clear_bag_buton)
    public void clearingButtonClick(View view){
        Log.d("Test","2");
        presenter.returnText(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }

    @Override
    public void showText(String text) {
        testTextView.setText(text);
    }
}
