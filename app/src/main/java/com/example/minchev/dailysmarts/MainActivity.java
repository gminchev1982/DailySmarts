package com.example.minchev.dailysmarts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.minchev.dailysmarts.dataRoom.QuoteEntity;
import com.example.minchev.dailysmarts.databinding.ActivityMainBinding;
import com.example.minchev.dailysmarts.ui.OnFragmentDataListener;
import com.example.minchev.dailysmarts.ui.PagerAdapter;

public class MainActivity extends AppCompatActivity implements OnFragmentDataListener {
    ActivityMainBinding binding;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        setupTabs();
    }

    public void setupTabs() {
        adapter = new PagerAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.grpTabs.setupWithViewPager(binding.viewPager);

    }


    @Override
    public void onDelete(QuoteEntity item) {
        adapter.onDelete(item);
    }
}
