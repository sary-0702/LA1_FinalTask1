package app.nunome.sary.finalproduct

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val view = mapFragment.view
        view?.isClickable = true

        val id = intent.getStringExtra("id")
        val names = intent.getStringExtra("name")
        val types= intent.getStringExtra("types")
        val prices = intent.getStringExtra("price")
        val adresss = intent.getStringExtra("adress")

        storesTextVIew.setText(names)
        typesTextView.setText(types)
        pricesTextView.setText(prices)
        adressTextView.setText(adresss)

        val toolbar = findViewById<Toolbar>(R.id.toolbar5)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        memoButton.setOnClickListener {
            val toMemoIntent = Intent(this@DetailsActivity, MemoActivity::class.java)
            toMemoIntent.putExtra("id", id)
            startActivity(toMemoIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toMainActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(toMainActivityIntent)

        val id = intent.getStringExtra("id")

        when (item.itemId) {
            R.id.delete_button -> {
                val delstoredata = realm.where(Store::class.java)
                        .equalTo("id", id)
                        .findAll()
                realm.executeTransaction {
                    delstoredata.deleteAllFromRealm()
                }
                startActivity(toMainActivityIntent)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val id = intent.getStringExtra("id")
        val storedata = realm.where(Store::class.java)
                .equalTo("id", id)
                .findFirst()
        Log.d("storedata", storedata.toString())

        val location = storedata?.let { LatLng(it?.firstmarker, it?.secondmarker) }
        googleMap.addMarker(location?.let { MarkerOptions().position(it) })
    }
}