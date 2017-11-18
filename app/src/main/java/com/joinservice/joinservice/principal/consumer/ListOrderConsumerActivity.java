package com.joinservice.joinservice.principal.consumer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import com.joinservice.joinservice.TelaInicial.PrimeiraTela;
import com.joinservice.joinservice.TelaInicial.SegundaTela;
import com.joinservice.joinservice.TelaInicial.TerceiraTela;
import com.joinservice.joinservice.TelaLogin;
import com.joinservice.joinservice.principal.consumer.registrer.RegisterOrderCategoryActivity;

import Fachada.Fachada;
import basica.Usuario;

public class ListOrderConsumerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nomeUsuario;
    ImageView fotoUsuario;
    ListView listaServicos;
    Fachada fachada;
    FragmentPagerAdapter adapterViewPager;
    Usuario usuario;
    private static int num_itens = 3;

    //Titulos das Paginas
    private static final String[] TITLES = new String[]{
            "Meus Serviços",
            "Serviços Salvos",
            "Todos os Serviços"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order_consumer);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new ListOrderConsumerActivity.MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("JoinService");

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
        if (intent.getSerializableExtra("usuario") != null) {
            usuario = (Usuario) intent.getSerializableExtra("usuario");
        } else {
            usuario = fachada.usuarioLogado();
        }

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
                    return PrimeiraTela.newInstance("Meu Serviços");
                case 1:
                    return SegundaTela.newInstance("Serviços Salvos");
                case 2:
                    return TerceiraTela.newInstance("Todos os Serviços");
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

    public void cadastrarServico() {
        Intent intentRegisterOrder = new Intent(this, RegisterOrderCategoryActivity.class);
        startActivity(intentRegisterOrder);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit_profile) {
            Intent intent = getIntent();
            Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
            Intent intentEditProfile = new Intent(this, EditProfile.class);
            intentEditProfile.putExtra("usuario", usuario);
            startActivity(intentEditProfile);

            // Handle the camera action
        } else if (id == R.id.nav_exit) {
            fachada.usuarioExcluirLogado();
            Intent intentTelaLogin = new Intent(this, TelaLogin.class);
            startActivity(intentTelaLogin);
        } /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void proximo() {
        Intent itProximo = new Intent(this, RegisterOrderCategoryActivity.class);
        startActivity(itProximo);
    }
}
