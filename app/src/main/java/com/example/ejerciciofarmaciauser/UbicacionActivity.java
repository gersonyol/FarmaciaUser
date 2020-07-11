package com.example.ejerciciofarmaciauser;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Marker marcador;
    private double lat = 0.0;
    private double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();


        LatLng Reforma = new LatLng(14.607227, -90.515770);
        mMap.addMarker(new MarkerOptions().position(Reforma).title("Centro").snippet("        Reforma"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Reforma, 13));

        LatLng cayala = new LatLng(14.613578, -90.491995);
        mMap.addMarker(new MarkerOptions().position(cayala).title("Sucursal").snippet("        Cayala"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cayala, 13));

        LatLng Norte = new LatLng(14.648164, -90.451054);
        mMap.addMarker(new MarkerOptions().position(Norte).title("Sucursal").snippet("Norte"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Norte, 13));

        LatLng Mixco = new LatLng(14.664325, -90.571338);
        mMap.addMarker(new MarkerOptions().position(Mixco).title("Sucursal").snippet("         Mixco"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mixco, 13));

    }


    public void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate ubicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 13);
        if (marcador != null)
            marcador.remove();

        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Tu ubicación").snippet("Se encuentra aqui"));
        mMap.animateCamera(ubicacion);
    }


    public void ubicacionActual(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }


    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            ubicacionActual(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public void miUbicacion() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Permisos no otorgados", Toast.LENGTH_SHORT).show();

            return;
        }else{
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            ubicacionActual(location);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 1, listener);


        }


    }



}
