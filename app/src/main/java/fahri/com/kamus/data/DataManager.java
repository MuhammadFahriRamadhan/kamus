package fahri.com.kamus.data;


import fahri.com.kamus.data.local.db.EngIndoKamusHelper;
import fahri.com.kamus.data.local.db.IndEngKamusHelper;
import fahri.com.kamus.data.local.preference.PreferencesHelper;

public interface DataManager extends PreferencesHelper, EngIndoKamusHelper,IndEngKamusHelper{
}
