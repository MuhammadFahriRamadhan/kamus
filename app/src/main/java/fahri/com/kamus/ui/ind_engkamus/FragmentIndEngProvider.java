package fahri.com.kamus.ui.ind_engkamus;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import fahri.com.kamus.ui.eng_indkamus.FragmentEngIndoModule;
@Module
public abstract class FragmentIndEngProvider {

    @ContributesAndroidInjector(modules = FragmentIndEngModule.class)
    abstract FragmentIndEng provideIndoEngFragmentFactory();
}
