package pl.looksoft.lsportfolio;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.looksoft.lsportfolio.base.BaseActivity;
import pl.looksoft.lsportfolio.fragment.AppListFragment;
import pl.looksoft.lsportfolio.fragment.ContactFragment;

/**
 * Created by Jermey on 2015-08-18.
 */
public class MainActivity extends BaseActivity{

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.nav_view)
    NavigationView mNavigationView;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AppListFragment()).commit();
        }
        setupDrawerContent(mNavigationView);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setChecked(true);
            switch (menuItem.getItemId()) {
                case R.id.nav_apps:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new AppListFragment()).commit();
                    break;
                case R.id.nav_contact:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new ContactFragment()).commit();
                    break;
            }
            mDrawerLayout.closeDrawers();
            return true;
        }
    };
}
