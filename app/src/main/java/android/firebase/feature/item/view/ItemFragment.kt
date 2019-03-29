package android.firebase.feature.item.view

import android.firebase.R
import android.firebase.common.view.recycler.SwipeTouchHelperCallback
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
            checkChangedListener = { item -> presenter.onItemCheckedChange(item) },
            clickListener = { item -> presenter.onItemClicked(item) }
        )

        itemRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }.also {
            ItemTouchHelper(
                SwipeTouchHelperCallback(
                    swipeListener = { position ->
                        presenter.itemSwiped(itemAdapter.items[position])
                    }
                )
            ).attachToRecyclerView(it)
        }
    }

    override fun showInviteError() {
        showError(getString(R.string.invite_error))
    }

    override fun showNotAuthenticated() {
        showError(getString(R.string.not_authenticated))
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
                setTitle(getString(R.string.title))

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                setView(input)

                setPositiveButton(getString(R.string.ok)) { _, _ -> presenter.addItem(input.text.toString()) }
                setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    override fun showEditDialog(item: Item) {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle(getString(R.string.title))

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                input.setText(item.title)
                setView(input)

                setPositiveButton(getString(R.string.ok)) { _, _ ->
                    presenter.updateItem(item.copy(
                        title = input.text.toString()
                    ))
                }
                setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    override fun showEmailInputDialog() {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle(getString(R.string.email))

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                setView(input)

                setPositiveButton(getString(R.string.ok)) { _, _ -> presenter.shareByEmail(input.text.toString()) }
                setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }
}