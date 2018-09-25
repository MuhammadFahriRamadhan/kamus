package fahri.com.kamus.ui.ind_engkamus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import fahri.com.kamus.data.model.db.IndonesiaEnglish;
import fahri.com.kamus.databinding.ItemIndEngBinding;
import fahri.com.kamus.ui.base.BaseViewHolder;

public class IndEngAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<IndonesiaEnglish> indonesiaEnglishList;

    public ItemResultClickListener mItemResultClickListener;

    public void setItemResultClickListener(ItemResultClickListener listener) {
        this.mItemResultClickListener = listener;
    }

    public IndEngAdapter(List<IndonesiaEnglish> indonesiaEnglishes) {
        this.indonesiaEnglishList = indonesiaEnglishes;
    }

    public void addItems(List<IndonesiaEnglish> indonesiaEnglishes) {
        indonesiaEnglishList.addAll(indonesiaEnglishes);
        notifyDataSetChanged();
    }

    public void clearItems() {
        indonesiaEnglishList.clear();
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemIndEngBinding binding = ItemIndEngBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return indonesiaEnglishList.size();
    }

    public class ItemViewHolder extends BaseViewHolder implements
            IndEngViewModel.ItemResultViewModelListener {

        private ItemIndEngBinding mBinding;
        private IndEngViewModel mViewModel;

        public ItemViewHolder(ItemIndEngBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final IndonesiaEnglish model = indonesiaEnglishList.get(position);
            mViewModel = new IndEngViewModel(position, model, this);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(int post) {
            mItemResultClickListener.onItemResultIndEngClicked(indonesiaEnglishList, post);
        }
    }

    public interface ItemResultClickListener {
        void onItemResultIndEngClicked(List<IndonesiaEnglish> indonesiaEnglishes, int post);
    }
}
