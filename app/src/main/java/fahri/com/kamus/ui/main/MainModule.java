package fahri.com.kamus.ui.main;

import dagger.Module;
import dagger.Provides;
import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.utils.rx.SchedulerProvider;

@Module
public class MainModule {

    @Provides
    MainViewModel provideHomeViewModel(DataManager dataManager,
                                       SchedulerProvider schedulerProvider) {
        return new MainViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MainPagerAdapter provideMainPagerAdapter(MainActivity activity) {
        return new MainPagerAdapter(activity.getSupportFragmentManager());
    }
}
