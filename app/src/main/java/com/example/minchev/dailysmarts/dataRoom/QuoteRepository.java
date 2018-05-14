package com.example.minchev.dailysmarts.dataRoom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class QuoteRepository {

    private QuoteDao mQuoteDao;
    private LiveData<List<QuoteEntity>> mAllQuote;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    QuoteRepository(Application application) {
        QuoteDatabase db = QuoteDatabase.getDatabase(application);
        mQuoteDao = db.QuoteDao();
        mAllQuote = mQuoteDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<QuoteEntity>> getAll() {
        return mAllQuote;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert(QuoteEntity Quote) {
        new insertAsyncTask(mQuoteDao).execute(Quote);
    }

    public void delete(QuoteEntity Quote) {
        new deleteAsyncTask(mQuoteDao).execute(Quote);
    }

    private static class insertAsyncTask extends AsyncTask<QuoteEntity, Void, Void> {

        private QuoteDao mAsyncTaskDao;

        insertAsyncTask(QuoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final QuoteEntity... params) {
            long id = mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<QuoteEntity, Void, Boolean> {

        Exception error;
        private QuoteDao mAsyncTaskDao;

        deleteAsyncTask(QuoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final QuoteEntity... params) {
            try {
                mAsyncTaskDao.delete(params[0]);
                return true;
            } catch (Exception e) {
                Exception error = e;
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
        }
    }
}
