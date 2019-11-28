package com.example.progetto;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CustomDialog extends Dialog implements View.OnClickListener {

    private EditText editText_username;
    private EditText editText_password;
    private EditText editText_email;
    private Button cancelButton;
    private Button inserisciButton;
    public String username = null;
    public String password = null;
    public String email = null;
    private Context context;
    private Boolean typeRequest;


    public CustomDialog(Context context, Boolean type) {
        super(context);
        this.context = context;
        this.typeRequest = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        setTitle("Aggiungi utente");

        editText_username = findViewById(R.id.editText_username);
        editText_password = findViewById(R.id.editText_password);
        editText_email = findViewById(R.id.editText_email);
        cancelButton = findViewById(R.id.dialog_cancella);
        inserisciButton = findViewById(R.id.dialog_inserisci);
        cancelButton.setOnClickListener(this);
        inserisciButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.dialog_inserisci:
                username = editText_username.getText().toString();
                password = editText_password.getText().toString();
                email = editText_email.getText().toString();
                if(username.contains("=") || password.contains("=")){
                    Toast.makeText(context, "Alcuni caratteri non sono permessi", Toast.LENGTH_SHORT).show();
                }else {
                    make_request();
                }
                dismiss();
                break;

            case R.id.dialog_cancella:
                dismiss();
                break;
        }
    }

    private void make_request(){
        HttpURLConnection client = null;
        URL url;
        try {
            // se la richiesta è POST
            url = new URL("http://programmazionemobile.altervista.org/aggiungi_utente_post.php");
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);
            client.setDoInput(true);
            // write data in request
            OutputStream out = new BufferedOutputStream(client.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            String data = URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8");

            data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                    + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode(email, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            InputStream in = client.getInputStream();
            String json_string = ReadResponse.readStream(in).trim();

            if (json_string.equals("1")) {
                Toast.makeText(this.context, "Inserimento effettuato", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.context, "Errore nell'inserimento", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.disconnect();
            }
        }

    }
}
