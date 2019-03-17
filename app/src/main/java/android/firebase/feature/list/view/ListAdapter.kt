package android.firebase.feature.list.view

import android.firebase.R
import android.firebase.feature.list.domain.model.MyList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val clickListener: (list: MyList) -> Unit,
    var lists: List<MyList> = mutableListOf()
) : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder =
        ListHolder(LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false))

    override fun getItemCount(): Int =
        lists.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(lists[position])
    }

    inner class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title by lazy { itemView.findViewById<TextView>(R.id.title) }

        fun bind(list: MyList) {
            itemView.setOnClickListener {
                clickListener.invoke(list)
            }

            title.text = list.title
        }
    }
}