package fahri.com.kamus.ui.eng_indkamus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import fahri.com.kamus.data.model.db.EnglishIndonesia;
import fahri.com.kamus.databinding.ItemEngIndBinding;
import fahri.com.kamus.ui.base.BaseViewHolder;

public class EngIndoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<EnglishIndonesia> englishIndonesiaList;

    public ItemResultClickListener mItemResultClickListener;

    public void setItemResultClickListener(ItemResultClickListener listener) {
        this.mItemResultClickListener = listener;
    }

    public EngIndoAdapter(List<EnglishIndonesia> englishIndonesias) {
        this.englishIndonesiaList = englishIndonesias;
    }

    public void addItems(List<EnglishIndonesia> englishIndonesias) {
        englishIndonesiaList.addAll(englishIndonesias);
        notifyDataSetChanged();
    }

    public void clearItems() {
        englishIndonesiaList.clear();
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemEngIndBinding binding = ItemEngIndBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return englishIndonesiaList.size();
    }

    public class ItemViewHolder extends BaseViewHolder implements
            EngIndoViewModel.ItemResultViewModelListener {

        private ItemEngIndBinding mBinding;
        private EngIndoViewModel mViewModel;

        public ItemViewHolder(ItemEngIndBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final EnglishIndonesia model = englishIndonesiaList.get(position);
            mViewModel = new EngIndoViewModel(position, model, this);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(int post) {
            mItemResultClickListener.onItemResultEngIndClicked(englishIndonesiaList, post);
        }
    }

    public interface ItemResultClickListener {
        void onItemResultEngIndClicked(List<EnglishIndonesia> englishIndonesiaList, int post);
    }
}

