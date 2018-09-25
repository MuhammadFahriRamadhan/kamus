package fahri.com.kamus.ui.main;

import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.ui.base.BaseViewModel;
import fahri.com.kamus.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private static final String TAG = "MainViewModel";

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
