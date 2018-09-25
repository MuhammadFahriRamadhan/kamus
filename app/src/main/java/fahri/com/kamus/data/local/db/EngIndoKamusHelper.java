package fahri.com.kamus.data.local.db;

import android.database.SQLException;

import java.util.List;

import fahri.com.kamus.data.model.db.EnglishIndonesia;
import io.reactivex.Observable;

public interface EngIndoKamusHelper {
    List<EnglishIndonesia> preLoadRawEngInd();

    EngIndoKamusHelper openEngInd() throws SQLException;

    void closeEngInd();

    Observable<List<EnglishIndonesia>> getDataBySearchWordEngInd(String word);

    Observable<List<EnglishIndonesia>> getSearchWordEngInd(String word);

    Observable<List<EnglishIndonesia>> getAllDataEngInd();

    long insertEngInd(EnglishIndonesia englishIndonesia);

    void beginTransactionEngInd();

    void setTransactionSuccessEngInd();

    void endTransactionEngInd();

    void insertTransactionEngInd(EnglishIndonesia englishIndonesia);

    int updateEngInd(EnglishIndonesia englishIndonesia);

    int deleteEngInd(int id);

    Observable<List<EnglishIndonesia>> fetchDatabaseEngInd();

}
