package game.ivan.ashancalculator.graphics;

import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.database.DatabaseItemsManager;
import game.ivan.ashancalculator.database.models.Item;
import game.ivan.ashancalculator.database.models.Tags;
import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class GraphicsActivity extends AppCompatActivity {

    DatabaseItemsManager databaseManager;
    PieView pieView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);
        pieView = (PieView)findViewById(R.id.pie_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseManager = new DatabaseItemsManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Observable.zip(databaseManager.readAllRecord(), databaseManager.getTagsList(),
                (items, tagses) -> {
                    ArrayList<PieHelper> resultList = new ArrayList<>(tagses.size());
                    Log.d("Test","tagses.size() = "+tagses.size());
                    Counter[] counter = new Counter[tagses.size()];
                    for(Item item: items){
                        int i =0;
                        for(Tags tag:tagses){
                            if(item.tag_id == tag._id){
                                if(counter[i]==null){
                                    counter[i] = new Counter();
                                }
                                counter[i].plus();
                                Log.d("Tes","counter ="+counter[i].getCounter());
                                break;
                            }
                            i++;
                        }
                    }

                    for(int j =0; j<tagses.size();j++){
                        Random rnd = new Random();
                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                        float precent = 100*counter[j].getCounter()/items.size();
                        Log.d("Test","precent = "+precent);
                        PieHelper helper = new PieHelper(precent,color);
                        resultList.add(helper);

                    }

                    return resultList;
                }).subscribe(pieHelpers -> {
            pieView.setDate(pieHelpers);
        });
    }

    class Counter{
        private int counter;
        private String name;

        public Counter(){
            counter = 0;
            name = "";
        }

        public void plus(){
            counter++;
        }

        public void setName(String name){
            this.name =name;
        }

        public int getCounter() {
            return counter;
        }

        public String getName(){
            return name;
        }
    }
}
