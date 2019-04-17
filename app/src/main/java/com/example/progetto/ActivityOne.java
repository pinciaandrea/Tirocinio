package com.example.progetto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ActivityOne extends AppCompatActivity
        implements View.OnClickListener {

    ViewPager viewPager;
    SlideAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
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
            viewPager= (ViewPager) findViewById(R.id.viewPager_id);

            adapter= new SlideAdapter(this);

            viewPager.setAdapter(adapter);

            Button bhome = (Button) findViewById(R.id.button);
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