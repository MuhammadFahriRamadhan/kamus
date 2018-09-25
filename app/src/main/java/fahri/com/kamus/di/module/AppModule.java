package fahri.com.kamus.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;


import fahri.com.kamus.R;
import fahri.com.kamus.data.AppDataManager;
import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.data.local.db.DatabaseHelper;
import fahri.com.kamus.data.local.db.EngIndoHelperKamus;
import fahri.com.kamus.data.local.db.EngIndoKamusHelper;
import fahri.com.kamus.data.local.db.IndEngHelperKamus;
import fahri.com.kamus.data.local.db.IndEngKamusHelper;
import fahri.com.kamus.data.local.preference.PreferencesAppHelper;
import fahri.com.kamus.data.local.preference.PreferencesHelper;
import fahri.com.kamus.di.DatabaseInfo;
import fahri.com.kamus.di.PreferenceInfo;
import fahri.com.kamus.utils.AppConstants;
import fahri.com.kamus.utils.rx.AppSchedulerProvider;
import fahri.com.kamus.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesAppHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    EngIndoKamusHelper provideKamusHelper(EngIndoHelperKamus appKamusHelper) {
        return appKamusHelper;
    }



    @Provides
    @Singleton
    IndEngKamusHelper provideKamusIndEngHelper(IndEngHelperKamus appKamusHelper) {
        return appKamusHelper;
    }

    @Provides
    @Singleton
    DatabaseHelper privdeDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }

//    @Provides
//    String provideApiKey() {
//        return BuildConfig.API_KEY;
//    }

//    @Provides
//    @Singleton
//    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
//        return appApiHelper;
//    }


    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
