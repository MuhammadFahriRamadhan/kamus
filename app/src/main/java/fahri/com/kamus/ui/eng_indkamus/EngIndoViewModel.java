package fahri.com.kamus.ui.eng_indkamus;

import android.databinding.ObservableField;

import fahri.com.kamus.data.model.db.EnglishIndonesia;

public class EngIndoViewModel {

    private static final String TAG = "ItemEngIndViewModel";

    private EnglishIndonesia mModel;

    public ObservableField<String> searchText = new ObservableField<>("");

    public ItemResultViewModelListener mListener;

    private int mPost;

    public EngIndoViewModel(int post, EnglishIndonesia model, ItemResultViewModelListener listener) {
        this.mPost = post;
        this.mModel = model;
        this.mListener = listener;

        searchText.set(mModel.getEngSearch());
    }

    public void onItemClicked() {
        mListener.onItemClick(mPost);
    }

    public interface ItemResultViewModelListener {
        void onItemClick(int post);
    }
}
