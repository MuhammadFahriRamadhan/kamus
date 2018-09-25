package fahri.com.kamus.data.local.preference;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import fahri.com.kamus.di.PreferenceInfo;

public class PreferencesAppHelper implements PreferencesHelper {

    private static final String FIRST_RUN = "FIRST_RUN";

    private final SharedPreferences mPrefs;

    @Inject
    public PreferencesAppHelper(Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setFirstRun(boolean firstRun) {
        mPrefs.edit().putBoolean(FIRST_RUN, firstRun).apply();
    }

    @Override
    public Boolean getFirstRun() {
        return mPrefs.getBoolean(FIRST_RUN, true);
    }
}
