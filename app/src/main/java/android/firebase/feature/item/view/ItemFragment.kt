package android.firebase.feature.item.view

import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.presentation.ItemPresenter
import android.firebase.feature.item.presentation.ItemView
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_item_list.*
import org.koin.android.ext.android.inject

class ItemFragment : Fragment(), ItemView, LifecycleOwner {

    private val presenter: ItemPresenter by inject()

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(android.firebase.R.layout.fragment_item_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter(
            clickListener = { item -> presenter.onItemCheckedChange(item) }
        )
        itemRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        presenter.start(this)

        addTodoButton.setOnClickListener {
            presenter.addTodoButtonClicked()
        }
    }

    override fun showItems(items: List<Item>) {
        itemAdapter.items = items
        itemAdapter.notifyDataSetChanged()
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

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }
}