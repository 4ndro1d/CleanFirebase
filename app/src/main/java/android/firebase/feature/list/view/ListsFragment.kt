package android.firebase.feature.list.view

import android.firebase.R
import android.firebase.common.view.recycler.SwipeTouchHelperCallback
import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.presentation.ListsPresenter
import android.firebase.feature.list.presentation.ListsView
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.fragment_lists.*
import org.koin.android.ext.android.inject

class ListsFragment : Fragment(), ListsView {

    private val presenter: ListsPresenter by inject()

    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_lists, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        presenter.start(this)

        addListButton.setOnClickListener {
            presenter.addListButtonClicked()
        }
    }

    private fun initRecycler() {
        listAdapter = ListAdapter(
            clickListener = { list -> presenter.onListClicked(list) },
            longClickListener = { list -> presenter.onListLongClicked(list) }
        )

        listRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL).apply {
                setDrawable(resources.getDrawable(R.drawable.divider))
            })
        }.also {
            ItemTouchHelper(SwipeTouchHelperCallback(
                swipeListener = { position ->
                    presenter.listSwiped(listAdapter.lists[position])
                })
            ).attachToRecyclerView(it)
        }
    }

    override fun showEditDialog(list: MyList) {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle(getString(R.string.title))

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                input.setText(list.title)
                setView(input)

                setPositiveButton(getString(R.string.ok)) { _, _ ->
                    presenter.updateList(list.copy(
                        title = input.text.toString()
                    ))
                }
                setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    override fun showMissingPermission() {
        Toast.makeText(context, getString(R.string.missing_permission), Toast.LENGTH_SHORT).show()
    }

    override fun showDeleteListConfirmation(myList: MyList) {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle(getString(R.string.confirm_delete))
                setPositiveButton(getString(R.string.ok)) { _, _ -> presenter.deleteList(myList) }
                setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    override fun navigateToItemsForList(listId: String, title: String) {
        val action = ListsFragmentDirections.actionListsFragmentToItemsFragment(listId, title)
        findNavController().navigate(action)
    }

    override fun showInputDialog() {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle(getString(R.string.title))

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                setView(input)

                setPositiveButton(getString(R.string.ok)) { _, _ -> presenter.addList(input.text.toString()) }
                setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    override fun listRemoved(list: MyList) {
        listAdapter.removeList(list)
    }

    override fun listAdded(list: MyList) {
        listAdapter.addList(list)
    }

    override fun listModified(list: MyList) {
        listAdapter.modifyList(list)
    }

    override fun showNotAuthenticated() {
        findNavController().navigate(R.id.action_listsFragment_to_authFragment)
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }
}