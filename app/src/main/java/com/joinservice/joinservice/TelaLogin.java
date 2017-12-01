package com.joinservice.joinservice;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.joinservice.joinservice.maps.MapsFragment;
import com.joinservice.joinservice.principal.consumer.StartCliente;
import com.joinservice.joinservice.principal.consumer.StartPrestador;
import com.joinservice.joinservice.register.RegisterEmailActivity;

import Fachada.Fachada;
import Util.NegocioException;
import basica.Servico;
import basica.Usuario;

public class TelaLogin extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    EditText email, senha;
    Fachada fachada;
    private SQLiteOpenHelper mDBHelper;
    Usuario usuario;

    private GoogleApiClient mGoogleApiClient;
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    GoogleMap map;
    double longitude;
    double latitude;
    Servico servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Button button = (Button) findViewById(R.id.btnEntrar_login);
        Button button1 = (Button) findViewById(R.id.btnCadastrar);
        fachada = Fachada.getInstance(this);

        usuario = fachada.usuarioLogado();

        if (usuario.getId() > 0) {
            verificarTipoUsuario(usuario.getTipo());
        }
    }

    public void entrar(View v) {

        email = (EditText) findViewById(R.id.editText_email_login);
        senha = (EditText) findViewById(R.id.editText_senha_login);

        try {
            usuario = fachada.usuarioLogar(email.getText().toString(), senha.getText().toString());

            if (usuario.getId() > 0) {
                verificarTipoUsuario(usuario.getTipo());
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.texto_toast_01), Toast.LENGTH_LONG).show();
            }
        } catch (NegocioException e) {
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void verificarTipoUsuario(String tipo){

        Toast.makeText(getApplicationContext(), getString(R.string.texto_toast_02), Toast.LENGTH_LONG).show();

        verificarLocalizacaoUsuario();

        Intent itEntrar;

        if (tipo.equals("Prestador")) {
            itEntrar = new Intent(TelaLogin.this, StartPrestador.class);
        }else{
            itEntrar = new Intent(TelaLogin.this, StartCliente.class);
        }

        startActivity(itEntrar);
        finish();
    }

    public void verificarLocalizacaoUsuario(){
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (checkLocationPermission()) {
            callConnection();
        }
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

            usuario.setLatitude(Double.toString(latitude));
            usuario.setLongitude(Double.toString(longitude));
            fachada.usuarioAtualizarLocalizacaoUsuarioLogado(usuario);
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

    public void cadastrar(View v) {
        Intent itCadastro = new Intent(TelaLogin.this, RegisterEmailActivity.class);
        startActivity(itCadastro);
        /*
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Deseja se cadastrar como: ");

        alert.setPositiveButton("Usuário", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Usuario usuario = new Usuario();
                usuario.setTipo("USUARIO");
                Intent itCadastro = new Intent(TelaLogin.this, RegisterEmailActivity.class);
                startActivity(itCadastro);
            }
        })
                .setNeutralButton("Prestador", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Usuario usuario = new Usuario();
                        usuario.setTipo("PRESTADOR");
                        Intent itCadastro = new Intent(TelaLogin.this, RegisterEmailActivity.class);
                        startActivity(itCadastro);
                    }
                });

        alert.show();*/

    }
}
