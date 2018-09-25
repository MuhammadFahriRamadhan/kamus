package fahri.com.kamus.ui.splashscreen;

import android.os.Bundle;

import javax.inject.Inject;

import fahri.com.kamus.R;
import fahri.com.kamus.BR;
import fahri.com.kamus.databinding.ActivityMainBinding;
import fahri.com.kamus.databinding.ActivitySplashscreenBinding;
import fahri.com.kamus.ui.base.BaseActivity;
import fahri.com.kamus.ui.main.MainActivity;

public class SplashScreenActivity  extends BaseActivity<ActivitySplashscreenBinding, SplashScreenViewModel>
        implements SplashScreenNavigator {

    @Inject
    SplashScreenViewModel mSplachScreenViewModel;

    ActivitySplashscreenBinding mActivitySplashScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashScreenBinding = getViewDataBinding();
        mSplachScreenViewModel.setNavigator(this);
    }

    @Override
    public SplashScreenViewModel getViewModel() {
        return mSplachScreenViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splashscreen;
    }

    @Override
    public void gotoMainActivity() {
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }
}