package app.nunome.sary.finalproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.easy_store_data_cell.view.*

class easyRecyclerViewAdapter(private val context: Context, private var listener: OnItemClickListener) : RecyclerView.Adapter<easyRecyclerViewAdapter.ViewHolder> () {

    val easystore : MutableList<Store> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.easy_store_data_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return easystore.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = easystore[position]
        holder.easyStoreNameTextView.text = store.storesname
        holder.easyStoreTypeTextView.text = store.foodtype
        holder.easyStorePriceTextView.text = store.foodprice

        holder.container?.setOnClickListener{
            listener.onItemClick(store)
        }
    }

    fun addAll(easystore: List<Store>) {
        this.easystore.addAll(easystore)
        notifyDataSetChanged()
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val easyStoreNameTextView : TextView = view.findViewById(R.id.easyStoreTextView)
        val easyStoreTypeTextView : TextView = view.findViewById(R.id.easyTypeTextView)
        val easyStorePriceTextView : TextView = view.findViewById(R.id.easyPriceTextView)

        val container : ConstraintLayout? = view.constraint
    }

    interface OnItemClickListener {
        fun onItemClick(item: Store)
    }
}