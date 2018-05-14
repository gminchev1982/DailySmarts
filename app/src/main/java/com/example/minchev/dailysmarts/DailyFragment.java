package com.example.minchev.dailysmarts;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.minchev.dailysmarts.Api.Api;
import com.example.minchev.dailysmarts.Model.Quote;
import com.example.minchev.dailysmarts.dataRoom.QuoteEntity;
import com.example.minchev.dailysmarts.dataRoom.QuoteViewModel;
import com.example.minchev.dailysmarts.databinding.CardQuoteBinding;
import com.example.minchev.dailysmarts.databinding.FragmentDailyBinding;
import com.example.minchev.dailysmarts.ui.OnFragmentDataListener;

public class DailyFragment extends Fragment {
    private FragmentDailyBinding binding;
    private QuoteViewModel mQuoteViewModel;
    private OnFragmentDataListener mListener;
    private boolean isLiked = false;
    private CardQuoteBinding gprCardQuote;
    private QuoteEntity quoteEntity = new QuoteEntity();

    public DailyFragment() {
        // Required empty public constructor
    }

    public static DailyFragment newInstance() {
        DailyFragment fragment = new DailyFragment();
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_daily, container, false);
        mQuoteViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        updateData();
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentDataListener) {
            mListener = (OnFragmentDataListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                updateData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void updateData() {

        gprCardQuote = DataBindingUtil.bind(binding.grpDailyQoute.getRoot());
        Api.getInstance().getDailyQuote("getQuote", "json", "en", new Api.DataListener<Quote>() {

            @Override
            public void onSuccess(Quote data) {
                updateQuoteData(data);
                //binding.swiperefresh.setRefreshing(false);
            }

            @Override
            public void onError() {
                //binding.swiperefresh.setRefreshing(false);
                Toast.makeText(getContext(), "Error while updating current weather", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateQuoteData(Quote data) {
        gprCardQuote.txtQuote.setText(data.getQuoteText());
        gprCardQuote.txtAuthor.setText(data.getQuoteAuthor());
        gprCardQuote.imgFavorite.setColorFilter(gprCardQuote.imgFavorite.getContext().getResources().getColor(R.color.colorPrimaryDark));
        gprCardQuote.imgShare.setColorFilter(gprCardQuote.imgShare.getContext().getResources().getColor(R.color.blue));
        //QuoteEntity weatherEntity = new QuoteEntity();
        setQuoteEntity(data);
        gprCardQuote.imgFavorite.setOnClickListener(v -> {
            if (!isLiked) {
                insertData(data);
            } else {

                deleteData(data);
            }

        });
    }

    private void deleteData(Quote data) {
        gprCardQuote.imgFavorite.setColorFilter(gprCardQuote.imgFavorite.getContext().getResources().getColor(R.color.colorPrimaryDark));
        isLiked = false;
        mListener.onDelete(quoteEntity);
    }

    private void insertData(Quote data) {
        gprCardQuote.imgFavorite.setColorFilter(gprCardQuote.imgFavorite.getContext().getResources().getColor(R.color.green));
        isLiked = true;
        mQuoteViewModel.insert(quoteEntity);
    }

    private void setQuoteEntity(Quote data) {
        quoteEntity.setQuoteAuthor(data.getQuoteAuthor());
        quoteEntity.setQuoteText(data.getQuoteText());
    }
}
