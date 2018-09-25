package fahri.com.kamus.ui.splashscreen;

import dagger.Module;
import dagger.Provides;
import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.utils.rx.SchedulerProvider;

@Module
public class SplashScreenModule {

    @Provides
    SplashScreenViewModel provideSplashScreenViewModel(DataManager dataManager,
                                                       SchedulerProvider schedulerProvider) {
        return new SplashScreenViewModel(dataManager, schedulerProvider);
    }
}
