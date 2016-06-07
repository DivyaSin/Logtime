package example.divyasingh.logtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (toolbar != null) {
            final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            toggle.syncState();
            mDrawer.setDrawerListener(toggle);
            getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // show back button
                        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onBackPressed();
                            }
                        });
                    } else {
                        //show hamburger
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        toggle.syncState();
                        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDrawer.openDrawer(GravityCompat.START);
                            }
                        });
                    }
                }
            });
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signOut:
                logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.clients:
                fragment = new FetchClientsFragment();
                break;
            case R.id.projects:
                fragment = new FetchProjectsFragment();
                break;
            case R.id.timesheets:
                fragment = new DisplayTimesheetsFragment();
                break;
            default:
                fragment = new HomeFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

        DrawerLayout mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert mDrawer != null;
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logoutUser() {
        LocalStorage.getInstance().setLoggedIn(false);
        LocalStorage.getInstance().setAuthToken(null);
        LocalStorage.getInstance().setEmail(null);
        LocalStorage.getInstance().setName(null);
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}