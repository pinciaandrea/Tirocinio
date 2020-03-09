package com.example.progetto;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener {


    ViewPager viewPager;
    SlideAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        ImageView wifiButtonALert = findViewById(R.id.imageView3);
        ImageView parkButtonALert = findViewById(R.id.imageView4);
        ImageView eatButtonALert = findViewById(R.id.imageView5);
        ImageView showerButtonALert = findViewById(R.id.imageView6);
        wifiButtonALert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "La struttura offre una copertura WIFi in tutto il locale e in un raggio d'azione entro 50 metri dalla struttura stessa.", Toast.LENGTH_LONG).show();

            }
        });
        parkButtonALert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Tra le comodità offerte dal nostro stabilimento, incluso nel prezzo dell'ombrellone avrete accesso al parcheggio privato custodito che troverete appena arrivati e seguendo le indicazioni stradali.", Toast.LENGTH_LONG).show();
            }
        });
        eatButtonALert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "MENU: Spaghetti alle vongole, Arrosto misto o Frittura, Dolce e caffe'.", Toast.LENGTH_LONG).show();
            }
        });
        showerButtonALert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Quattro docce grauite a tutti i bagnanti dello stabilimento e possibilità di una doccia calda a €0.50.", Toast.LENGTH_LONG).show();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_prenotazione:
                        Intent intent2 = new Intent(ActivityOne.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_recensioni:
                        Intent intent3 = new Intent(ActivityOne.this, ActivityThree.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

            viewPager= findViewById(R.id.viewPager_id);
            adapter= new SlideAdapter(this);
            viewPager.setAdapter(adapter);

            Button bhome = findViewById(R.id.button);
            bhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent4 = new Intent(ActivityOne.this, MainActivity.class);
                    startActivity(intent4);
                }
            });

        }

        @Override
        public void onClick(View v) { }


}

