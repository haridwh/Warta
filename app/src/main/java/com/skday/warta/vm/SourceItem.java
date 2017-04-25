package com.skday.warta.vm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skday.warta.databinding.SourceItemBinding;
import com.skday.warta.model.Source;
import com.skday.warta.prefs.PrefSource;

/**
 * Created by skday on 4/18/17.
 */

public class SourceItem extends RecyclerView.ViewHolder {
    public ObservableField<String> bSource = new ObservableField<>();

    public SourceItem(SourceItemBinding binding, final Context context, final Source source) {
        super(binding.getRoot());
        bSource.set(source.getName());
        binding.tvSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefSource.setSource(context, source.getId());
                ((Activity) context).finish();
            }
        });
    }
}
