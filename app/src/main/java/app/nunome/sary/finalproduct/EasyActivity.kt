package app.nunome.sary.finalproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_easy.*
import kotlinx.android.synthetic.main.activity_easy.view.*

class EasyActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy)

        val easyStore = readAll()
        Log.d("data", easyStore.toString())

        val easyadapter = easyRecyclerViewAdapter(this, object : easyRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(item: Store) {
                val toDetailsIntent = Intent(this@EasyActivity, DetailsActivity::class.java)
                toDetailsIntent.putExtra("id", item.id)
                /*
                toDetailsIntent.putExtra("name", item.storesname)
                toDetailsIntent.putExtra("types", item.foodtype)
                toDetailsIntent.putExtra("price", item.foodprice)
                toDetailsIntent.putExtra("adress", item.adrees)
                toDetailsIntent.putExtra("checks", item.check)

                 */
                startActivity(toDetailsIntent)
            }
        })

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).getOrientation())
        recyclerView.addItemDecoration(dividerItemDecoration)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = easyadapter

        easyadapter.addAll(easyStore)

        easyaddFloatingActionButton.setOnClickListener {
            val toAddIntent = Intent(this@EasyActivity, AddActivity::class.java)
            startActivity(toAddIntent)
        }

        val switch = findViewById<Switch>(R.id.easySwitch)
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            val toRewardIntent = Intent(this@EasyActivity, RewardActivity::class.java)
            startActivity(toRewardIntent)
        }
    }

    fun readAll(): RealmResults<Store> {
        return realm.where(Store::class.java)
            .equalTo("check", "1")
            .findAll()

    }
}