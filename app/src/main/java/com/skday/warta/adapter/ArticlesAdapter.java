package com.skday.warta.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.skday.warta.R;
import com.skday.warta.databinding.NewsItemBinding;
import com.skday.warta.model.Article;
import com.skday.warta.vm.NewsItem;

import java.util.List;

/**
 * Created by skday on 4/18/17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.BindingHolder> {

    private Context context;
    private List<Article> articleList;

    public ArticlesAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
        return new ItemBindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ((ItemBindingHolder) holder).getBinding()
                .setVm(new NewsItem(((ItemBindingHolder) holder).getBinding(), context, articleList.get(position)));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding binding;

        public BindingHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }

    public static class ItemBindingHolder extends BindingHolder{
        private NewsItemBinding binding;
        public ItemBindingHolder(NewsItemBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        public NewsItemBinding getBinding() {
            return binding;
        }
    }
}
