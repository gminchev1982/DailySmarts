package com.example.minchev.dailysmarts.dataRoom;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {QuoteEntity.class}, version = 1)
public abstract class QuoteDatabase extends RoomDatabase {
    private static QuoteDatabase INSTANCE;
    private static Callback sRoomDatabaseCallback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new OpenDbAsync(INSTANCE).execute();
        }
    };

    public static QuoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuoteDatabase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuoteDatabase.class, "quote.db")
                            .fallbackToDestructiveMigration()
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract QuoteDao QuoteDao();

    //open db and delete all rows ;)
    private static class OpenDbAsync extends AsyncTask<Void, Void, Void> {

        private final QuoteDao mDao;

        OpenDbAsync(QuoteDatabase db) {
            mDao = db.QuoteDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            // mDao.deleteAll();

            //Word word = new Word("Hello");
            // mDao.insert(word);
            // word = new Word("World");
            // mDao.insert(word);
            return null;
        }
    }
}
