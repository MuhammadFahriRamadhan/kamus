package fahri.com.kamus.ui.eng_indkamus;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import fahri.com.kamus.ViewModelProviderFactory;
import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.data.model.db.EnglishIndonesia;
import fahri.com.kamus.utils.rx.SchedulerProvider;

@Module
public class FragmentEngIndoModule {

    @Provides
    FragmentEngIndoViewModel provideFragmentEngIndoViewModel(DataManager dataManager,
                                            SchedulerProvider schedulerProvider) {
        return new FragmentEngIndoViewModel(dataManager, schedulerProvider);
    }

    @Provides
    EngIndoAdapter provideEngIndAdapter() {
        return new EngIndoAdapter(new ArrayList<EnglishIndonesia>());
    }

    @Provides
    ViewModelProvider.Factory provideSearchViewModelProviderFactory(FragmentEngIndoViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    LinearLayoutManager provideSearchLinearLayoutManager(FragmentEngIndo fragment) {

        return new LinearLayoutManager(fragment.getActivity());
    }
}
