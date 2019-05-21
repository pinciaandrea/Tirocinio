package com.example.progetto.Database.Model;


public class Umbrella {
    public static final String TABLE_NAME = "umbrella";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VALUE = "value";
    //1 se libero, 0 se occupato
    private int idUmbrella;
    private int valueUmbrella;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_VALUE + " INTEGER "
                    + ")"
                    + "INSERT INTO" + TABLE_NAME + "VALUES" + "(NULL, '0'),(NULL, '1')";

    public Umbrella() {
    }

    public Umbrella(int idUmbrella, int valueUmbrella) {
        this.idUmbrella = idUmbrella;
        this.valueUmbrella = valueUmbrella;
    }

    public int getId() {
        return idUmbrella;
    }

    public int getValue() {
        return valueUmbrella;
    }

    public void setValue(int valueUmbrella) {
        this.valueUmbrella = valueUmbrella;
    }

    public void setId(int idUmbrella) {
        this.idUmbrella = idUmbrella;
    }
}