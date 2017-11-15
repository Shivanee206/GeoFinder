package com.example.shivani.geofinder;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Arrays;
import java.util.List;

public class PolyLineActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private static final PatternItem DOT = new Dot();
    private static float PATTERN_GAP_LENGTH_PX=20;
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    //
// Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poly_line);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add polylines and polygons to the map. This section shows just
        // a single polyline. Read the rest of the tutorial to learn more.
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.DKGRAY)
                .geodesic(true)
                .add(
                        new LatLng(28.6139, 77.2090),
                        new LatLng(26.4499, 80.3319),
                        new LatLng(25.3176, 82.9739),
                        new LatLng(19.0760, 72.8777),
                        new LatLng(12.9716, 77.5946)));
        // and set the zoom factor so most of Australia shows on the screen.
        googleMap.addMarker(new MarkerOptions().position(new LatLng(28.6139, 77.2090))
                .snippet("I am living here since one year !")
                .title("National Capital"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(26.4499, 80.3319))
                .snippet("I have Graduated from here recently...")
                .title("The Manchester of the East"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(25.3176, 82.9739))
                .snippet("My Home Town, missing it badly !")
                .title("Oldest Living City"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(19.0760, 72.8777))
                .snippet("Highest Tax Payers of India live here..")
                .title("Bollywood Factory"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(12.9716, 77.5946))
                .snippet("The mushroom of IT industry in India")
                .title("IT HUB"));      
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.6139, 77.2090), 4));

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        //googleMap.setOnPolygonClickListener(this);
    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }

//        Toast.makeText(this, "Route type " + polyline.getTag().toString(),
               // Toast.LENGTH_SHORT).show();
    }
}

