package android.firebase.feature.item.view

import android.firebase.R
import android.firebase.common.view.recycler.DeleteTouchHelperCallback
import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.presentation.ItemPresenter
import android.firebase.feature.item.presentation.ItemView
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_items.*
import org.koin.android.ext.android.inject

class ItemFragment : Fragment(), ItemView, LifecycleOwner {

    private val presenter: ItemPresenter by inject()

    private val safeArgs by lazy { arguments?.let { ItemFragmentArgs.fromBundle(it) } }
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(android.firebase.R.layout.fragment_items, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        safeArgs?.listId?.let { presenter.setListId(it) }
        presenter.start(this)

        addItemButton.setOnClickListener {
            presenter.addItemButtonClicked()
        }

        shareButton.setOnClickListener {
            presenter.shareButtonClicked()
        }
    }

    private fun initRecycler() {
        itemAdapter = ItemAdapter(
            clickListener = { item -> presenter.onItemCheckedChange(item) }
        )

        itemRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }.also {
            ItemTouchHelper(
                DeleteTouchHelperCallback(
                    deleteIcon = resources.getDrawable(R.drawable.ic_add),
                    swipeListener = { position ->
                        presenter.removeItem(itemAdapter.items[position])
                    }
                )
            ).attachToRecyclerView(it)
        }
    }

    override fun showNotAuthenticated() {
        Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun itemRemoved(item: Item) {
        itemAdapter.removeItem(item)
    }

    override fun itemAdded(item: Item) {
        itemAdapter.addItem(item)
    }

    override fun itemModified(item: Item) {
        itemAdapter.modifyItem(item)
    }

    override fun showInputDialog() {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Title")

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                setView(input)

                setPositiveButton("OK") { _, _ -> presenter.addItem(input.text.toString()) }
                setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    override fun showEmailInputDialog() {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Email:")

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                setView(input)

                setPositiveButton("OK") { _, _ -> presenter.shareByEmail(input.text.toString()) }
                setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }
}