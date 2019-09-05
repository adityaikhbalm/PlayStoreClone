package budiluhur.ac.id.tugas2;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private int nomor, nomor2 = 0, colorFrom, colorTo, colorStatusFrom, colorStatusTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Muhammad Wahyudi");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.container);

        tabLayout.setupWithViewPager(mViewPager);
        setupViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0:
                        navigationView.setCheckedItem(R.id.nav_home);
                        gantiNomor(0);
                        break;
                    case 1:
                        navigationView.setCheckedItem(R.id.nav_games);
                        gantiNomor(1);
                        break;
                    case 2:
                        navigationView.setCheckedItem(R.id.nav_movie);
                        gantiNomor(2);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void showAppBar() {
        appBarLayout.setExpanded(true);
    }

    public void hideAppBar() {
        appBarLayout.setExpanded(false);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        int select = 0;

        if (id == R.id.nav_home) {
            select = 0;
        } else if (id == R.id.nav_games) {
            select = 1;
        } else if (id == R.id.nav_movie) {
            select = 2;
        }

        gantiNomor(select);
        tabLayout.getTabAt(select).select();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void gantiNomor(int ganti) {
        if(ganti == 0) {
            nomor = 0;
            changeColor(nomor, nomor2);
            nomor2 = 0;
        }
        else if(ganti == 1) {
            nomor = 1;
            changeColor(nomor, nomor2);
            nomor2 = 1;
        }
        else if(ganti == 2) {
            nomor = 2;
            changeColor(nomor, nomor2);
            nomor2 = 2;
        }

    }

    public void changeColor(int nomor, int nomor2) {

        if(nomor2 == 0) {
            colorStatusFrom = ContextCompat.getColor(this, R.color.status);
            colorFrom = ContextCompat.getColor(this, R.color.top);
        }
        else if(nomor2 == 1) {
            colorStatusFrom = ContextCompat.getColor(this, R.color.status2);
            colorFrom = ContextCompat.getColor(this, R.color.top2);
        }
        else if(nomor2 == 2) {
            colorStatusFrom = ContextCompat.getColor(this, R.color.status3);
            colorFrom = ContextCompat.getColor(this, R.color.top3);
        }

        if(nomor == 0) {
            colorStatusTo = ContextCompat.getColor(this, R.color.status);
            colorTo = ContextCompat.getColor(this, R.color.top);
        }
        else if(nomor == 1) {
            colorStatusTo = ContextCompat.getColor(this, R.color.status2);
            colorTo = ContextCompat.getColor(this, R.color.top2);
        }
        else if(nomor == 2) {
            colorStatusTo = ContextCompat.getColor(this, R.color.status3);
            colorTo = ContextCompat.getColor(this, R.color.top3);
        }

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        ValueAnimator colorStatusAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorStatusFrom, colorStatusTo);

        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                appBarLayout.setBackgroundColor((Integer) animator.getAnimatedValue());
            }
        });

        colorStatusAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor((Integer) animator.getAnimatedValue());
                }
            }
        });

        colorAnimation.setDuration(1000);
        colorAnimation.setStartDelay(0);
        colorAnimation.start();
        colorStatusAnimation.setDuration(1000);
        colorStatusAnimation.setStartDelay(0);
        colorStatusAnimation.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.voice) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "HOME");
        adapter.addFrag(new GamesFragment(), "GAMES");
        adapter.addFrag(new MovieFragment(), "MOVIE");
        viewPager.setAdapter(adapter);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
