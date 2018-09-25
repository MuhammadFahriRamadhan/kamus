package fahri.com.kamus.ui.eng_indkamus;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentEngIndoProvider {

    @ContributesAndroidInjector(modules = FragmentEngIndoModule.class)
    abstract FragmentEngIndo provideEngIndoFragmentFactory();
}
