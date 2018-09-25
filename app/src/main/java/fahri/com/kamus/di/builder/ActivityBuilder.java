package fahri.com.kamus.di.builder;



import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import fahri.com.kamus.ui.eng_indkamus.FragmentEngIndoProvider;
import fahri.com.kamus.ui.ind_engkamus.FragmentIndEngProvider;
import fahri.com.kamus.ui.main.MainActivity;
import fahri.com.kamus.ui.main.MainModule;
import fahri.com.kamus.ui.splashscreen.SplashScreenActivity;
import fahri.com.kamus.ui.splashscreen.SplashScreenModule;


@Module
public abstract class ActivityBuilder {



    @ContributesAndroidInjector(modules = {SplashScreenModule.class})
    abstract SplashScreenActivity bindSplashScreenActivity();

    @ContributesAndroidInjector(modules = {
            MainModule.class,
            FragmentEngIndoProvider.class,
            FragmentIndEngProvider.class
    })
    abstract MainActivity bindMainActivity();


}
