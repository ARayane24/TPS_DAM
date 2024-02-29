package com.example.tp1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tp1.Models.Notes.Note;

import java.time.InstantSource;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyDataBase extends SQLiteOpenHelper {

    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String CREATED_AT = "created_at";
    public static final String LAST_MODIFICATION = "last_modification";
    public static final String EXO_NAME = "ExoName";
    public static final String TAGS = "Tags";
    public static final String TAG_TEXT = "tagText";
    public static final String ID_NOTES = "idNotes";
    private Context context;
    public static final String NOTES = "Notes";
    private static final String DATABASE_NAME = NOTES + ".db";
    private static final int DATABASE_VERSION = 1;

    public MyDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + NOTES + " (id INTEGER PRIMARY KEY AUTOINCREMENT ," + TITLE + " TEXT," + TEXT + " TEXT ," + CREATED_AT + " DATE" +
                " ," + LAST_MODIFICATION + " DATE , " + EXO_NAME + " TEXT);";
        db.execSQL(createTable);

        String createTableTags = "CREATE TABLE " + TAGS + " (" + ID_NOTES + " INTEGER REFERENCES " +NOTES+"(id), " + TAG_TEXT + " TEXT , PRIMARY KEY (" + ID_NOTES + "," +TAG_TEXT+"));";
        db.execSQL(createTableTags);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOneNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE,note.getTitle());
        cv.put(TEXT,note.getText());
        cv.put(CREATED_AT, note.getCreated_at().getTime());
        cv.put(LAST_MODIFICATION, note.getLast_modification().getTime());
        cv.put(EXO_NAME,note.getExo());

        long id = db.insert(NOTES,null,cv);
        return  (id ) != -1 && addAllTags(id,note.getTags());
    }
    public boolean addAllTags(long id,LinkedList<String> tags){
        boolean test = true;
        if (tags == null || tags.size() == 0) return true;

        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < tags.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put(TAG_TEXT,tags.get(i));
            cv.put(ID_NOTES,id);

            if(db.insert(TAGS , null , cv) == -1){
                return false;
            }
        }
        return true;
    }

    public LinkedList<String> noteTags(int id){
        LinkedList<String> tags = new LinkedList<>();
        String query = "SELECT * FROM "+TAGS+" WHERE " +ID_NOTES+" = (?);";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] inputs = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(query,inputs);
        while (cursor.moveToNext()){
            tags.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
        return tags;
    }

    public LinkedList<Note> allNotes(){
        LinkedList<Note> notes = new LinkedList<>();
        String query = "SELECT * FROM "+NOTES+" ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            String title  = cursor.getString(1);
            String text  = cursor.getString(2);
            Date created_At  =new Date(TimeUnit.SECONDS.toMillis(cursor.getLong(3)));
            Date Last  =new Date(TimeUnit.SECONDS.toMillis(cursor.getLong(4)));
            LinkedList<String> tags = noteTags(id);
            Note a;
            notes.add(a = new Note(id,title,text,created_At,Last,tags));
            a.setExoColumn(cursor.getString(5));
        }
        cursor.close();
        db.close();

        return notes;
    }
}
