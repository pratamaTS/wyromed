package com.example.wyromed.Fragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.wyromed.Activity.DetailMessageActivity
import com.example.wyromed.Activity.Interface.InboxInterface
import com.example.wyromed.Helper.RecyclerItemTouchHelper
import com.example.wyromed.Activity.Interface.RecyclerItemTouchHelperListener
import com.example.wyromed.Activity.Presenter.InboxPresenter
import com.example.wyromed.Activity.Presenter.StockItemPresenter
import com.example.wyromed.Activity.Presenter.TotalStockItemPresenter
import com.example.wyromed.Activity.SettingActivity
import com.example.wyromed.Model.ListMessage
import com.example.wyromed.R
import com.example.wyromed.Response.Inbox.DataInbox
import com.example.wyromed.ViewHolder.ListMessageViewHolder
import com.example.wyromedapp.Adapter.ListMessageAdapter
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class InboxFragment : Fragment(), RecyclerItemTouchHelperListener, InboxInterface, ListMessageAdapter.CellClickListener {
    var listMessages: ArrayList<DataInbox> = ArrayList()
    var listMessagesSearch: ArrayList<DataInbox> = ArrayList()
    var rvListMessage: RecyclerView? = null
    var adapter: ListMessageAdapter? = null
    var searchView: androidx.appcompat.widget.SearchView? = null
    private var rootLayout: ConstraintLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_inbox, container, false)

        //INIT VIEW
        rvListMessage = v.findViewById(R.id.rv_message)
        rootLayout = v.findViewById(R.id.rootLayout)
        searchView = v.findViewById(R.id.inboxSearchView)

        //Get Message
        getMessage()

        return v
    }

    private fun getMessage(){
        InboxPresenter(this).getAllInbox(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_message, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val drawable = menu.getItem(0).icon
        drawable.mutate()
        drawable.setColorFilter(resources.getColor(R.color.colorGreen), PorterDuff.Mode.SRC_IN)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_edit) {
            true
        } else super.onOptionsItemSelected(item)
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        if (viewHolder is ListMessageViewHolder) {
            val title: String = listMessages[viewHolder.getAdapterPosition()].title.toString()
            val deleteItem = listMessages[viewHolder.getAdapterPosition()]
            val deleteIndex = viewHolder.getAdapterPosition()
            adapter!!.removeItem(deleteIndex)
            val snackbar =
                Snackbar.make(rootLayout!!, "$title Removed Message! ", Snackbar.LENGTH_LONG)
            snackbar.setAction(
                "UNDO"
            ) { adapter!!.restoreItem(deleteItem, deleteIndex) }
            snackbar.setActionTextColor(Color.WHITE)
            snackbar.show()
        }
    }

    override fun onSuccessGetInbox(dataInbox: ArrayList<DataInbox?>?) {
        listMessages = dataInbox as ArrayList<DataInbox>

        listMessagesSearch.clear()
        listMessagesSearch.addAll(listMessages)

        if(context != null) {
            adapter = ListMessageAdapter(requireContext(), listMessagesSearch, this)
            rvListMessage?.setLayoutManager(LinearLayoutManager(context))
            rvListMessage?.setAdapter(adapter)
            rvListMessage?.setItemAnimator(DefaultItemAnimator())
            rvListMessage?.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        searchView?.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    listMessagesSearch.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    listMessages.forEach {
                        if(it.title!!.toLowerCase(Locale.getDefault()).contains(search)){
                            listMessagesSearch.add(it)
                        }
                    }

                    rvListMessage?.adapter!!.notifyDataSetChanged()
                }else{
                    listMessagesSearch.clear()
                    listMessagesSearch.addAll(listMessages)
                    rvListMessage?.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })

        val itemTouchHelperCallBack: ItemTouchHelper.SimpleCallback =
            RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(rvListMessage)
    }

    override fun onErrorGetInbox(msg: String?) {
        toast("Failed to get data message")
    }

    override fun onCellClickListener(id: Int, choose: Int, title: String) {
        var message = ""
        when(choose){
            1 -> { startActivity<DetailMessageActivity>(
                DetailMessageActivity.TAGS.MESSAGE to message,
                DetailMessageActivity.TAGS.ID to id,
                DetailMessageActivity.TAGS.CHOOSE to choose,
                DetailMessageActivity.TAGS.TITLE to title) }
            2 -> { startActivity<DetailMessageActivity>(
                DetailMessageActivity.TAGS.MESSAGE to message,
                DetailMessageActivity.TAGS.ID to id,
                DetailMessageActivity.TAGS.CHOOSE to choose,
                DetailMessageActivity.TAGS.TITLE to title) }
            3 -> { startActivity<DetailMessageActivity>(
                DetailMessageActivity.TAGS.MESSAGE to message,
                DetailMessageActivity.TAGS.ID to id,
                DetailMessageActivity.TAGS.CHOOSE to choose,
                DetailMessageActivity.TAGS.TITLE to title) }
            else -> toast("error")
        }
    }
}