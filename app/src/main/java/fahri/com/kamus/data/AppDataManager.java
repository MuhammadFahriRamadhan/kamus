/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package fahri.com.kamus.data;

import android.content.Context;
import android.database.SQLException;
import android.databinding.ObservableInt;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fahri.com.kamus.data.local.db.EngIndoKamusHelper;
import fahri.com.kamus.data.local.db.IndEngKamusHelper;
import fahri.com.kamus.data.local.preference.PreferencesHelper;
import fahri.com.kamus.data.model.db.EnglishIndonesia;
import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import io.reactivex.Observable;


@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final EngIndoKamusHelper mKamusEngIndHelper;
    private final IndEngKamusHelper mKamusIndEngHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context,
                          EngIndoKamusHelper kamusEngIndHelper,
                          IndEngKamusHelper kamusIndEngHelper,
                          PreferencesHelper preferencesHelper) {
        this.mContext = context;
        this.mKamusEngIndHelper = kamusEngIndHelper;
        this.mKamusIndEngHelper = kamusIndEngHelper;
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public List<EnglishIndonesia> preLoadRawEngInd() {
        return mKamusEngIndHelper.preLoadRawEngInd();
    }

    @Override
    public EngIndoKamusHelper openEngInd() throws SQLException {
        return mKamusEngIndHelper.openEngInd();
    }

    @Override
    public void closeEngInd() {
        mKamusEngIndHelper.closeEngInd();
    }

    @Override
    public Observable<List<EnglishIndonesia>> getDataBySearchWordEngInd(String word) {
        return mKamusEngIndHelper.getDataBySearchWordEngInd(word);
    }

    @Override
    public Observable<List<EnglishIndonesia>> getSearchWordEngInd(String word) {
        return mKamusEngIndHelper.getSearchWordEngInd(word);
    }

    @Override
    public Observable<List<EnglishIndonesia>> getAllDataEngInd() {
        return mKamusEngIndHelper.getAllDataEngInd();
    }

    @Override
    public long insertEngInd(EnglishIndonesia englishIndonesia) {
        return mKamusEngIndHelper.insertEngInd(englishIndonesia);
    }

    @Override
    public void beginTransactionEngInd() {
        mKamusEngIndHelper.beginTransactionEngInd();
    }

    @Override
    public void setTransactionSuccessEngInd() {
        mKamusEngIndHelper.setTransactionSuccessEngInd();
    }

    @Override
    public void endTransactionEngInd() {
        mKamusEngIndHelper.endTransactionEngInd();
    }

    @Override
    public void insertTransactionEngInd(EnglishIndonesia englishIndonesia) {
        mKamusEngIndHelper.insertTransactionEngInd(englishIndonesia);
    }

    @Override
    public int updateEngInd(EnglishIndonesia englishIndonesia) {
        return mKamusEngIndHelper.updateEngInd(englishIndonesia);
    }

    @Override
    public int deleteEngInd(int id) {
        return mKamusEngIndHelper.deleteEngInd(id);
    }

    @Override
    public Observable<List<EnglishIndonesia>> fetchDatabaseEngInd() {
        return mKamusEngIndHelper.fetchDatabaseEngInd();
    }

    @Override
    public void setFirstRun(boolean firstRun) {
        mPreferencesHelper.setFirstRun(firstRun);
    }

    @Override
    public Boolean getFirstRun() {
        return mPreferencesHelper.getFirstRun();
    }

    @Override
    public List<IndonesiaEnglish> preLoadRawIndEng() {
        return mKamusIndEngHelper.preLoadRawIndEng();
    }

    @Override
    public IndEngKamusHelper openIndEng() throws SQLException {
        return mKamusIndEngHelper.openIndEng();
    }

    @Override
    public void closeIndEng() {
       mKamusIndEngHelper.closeIndEng();
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getDataBySearchWordIndEng(String word) {
        return mKamusIndEngHelper.getDataBySearchWordIndEng(word);
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getSearchWordIndEng(String word) {
       return mKamusIndEngHelper.getSearchWordIndEng(word);
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getAllDataIndEng() {
        return mKamusIndEngHelper.getAllDataIndEng();
    }

    @Override
    public long insertIndEng(IndonesiaEnglish indonesiaEnglish) {
        return mKamusIndEngHelper.insertIndEng(indonesiaEnglish);
    }

    @Override
    public void beginTransactionIndEng() {
        mKamusIndEngHelper.beginTransactionIndEng();
    }

    @Override
    public void setTransactionSuccessIndEng() {
        mKamusIndEngHelper.setTransactionSuccessIndEng();
    }

    @Override
    public void endTransactionIndEng() {
        mKamusIndEngHelper.endTransactionIndEng();
    }

    @Override
    public void insertTransactionIndEng(IndonesiaEnglish indonesiaEnglish) {
        mKamusIndEngHelper.insertTransactionIndEng(indonesiaEnglish);
    }

    @Override
    public int updateIndEng(IndonesiaEnglish englishIndonesia) {
        return mKamusIndEngHelper.updateIndEng(englishIndonesia);
    }

    @Override
    public int deleteIndEng(int id) {
        return mKamusIndEngHelper.deleteIndEng(id);
    }

    @Override
    public Observable<List<IndonesiaEnglish>> fetchDatabaseIndEng() {
        return mKamusIndEngHelper.fetchDatabaseIndEng();
    }




}
