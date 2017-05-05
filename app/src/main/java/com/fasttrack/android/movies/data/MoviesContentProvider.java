package com.fasttrack.android.movies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.fasttrack.android.movies.data.MoviesContract.FavMoviesEntry;

import static com.fasttrack.android.movies.data.MoviesContract.FavMoviesEntry.TABLE_NAME;

/**
 * Created by megamedia on 27/4/17.
 */

public class MoviesContentProvider extends ContentProvider {

    public static final int FAV_MOVIES = 100;
    public static final int FAV_MOVIES_WITH_ID = 101;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_MOVIES, FAV_MOVIES);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_MOVIES + "/#", FAV_MOVIES_WITH_ID);

        return uriMatcher;
    }

    private MoviesDBHelper moviesDBHelper;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        moviesDBHelper = new MoviesDBHelper(context);
        return true;
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = moviesDBHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case FAV_MOVIES:
                long id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(FavMoviesEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }


    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = moviesDBHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {
            case FAV_MOVIES:
                retCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case FAV_MOVIES_WITH_ID:
                String id = uri.getPathSegments().get(1);
                retCursor = db.query(TABLE_NAME,
                        projection,
                        FavMoviesEntry.COLUMN_TMDB_ID + " = ?",
                        new String[]{id},
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }


    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = moviesDBHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);

        int tasksDeleted;

        switch (match) {
            case FAV_MOVIES_WITH_ID:
                String id = uri.getPathSegments().get(1);
                tasksDeleted = db.delete(TABLE_NAME, FavMoviesEntry.COLUMN_TMDB_ID + "=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (tasksDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return tasksDeleted;
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
