package com.skday.warta.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skday.warta.R;
import com.skday.warta.databinding.NewsHeaderBinding;
import com.skday.warta.databinding.NewsItemBinding;
import com.skday.warta.model.Article;
import com.squareup.picasso.Picasso;

/**
 * Created by skday on 4/26/17.
 */

public class NewsHeader extends RecyclerView.ViewHolder {
    public ObservableField<String> bTitle = new ObservableField<>();
    public ObservableField<String> bDescription = new ObservableField<>();
    public ObservableField<String> bPublishedAt = new ObservableField<>();

    public NewsHeader(NewsHeaderBinding binding, final Context context, final Article article ) {
        super(binding.getRoot());
        Picasso.with(context).load(article.getUrlToImage()).into(binding.ivImg);
        bTitle.set(article.getTitle());
        bDescription.set(article.getDescription());
        if (article.getPublishedAt() == null){
            bPublishedAt.set(article.getPublishedAt());
        } else {
            bPublishedAt.set(article.getPublishedAt().substring(0, 10));
        }
        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setShowTitle(true);
                builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(article.getUrl()));
            }
        });
    }
}

