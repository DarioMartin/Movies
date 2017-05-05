package com.fasttrack.android.movies.data;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import com.fasttrack.android.movies.data.MoviesContract.FavMoviesEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by megamedia on 27/4/17.
 */

@RunWith(AndroidJUnit4.class)
public class MoviesContentProviderTest {

    private final Context context = InstrumentationRegistry.getTargetContext();

    @Before
    public void setUp() {
        MoviesDBHelper dbHelper = new MoviesDBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(FavMoviesEntry.TABLE_NAME, null, null);
    }

    @After
    public final void tearDown() {
        MoviesDBHelper dbHelper = new MoviesDBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(FavMoviesEntry.TABLE_NAME, null, null);
    }

    @Test
    public void testProviderRegistry() {
        String packageName = context.getPackageName();
        String moviesProviderClassName = MoviesContentProvider.class.getName();
        ComponentName componentName = new ComponentName(packageName, moviesProviderClassName);

        try {
            PackageManager pm = context.getPackageManager();
            ProviderInfo providerInfo = pm.getProviderInfo(componentName, 0);
            String actualAuthority = providerInfo.authority;

            /* Make sure that the registered authority matches the authority from the Contract */
            String incorrectAuthority = "Error: MovieContentProvider registered with authority: " + actualAuthority + " instead of expected authority: " + packageName;
            assertEquals(incorrectAuthority, actualAuthority, packageName);

        } catch (PackageManager.NameNotFoundException e) {
            String providerNotRegisteredAtAll = "Error: MovieContentProvider not registered at " + context.getPackageName();
            fail(providerNotRegisteredAtAll);
        }
    }

    private static final Uri TEST_FAV_MOVIES = FavMoviesEntry.CONTENT_URI;

    @Test
    public void testUriMatcher() {
        UriMatcher testMatcher = MoviesContentProvider.buildUriMatcher();
        String moviesUriDoesNotMatch = "Error: The MOVIES URI was matched incorrectly.";
        int actualMoviesMatchCode = testMatcher.match(TEST_FAV_MOVIES);
        int expectedMoviesMatchCode = MoviesContentProvider.FAV_MOVIES;
        assertEquals(moviesUriDoesNotMatch, actualMoviesMatchCode, expectedMoviesMatchCode);
    }

    /**
     * Tests inserting a single row of data via a ContentResolver
     */
    @Test
    public void testInsert() {
        ContentValues testMovieValues = new ContentValues();
        testMovieValues.put(FavMoviesEntry._ID, 1);
        testMovieValues.put(FavMoviesEntry.COLUMN_TITLE, "Peli prueba 1");
        testMovieValues.put(FavMoviesEntry.COLUMN_TMDB_ID, "000");
        testMovieValues.put(FavMoviesEntry.COLUMN_POSTER, "Poster");

        TestUtilities.TestContentObserver movieObserver = TestUtilities.getTestContentObserver();

        ContentResolver contentResolver = context.getContentResolver();

        contentResolver.registerContentObserver(FavMoviesEntry.CONTENT_URI, true, movieObserver);

        Uri uri = contentResolver.insert(FavMoviesEntry.CONTENT_URI, testMovieValues);

        Uri expectedUri = ContentUris.withAppendedId(FavMoviesEntry.CONTENT_URI, 1);

        String insertProviderFailed = "Unable to insert item through Provider";
        assertEquals(insertProviderFailed, uri, expectedUri);

        movieObserver.waitForNotificationOrFail();

        contentResolver.unregisterContentObserver(movieObserver);
    }


    /**
     * Inserts data, then tests if a query for the movies directory returns that data as a Cursor
     */
    @Test
    public void testQuery() {
        MoviesDBHelper dbHelper = new MoviesDBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues testMovieValues = new ContentValues();
        testMovieValues.put(FavMoviesEntry.COLUMN_TMDB_ID, "1234");
        testMovieValues.put(FavMoviesEntry.COLUMN_TITLE, "Test title");
        testMovieValues.put(FavMoviesEntry.COLUMN_POSTER, "Test poster");

        long movieRowId = database.insert(FavMoviesEntry.TABLE_NAME, null, testMovieValues);

        String insertFailed = "Unable to insert directly into the database";
        assertTrue(insertFailed, movieRowId != -1);

        database.close();

        Cursor movieCursor = context.getContentResolver().query(FavMoviesEntry.CONTENT_URI, null, null, null, null);

        String queryFailed = "Query failed to return a valid Cursor";
        assertTrue(queryFailed, movieCursor != null);

        movieCursor.close();
    }


    /**
     * Tests deleting a single row of data via a ContentResolver
     */
    @Test
    public void testDelete() {
        MoviesDBHelper helper = new MoviesDBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues testMovieValues = new ContentValues();
        testMovieValues.put(FavMoviesEntry.COLUMN_TMDB_ID, "1234");
        testMovieValues.put(FavMoviesEntry.COLUMN_TITLE, "Test title");
        testMovieValues.put(FavMoviesEntry.COLUMN_POSTER, "Test poster");

        long movieRowId = database.insert(FavMoviesEntry.TABLE_NAME, null, testMovieValues);

        String insertFailed = "Unable to insert directly into the database";
        assertTrue(insertFailed, movieRowId != -1);

        database.close();

        TestUtilities.TestContentObserver movieObserver = TestUtilities.getTestContentObserver();

        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.registerContentObserver(FavMoviesEntry.CONTENT_URI, true, movieObserver);

        Uri uriToDelete = FavMoviesEntry.CONTENT_URI.buildUpon().appendPath("1234").build();
        int movieDeleted = contentResolver.delete(uriToDelete, null, null);

        String deleteFailed = "Unable to delete item in the database";
        assertTrue(deleteFailed, movieDeleted != 0);

        movieObserver.waitForNotificationOrFail();

        contentResolver.unregisterContentObserver(movieObserver);
    }

}