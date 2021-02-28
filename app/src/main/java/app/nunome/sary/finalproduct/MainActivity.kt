package app.nunome.sary.finalproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        easyButton.setOnClickListener {
            val toEasyIntent = Intent(this@MainActivity, EasyActivity::class.java)
            startActivity(toEasyIntent)
        }

        rewardButton.setOnClickListener {
            val toRewardIntent = Intent(this@MainActivity, RewardActivity::class.java)
            startActivity(toRewardIntent)
        }

        addFloatingActionButton.setOnClickListener {
            val toAddIntent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(toAddIntent)
        }
    }
}