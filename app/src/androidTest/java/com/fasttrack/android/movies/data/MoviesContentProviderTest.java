package com.fasttrack.android.movies.data;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import com.fasttrack.android.movies.data.MoviesContract.FavMoviesEntry;

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
            String incorrectAuthority = "Error: TaskContentProvider registered with authority: " + actualAuthority + " instead of expected authority: " + packageName;
            assertEquals(incorrectAuthority, actualAuthority, packageName);

        } catch (PackageManager.NameNotFoundException e) {
            String providerNotRegisteredAtAll = "Error: TaskContentProvider not registered at " + context.getPackageName();
            fail(providerNotRegisteredAtAll);
        }
    }

    private static final Uri TEST_FAV_MOVIES = FavMoviesEntry.CONTENT_URI;

    @Test
    public void testUriMatcher() {
        UriMatcher testMatcher = MoviesContentProvider.buildUriMatcher();
        String tasksUriDoesNotMatch = "Error: The TASKS URI was matched incorrectly.";
        int actualTasksMatchCode = testMatcher.match(TEST_FAV_MOVIES);
        int expectedTasksMatchCode = MoviesContentProvider.FAV_MOVIES;
        assertEquals(tasksUriDoesNotMatch, actualTasksMatchCode, expectedTasksMatchCode);
    }

    /**
     * Tests inserting a single row of data via a ContentResolver
     */
    @Test
    public void testInsert() {
        ContentValues testTaskValues = new ContentValues();
        testTaskValues.put(FavMoviesEntry.COLUMN_TITLE, "Peli prueba 1");
        testTaskValues.put(FavMoviesEntry.COLUMN_TMDB_ID, "000");
        testTaskValues.put(FavMoviesEntry.COLUMN_POSTER, "Poster");

        TestUtilities.TestContentObserver taskObserver = TestUtilities.getTestContentObserver();

        ContentResolver contentResolver = context.getContentResolver();

        contentResolver.registerContentObserver(FavMoviesEntry.CONTENT_URI, true, taskObserver);

        Uri uri = contentResolver.insert(FavMoviesEntry.CONTENT_URI, testTaskValues);

        Uri expectedUri = ContentUris.withAppendedId(FavMoviesEntry.CONTENT_URI, 1);

        String insertProviderFailed = "Unable to insert item through Provider";
        assertEquals(insertProviderFailed, uri, expectedUri);

        taskObserver.waitForNotificationOrFail();

        contentResolver.unregisterContentObserver(taskObserver);
    }


}