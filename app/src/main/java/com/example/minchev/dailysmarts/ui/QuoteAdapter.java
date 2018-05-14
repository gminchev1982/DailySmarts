package com.example.minchev.dailysmarts.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.minchev.dailysmarts.QuoteFragment;
import com.example.minchev.dailysmarts.R;
import com.example.minchev.dailysmarts.dataRoom.QuoteEntity;
import com.example.minchev.dailysmarts.databinding.CardQuoteBinding;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteHolder> {

    private List<QuoteEntity> data;
    private OnFragmentDataListener mListener;
    private OnShareListener mSharelistener;

    public QuoteAdapter() {

    }

    @NonNull
    @Override
    public QuoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardQuoteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.card_quote, parent, false);

        return new QuoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteHolder holder, int position) {
        QuoteEntity current = data.get(position);
        holder.bind(current, mListener, mSharelistener);
    }


    public void setData(List<QuoteEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if (data == null)
            return 0;
        else return data.size();
    }

    public void setListener(OnFragmentDataListener listener, OnShareListener mShareListener) {
        this.mListener = listener;
        this.mSharelistener = mShareListener;
    }
}
