package com.skday.warta.vm;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.skday.warta.R;
import com.skday.warta.base.BaseActivity;
import com.skday.warta.databinding.ActivityAboutBinding;

public class AboutActivity extends BaseActivity {

    ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        binding.setVm(this);
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");
        binding.poweredBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setShowTitle(true);
                builder.setToolbarColor(ContextCompat.getColor(AboutActivity.this, R.color.colorPrimary));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(AboutActivity.this, Uri.parse("https://newsapi.org/"));
            }
        });
    }
}
