package com.example.progetto.Database.Model;

public class Umbrella {
    public static final String TABLE_NAME = "umbrella";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VALUE = "value";
    //1 se libero, 0 se occupato
    private int idUmbrella;
    private boolean valueUmbrella;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_VALUE + " INTEGER "
                    + ")"
                    ;

    public Umbrella (){

    }

    public Umbrella(int idUmbrella, boolean valueUmbrella) {
        this.idUmbrella = idUmbrella;
        this.valueUmbrella = valueUmbrella;
    }

    public int getId() {
        return idUmbrella;
    }

    public boolean getValue() {
        return valueUmbrella;
    }

    public void setValue(boolean valueUmbrella) {
        this.valueUmbrella = valueUmbrella;
    }

    public void setId(int idUmbrella) {
        this.idUmbrella = idUmbrella;
    }

}

