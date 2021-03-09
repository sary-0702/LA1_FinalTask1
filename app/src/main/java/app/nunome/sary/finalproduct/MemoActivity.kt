package app.nunome.sary.finalproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_easy.*
import kotlinx.android.synthetic.main.activity_memo.*
import java.util.*

class MemoActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        val memoAdapter = memoRecyclerViewAdapter(this)
        val id = intent.getStringExtra("id")

        val memoStore = readAll()

        memoRecyclerView.layoutManager = LinearLayoutManager(this)
        memoRecyclerView.adapter = memoAdapter

        memoAdapter.addAll(memoStore)

        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        memoaddFloatingActionButton.setOnClickListener {
            val toMemoIntent = Intent(this@MemoActivity, memoAddActivity::class.java)
            toMemoIntent.putExtra("id", id)
            startActivity(toMemoIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toDetailIntent = Intent(this@MemoActivity, DetailsActivity::class.java)
        val id = intent.getStringExtra("id")

        toDetailIntent.putExtra("id", id)
        startActivity(toDetailIntent)
        return super.onOptionsItemSelected(item)
    }

    fun readAll(): RealmResults<Memo> {
        val id = intent.getStringExtra("id")
        return realm.where(Memo::class.java)
                .equalTo("storeId", id)
                .findAll()
    }
}