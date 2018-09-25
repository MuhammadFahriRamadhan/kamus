package fahri.com.kamus.data.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.databinding.ObservableInt;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fahri.com.kamus.R;
import fahri.com.kamus.data.local.preference.PreferencesHelper;
import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import io.reactivex.Observable;

import static android.provider.BaseColumns._ID;
import static fahri.com.kamus.data.local.db.DatabaseContract.KamusColumnsIndEng.RESULT_WORD_IND_ENG;
import static fahri.com.kamus.data.local.db.DatabaseContract.KamusColumnsIndEng.SEARCH_WORD_IND_ENG;
import static fahri.com.kamus.data.local.db.DatabaseContract.TABLE_NAME_IND_ENG;

public class IndEngHelperKamus implements IndEngKamusHelper {

    private static final String TAG = "IndEngHelperKamus";

    private final Context mContext;
    private final DatabaseHelper mDataBaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    private SQLiteDatabase mSQLiteDatabase;

    @Inject
    public IndEngHelperKamus(Context context, DatabaseHelper dataBaseHelper, PreferencesHelper preferencesHelper) {
        this.mContext = context;
        this.mDataBaseHelper = dataBaseHelper;
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public List<IndonesiaEnglish> preLoadRawIndEng() {
        final ArrayList<IndonesiaEnglish> indonesiaEnglishes = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = mContext.getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                IndonesiaEnglish indonesiaEnglish;

                indonesiaEnglish = new IndonesiaEnglish(splitstr[0], splitstr[1]);
                indonesiaEnglishes.add(indonesiaEnglish);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return indonesiaEnglishes;
    }

    @Override
    public IndEngHelperKamus openIndEng() throws SQLException {
        mSQLiteDatabase = mDataBaseHelper.getWritableDatabase();
        return this;
    }

    @Override
    public void closeIndEng() {
        mDataBaseHelper.close();
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getDataBySearchWordIndEng(String word) {
        Log.i(TAG, "getDataBySearchWordIndEng: "+word);
        String result = "";
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME_IND_ENG,null, SEARCH_WORD_IND_ENG +" LIKE ?",
                new String[]{word},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        final ArrayList<IndonesiaEnglish> arrayList = new ArrayList<>();
        IndonesiaEnglish indonesiaEnglish;
        if (cursor.getCount() > 0) {
            do {
                indonesiaEnglish = new IndonesiaEnglish();
                indonesiaEnglish.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indonesiaEnglish.setIndSearch(cursor.getString(cursor.getColumnIndexOrThrow(SEARCH_WORD_IND_ENG)));
                indonesiaEnglish.setEngResult(cursor.getString(cursor.getColumnIndexOrThrow(RESULT_WORD_IND_ENG)));

                arrayList.add(indonesiaEnglish);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return Observable.fromCallable(() -> arrayList);
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getSearchWordIndEng(String word) {
        Log.i(TAG, "getSearchWordIndEng: "+word);
        String result = "";
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME_IND_ENG,null, SEARCH_WORD_IND_ENG +" LIKE ?",
                new String[]{word + "%"},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        final ArrayList<IndonesiaEnglish> arrayList = new ArrayList<>();
        IndonesiaEnglish indonesiaEnglish;
        if (cursor.getCount() > 0) {
            do {
                indonesiaEnglish = new IndonesiaEnglish();
                indonesiaEnglish.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indonesiaEnglish.setIndSearch(cursor.getString(cursor.getColumnIndexOrThrow(SEARCH_WORD_IND_ENG)));
                indonesiaEnglish.setEngResult(cursor.getString(cursor.getColumnIndexOrThrow(RESULT_WORD_IND_ENG)));

                arrayList.add(indonesiaEnglish);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return Observable.fromCallable(() -> arrayList);
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getAllDataIndEng() {
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME_IND_ENG,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        final ArrayList<IndonesiaEnglish> arrayList = new ArrayList<>();
        IndonesiaEnglish indonesiaEnglish;
        if (cursor.getCount() > 0) {
            do {
                indonesiaEnglish = new IndonesiaEnglish();
                indonesiaEnglish.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indonesiaEnglish.setIndSearch(cursor.getString(cursor.getColumnIndexOrThrow(SEARCH_WORD_IND_ENG)));
                indonesiaEnglish.setEngResult(cursor.getString(cursor.getColumnIndexOrThrow(RESULT_WORD_IND_ENG)));


                arrayList.add(indonesiaEnglish);
                cursor.moveToNext();


            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return Observable.fromCallable(() -> arrayList);
    }

    @Override
    public long insertIndEng(IndonesiaEnglish indonesiaEnglish) {
        Log.i(TAG, "insertIndEng: "+indonesiaEnglish);
        ContentValues initialValues =  new ContentValues();
        initialValues.put(SEARCH_WORD_IND_ENG, indonesiaEnglish.getIndSearch());
        initialValues.put(RESULT_WORD_IND_ENG, indonesiaEnglish.getEngResult());
        return mSQLiteDatabase.insert(TABLE_NAME_IND_ENG, null, initialValues);
    }

    @Override
    public void beginTransactionIndEng() {
        mSQLiteDatabase.beginTransaction();
    }

    @Override
    public void setTransactionSuccessIndEng() {
        mSQLiteDatabase.setTransactionSuccessful();
    }

    @Override
    public void endTransactionIndEng() {
        mSQLiteDatabase.endTransaction();
    }

    @Override
    public void insertTransactionIndEng(IndonesiaEnglish indonesiaEnglish) {
        Log.i(TAG, "insertTransactionIndEng: "+indonesiaEnglish);
        String sql = "INSERT INTO "+ TABLE_NAME_IND_ENG +" ("+ SEARCH_WORD_IND_ENG +", "+ RESULT_WORD_IND_ENG
                +") VALUES (?, ?)";
        SQLiteStatement stmt = mSQLiteDatabase.compileStatement(sql);
        stmt.bindString(1, indonesiaEnglish.getIndSearch());
        stmt.bindString(2, indonesiaEnglish.getEngResult());
        stmt.execute();
        stmt.clearBindings();
    }

    @Override
    public int updateIndEng(IndonesiaEnglish englishIndonesia) {
        ContentValues args = new ContentValues();
        args.put(SEARCH_WORD_IND_ENG, englishIndonesia.getIndSearch());
        args.put(RESULT_WORD_IND_ENG, englishIndonesia.getEngResult());
        return mSQLiteDatabase.update(TABLE_NAME_IND_ENG, args, _ID + "= '" + englishIndonesia.getId() + "'", null);
    }

    @Override
    public int deleteIndEng(int id) {
        return mSQLiteDatabase.delete(TABLE_NAME_IND_ENG, _ID + " = '"+id+"'", null);
    }

    @Override
    public Observable<List<IndonesiaEnglish>> fetchDatabaseIndEng() {
        return Observable.create(emitter -> {
            List<IndonesiaEnglish> Indonesia_english = preLoadRawIndEng();
            Log.i(TAG, "fetchDatabaseIndEng: "+Indonesia_english);
            openIndEng();
            beginTransactionIndEng();
            try {
                for (IndonesiaEnglish model : Indonesia_english) {
                    insertTransactionIndEng(model);
                }

                Log.i(TAG, "sukses: ");
                setTransactionSuccessIndEng();
            } catch (Exception e) {

                Log.e(TAG, "doInBackground: Exception");
            }

            endTransactionIndEng();
            closeIndEng();


            emitter.onNext(Indonesia_english);
            emitter.onComplete();
        });
    }
}

