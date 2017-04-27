package com.fasttrack.android.movies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fasttrack.android.movies.data.MoviesContract.FavMoviesEntry;

/**
 * Created by megamedia on 27/4/17.
 */

public class MoviesDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "moives.db";
    private static final int DATABASE_VERSION = 1;

    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAV_MOVIES_TABLE =
                "CREATE TABLE " + FavMoviesEntry.TABLE_NAME + " (" +
                        FavMoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FavMoviesEntry.COLUMN_TMDB_ID + " TEXT NOT NULL, " +
                        FavMoviesEntry.COLUMN_TITLE + " TEXT, " +
                        FavMoviesEntry.COLUMN_POSTER + " TEXT" + ");";

        db.execSQL(SQL_CREATE_FAV_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavMoviesEntry.TABLE_NAME);
        onCreate(db);
    }
}
