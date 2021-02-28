package app.nunome.sary.finalproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_easy.*

class EasyActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy)

        val easyStore = readAll()

        val easyadapter = easyRecyclerViewAdapter(this, object : easyRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(item: Store) {
                val toDetailsIntent = Intent(this@EasyActivity, DetailsActivity::class.java)
                toDetailsIntent.putExtra("name", item.storesname)
                toDetailsIntent.putExtra("types", item.foodtype)
                toDetailsIntent.putExtra("price", item.foodprice)
                toDetailsIntent.putExtra("checks", item.check)
                startActivity(toDetailsIntent)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = easyadapter

        easyadapter.addAll(easyStore)

        easyaddFloatingActionButton.setOnClickListener {
            val toAddIntent = Intent(this@EasyActivity, AddActivity::class.java)
            startActivity(toAddIntent)
        }
    }

    fun readAll(): RealmResults<Store> {
        return realm.where(Store::class.java)
            .equalTo("check", "1")
            .findAll()
    }
}