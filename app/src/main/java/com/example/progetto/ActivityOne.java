package com.example.progetto;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener {


    ViewPager viewPager;
    SlideAdapter adapter;
    ActivityOne activityOne;

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

                final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityOne.this);
                View  mView = getLayoutInflater().inflate(R.layout.servizi1, null);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(true);

                alertDialog.show();
            }
        });
        parkButtonALert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityOne.this);
                View  mView = getLayoutInflater().inflate(R.layout.servizi2, null);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(true);

                alertDialog.show();
            }
        });
        eatButtonALert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityOne.this);
                View  mView = getLayoutInflater().inflate(R.layout.servizi3, null);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(true);

                alertDialog.show();
            }
        });
        showerButtonALert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityOne.this);
                View  mView = getLayoutInflater().inflate(R.layout.servizi4, null);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(true);

                alertDialog.show();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
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
                    case R.id.action_profilo:
                        Intent intent4 = new Intent(ActivityOne.this,MainActivity.class);
                        startActivity(intent4);
                }
                return false;
            }
        });

            viewPager= findViewById(R.id.viewPager_id);
            adapter= new SlideAdapter(this);
            viewPager.setAdapter(adapter);


        }

        @Override
        public void onClick(View v) { }


}

