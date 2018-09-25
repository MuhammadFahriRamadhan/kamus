package fahri.com.kamus.ui.ind_engkamus;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import java.util.List;

import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import fahri.com.kamus.ui.base.BaseViewModel;
import fahri.com.kamus.utils.rx.SchedulerProvider;

public class FragmentIndEngViewModel extends BaseViewModel<FragmentIndEngNavigator> {

    private static final String TAG = "FragmentIndEngViewModel";


    private final ObservableArrayList<IndonesiaEnglish> indonesiaEnglishObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<IndonesiaEnglish>> indonesiaEnglishListLiveData;

    public ObservableField<String> resultText = new ObservableField<>("");



    private Boolean typeWord;

    public FragmentIndEngViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);


        indonesiaEnglishListLiveData = new MutableLiveData<>();


        typeWord = false;
    }



    public void setTypeWord(String word) {

            setSearchIndEng(word);

    }

    public void onSearchClicked() {
        getNavigator().onSearchClicked();
        Log.d(TAG, "onSearchClicked: ");
    }


    public void setSingleSearchIndEng(String word) {
        Log.i(TAG, "setSingleSearchIndEng:atas "+word);
        getDataManager().openIndEng();
        getCompositeDisposable().add(getDataManager().getDataBySearchWordIndEng(word)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(indonesiaEnglishes -> {

                    String search = indonesiaEnglishes.get(0).getIndSearch();
                    Log.i(TAG, "setSingleSearchIndEng:search"+search);
                    String result = indonesiaEnglishes.get(0).getEngResult();

                    resultText.set(search + " : \n" + result);

                    Log.i(TAG, "setSingleSearchIndEng: " + result);

                }, throwable -> {

                }));
        getDataManager().closeIndEng();
    }



    private void setSearchIndEng(String word) {
        Log.d(TAG, "setSearchIndEng: "+word);
        getDataManager().openIndEng();
        getCompositeDisposable().add(getDataManager().getSearchWordIndEng(word)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(indonesiaEnglishes -> {

                    indonesiaEnglishListLiveData.setValue(indonesiaEnglishes);

                }, throwable -> {

                    Log.e(TAG, "setSearchEngInd: ", throwable);

                }));
        getDataManager().closeIndEng();
    }

    public void setResultText(String text) {
        this.resultText.set(text);
    }


    public MutableLiveData<List<IndonesiaEnglish>> getIndonesiaEnglishListLiveData() {
        return indonesiaEnglishListLiveData;
    }

    public ObservableArrayList<IndonesiaEnglish> getIndonesiaEnglishObservableArrayList() {
        return indonesiaEnglishObservableArrayList;
    }

    public void addIndonesiaEnglishlistItemsToList(List<IndonesiaEnglish> indonesiaEnglish) {
        indonesiaEnglishObservableArrayList.clear();
        indonesiaEnglishObservableArrayList.addAll(indonesiaEnglish);
    }
}

