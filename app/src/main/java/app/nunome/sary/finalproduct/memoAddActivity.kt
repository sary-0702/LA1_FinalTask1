package app.nunome.sary.finalproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_memo_add.*
import java.util.*

class memoAddActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_add)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        memoAddButton.setOnClickListener {
            val memo: String = addMemoEditText.text.toString()
            val id = intent.getStringExtra("id")

            realm.executeTransaction {
                var storesmemo = realm.where<Memo>().equalTo("storeId", id).findFirst()
                var oldmemodata = storesmemo?.memo.toString()
                storesmemo?.memo = oldmemodata + "\n" + memo
            }
            val toMemoIntent = Intent(this@memoAddActivity, MemoActivity::class.java)
            toMemoIntent.putExtra("id", id)
            startActivity(toMemoIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toMemoIntent = Intent(this@memoAddActivity, MemoActivity::class.java)
        startActivity(toMemoIntent)
        return super.onOptionsItemSelected(item)
    }
}