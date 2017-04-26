package com.skday.warta.vm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.skday.warta.R;
import com.skday.warta.adapter.ArticlesAdapter;
import com.skday.warta.databinding.ActivityMainBinding;
import com.skday.warta.model.Article;
import com.skday.warta.model.ArticlesDao;
import com.skday.warta.prefs.PrefSource;
import com.skday.warta.service.ApiClient;
import com.skday.warta.service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Article> articleList = new ArrayList<>();
    public ArticlesAdapter adapter;
    private String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setVm(this);
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Warta");
        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rv.setHasFixedSize(true);
        adapter = new ArticlesAdapter(this, articleList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSource();
        loadArticle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.source_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_src){
            startActivity(new Intent(this, SourceActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void getSource(){
        source = PrefSource.getSource(this);
        if (source == null){
            source = "bbc-news";
        }
    }

    public void loadArticle(){
        articleList.clear();
        binding.rv.setVisibility(View.GONE);
        ApiClient client = Service.createService(ApiClient.class);
        Call<ArticlesDao> call = client.getArticles(source);
        call.enqueue(new Callback<ArticlesDao>() {
            @Override
            public void onResponse(Call<ArticlesDao> call, Response<ArticlesDao> response) {
                ArticlesDao articlesDao = response.body();
                articleList.addAll(articlesDao.getArticles());
                adapter.notifyDataSetChanged();
                binding.rv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArticlesDao> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Load Data Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
