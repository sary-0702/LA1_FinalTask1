package app.nunome.sary.finalproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.easy_store_data_cell.view.*

class memoRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<memoRecyclerViewAdapter.ViewHolder> () {

    val storememo: MutableList<Store> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.memo_data_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storememo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = storememo[position]
        holder.StoreMemoTextView.text = store.memo

        /*
        holder.container?.setOnClickListener {
            listener.onItemClick(store)
        }
        */
    }

    fun addAll(storememo: List<Store>) {
        this.storememo.addAll(storememo)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val StoreMemoTextView: TextView = view.findViewById(R.id.memotextView)

        //val container: ConstraintLayout? = view.constraint
    }
}