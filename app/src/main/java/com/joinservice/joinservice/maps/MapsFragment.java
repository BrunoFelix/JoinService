package com.joinservice.joinservice.maps;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback  {

    private GoogleMap mMap;
    double  longitude;
    double  latitude;
    boolean exibirRota;
    double longitudeProfissional;
    double latitudeProfissional;
    private Polyline polyline;
    private List<LatLng> list;



    public MapsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);

        if (getArguments() != null) {
            longitude = getArguments().getDouble("LONGITUDE");
            latitude = getArguments().getDouble("LATITUDE");
            exibirRota = getArguments().getBoolean("EXIBIRROTA");

            if (exibirRota){
                longitudeProfissional = getArguments().getDouble("LONGITUDEPROFISSIONAL");
                latitudeProfissional = getArguments().getDouble("LATITUDEEPROFISSIONAL");
            }
        }

        Route Route = new Route(new LatLng(latitude, longitude), new LatLng(latitudeProfissional, longitudeProfissional));
        Route.execute();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);


        if (exibirRota) {
            /*LatLngBounds zoom = new LatLngBounds(
                    new LatLng(latitude, longitude), new LatLng(latitudeProfissional, longitudeProfissional));*/

            //Defini um marcador no local onde o serviço deve ser executado
            LatLng marcacao = new LatLng(latitude, longitude);
            MarkerOptions mo = new MarkerOptions();
            mo.position(marcacao).title("Sua localização atual");
            mMap.addMarker(mo);

            //Defini um marcador onde o profissional se encontra
            marcacao = new LatLng(latitudeProfissional, longitudeProfissional);
            mo = new MarkerOptions();
            mo.position(marcacao).title("Serviço deve ser feito aqui");
            mMap.addMarker(mo);


            LatLng marcador = new LatLng(latitude,longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcador, 15));

          // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom.getCenter(), 10));

        }else{
            LatLng marcador = new LatLng(latitude,longitude);

            //Defini um marcador no local onde o serviço deve ser executado
            LatLng marcacao = new LatLng(latitude, longitude);
            MarkerOptions mo = new MarkerOptions();
            mo.position(marcacao).title("Sua localização atual");
            mMap.addMarker(mo);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcador, 15));
        }
    }

    public class Route extends AsyncTask<Void, Void, List<LatLng>> {
        private List<Marker> originMarkers = new ArrayList<>();
        private List<Marker> destinationMarkers = new ArrayList<>();
        private List<Polyline> polylinePaths = new ArrayList<>();
        private ProgressDialog progressDialog;
        private long distance;
        private List<LatLng> listaRouter;
        ProgressDialog load;

        LatLng origin;
        LatLng destination;

        public Route(LatLng origin, LatLng destination){
            this.origin = origin;
            this.destination = destination;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            load = new ProgressDialog(getActivity());
            // Setting Title
            load.setTitle("Traçando rota, aguarde...");
            // Setting Message
            load.setMessage("encontrando a melhor rota entre você e o serviço...");
            // Progress Dialog Style Horizontal
            load.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            /*// Progress Dialog Style Spinner
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);*/
            load.setMax(100);
            load.show();
        }

        @Override
        protected List<LatLng> doInBackground(Void... voids) {
            load.incrementProgressBy(10);
            listaRouter = getRoute();
            ajustandoTempo();
            load.incrementProgressBy(10);
            return listaRouter;
        }

        @Override
        protected void onPostExecute(List<LatLng> latLngs) {
            list = listaRouter;
            ajustandoTempo();
            load.incrementProgressBy(10);
            load.dismiss();
            tracarRota();

        }

        public void tracarRota(){
            try {
                if (list != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            //Log.i("Script", answer);
                            drawRoute();

                        }
                    });
                }
            }catch (Exception e){
                //
            }
        }

        public void ajustandoTempo(){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // WEB CONNECTION
        //public void getRoute(final String origin, final String destination){
        public List<LatLng> getRoute()  {
            String link= "http://maps.googleapis.com/maps/api/directions/json?origin="
                    + origin.latitude+","+origin.longitude+"&destination="
                    + destination.latitude+","+destination.longitude+"&sensor=false";

            URL url = null;
            try {
                url = new URL(link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            load.incrementProgressBy(10);

            ajustandoTempo();

            InputStream is = null;
            try {
                is = url.openConnection().getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            load.incrementProgressBy(10);

            ajustandoTempo();

            final StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            load.incrementProgressBy(10);

            ajustandoTempo();

            try {
                list = buildJSONRoute(buffer.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            load.incrementProgressBy(10);

            return list;
        /*this.runOnUiThread(new Runnable(){
            public void run(){
                try {
                    //Log.i("Script", answer);
                    list = buildJSONRoute(buffer.toString());
                    if (!list.isEmpty()) {
                        drawRoute();
                    }
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/

        }

        // PARSER JSON
        public List<LatLng> buildJSONRoute(String json) throws JSONException{
            JSONObject result = new JSONObject(json);
            JSONArray routes = result.getJSONArray("routes");

            distance = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getInt("value");

            load.incrementProgressBy(10);

            JSONArray steps = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
            List<LatLng> lines = new ArrayList<LatLng>();

            for(int i=0; i < steps.length(); i++) {
                Log.i("Script", "STEP: LAT: "+steps.getJSONObject(i).getJSONObject("start_location").getDouble("lat")+" | LNG: "+steps.getJSONObject(i).getJSONObject("start_location").getDouble("lng"));


                String polyline = steps.getJSONObject(i).getJSONObject("polyline").getString("points");

                for(LatLng p : decodePolyline(polyline)) {
                    lines.add(p);
                }

                Log.i("Script", "STEP: LAT: "+steps.getJSONObject(i).getJSONObject("end_location").getDouble("lat")+" | LNG: "+steps.getJSONObject(i).getJSONObject("end_location").getDouble("lng"));
            }

            load.incrementProgressBy(10);

            ajustandoTempo();

            return(lines);
        }

        // DECODE POLYLINE
        private List<LatLng> decodePolyline(String encoded) {

            List<LatLng> listPoints = new ArrayList<LatLng>();
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));
                Log.i("Script", "POL: LAT: "+p.latitude+" | LNG: "+p.longitude);
                listPoints.add(p);
            }
            return listPoints;
        }
    }

    public void drawRoute(){
        PolylineOptions po;

        if(polyline == null){
            po = new PolylineOptions();

            for(int i = 0, tam = list.size(); i < tam; i++){
                po.add(list.get(i));
            }

            po.color(Color.BLACK).width(4);
            polyline = mMap.addPolyline(po);

        }
        else{
            polyline.setPoints(list);
        }
    }
}
