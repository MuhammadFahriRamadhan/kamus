package fahri.com.kamus.ui.ind_engkamus;

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
import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import fahri.com.kamus.databinding.ActivityFragmentIndengBinding;
import fahri.com.kamus.ui.base.BaseFragment;

public class FragmentIndEng extends BaseFragment<ActivityFragmentIndengBinding, FragmentIndEngViewModel>
        implements FragmentIndEngNavigator,IndEngAdapter.ItemResultClickListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    IndEngAdapter mIndoEngAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    private FragmentIndEngViewModel mIndoEngViewModel;

    ActivityFragmentIndengBinding mFragmentIndoEngBinding;

    private static final String TAG = "FragmentIndEng";

    public static FragmentIndEng newInstance() {
        Bundle args = new Bundle();
        FragmentIndEng fragment = new FragmentIndEng();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndoEngViewModel.setNavigator(this);
        mIndoEngAdapter.setItemResultClickListener(this);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentIndoEngBinding = getViewDataBinding();
        setUp();
        setupRx();
        subscribeToLiveData();
    }

    private void setupRx() {
        mIndoEngViewModel.getCompositeDisposable().add(
                RxTextView.textChanges(mFragmentIndoEngBinding.inputWordEdt)
                        .debounce(1, TimeUnit.SECONDS)
                        .filter(charSequence -> charSequence.toString().trim().length() >= 1)
                        .subscribeOn(mIndoEngViewModel.getSchedulerProvider().io())
                        .observeOn(mIndoEngViewModel.getSchedulerProvider().ui())
                        .subscribe(charSequence -> {
                            mIndoEngViewModel.setTypeWord(charSequence.toString());

                        }, throwable -> {



                        }));
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mFragmentIndoEngBinding.IndEngRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentIndoEngBinding.IndEngRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mFragmentIndoEngBinding.IndEngRecyclerView.setAdapter(mIndoEngAdapter);


    }

    private void subscribeToLiveData() {
        mIndoEngViewModel.getIndonesiaEnglishListLiveData().observe(this, indonesiaEnglishes ->
                mIndoEngViewModel.addIndonesiaEnglishlistItemsToList(indonesiaEnglishes));

    }

    @Override
    public void onItemResultIndEngClicked(List<IndonesiaEnglish> kata, int post) {
        String searchText = kata.get(post).getIndSearch();
        Log.i(TAG, "onItemResultIndEngClicked: "+searchText);
        String resultText = kata.get(post).getEngResult();
        mIndoEngViewModel.setResultText(searchText.toUpperCase() + " : \n" + resultText);
        mFragmentIndoEngBinding.inputWordEdt.setText("");
        mIndoEngAdapter.clearItems();
        hideKeyboard();
    }

    @Override
    public void onSearchClicked() {
        String word = mFragmentIndoEngBinding.inputWordEdt.getText().toString().trim();
        if (!word.isEmpty()) {
            mIndoEngViewModel.setSingleSearchIndEng(word.toUpperCase());
            mFragmentIndoEngBinding.inputWordEdt.setText("");
            mIndoEngAdapter.clearItems();
            hideKeyboard();
        }
    }


    @Override
    public FragmentIndEngViewModel getViewModel() {
        mIndoEngViewModel = ViewModelProviders.of(this, mViewModelFactory).get(FragmentIndEngViewModel.class);
        return mIndoEngViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment_indeng;
    }




}
