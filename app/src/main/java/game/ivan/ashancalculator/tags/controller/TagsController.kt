package game.ivan.ashancalculator.tags.controller

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.EditText
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.bluelinelabs.conductor.Controller
import com.hannesdorfmann.mosby.conductor.viewstate.MvpViewStateController
import game.ivan.ashancalculator.R
import game.ivan.ashancalculator.database.models.Tags
import game.ivan.ashancalculator.tags.presenter.TagsPresenter
import game.ivan.ashancalculator.tags.view.TagsView

/**
 * Created by ivan on 05.02.2017.
 */
class TagsController: MvpViewStateController<TagsView, TagsPresenter, TagStateView>(), TagsView,
MaterialDialog.SingleButtonCallback, TagsListAdapterCallback{

    companion object{
        @JvmStatic val DEFAULT_DIVISION_VALUE:Int = 1
    }

    var namless:String = "Безымянный"
    lateinit internal var list: RecyclerView
    lateinit internal var layoutManager: RecyclerView.LayoutManager
    /*Адптер списка*/
    lateinit internal var adapter: TagsListAdapter
    /*Маке идалога*/
    lateinit internal var dialogAdd: View
    lateinit internal var dialog: MaterialDialog

    init {
        setHasOptionsMenu(true)
        retainViewMode = Controller.RetainViewMode.RETAIN_DETACH
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        var view = inflateView(inflater,container)
        onViewBound(view)
        return view
    }

    protected fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.list_layout, container, false)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
    }

    fun onViewBound(view:View){
        list = view.findViewById(R.id.list) as RecyclerView
        layoutManager = LinearLayoutManager(applicationContext)
        list.layoutManager = layoutManager
        adapter = TagsListAdapter()
        adapter.setCallback(this)
        list.adapter = adapter
    }

    override fun createPresenter(): TagsPresenter = TagsPresenter()

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_tags, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.getItemId()

        if (id == R.id.add_item) {
            getViewState().setShowCreateDialogState()
            showCreateDialog()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView(view: View?) {
        super.onDestroyView(view)
    }

    override fun onClick(dialog: MaterialDialog, which: DialogAction) {
        var layout = dialog.customView

        var name = (layout?.findViewById(R.id.name_edit_field) as EditText)
                .text
                .toString()
        if (name == "")
            name = namless

        var division: Int
        try {
            division = Integer
                    .valueOf(
                            (layout?.findViewById(R.id.count_edit_field) as EditText)
                                    .text
                                    .toString())!!
        } catch (e: NumberFormatException) {
            division = DEFAULT_DIVISION_VALUE
        } catch (e: NullPointerException) {
            division = DEFAULT_DIVISION_VALUE
        }

        presenter.addTag(Tags(name, division))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showEditDialog(tag: Tags) {
        val wrapInScrollView = true
        if (dialogAdd == null) {
            dialog = MaterialDialog.Builder(activity)
                    .title(R.string.edit_tag)
                    .customView(R.layout.editor_tag_layout, wrapInScrollView)
                    .positiveText(R.string.edit)
                    .negativeText(R.string.delete)
                    .onPositive({ materialDialog, dialogAction ->
                        run {
                            var name = (dialog.findViewById(R.id.name_edit_field) as EditText).text.toString()
                            if (name == "") {
                                name = namless
                            } else {
                                tag.nameTags = name
                            }

                            val division = (dialog.findViewById(R.id.count_edit_field) as EditText).text.toString()
                            if (division == "") {
                                tag.divisionFactor = DEFAULT_DIVISION_VALUE
                            } else {
                                tag.divisionFactor = Integer.valueOf(division)!!
                            }
                            presenter.addTag(tag)
                        }
                    })
                    .onNegative({ materialDialog, dialogAction ->
                        run {
                            presenter.deleteTag(tag)
                    }  })
                    .build()

            dialogAdd = dialog.customView!!
        } else {
            dialog = MaterialDialog.Builder(activity)
                    .title(R.string.edit_tag)
                    .customView(R.layout.editor_tag_layout, wrapInScrollView)
                    .positiveText(R.string.edit)
                    .negativeText(R.string.delete)
                    .onPositive({ materialDialog, dialogAction ->
                        run {
                            var name = (dialog.findViewById(R.id.name_edit_field) as EditText).text.toString()
                            if (name == "") {
                                tag.nameTags = namless
                            } else {
                                tag.nameTags = name
                            }

                            val division = (dialog.findViewById(R.id.count_edit_field) as EditText).text.toString()
                            if (division == "") {
                                tag.divisionFactor = DEFAULT_DIVISION_VALUE
                            } else {
                                tag.divisionFactor = Integer.valueOf(division)!!
                            }
                            presenter.addTag(tag)
                        }
                    })
                    .onNegative({ materialDialog, dialogAction ->
                        run {
                            presenter.deleteTag(tag)
                        }
                    })
                    .build()
        }

        (dialog.findViewById(R.id.name_edit_field) as EditText).setText(tag.nameTags.toString())
        (dialog.findViewById(R.id.count_edit_field) as EditText).setText(tag.divisionFactor.toString())
        dialog.show()
    }

    override fun showCreateDialog() {
        dialog = MaterialDialog.Builder(activity)
                .title(R.string.create_tag)
                .customView(R.layout.editor_tag_layout, true)
                .positiveText(R.string.add_label_menu_item)
                .negativeText(R.string.editor_cancel_button_label)
                .onPositive(this)
                .build()

        dialogAdd = dialog.customView!!

        (dialog.findViewById(R.id.name_edit_field) as EditText).setText("")
        (dialog.findViewById(R.id.count_edit_field) as EditText).setText("")
        dialog.show()
    }

    override fun onListItemSelect(tag: Tags) {
        getViewState().setShowEditDialogState()
        getViewState().setEditableTag(tag)
        showEditDialog(tag)
    }

    override fun createViewState(): TagStateView = TagStateView()

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean){}

    override fun onNewViewStateInstance() {
        presenter.loadTags(false)
    }

    override fun refreshList(list: List<Tags>) {
        adapter.tags = list
        getViewState().setShowContentState()
        getViewState().setTags(list)
    }
}