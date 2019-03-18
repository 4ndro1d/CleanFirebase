package android.firebase.feature.list.view

import android.firebase.R
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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
            clickListener = { list -> presenter.onListClicked(list) }
        )

        listRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    override fun navigateToItemsForList(listId: String, title: String) {
        val action = ListsFragmentDirections.actionListsFragmentToItemsFragment(listId, title)
        view?.findNavController()?.navigate(action)
    }

    override fun showInputDialog() {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Title")

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                setView(input)

                setPositiveButton("OK") { _, _ -> presenter.addList(input.text.toString()) }
                setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
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
        Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }
}