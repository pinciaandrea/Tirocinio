package com.example.progetto.Database.Model;

public class Umbrella {
    public static final String TABLE_NAME = "umbrella";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VALUE = "value";
    private int idUmbrella;
    private boolean valueUmbrella;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_VALUE + " INTEGER "
                    + ")"
                    ;

    public int getId() {
        return idUmbrella;
    }
    public void setId(int idUmbrella) {
        this.idUmbrella = idUmbrella;
    }

}

