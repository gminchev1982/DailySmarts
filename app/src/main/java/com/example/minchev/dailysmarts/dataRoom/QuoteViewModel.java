package com.example.minchev.dailysmarts.dataRoom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class QuoteViewModel extends AndroidViewModel {
    private QuoteRepository mRepository;
    private LiveData<List<QuoteEntity>> mAllQuote;

    public QuoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new QuoteRepository(application);
        mAllQuote = mRepository.getAll();
    }

    public LiveData<List<QuoteEntity>> getAll() {
        return mAllQuote;
    }

    public void insert(QuoteEntity Quote) {
        mRepository.insert(Quote);
    }

    public void delete(QuoteEntity Quote) {
        mRepository.delete(Quote);
    }
}
