package com.joinservice.joinservice.principal.consumer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.joinservice.joinservice.EditProfile;
import com.joinservice.joinservice.MyServicesFragment;
import com.joinservice.joinservice.R;
import com.joinservice.joinservice.TelaInicialPrestador.PrestadorTela1;
import com.joinservice.joinservice.TelaInicialPrestador.PrestadorTela2;
import com.joinservice.joinservice.TelaInicialPrestador.PrestadorTela3;
import com.joinservice.joinservice.TelaLogin;

import Fachada.Fachada;
import basica.Usuario;


public class StartPrestador extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    TextView nomeUsuario;
    ImageView fotoUsuario;
    ListView listaServicos;
    Fachada fachada;
    FragmentPagerAdapter adapterViewPager;
    Usuario usuario;
    private static int num_itens = 3;

    //Titulos das Paginas
    private static final String[] TITLES = new String[]{
            "Abertos",
            "Finalizados",
            "Todos os Serviços"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_prestador);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new StartPrestador.MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Serviços");

        fachada = Fachada.getInstance(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < num_itens; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(TITLES[i]));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vpPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(onTabSelectedListener(viewPager));


        Intent intent = getIntent();

        //Pega Usuário logado
        usuario = fachada.usuarioLogado();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        nomeUsuario = (TextView) header.findViewById(R.id.textViewPrincipalNomeUsuario);
        nomeUsuario.setText(usuario.getNome());

    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager pager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
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
            usuario = fachada.usuarioLogado();
            Intent intentEditProfile = new Intent(this, EditProfile.class);
            intentEditProfile.putExtra("usuario", usuario);
            startActivity(intentEditProfile);

        } else if (id == R.id.nav_exit) {
            fachada.usuarioExcluirLogado();
            Intent intentTelaLogin = new Intent(this, TelaLogin.class);
            startActivity(intentTelaLogin);

        } else if (id == R.id.nav_prestador) {
            usuario.setTipo("Cliente");
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {


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
                    return PrestadorTela1.newInstance("Find a Job");
                case 1:
                    return PrestadorTela2.newInstance("Finalizados");
                case 2:
                    return PrestadorTela3.newInstance("Todos os Serviços");
                default:
                    break;
            }
            return null;
        }


        public static MyServicesFragment newInstance(int page, String title) {
            MyServicesFragment fragment = new MyServicesFragment();
            Bundle args = new Bundle();
            args.putInt("someInt", page);
            args.putString("someTitle", title);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position % getCount()].toUpperCase();
        }
    }
}
