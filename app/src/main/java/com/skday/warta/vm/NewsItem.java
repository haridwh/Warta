package com.skday.warta.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skday.warta.R;
import com.skday.warta.databinding.NewsItemBinding;
import com.skday.warta.model.Article;
import com.squareup.picasso.Picasso;

/**
 * Created by skday on 4/18/17.
 */

public class NewsItem extends RecyclerView.ViewHolder {
    public ObservableField<String> bTitle = new ObservableField<>();
    public ObservableField<String> bAuthor = new ObservableField<>();
    public ObservableField<String> bDescription = new ObservableField<>();
    public ObservableField<String> bPublishedAt = new ObservableField<>();

    public NewsItem(NewsItemBinding binding, final Context context, final Article article ) {
        super(binding.getRoot());
        Picasso.with(context).load(article.getUrlToImage()).into(binding.ivImg);
        bTitle.set(article.getTitle());
        bAuthor.set("Published by "+article.getAuthor());
        bDescription.set(article.getDescription());
        bPublishedAt.set(article.getPublishedAt());
        binding.ivImg.setOnClickListener(new View.OnClickListener() {
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
