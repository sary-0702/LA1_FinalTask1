package app.nunome.sary.finalproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_easy.*
import kotlinx.android.synthetic.main.activity_reward.*
import kotlinx.android.synthetic.main.activity_reward.recyclerView

class RewardActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)

        val rewardStore = readAll()

        val rewardadapter = easyRecyclerViewAdapter(this, object : easyRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(item: Store) {
                val toDetailsIntent = Intent(this@RewardActivity, DetailsActivity::class.java)
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
        recyclerView.adapter = rewardadapter

        rewardadapter.addAll(rewardStore)

        rewardaddFloatingActionButton.setOnClickListener {
            val toAddIntent = Intent(this@RewardActivity, AddActivity::class.java)
            startActivity(toAddIntent)
        }

        val switch = findViewById<Switch>(R.id.RewardSwitch)
        switch.setOnCheckedChangeListener { buttonView, isCheckedOff ->
            val toEasyIntent = Intent(this@RewardActivity, EasyActivity::class.java)
            startActivity(toEasyIntent)
        }
    }

    fun readAll(): RealmResults<Store> {
        return realm.where(Store::class.java)
                .equalTo("check", "2")
                .findAll()
    }
}