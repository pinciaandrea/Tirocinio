package com.example.progetto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityTwo extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    int []arr = {R.drawable.umbrella_free};

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> user = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                view.setMinDate(System.currentTimeMillis() - 1000);
            }
        };

        recyclerView = findViewById(R.id.viewRecycler);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter= new RecyclerViewAdapter(arr);
        recyclerView.setAdapter( recyclerViewAdapter);

        Button dataButton = (Button) findViewById(R.id.button4);
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDate = new DatePickerDialog(ActivityTwo.this, date, 2019, 4, 22);
                mDate.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDate.show();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_informazioni:
                        Intent intent1 = new Intent(ActivityTwo.this, ActivityOne.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_recensioni:
                        Intent intent3 = new Intent(ActivityTwo.this, ActivityThree.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

        Button bhome2 = (Button) findViewById(R.id.button2);
        bhome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(ActivityTwo.this, MainActivity.class);
                startActivity(intent5);
            }
        });
        //riempie recycler view, mentre il colore lo decido nell'adapter della recyclerview
        db.collection("Ombrelloni").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        
                    }
                }
            }
        });
        }



        /*
        db = new DatabaseHelper(this);
        db.InsertAllUmbrella();
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();

        if(ombrelloni.get(0).getPrenotato()==0) img0.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(0).getPrenotato()==1) img0.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(1).getPrenotato()==0) img1.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(1).getPrenotato()==1) img1.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(2).getPrenotato()==0) img2.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(2).getPrenotato()==1) img2.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(3).getPrenotato()==0) img3.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(3).getPrenotato()==1) img3.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(4).getPrenotato()==0) img4.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(4).getPrenotato()==1) img4.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(5).getPrenotato()==0) img5.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(5).getPrenotato()==1) img5.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(6).getPrenotato()==0) img6.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(6).getPrenotato()==1) img6.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(7).getPrenotato()==0) img7.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(7).getPrenotato()==1) img7.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(8).getPrenotato()==0) img8.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(8).getPrenotato()==1) img8.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(9).getPrenotato()==0) img9.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(9).getPrenotato()==1) img9.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(10).getPrenotato()==0) img10.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(10).getPrenotato()==1) img10.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(11).getPrenotato()==0) img11.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(11).getPrenotato()==1) img11.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(12).getPrenotato()==0) img12.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(12).getPrenotato()==1) img12.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(13).getPrenotato()==0) img13.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(13).getPrenotato()==1) img13.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(14).getPrenotato()==0) img14.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(14).getPrenotato()==1) img14.setImageResource(R.drawable.umbrella_busy);
        if(ombrelloni.get(15).getPrenotato()==0) img15.setImageResource(R.drawable.umbrella_free);
        if(ombrelloni.get(15).getPrenotato()==1) img15.setImageResource(R.drawable.umbrella_busy);

    }

    public void libero0 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone0);
                Umbrella_obj umbrella = new Umbrella_obj(15,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(0).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }

    public void libero1 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone1);
                Umbrella_obj umbrella = new Umbrella_obj(14,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(1).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }

        public void libero2(View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        final ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone2);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                imgToChange.setImageResource(R.drawable.umbrella_busy);

                Umbrella_obj umbrella = new Umbrella_obj(13,1);
                db.updateUmbrella(umbrella);

                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(2).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }
    public void libero3(View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        final ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone3);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                imgToChange.setImageResource(R.drawable.umbrella_busy);

                Umbrella_obj umbrella = new Umbrella_obj(12,1);
                db.updateUmbrella(umbrella);

                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login per prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(3).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }
        public void libero4 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        final ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone4);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                imgToChange.setImageResource(R.drawable.umbrella_busy);

                Umbrella_obj umbrella = new Umbrella_obj(11,1);
                db.updateUmbrella(umbrella);

                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
            List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
            if(name.equals("0")){
                Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
            }
            else if(name.equals("1") && ombrelloni.get(4).getPrenotato() == 0)  {
                alertDialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
            }
    }
        public void libero5 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone5);
                Umbrella_obj umbrella = new Umbrella_obj(10,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
            List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
            if(name.equals("0")){
                Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
            }
            else if(name.equals("1") && ombrelloni.get(5).getPrenotato() == 0)  {
                alertDialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
            }
    }
        public void libero6 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone6);
                Umbrella_obj umbrella = new Umbrella_obj(9,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
            List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
            if(name.equals("0")){
                Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
            }
            else if(name.equals("1") && ombrelloni.get(6).getPrenotato() == 0)  {
                alertDialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
            }
    }
        public void libero7 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone7);
                Umbrella_obj umbrella = new Umbrella_obj(8,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
            List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
            if(name.equals("0")){
                Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
            }
            else if(name.equals("1") && ombrelloni.get(7).getPrenotato() == 0)  {
                alertDialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
            }
    }
        public void libero8 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone8);
                Umbrella_obj umbrella = new Umbrella_obj(7,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
            List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
            if(name.equals("0")){
                Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
            }
            else if(name.equals("1") && ombrelloni.get(8).getPrenotato() == 0)  {
                alertDialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
            }
    }
        public void libero9 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone9);
                Umbrella_obj umbrella = new Umbrella_obj(6,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
            List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
            if(name.equals("0")){
                Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
            }
            else if(name.equals("1") && ombrelloni.get(9).getPrenotato() == 0)  {
                alertDialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
            }
    }

    public void libero10 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone10);
                Umbrella_obj umbrella = new Umbrella_obj(5,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(10).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }
    public void libero11 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone11);
                Umbrella_obj umbrella = new Umbrella_obj(4,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(11).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }

    public void libero12 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone12);
                Umbrella_obj umbrella = new Umbrella_obj(3,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(12).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }

    public void libero13 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone13);
                Umbrella_obj umbrella = new Umbrella_obj(2,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(13).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }
    public void libero14 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone14);
                Umbrella_obj umbrella = new Umbrella_obj(1,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(14).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }

    public void libero15 (View view){

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                ImageButton imgToChange = (ImageButton) findViewById(R.id.ombrellone15);
                Umbrella_obj umbrella = new Umbrella_obj(0,1);
                db.updateUmbrella(umbrella);
                imgToChange.setImageResource(R.drawable.umbrella_busy);
                alertDialog.dismiss();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        List<Umbrella_obj> ombrelloni = db.getAllUmbrella();
        if(name.equals("0")){
            Toast.makeText(getApplicationContext(), "Devi effettuare il login pre prenotare un ombrellone!!", Toast.LENGTH_LONG).show();
        }
        else if(name.equals("1") && ombrelloni.get(15).getPrenotato() == 0)  {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "Ombrellone già prenotato!!", Toast.LENGTH_LONG).show();
        }
    }



*/

}
