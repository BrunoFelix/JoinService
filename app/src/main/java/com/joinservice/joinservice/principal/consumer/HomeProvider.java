package com.joinservice.joinservice.principal.consumer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.joinservice.joinservice.EditProfile;
import com.joinservice.joinservice.R;
import com.joinservice.joinservice.SectionsPagerAdapter;
import com.joinservice.joinservice.TelaInicialPrestador.Tab1ProviderFragment;
import com.joinservice.joinservice.TelaInicialPrestador.Tab2ProviderFragment;
import com.joinservice.joinservice.TelaInicialPrestador.Tab3ProviderFragment;
import com.joinservice.joinservice.TelaLogin;
import com.joinservice.joinservice.principal.consumer.StartCliente;
import com.joinservice.joinservice.principal.consumer.StartPrestador;

import Fachada.Fachada;
import basica.Usuario;

public class HomeProvider extends AppCompatActivity implements SearchView.OnQueryTextListener ,NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "HomeProvider";
    private SectionsPagerAdapter mSectionsPagerAdapter;

    TextView nomeUsuario;
    ImageView fotoUsuario;
    ListView listaServicos;
    Fachada fachada;
    FragmentPagerAdapter adapterViewPager;
    Usuario usuario;
    private static int num_itens = 3;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_provider);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        //Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Nav
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        //ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Serviços");

        //Verifica usuario logado
        Intent intent = getIntent();
        if (intent.getSerializableExtra("usuario") != null) {
            usuario = (Usuario) intent.getSerializableExtra("usuario");
        } else {
            usuario = fachada.usuarioLogado();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit_profile) {
            Intent intent = getIntent();
            usuario = (Usuario) intent.getSerializableExtra("usuario");
            Intent intentEditProfile = new Intent(this, EditProfile.class);
            intentEditProfile.putExtra("usuario", usuario);
            startActivity(intentEditProfile);

        } else if (id == R.id.nav_exit) {
            fachada.usuarioExcluirLogado();
            Intent intentTelaLogin = new Intent(this, TelaLogin.class);
            startActivity(intentTelaLogin);

        } else if (id == R.id.nav_prestador) {
            usuario.setTipo("Consumidor");
            fachada.usuarioAtualizarUsuarioLogado(usuario);
            Intent intentCliente = new Intent(this, StartCliente.class);
            startActivity(intentCliente);
        }else if (id == R.id.findajob_menu_search) {
            Intent intentSearch = new Intent(this, StartPrestador.class);
            startActivity(intentSearch);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1ProviderFragment(),"Abertos");
        adapter.addFragment(new Tab2ProviderFragment(),"Fechados");
        adapter.addFragment(new Tab3ProviderFragment(),"Todos os Serviços");
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        MenuItem searchItem = menu.findItem(R.id.findajob_menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
