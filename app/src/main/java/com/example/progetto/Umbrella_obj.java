package com.example.progetto;

public class Umbrella_obj {

    boolean prenotato;
    String id;

    public Umbrella_obj(boolean prenotato, String id ) {
        this.prenotato = prenotato;
        this.id = id;
    }

    public boolean getPrenotato() {
        return prenotato;
    }

    public String getId (){
        return id;
    }

    public void setPrenotato(boolean prenotato){
        this.prenotato = prenotato;
    }

}
