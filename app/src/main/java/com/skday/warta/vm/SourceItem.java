package com.skday.warta.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;

import com.skday.warta.databinding.SourceItemBinding;
import com.skday.warta.model.Source;

/**
 * Created by skday on 4/18/17.
 */

public class SourceItem extends RecyclerView.ViewHolder {
    public ObservableField<String> bSource = new ObservableField<>();

    public SourceItem(SourceItemBinding binding, Context context, Source source) {
        super(binding.getRoot());
        bSource.set(source.getName());
    }
}
