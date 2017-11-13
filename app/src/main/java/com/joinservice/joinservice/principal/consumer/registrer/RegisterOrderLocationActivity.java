package com.joinservice.joinservice.principal.consumer.registrer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.joinservice.joinservice.R;
import com.joinservice.joinservice.maps.MapsFragment;

import basica.Servico;

public class RegisterOrderLocationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback{

    private GoogleApiClient mGoogleApiClient;
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    GoogleMap map;
    double longitude;
    double latitude;
    Servico servico;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_order_location);

        Intent intent  = getIntent();
        servico = (Servico) intent.getSerializableExtra("servico");

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (checkLocationPermission()) {
            callConnection();
        }

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.frameLayoutContainerMapRegisterOrderLocation, new MapsFragment(), "MapsFragment" );

        transaction.commitAllowingStateLoss();

        /*MapView mapa = (MapView) findViewById(R.id.map_viewRegisterOrderLocation);
        mapa.getController().setZoom(17);
        GeoPoint status = new GeoPoint((int) latitude * 1000000, (int) longitude * 1000000);
        mapa.getController().setCenter(status);
        mapa.setBuiltInZoomControls(true);*/

        /*MyLocationOverlay mlo = new MyLocationOverlay(this, mapView) ;
        mlo.enableCompass() ;
        mlo.enableMyLocation() ;
        mapView.getOverlays().add(mlo) ;*/
    }

    public void proximo(View v){
        servico.setLatitude(Double.toString(latitude));
        servico.setLongitude(Double.toString(longitude));
        Intent itProximo = new Intent(this, RegisterOrderDescriptionActivity.class);
        itProximo.putExtra("servico", servico);
        startActivity(itProximo);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public boolean checkLocationPermission()
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    private synchronized void callConnection(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOG", "onConnected" + bundle + ")");

        Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (l != null){
            Log.i("LOG", "Latitude:" + l.getLatitude() + ")");
            Log.i("LOG", "longitude" + l.getLongitude() + ")");
            longitude = l.getLongitude();
            latitude = l.getLatitude();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //
    }
}
