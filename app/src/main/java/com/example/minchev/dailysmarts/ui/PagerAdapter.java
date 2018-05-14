package com.example.minchev.dailysmarts.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.minchev.dailysmarts.DailyFragment;
import com.example.minchev.dailysmarts.QuoteFragment;
import com.example.minchev.dailysmarts.dataRoom.QuoteEntity;

public class PagerAdapter extends FragmentPagerAdapter implements OnFragmentDataListener {

    private Context context;
    private DailyFragment mDaily = new DailyFragment();
    private QuoteFragment mQuote = new QuoteFragment();

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mDaily;
            case 1:
                return mQuote;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Daily Quote";
            case 1:
                return "My Quotes";

            default:
                return null;
        }
    }


    @Override
    public void onDelete(QuoteEntity item) {
        mQuote.onDelete(item);
    }
}
