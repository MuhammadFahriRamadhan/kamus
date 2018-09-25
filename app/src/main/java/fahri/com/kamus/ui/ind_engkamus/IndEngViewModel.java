package fahri.com.kamus.ui.ind_engkamus;

import android.databinding.ObservableField;

import fahri.com.kamus.data.model.db.IndonesiaEnglish;

public class IndEngViewModel {
    private static final String TAG = "IndEngViewModel";

    private IndonesiaEnglish mModel;

    public ObservableField<String> searchText = new ObservableField<>("");

    public ItemResultViewModelListener mListener;

    private int mPost;

    public IndEngViewModel(int post, IndonesiaEnglish model, ItemResultViewModelListener listener) {
        this.mPost = post;
        this.mModel = model;
        this.mListener = listener;

        searchText.set(mModel.getIndSearch());
    }

    public void onItemClicked() {
        mListener.onItemClick(mPost);
    }

    public interface ItemResultViewModelListener {
        void onItemClick(int post);
    }
}
