package game.ivan.ashancalculator

import android.app.Application

/**
 * Created by ivan on 27.12.16.
 */

class AshanApplication : Application(){
    companion object {
        @JvmStatic lateinit var instance: AshanApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}
