package com.example.shivani.geofinder;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PolygonActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolygonClickListener {
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);
    private String TAG = "AREA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polygon);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .strokeColor(Color.YELLOW)
                .fillColor(Color.GRAY)
                .strokePattern(PATTERN_POLYGON_ALPHA)
                .add(
                        new LatLng(28.6139, 77.2090),
                        new LatLng(26.4499, 80.3319),
                        new LatLng(25.3176, 82.9739),
                        new LatLng(12.9716, 77.5946),
                        new LatLng(19.0760, 72.8777)

                ));
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.delhi);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(28.6139, 77.2090))
                .icon(icon)
                .snippet("I am living here since one year !")
                .title("National Capital"));
        BitmapDescriptor icon2 = BitmapDescriptorFactory.fromResource(R.drawable.career);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(12.9716, 77.5946))
                .icon(icon2)
                .snippet("I want to join a Reputed MNC there !")
                .title("The Sillicon Valley Of India"));
        BitmapDescriptor icon3 = BitmapDescriptorFactory.fromResource(R.drawable.entertainment);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(19.0760, 72.8777))
                .icon(icon3)
                .snippet("Place where Ranbir Kapoor Lives !")
                .title("Entertainement Hub"));
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(21.1458, 79.0882));
        latLngs.add(new LatLng(22.7196, 75.8577));
        latLngs.add(new LatLng(26.2183, 78.1828));
        Log.i(TAG, "computeArea " + SphericalUtil.computeArea(latLngs));
        Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .strokePattern(PATTERN_POLYGON_BETA)
                .strokeColor(Color.CYAN)
                .fillColor(Color.BLUE)
                .add(
                        new LatLng(21.1458, 79.0882),
                        new LatLng(22.7196, 75.8577),
                        new LatLng(26.2183, 78.1828)));


        Polygon polygon3 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .strokeColor(Color.MAGENTA)
                .fillColor(Color.GREEN)
                .add(
                        new LatLng(26.8467, 80.9462),
                        new LatLng(26.9124, 75.7873),
                        new LatLng(31.1048, 77.1734)

                ));
// Store a data object with the polygon, used here to indicate an arbitrary type.
        // polygon1.setTag("alpha");
        // stylePolygon(polygon1);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.6139, 77.2090), 4));
        googleMap.setOnPolygonClickListener(this);
    }

    private void stylePolygon(Polygon polygon) {
        String type = "";
        // Get the data object stored with the polygon.
        if (polygon.getTag() != null) {
            type = polygon.getTag().toString();
        }

        List<PatternItem> pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "alpha":
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_PURPLE_ARGB;
                fillColor = COLOR_BLUE_ARGB;
                break;
            case "beta":
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_BETA;
                strokeColor = COLOR_ORANGE_ARGB;
                fillColor = COLOR_BLUE_ARGB;
                break;
        }

        polygon.setStrokePattern(pattern);
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(strokeColor);
        polygon.setFillColor(fillColor);
    }


    @Override
    public void onPolygonClick(Polygon polygon) {

    }
}
