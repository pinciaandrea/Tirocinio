package com.example.progetto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Login;
    private Button aggiungiUtentePost;
    private CustomDialog customDialog;
    private CustomDialogLogin customDialogLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name","0");
        if(name.equals("0")) {
            Login = findViewById(R.id.login);
            aggiungiUtentePost = findViewById(R.id.aggiungi_utente_post);
            Login.setOnClickListener(this);
            aggiungiUtentePost.setOnClickListener(this);
        }
        else{
            findViewById(R.id.login).setVisibility(View.INVISIBLE);
            findViewById(R.id.aggiungi_utente_post).setVisibility(View.INVISIBLE);
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_informazioni:
                        Intent intent1 = new Intent(MainActivity.this, ActivityOne.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_prenotazione:
                        Intent intent2 = new Intent(MainActivity.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_recensioni:
                        Intent intent3 = new Intent(MainActivity.this, ActivityThree.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

    }


    private JSONObject convert2JSON(String json_data) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(json_data);
            Log.d("My App", obj.toString());
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + json_data + "\"");
        }
        return obj;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                customDialogLogin = new CustomDialogLogin(this, true);
                customDialogLogin.show();
                break;

            case R.id.aggiungi_utente_post:
                customDialog = new CustomDialog(this, false);
                customDialog.show();
                break;
        }


    }
}