package com.example.minchev.dailysmarts;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.minchev.dailysmarts.Model.Quote;
import com.example.minchev.dailysmarts.dataRoom.QuoteEntity;
import com.example.minchev.dailysmarts.databinding.ActivityMainBinding;
import com.example.minchev.dailysmarts.ui.OnFragmentDataListener;
import com.example.minchev.dailysmarts.ui.OnShareListener;
import com.example.minchev.dailysmarts.ui.PagerAdapter;

public class MainActivity extends AppCompatActivity implements OnFragmentDataListener, OnShareListener {
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

    @Override
    public void onShare(Quote mQuote) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Be smart and read this");
        String shareText = "\n" + mQuote.getQuoteText() + "\n";
        shareText += mQuote.getQuoteAuthor() + "\n";
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        Intent chooserIntent = Intent.createChooser(intent, "Choose one");
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chooserIntent);
    }
}
