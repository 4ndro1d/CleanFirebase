package android.firebase.feature.lists.view

import android.firebase.R
import android.firebase.feature.lists.domain.model.Todo
import android.firebase.feature.lists.presentation.TodoPresenter
import android.firebase.feature.lists.presentation.TodoView
import android.firebase.feature.lists.remote.repository.TodoRemoteSourceImpl.Companion.COLLECTION_TODOS
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.koin.android.ext.android.inject

class TodoFragment : Fragment(), TodoView, LifecycleOwner {

    private val presenter: TodoPresenter by inject()

    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(android.firebase.R.layout.fragment_todo_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.start(this)

        initAdapter()

        addTodoButton.setOnClickListener {
            presenter.addTodoButtonClicked()
        }
    }

    private fun initAdapter() {
        //TODO FirebaseUI won't work with clean :(
        val query = firestore
            .collection(COLLECTION_TODOS)
            .limit(50)

        val options = FirestoreRecyclerOptions.Builder<Todo>()
            .setQuery(query, Todo::class.java)
            .setLifecycleOwner(this)
            .build()

        val adapter = object : FirestoreRecyclerAdapter<Todo, TodoHolder>(options) {
            override fun onBindViewHolder(holder: TodoHolder, position: Int, model: Todo) {
                holder.bind(model)
            }

            override fun onCreateViewHolder(group: ViewGroup, i: Int) =
                TodoHolder(LayoutInflater.from(group.context).inflate(R.layout.todo, group, false))
        }

        todoList.adapter = adapter
        todoList.layoutManager = LinearLayoutManager(context)
    }

    override fun showInputDialog() {
        context?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Title")

                val input = EditText(context)
                input.inputType = InputType.TYPE_CLASS_TEXT
                setView(input)

                setPositiveButton("OK") { _, _ -> presenter.addTodo(input.text.toString()) }
                setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    //TODO use LayoutContainer
    inner class TodoHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val title by lazy<TextView> { view.findViewById(R.id.title) }
        private val checkBox by lazy<CheckBox> { view.findViewById(R.id.todoCheckbox) }

        fun bind(todo: Todo) {
            title.text = todo.title
            checkBox.isChecked = todo.done
            checkBox.setOnCheckedChangeListener { _, checked -> presenter.updateTodo(todo.copy(done = checked)) }
        }
    }
}