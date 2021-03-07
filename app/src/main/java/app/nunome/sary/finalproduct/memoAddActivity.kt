package app.nunome.sary.finalproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import io.realm.Realm
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
            //val id = intent.getStringExtra("id")
            realm.executeTransaction {
                val addmemo = realm.createObject(Store::class.java, UUID.randomUUID().toString())
                addmemo.memo = memo
            }
            val toMemoIntent = Intent(this@memoAddActivity, MemoActivity::class.java)
            startActivity(toMemoIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toMemoIntent = Intent(this@memoAddActivity, MemoActivity::class.java)
        startActivity(toMemoIntent)
        return super.onOptionsItemSelected(item)
    }
}