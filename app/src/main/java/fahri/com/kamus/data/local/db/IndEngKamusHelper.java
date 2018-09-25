package fahri.com.kamus.data.local.db;

import android.database.SQLException;
import android.databinding.ObservableInt;

import java.util.List;

import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import io.reactivex.Observable;

public interface IndEngKamusHelper {

    List<IndonesiaEnglish> preLoadRawIndEng();

    IndEngKamusHelper openIndEng() throws SQLException;

    void closeIndEng();

    Observable<List<IndonesiaEnglish>> getDataBySearchWordIndEng(String word);

    Observable<List<IndonesiaEnglish>> getSearchWordIndEng(String word);

    Observable<List<IndonesiaEnglish>> getAllDataIndEng();

    long insertIndEng(IndonesiaEnglish indonesiaEnglish);

    void beginTransactionIndEng();

    void setTransactionSuccessIndEng();

    void endTransactionIndEng();

    void insertTransactionIndEng(IndonesiaEnglish indonesiaEnglish);

    int updateIndEng(IndonesiaEnglish englishIndonesia);

    int deleteIndEng(int id);
    Observable<List<IndonesiaEnglish>> fetchDatabaseIndEng();

    //Observable<Integer> fetchDatabaseIndEng(List<IndonesiaEnglish> indonesiaEnglish, ObservableInt progress);
}
