package com.skday.warta.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skday.warta.R;
import com.skday.warta.databinding.SourceItemBinding;
import com.skday.warta.model.Source;
import com.skday.warta.vm.SourceActivity;
import com.skday.warta.vm.SourceItem;

import java.util.List;

/**
 * Created by skday on 4/18/17.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.BindingHolder> {
    private Context context;
    private List<Source> sourceList;

    public SourceAdapter(Context context, List<Source> sourceList) {
        this.context = context;
        this.sourceList = sourceList;
    }

    @Override
    public SourceAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SourceItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.source_item, parent, false);
        return new ItemBindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(SourceAdapter.BindingHolder holder, int position) {
        ((ItemBindingHolder) holder).getBinding()
                .setVm(new SourceItem(((ItemBindingHolder) holder).getBinding(), context, sourceList.get(position)));
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder{

        private ViewDataBinding binding;
        public BindingHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public static class ItemBindingHolder extends BindingHolder{

        private SourceItemBinding binding;
        public ItemBindingHolder(SourceItemBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        public SourceItemBinding getBinding() {
            return binding;
        }
    }
}
