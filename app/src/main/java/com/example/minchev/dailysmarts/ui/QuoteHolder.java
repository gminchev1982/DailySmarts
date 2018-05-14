package com.example.minchev.dailysmarts.ui;

import android.support.v7.widget.RecyclerView;

import com.example.minchev.dailysmarts.R;
import com.example.minchev.dailysmarts.dataRoom.QuoteEntity;
import com.example.minchev.dailysmarts.databinding.CardQuoteBinding;


public class QuoteHolder extends RecyclerView.ViewHolder {

    CardQuoteBinding binding;


    public QuoteHolder(CardQuoteBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(QuoteEntity item, OnFragmentDataListener listener) {
        // binding.grpCard.setBackgroundColor(binding.getRoot().getContext().getResources().getColor(WeatherUtils.getColorByTemperature(tomorrowForecast.getTemperature())));
        binding.txtAuthor.setText(item.getQuoteAuthor());
        binding.txtQuote.setText(item.getQuoteText());
        binding.imgFavorite.setColorFilter(binding.imgFavorite.getContext().getResources().getColor(R.color.green));
        binding.imgShare.setColorFilter(binding.imgShare.getContext().getResources().getColor(R.color.blue));
        binding.imgFavorite.setOnClickListener(v -> listener.onDelete(item));
    }


}
