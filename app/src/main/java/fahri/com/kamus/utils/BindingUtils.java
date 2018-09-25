package fahri.com.kamus.utils;


import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fahri.com.kamus.data.model.db.EnglishIndonesia;
import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import fahri.com.kamus.ui.eng_indkamus.EngIndoAdapter;
import fahri.com.kamus.ui.ind_engkamus.IndEngAdapter;


public final class BindingUtils {

    private static final String TAG = "BindingUtils";

    @BindingAdapter("progress")
    public static void setProgressBar(ProgressBar view, int progress) {
        view.setProgress(progress);
    }

    @BindingAdapter({"engIndAdapter"})
    public static void addEngIndAdapter(RecyclerView recyclerView,
                                        ArrayList<EnglishIndonesia> models) {
        EngIndoAdapter adapter = (EngIndoAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            Log.i(TAG, "addEngIndAdapter:not ");
            adapter.clearItems();
            adapter.addItems(models);
        }
    }

    @BindingAdapter({"indEngAdapter"})
    public static void addIndEngAdapter(RecyclerView recyclerView,
                                        ArrayList<IndonesiaEnglish> models) {
        IndEngAdapter adapter = (IndEngAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(models);
        }
    }

}
