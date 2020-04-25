package com.example.progetto.Database.Model;

public class Note {

    private String note;
    private int id;

    public Note() {
    }

    public Note(int id,String note) {
        this.id = id;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}