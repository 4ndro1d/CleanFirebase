package android.firebase.feature.list.view

import android.firebase.R
import android.firebase.feature.list.presentation.ListsPresenter
import android.firebase.feature.list.presentation.ListsView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_lists.*
import org.koin.android.ext.android.inject

class ListsFragment : Fragment(), ListsView {

    private val presenter: ListsPresenter by inject()

    lateinit var adapter: ListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_lists, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListAdapter(
            clickListener = { list -> presenter.onListClicked(list) }
        )

        listsRecycler.apply {
            setHasFixedSize(true)
            adapter = adapter
        }

        presenter.start(this)
    }

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }

    override fun navigateToItemsForList(id: String) {
        view?.findNavController()?.navigate(R.id.action_listsFragment_to_itemsFragment)
    }
}