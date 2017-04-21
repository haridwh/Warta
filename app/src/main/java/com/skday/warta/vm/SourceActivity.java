package com.skday.warta.vm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.skday.warta.R;
import com.skday.warta.adapter.SourceAdapter;
import com.skday.warta.databinding.ActivitySourceBinding;
import com.skday.warta.model.Source;
import com.skday.warta.model.SourcesDao;
import com.skday.warta.service.ApiClient;
import com.skday.warta.service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceActivity extends AppCompatActivity {

    private ActivitySourceBinding binding;
    private List<Source> sourceList = new ArrayList<>();
    public SourceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_source);
        binding.setVm(this);
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Source");
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SourceAdapter(this, sourceList);
        loadSource();
    }

    private void loadSource() {
        ApiClient client = Service.createService(ApiClient.class);
        Call<SourcesDao> call = client.getSources();
        call.enqueue(new Callback<SourcesDao>() {
            @Override
            public void onResponse(Call<SourcesDao> call, Response<SourcesDao> response) {
                SourcesDao sourcesDao = response.body();
                sourceList.addAll(sourcesDao.getSources());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SourcesDao> call, Throwable t) {
                Toast.makeText(SourceActivity.this, "Load Source Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
