package com.example.minchev.dailysmarts;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minchev.dailysmarts.dataRoom.QuoteEntity;
import com.example.minchev.dailysmarts.dataRoom.QuoteViewModel;
import com.example.minchev.dailysmarts.databinding.FragmentQuoteBinding;
import com.example.minchev.dailysmarts.ui.OnFragmentDataListener;
import com.example.minchev.dailysmarts.ui.OnShareListener;
import com.example.minchev.dailysmarts.ui.QuoteAdapter;

import java.util.List;

public class QuoteFragment extends Fragment implements OnFragmentDataListener {

    public QuoteAdapter adapter;
    FragmentQuoteBinding binding;
    private QuoteViewModel mQuoteViewModel;
    private List<QuoteEntity> weatherList = null;
    private OnShareListener mShareListener;
    private OnFragmentDataListener mListener;
    public QuoteFragment() {
        // Required empty public constructor
    }

    public static QuoteFragment newInstance() {
        QuoteFragment fragment = new QuoteFragment();
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_quote, container, false);

        mQuoteViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        setupViews();

        return binding.getRoot();

        //return inflater.inflate(R.layout.fragment_my_quote, container, false);
    }


    private void setupViews() {

        binding.recDailyView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new QuoteAdapter();
        adapter.setListener(mListener, mShareListener);
        binding.recDailyView.setAdapter(adapter);

        mQuoteViewModel.getAll().observe(this, (List<QuoteEntity> quotes) -> {
                adapter.setData(quotes);
        });
    }

    @Override
    public void onDelete(QuoteEntity item) {
        mQuoteViewModel.delete(item);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentDataListener) {
            mListener = (OnFragmentDataListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentListener");
        }

        if (context instanceof OnShareListener) {
            mShareListener = (OnShareListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mShareListener = null;
        mListener = null;
    }

}
