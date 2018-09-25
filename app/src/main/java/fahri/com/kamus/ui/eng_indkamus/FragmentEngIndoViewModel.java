package fahri.com.kamus.ui.eng_indkamus;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import java.util.List;

import fahri.com.kamus.data.DataManager;
import fahri.com.kamus.data.model.db.EnglishIndonesia;
import fahri.com.kamus.ui.base.BaseViewModel;
import fahri.com.kamus.utils.rx.SchedulerProvider;

public class FragmentEngIndoViewModel extends BaseViewModel<FragmentEngIndoNavigator> {

    private static final String TAG = "FragmentEngIndoViewMode";

    private final ObservableArrayList<EnglishIndonesia> englishIndonesiaObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<EnglishIndonesia>> englishIndonesiaListLiveData;
    public ObservableField<String> resultText = new ObservableField<>("");
    public FragmentEngIndoViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.englishIndonesiaListLiveData = new MutableLiveData<>();
    }

    public void onSearchClicked() {
        getNavigator().onSearchClicked();
    }

    public void setSingleSearchEngInd(String word) {
        Log.d(TAG, "setSingleSearchEngInd: "+word);
        getDataManager().openEngInd();
        getCompositeDisposable().add(getDataManager().getDataBySearchWordEngInd(word)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(englishIndonesias -> {

                    String search = englishIndonesias.get(0).getEngSearch();
                    String result = englishIndonesias.get(0).getIndResult();

                    resultText.set(search + " : \n" + result);

                    Log.i(TAG, "setSingleSearchEngInd: " + result);

                }, throwable -> {

                }));
        getDataManager().closeEngInd();
    }

    public void setTypeWord(String word) {

            setSearchEngInd(word);


    }

    private void setSearchEngInd(String word) {
        getDataManager().openEngInd();
        getCompositeDisposable().add(getDataManager().getSearchWordEngInd(word)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(englishIndonesias -> {

                    englishIndonesiaListLiveData.setValue(englishIndonesias);

                }, throwable -> {

                    Log.e(TAG, "setSearchEngInd: ", throwable);

                }));
        getDataManager().closeEngInd();
    }

    public void setResultText(String text) {
        this.resultText.set(text);
    }


    public MutableLiveData<List<EnglishIndonesia>> getEnglishIndonesiaListLiveData() {
        return englishIndonesiaListLiveData;
    }

    public ObservableArrayList<EnglishIndonesia> getEnglishIndonesiaObservableArrayList() {
        return englishIndonesiaObservableArrayList;
    }

    public void addEnglishIndonesialistItemsToList(List<EnglishIndonesia> englishIndonesias) {
        englishIndonesiaObservableArrayList.clear();
        englishIndonesiaObservableArrayList.addAll(englishIndonesias);
    }

}