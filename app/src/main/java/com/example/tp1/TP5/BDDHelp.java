package com.example.tp1.TP5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;

public class BDDHelp extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String NAME_DB = "TP5.db";
    String etudiant = "etudiant";
    public BDDHelp(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + etudiant + " (_id INTEGER  PRIMARY KEY AUTOINCREMENT , mat TEXT," +
                "nom TEXT , prenom TEXT , pohot BLOB );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + etudiant);
        onCreate(db);
    }

    public ArrayList<Etudiant> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + etudiant , null);
        ArrayList<Etudiant> list = new ArrayList<>();

       if (cursor.moveToFirst()){
           do{

               Etudiant e  = new Etudiant(cursor.getInt(0) , cursor.getString(1) ,cursor.getString(2) ,
                       cursor.getString(3) );

               list.add(e);

           } while(cursor.moveToNext());

           return list;
       }else {
           return null;
       }

    }

    public Etudiant getByMat(String mat){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + etudiant + " WHERE mat = ?",  new String[]{mat});

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        return new Etudiant(cursor.getInt(0) , cursor.getString(1) ,cursor.getString(2) ,
                cursor.getString(3) );
    }

    public boolean insert(Etudiant etu){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues b = new ContentValues();
            b.put("mat",etu.getMat());
            b.put("nom",etu.getNom());
            b.put("prenom",etu.getPrenom());

            db.insert(etudiant , null , b);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean update(Etudiant etu){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues b = new ContentValues();
            b.put("nom",etu.getNom());
            b.put("prenom",etu.getPrenom());

            db.update(etudiant , b, "mat = ?" , new String[]{etu.getMat().toString()} );
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean delete(Etudiant etu){
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            db.delete(etudiant ,"mat = ?" , new String[]{etu.getMat().toString()} );
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
