package game.ivan.ashancalculator.start

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import game.ivan.ashancalculator.R
import game.ivan.ashancalculator.service.ActionBarProvider
import game.ivan.ashancalculator.start.controller.StartController
import android.support.v7.app.ActionBar

class StartActivity : AppCompatActivity(), ActionBarProvider {

    lateinit  var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        toolbar.setTitleTextColor(resources.getColor(R.color.colorAccent))
        val container = findViewById(R.id.container) as ViewGroup
        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(StartController()))
        }

    }

    override fun getSupportActionBar(): ActionBar{
        super<ActionBarProvider>. getSupportActionBar()
        return super<AppCompatActivity>.getSupportActionBar()!!
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

}
