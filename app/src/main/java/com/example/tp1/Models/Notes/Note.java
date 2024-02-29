package com.example.tp1.Models.Notes;

import android.content.Context;

import com.example.tp1.Database.MyDataBase;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Note {
    private Context context;
    private int id;
    private String title;
    private String text;
    private Date created_at;
    private Date last_modification;
    private LinkedList<String> tags = new LinkedList<>();

    private String ExoName;

    public Note(Context context){this.context = context;}
    public Note(int id, String title , String text , Date created_at, Date last_modification,
                LinkedList<String> tags){
        this(title, text , created_at ,last_modification , tags);
        this.id = id;
    }
    public Note(String title , String text , Date created_at, Date last_modification,
                LinkedList<String> tags){
        this.title = title;
        this.text = text;
        this.created_at = created_at;
        this.last_modification = last_modification;
        this.tags = tags;
    }

    public Note(Context context, String title, String text , LinkedList<String> tags){
        this.title = title;
        this.text = text;
        this.tags = tags;
        this.created_at = this.last_modification = Calendar.getInstance().getTime();
        this.context = context;
    }

    @Override
    public String toString(){
        return "Title : " + title + "\n" + "Text : " + text;
    }

    public void insertToDB(){
        MyDataBase dataBase = new MyDataBase(context);
        dataBase.addOneNote(this);
    }
    public LinkedList<Note> getFromDB(){
        MyDataBase dataBase = new MyDataBase(context);
        return dataBase.allNotes();
    }
    public void deleteFromDB(){

    }
    public void updateInDB(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getLast_modification() {
        return last_modification;
    }

    public void setLast_modification(Date last_modification) {
        this.last_modification = last_modification;
    }

    public LinkedList<String> getTags() {
        return tags;
    }

    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }

    public void setExoColumn(String exoName) {
        this.ExoName = ExoName;
    }

    public String getExo() {
        return ExoName;
    }
}
