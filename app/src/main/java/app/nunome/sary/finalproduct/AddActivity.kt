package app.nunome.sary.finalproduct

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*


class AddActivity : AppCompatActivity(), OnMapReadyCallback {

    var checks : String = ""
    var realm : Realm = Realm.getDefaultInstance()
    var area = LatLng(0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val easycheckBox = findViewById<CheckBox>(R.id.easyCheckBox)
        val rewardcheckBox = findViewById<CheckBox>(R.id.rewardCheckBox)

        easycheckBox.setOnClickListener {
            checks = "1"
        }
        rewardcheckBox.setOnClickListener {
            checks = "2"
        }


        findButton.setOnClickListener {
            val findname = findEditText.text.toString()
            val mapIntent = Intent(Intent.ACTION_VIEW)
            mapIntent.data = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + findname)
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            }
        }


        registerButton.setOnClickListener {
            val storename: String = storeEditText.text.toString()
            val type: String = typeEditText.text.toString()
            val price: String = priceEditText.text.toString()
            val adre: String = adressEditText.text.toString()
            val area: LatLng = area
            val onemark: Double = area.latitude
            val twomark: Double = area.longitude

            save(storename, type, price, adre, checks, onemark, twomark)

            val toMainIntent = Intent(this@AddActivity, MainActivity::class.java)
            startActivity(toMainIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


    fun save(storename: String, type: String, price: String, adre: String, checks: String, onemark: Double, twomark: Double) {
        realm.executeTransaction {
            val store = realm.createObject(Store::class.java, UUID.randomUUID().toString())
            store.storesname = storename
            store.foodtype = type
            store.foodprice = price
            store.adrees = adre
            store.check = checks
            store.firstmarker = onemark
            store.secondmarker = twomark
            val id : String = store.id
            save2(id)
        }
    }

    fun save2(id: String) {
        val memo = realm.createObject(Memo::class.java, UUID.randomUUID().toString())
        memo.storeId = id
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toListActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(toListActivityIntent)
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        val map = googleMap

        map.setOnMapLongClickListener(object : GoogleMap.OnMapLongClickListener {

            override fun onMapLongClick(latlng: LatLng) {
                val location = LatLng(latlng.latitude, latlng.longitude)
                googleMap.addMarker(MarkerOptions().position(location))
                val onemark = latlng.latitude
                val twomark = latlng.longitude
                area = LatLng(onemark, twomark)
            }
        })
    }
}