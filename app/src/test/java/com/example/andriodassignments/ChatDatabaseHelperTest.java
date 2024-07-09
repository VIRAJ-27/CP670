package com.example.andriodassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ChatDatabaseHelperTest {

    private Context mockContext;
    private ChatDatabaseHelper dbHelper;
    private SQLiteDatabase mockDb;

    @Before
    public void setUp() {
        // Mock the context
        mockContext = mock(Context.class);

        // Mock the SQLiteDatabase
        mockDb = mock(SQLiteDatabase.class);

        // Create an instance of the ChatDatabaseHelper with the mocked context
        dbHelper = new ChatDatabaseHelper(mockContext);
    }

    @Test
    public void testOnCreate() {
        // Call the onCreate method with the mocked database
        dbHelper.onCreate(mockDb);

        // Verify that the execSQL method was called with the expected SQL command
        verify(mockDb).execSQL("CREATE TABLE " + ChatDatabaseHelper.TABLE_NAME + " (" +
                ChatDatabaseHelper.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChatDatabaseHelper.KEY_MESSAGE + " TEXT);");
    }

    @Test
    public void testOnUpgrade() {
        // Call the onUpgrade method with the mocked database
        dbHelper.onUpgrade(mockDb, 1, 2);

        // Verify that the execSQL method was called with the expected SQL commands
        verify(mockDb).execSQL("DROP TABLE IF EXISTS " + ChatDatabaseHelper.TABLE_NAME);
        verify(mockDb).execSQL("CREATE TABLE " + ChatDatabaseHelper.TABLE_NAME + " (" +
                ChatDatabaseHelper.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChatDatabaseHelper.KEY_MESSAGE + " TEXT);");
    }

    @Test
    public void testDatabaseName() {
        // Verify that the database name is set correctly
        assertEquals("Messages.db", dbHelper.getDatabaseName());
    }

    @Test
    public void testDatabaseVersion() {
        // Verify that the database version is set correctly
        assertEquals(1, dbHelper.getReadableDatabase().getVersion());
    }
}
