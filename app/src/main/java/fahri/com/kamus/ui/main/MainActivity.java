package fahri.com.kamus.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import fahri.com.kamus.R;
import fahri.com.kamus.BR;
import fahri.com.kamus.databinding.ActivityMainBinding;
import fahri.com.kamus.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements HasSupportFragmentInjector, MainNavigator {

    private static final String TAG = "MainActivity";

    @Inject
    MainPagerAdapter mPagerAdapter;

    @Inject
    MainViewModel mMainViewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    ActivityMainBinding mActivityMainBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);
        setUp();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onBackPressed() {}


        private void setUp () {
            setSupportActionBar(mActivityMainBinding.toolbar);

            mPagerAdapter.setCount(2);

            mActivityMainBinding.baseViewPager.setAdapter(mPagerAdapter);

            mActivityMainBinding.tabLayout.addTab(mActivityMainBinding.tabLayout.newTab().setText(R.string.indonesia_inggris));
            mActivityMainBinding.tabLayout.addTab(mActivityMainBinding.tabLayout.newTab().setText(getString(R.string.inggris_indonesia)));
            mActivityMainBinding.baseViewPager.setOffscreenPageLimit(mActivityMainBinding.tabLayout.getTabCount());
            mActivityMainBinding.baseViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mActivityMainBinding.tabLayout));

            mActivityMainBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mActivityMainBinding.baseViewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }


        @Override
        public MainViewModel getViewModel () {
            return mMainViewModel;
        }

        @Override
        public int getBindingVariable () {
            return BR.viewModel;
        }

        @Override
        public int getLayoutId () {
            return R.layout.activity_main;
        }


        @Override
        public AndroidInjector<Fragment> supportFragmentInjector () {
            return fragmentDispatchingAndroidInjector;
        }



}