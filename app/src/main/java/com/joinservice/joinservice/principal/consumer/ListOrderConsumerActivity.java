package com.joinservice.joinservice.principal.consumer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.joinservice.joinservice.EditProfile;
import com.joinservice.joinservice.MyServicesFragment;
import com.joinservice.joinservice.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListaAdapterServico;
import Fachada.Fachada;
import basica.Servico;
import basica.Usuario;

public class ListOrderConsumerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nomeUsuario;
    ImageView fotoUsuario;
    ListView listaServicos;
    Fachada fachada;
    FragmentPagerAdapter adapterViewPager;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order_consumer);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        TextView textView = (TextView) findViewById(R.id.tvLabel);
        adapterViewPager = new ListOrderConsumerActivity.MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Meus serviços");

        fachada = Fachada.getInstance(this);

        listaServicos = (ListView) findViewById(R.id.listViewServicos);

        List<Servico> servicos = fachada.ListarServicosUsuario();

        ListaAdapterServico adapterServico = new ListaAdapterServico(this, (ArrayList<Servico>) servicos);

        listaServicos.setAdapter(adapterServico);

        Intent intent  = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
        //TextView nome_usuario = (TextView)findViewById(R.id.nome_usuario);
        //nome_usuario.setText(usuario.getNome().toString());

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        //fotoUsuario = (ImageView) findViewById(R.id.imageViewPrincipalUsuario);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        nomeUsuario = (TextView) header.findViewById(R.id.textViewPrincipalNomeUsuario);
        nomeUsuario.setText(usuario.getNome());

    }



    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int num_itens = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        //retorna o número total de páginas
        @Override
        public int getCount() {
            return num_itens;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MyServicesFragment.newInstance(1, "Meus Serviços");
                case 1:
                    return MyServicesFragment.newInstance(2, "Serviços Salvos");
                case 2:
                    return MyServicesFragment.newInstance(3, "Todos os Serviços");
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }







    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_order_consumer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit_profile) {
            Intent intent  = getIntent();
            Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
            Intent intentEditProfile = new Intent(this, EditProfile.class);
            intentEditProfile.putExtra("usuario", usuario);
            startActivity(intentEditProfile);

            // Handle the camera action
        } /*else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
