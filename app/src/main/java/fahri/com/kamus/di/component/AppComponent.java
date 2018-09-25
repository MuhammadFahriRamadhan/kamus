package fahri.com.kamus.di.component;

import android.app.Application;

import javax.inject.Singleton;


import fahri.com.kamus.KamusApp;
import fahri.com.kamus.di.builder.ActivityBuilder;
import fahri.com.kamus.di.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(KamusApp app);
}
