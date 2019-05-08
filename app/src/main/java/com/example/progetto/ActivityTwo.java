package com.example.progetto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity
    implements View.OnClickListener {
        @Override
        protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    view.setMinDate(System.currentTimeMillis() -1000);
                }
        };

        Button dataButton = (Button)findViewById(R.id.button4);
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDate = new DatePickerDialog(ActivityTwo.this, date, 2019, 4, 22);
                mDate.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
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

        Button bhome2= (Button) findViewById(R.id.button2);
        bhome2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent5 = new Intent(ActivityTwo.this, MainActivity.class);
                startActivity(intent5);
            }
        });
        }

    //https://www.youtube.com/watch?v=Y_G8HX2NEXs&frags=pl%2Cwn
    public void btn_showdialogfree(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.freeumbrelladialog, null);

        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button)mView.findViewById(R.id.btn_confirm);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                alertDialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                Toast.makeText(getApplicationContext(), "PRENOTAZIONE EFFETTUATA. Controlla la tua mail per tutti i dettagli", Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public void btn_showdialogbusy(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityTwo.this);
        View mView = getLayoutInflater().inflate(R.layout.busyumbrelladialog, null);

        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onClick(View v) {

    }
}

