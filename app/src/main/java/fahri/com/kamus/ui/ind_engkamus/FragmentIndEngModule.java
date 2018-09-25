package fahri.com.kamus.ui.ind_engkamus;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import fahri.com.kamus.ViewModelProviderFactory;
import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import fahri.com.kamus.utils.rx.SchedulerProvider;

@Module
public class FragmentIndEngModule {

    @Provides
    FragmentIndEngViewModel provideFragmentIndoEngViewModel(DataManager dataManager,
                                                             SchedulerProvider schedulerProvider) {
        return new FragmentIndEngViewModel(dataManager, schedulerProvider);
    }

    @Provides
    IndEngAdapter provideIndEngAdapter() {
        return new IndEngAdapter(new ArrayList<IndonesiaEnglish>());
    }

    @Provides
    ViewModelProvider.Factory providesearchViewModelProviderFactory(FragmentIndEngViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    LinearLayoutManager providesearchLinearLayoutManager(FragmentIndEng fragment) {

        return new LinearLayoutManager(fragment.getActivity());
    }
}
