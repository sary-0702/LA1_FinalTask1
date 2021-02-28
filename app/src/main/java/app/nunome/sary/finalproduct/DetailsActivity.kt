package app.nunome.sary.finalproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val names = intent.getStringExtra("name")
        val types= intent.getStringExtra("types")
        val prices = intent.getStringExtra("price")

        storesTextVIew.setText(names)
        typesTextView.setText(types)
        pricesTextView.setText(prices)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
                MarkerOptions()
                        .position(LatLng(0.0, 0.0))
                        .title("Marker")
        )
    }
}