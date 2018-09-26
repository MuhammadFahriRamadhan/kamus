package fahri.com.kamus.ui.eng_indkamus;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import fahri.com.kamus.R;
import fahri.com.kamus.BR;
import fahri.com.kamus.data.model.db.EnglishIndonesia;
import fahri.com.kamus.databinding.ActivityFragmentEngindoBinding;

import fahri.com.kamus.ui.base.BaseFragment;

public class FragmentEngIndo extends BaseFragment<ActivityFragmentEngindoBinding, FragmentEngIndoViewModel>
        implements FragmentEngIndoNavigator,EngIndoAdapter.ItemResultClickListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    EngIndoAdapter mEngIndoAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    private FragmentEngIndoViewModel mEngIndoViewModel;

    ActivityFragmentEngindoBinding mFragmentEngIndoBinding;

    private static final String TAG = "FragmentEngIndo";

    public static FragmentEngIndo newInstance() {
        Bundle args = new Bundle();
        FragmentEngIndo fragment = new FragmentEngIndo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEngIndoViewModel.setNavigator(this);
        mEngIndoAdapter.setItemResultClickListener(this);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentEngIndoBinding = getViewDataBinding();
        setUp();
        setupRx();
        subscribeToLiveData();
    }

    private void setupRx() {
        mEngIndoViewModel.getCompositeDisposable().add(
                RxTextView.textChanges(mFragmentEngIndoBinding.inputWordEdt)
                        .debounce(1, TimeUnit.SECONDS)
                        .filter(charSequence -> charSequence.toString().trim().length() >= 1)
                        .subscribeOn(mEngIndoViewModel.getSchedulerProvider().io())
                        .observeOn(mEngIndoViewModel.getSchedulerProvider().ui())
                        .subscribe(charSequence -> {
                            mEngIndoViewModel.setTypeWord(charSequence.toString());

                        }, throwable -> {



                        }));
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mFragmentEngIndoBinding.engIndRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentEngIndoBinding.engIndRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mFragmentEngIndoBinding.engIndRecyclerView.setAdapter(mEngIndoAdapter);


    }

    private void subscribeToLiveData() {
        mEngIndoViewModel.getEnglishIndonesiaListLiveData().observe(this, englishIndonesias ->
                mEngIndoViewModel.addEnglishIndonesialistItemsToList(englishIndonesias));

    }

    @Override
    public void onItemResultEngIndClicked(List<EnglishIndonesia> englishIndonesias, int post) {
        String searchText = englishIndonesias.get(post).getEngSearch();
        String resultText = englishIndonesias.get(post).getIndResult();
        mEngIndoViewModel.setResultText(searchText.toUpperCase() + " : \n" + resultText);
        mFragmentEngIndoBinding.inputWordEdt.setText("");
        mEngIndoAdapter.clearItems();
        hideKeyboard();
    }

    @Override
    public void onSearchClicked() {
        String word = mFragmentEngIndoBinding.inputWordEdt.getText().toString().trim();
        if (!word.isEmpty()) {
            mEngIndoViewModel.setSingleSearchEngInd(word.toUpperCase());
            mFragmentEngIndoBinding.inputWordEdt.setText("");
            mEngIndoAdapter.clearItems();
            hideKeyboard();
        }
    }





    @Override
    public FragmentEngIndoViewModel getViewModel() {
        mEngIndoViewModel = ViewModelProviders.of(this, mViewModelFactory).get(FragmentEngIndoViewModel.class);
        return mEngIndoViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment_engindo;
    }




}
