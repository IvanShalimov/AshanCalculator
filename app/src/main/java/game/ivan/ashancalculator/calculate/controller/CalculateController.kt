package game.ivan.ashancalculator.calculate.controller

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import butterknife.BindView
import com.bluelinelabs.conductor.Controller
import com.hannesdorfmann.mosby.conductor.viewstate.MvpViewStateController
import game.ivan.ashancalculator.R
import game.ivan.ashancalculator.calculate.presenter.CalculatedPresenter
import game.ivan.ashancalculator.calculate.view.CalculaterView
import game.ivan.ashancalculator.database.models.Item

/**
 * Created by ivan on 28.01.2017.
 */
class CalculateController:
        MvpViewStateController<CalculaterView, CalculatedPresenter, CalculatorViewState>(),
        CalculaterView, AdapterView.OnItemSelectedListener {

    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: CalculatedListItemAdapter
    internal var onePrice: Double = 0.toDouble()
    internal var sumPrice:Double = 0.toDouble()

    @BindView(R.id.tag_spinner)
    lateinit var tagSpinner: Spinner
    lateinit var headerLsit: TextView
    lateinit  var oneManPrice: TextView
    lateinit var sum: TextView
    lateinit  var itemPositionList: RecyclerView

    init {
        retainViewMode = Controller.RetainViewMode.RETAIN_DETACH
    }

    override fun onNewViewStateInstance() {
        presenter.getTags()
    }

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {

    }

    override fun createViewState(): CalculatorViewState = CalculatorViewState()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        onViewBound(view)
        return view
    }

    protected fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.calculater_layout, container, false)
    }

    protected fun onViewBound(view: View) {
        tagSpinner = view.findViewById(R.id.tag_spinner) as Spinner
        headerLsit =  view.findViewById(R.id.list_header_label) as TextView
        oneManPrice = view. findViewById(R.id.one_man_label) as TextView
        sum = view. findViewById(R.id.sum_label) as TextView
        itemPositionList = view. findViewById(R.id.list_item_position) as RecyclerView

        layoutManager = LinearLayoutManager(applicationContext)
        itemPositionList.setLayoutManager(layoutManager)
        adapter = CalculatedListItemAdapter()
        itemPositionList.setAdapter(adapter)
    }

    override fun createPresenter(): CalculatedPresenter = CalculatedPresenter()

    override fun onAttach(view: View) {
        super.onAttach(view)
    }

    override fun setSpinnerData(list: List<String>) {
        val spinnerAdapter = ArrayAdapter<String>(
                applicationContext,
                R.layout.spinner_item, list)
        tagSpinner.adapter = spinnerAdapter
        tagSpinner.setSelection(getViewState().currentState)
        tagSpinner.onItemSelectedListener = this
        getViewState().labels = list}

    override fun refreshList(list: List<Item>) {
        adapter.items = list}

    override fun showOneManPrice(price: Double) {
        onePrice = price
        oneManPrice.text = "С одного человека: $price"
    }

    override fun showSum(price: Double) {
        sumPrice = price
        sum.text = "Общее: $price"
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        getViewState().currentState = position
        presenter.getDateForScreen(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(false)
    }

    override fun onDestroyView(view: View?) {
        super.onDestroyView(view)
    }
}
