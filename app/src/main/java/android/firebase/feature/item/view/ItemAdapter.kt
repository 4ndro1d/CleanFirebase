package android.firebase.feature.item.view

import android.annotation.SuppressLint
import android.firebase.R
import android.firebase.feature.item.domain.model.Item
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val clickListener: (item: Item) -> Unit,
    var items: MutableList<Item> = mutableListOf()
) : RecyclerView.Adapter<ItemAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun getItemCount() =
        items.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position])
    }

    fun removeItem(item: Item) {
        notifyItemRemoved(items.indexOf(item))
        items.remove(item)
    }

    fun modifyItem(item: Item) {
        val index = items.indexOfFirst { it.id == item.id }
        items[index] = item
        notifyItemChanged(index)
    }

    fun addItem(item: Item) {
        val index = items.size
        items.add(index, item)
        notifyItemInserted(index)
    }

    //TODO use LayoutContainer
    inner class ItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val title by lazy<TextView> { view.findViewById(R.id.title) }
        private val checkBox by lazy<CheckBox> { view.findViewById(R.id.todoCheckbox) }

        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: Item) {
            title.text = item.title
            checkBox.isChecked = item.done
            checkBox.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    clickListener.invoke(item.copy(done = !item.done))
                }
                true
            }
//            checkBox.setOnCheckedChangeListener { _, checked -> clickListener.invoke(item.copy(done = checked)) }
        }
    }
}