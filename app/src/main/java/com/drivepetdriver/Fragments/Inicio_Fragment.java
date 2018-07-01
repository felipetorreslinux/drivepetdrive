package com.drivepetdriver.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.drivepetdriver.R;
import com.drivepetdriver.View.View_Menu;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Inicio_Fragment extends Fragment implements View.OnClickListener {

    View rootview;

    MapView mMapView;
    GoogleMap googleMap;
    FusedLocationProviderClient mFusedLocationClient;
    Geocoder geocoder;
    List<Address> addresses;

    LinearLayout item_my_location;

    @Nullable
    @Override
    @SuppressLint("MissingPermission")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_inicio, viewGroup, false);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        item_my_location = (LinearLayout) rootview.findViewById(R.id.item_my_location);
        item_my_location.setOnClickListener(this);
        mMapView = (MapView) rootview.findViewById(R.id.map_inicio);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        MapsInitializer.initialize(getActivity().getApplicationContext());
        mapLocalUser();

        return rootview;
    };

    public void mapLocalUser(){
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(final Location location) {
                            if (location != null) {
                                final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(16).build();
                                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            }else{
                                googleMap.clear();
                            }
                        }
                    });

            }
        });
    };

    public void moveCameraMap(){
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(googleMap.getMyLocation().getLatitude(), googleMap.getMyLocation().getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();

            System.out.print(address);

        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_my_location:
                mapLocalUser();
                break;
        }
    }
}
