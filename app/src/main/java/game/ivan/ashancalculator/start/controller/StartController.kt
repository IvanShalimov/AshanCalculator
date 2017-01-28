package game.ivan.ashancalculator.start.controller

import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.hannesdorfmann.mosby.mvp.conductor.MvpController
import game.ivan.ashancalculator.R
import game.ivan.ashancalculator.calculate.controller.CalculateController
import game.ivan.ashancalculator.items.controller.ItemsController
import game.ivan.ashancalculator.service.ActionBarProvider
import game.ivan.ashancalculator.start.presenter.StartPresenter
import game.ivan.ashancalculator.start.view.StartView
import game.ivan.ashancalculator.tags.controller.TagsController

/**
 * Created by ivan on 28.01.2017.
 */
class StartController: MvpController<StartView, StartPresenter>,StartView, View.OnClickListener{


    private var unbinder: Unbinder? = null

    lateinit var addItemButton: FloatingActionButton
    lateinit var addTagButton: FloatingActionButton
    lateinit var clearItemsButton: FloatingActionButton

    override fun openProductBag() {
        if (presenter.isTagsListNotEmpty()) {
            router.pushController(
                    RouterTransaction.with(ItemsController())
                            .pushChangeHandler(HorizontalChangeHandler())
                            .popChangeHandler(HorizontalChangeHandler()))
        } else {
            Toast.makeText(applicationContext, R.string.please_add_tags, Toast.LENGTH_SHORT).show()
        }
    }

    override fun openTagEditor() {
        router.pushController(RouterTransaction.with(TagsController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
    }

    override fun openCalculated() {
        if (presenter.isTagsListNotEmpty()) {
            router.pushController(RouterTransaction.with(CalculateController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler()))
        } else {
            Toast.makeText(applicationContext, R.string.please_add_tags, Toast.LENGTH_SHORT).show()
        }
    }

    override fun createPresenter(): StartPresenter {
        return StartPresenter()}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        unbinder = ButterKnife.bind(this, view)
        onViewBound(view)
        return view
    }


    private fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.start_controller_layout, container, false)
    }

    private fun onViewBound(view: View) {
        getActionBar()?.setIcon(R.mipmap.ic_launcher)
        addItemButton = view.findViewById(R.id.add_item_button) as FloatingActionButton
        addItemButton.setOnClickListener(this)
        addTagButton = view.findViewById(R.id.add_tag_button) as FloatingActionButton
        addTagButton.setOnClickListener(this)
        clearItemsButton = view.findViewById(R.id.clear_bag_button) as FloatingActionButton
        clearItemsButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.add_item_button -> openTagEditor()
            R.id.add_tag_button -> openTagEditor()
            R.id.clear_bag_button -> openCalculated()
        }
    }

    private fun getActionBar(): ActionBar? {
        val actionBarProvider = activity as ActionBarProvider
        return actionBarProvider.getSupportActionBar()
    }

    constructor():super()

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(false)
    }

    override fun onDestroyView(view: View?) {
        super.onDestroyView(view)
        unbinder?.unbind()
        unbinder = null
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)
    }

}