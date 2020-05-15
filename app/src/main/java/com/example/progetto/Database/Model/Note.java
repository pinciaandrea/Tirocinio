package com.example.progetto.Database.Model;

public class Note {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}