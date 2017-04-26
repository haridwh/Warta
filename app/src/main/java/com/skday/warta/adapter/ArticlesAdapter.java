package com.skday.warta.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.skday.warta.R;
import com.skday.warta.databinding.NewsHeaderBinding;
import com.skday.warta.databinding.NewsItemBinding;
import com.skday.warta.model.Article;
import com.skday.warta.vm.NewsHeader;
import com.skday.warta.vm.NewsItem;

import java.util.List;

/**
 * Created by skday on 4/18/17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.BindingHolder> {

    private Context context;
    private List<Article> articleList;
    public final int ITEM_TYPE_NORMAL = 0;
    public final int ITEM_TYPE_HEADER = 1;

    public ArticlesAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER){
            NewsHeaderBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_header, parent, false);
            return new HeaderBindingHolder(binding);
        }else {
            NewsItemBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
            return new ItemBindingHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final int viewType = getItemViewType(position);
        if (viewType == ITEM_TYPE_HEADER){

            ((HeaderBindingHolder) holder).getBinding()
                    .setVm(new NewsHeader(((HeaderBindingHolder) holder).getBinding(), context, articleList.get(position)));
        }else if (viewType == ITEM_TYPE_NORMAL){
            ((ItemBindingHolder) holder).getBinding()
                    .setVm(new NewsItem(((ItemBindingHolder) holder).getBinding(), context, articleList.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_TYPE_HEADER;
        }else{
            return ITEM_TYPE_NORMAL;
        }
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

    public static class HeaderBindingHolder extends BindingHolder{
        private NewsHeaderBinding binding;
        public HeaderBindingHolder(NewsHeaderBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        public NewsHeaderBinding getBinding() {
            return binding;
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
