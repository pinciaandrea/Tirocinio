package com.example.progetto;

public class Umbrella_obj {

    static int id;
    int prenotato;

    public Umbrella_obj() {
    }

    public Umbrella_obj(int id, int prenotato) {
        this.id = id;
        this.prenotato = prenotato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrenotato() {
        return prenotato;
    }

    public void setPrenotato(int prenotato) {
        this.prenotato = prenotato;
    }
}
