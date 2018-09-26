package fahri.com.kamus.ui.splashscreen;

import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.data.model.db.EnglishIndonesia;
import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import fahri.com.kamus.ui.base.BaseViewModel;
import fahri.com.kamus.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SplashScreenViewModel extends BaseViewModel<SplashScreenNavigator> {

    private static final String TAG = "SplashScreenViewModel";

    public ObservableInt progressObs = new ObservableInt(0);
    public ObservableInt showProgress;

    public SplashScreenViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        setup();
    }

    private void setup() {
        showProgress = new ObservableInt();
        showProgress.set(View.INVISIBLE);
        Dbenglish();

    }

    public void Dbenglish() {
        Boolean firstRun = getDataManager().getFirstRun();
        if (firstRun) {
            showProgress.set(View.VISIBLE);
            getDataManager().fetchDatabaseEngInd()
                    .flatMap(englishIndonesiaList -> {
                        for (int i = 0; i < englishIndonesiaList.size(); i++) {
                            if (i % 100 == 0 || i == englishIndonesiaList.size()) {
                                progressObs.set(i);
                                Log.i(TAG, "doProgressDb: " + i);
                            }
                        }
                        return Observable.fromIterable(englishIndonesiaList);
                    })
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Observer<EnglishIndonesia>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onNext(EnglishIndonesia englishIndonesia) {
                            Log.i(TAG, "onNext: " + englishIndonesia.getEngSearch());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: ", e);
                        }

                        @Override
                        public void onComplete() {
                            dbIndo();
                        }
                    });
        }
    }

    public void dbIndo() {
        Boolean firstRun = getDataManager().getFirstRun();
        if (firstRun) {
            showProgress.set(View.VISIBLE);
            getDataManager().fetchDatabaseIndEng()
                    .flatMap(indonesiaEnglishList -> {
                        for (int i = 0; i < indonesiaEnglishList.size(); i++) {
                            if (i % 100 == 0 || i == indonesiaEnglishList.size()) {
                                progressObs.set(i);
                                Log.i(TAG, "doProgressDb: " + i);
                            }
                        }
                        return Observable.fromIterable(indonesiaEnglishList);
                    })
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Observer<IndonesiaEnglish>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onNext(IndonesiaEnglish IndonesiaEnglish) {
                            Log.i(TAG, "onNext: " + IndonesiaEnglish.getIndSearch());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: ", e);
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "finish: ");
                            showProgress.set(View.INVISIBLE);
                            getNavigator().gotoMainActivity();
                        }
                    });
        }
    }
}
