package app.nunome.sary.finalproduct

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val easycheckBox = findViewById<CheckBox>(R.id.easyCheckBox)
        val rewardcheckBox = findViewById<CheckBox>(R.id.rewardCheckBox)

        easycheckBox.setOnClickListener {
            checks = "1"
        }
        rewardcheckBox.setOnClickListener{
            checks = "2"
        }

        registerButton.setOnClickListener {
            val storename: String = storeEditText.text.toString()
            val type: String = typeEditText.text.toString()
            val price: String = priceEditText.text.toString()
            save(storename, type, price, checks)

            val toMainIntent = Intent(this@AddActivity, MainActivity::class.java)
            startActivity(toMainIntent)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun save(storename: String, type: String, price: String, checks: String) {
        realm.executeTransaction {
            val store = it.createObject(Store::class.java, UUID.randomUUID().toString())
            store.storesname = storename
            store.foodtype = type
            store.foodprice = price
            store.check = checks
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
                MarkerOptions()
                        .position(LatLng(0.0, 0.0))
                        .title("Marker")
        )
    }
}