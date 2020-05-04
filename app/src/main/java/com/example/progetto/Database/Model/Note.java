package com.example.progetto.Database.Model;

public class Note {

    private String note;
    private int id;
    private String userID;
    private float rating;
    private String testo;

    public Note(String testo, String userID, float rating) {
        this.testo = testo;
        this.userID = userID;
        this.rating = rating;
    }

    public String getTesto(){
        return testo;
    }

    public String getUserID(){
        return userID;
    }

    public float getRating(){
        return rating;
    }


    public Note(String note,float rating) {
        this.note = note;
        this.rating = rating;
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

    public void setRating(Float rating){
        this.rating = rating;
    }

}