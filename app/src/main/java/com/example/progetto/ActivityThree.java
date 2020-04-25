package com.example.progetto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progetto.Database.Model.Note;
import com.example.progetto.Utils.MyDividerItemDecoration;
import com.example.progetto.Utils.NotesAdapter;
import com.example.progetto.Utils.RecyclerTouchListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ActivityThree extends AppCompatActivity
    implements View.OnClickListener {

    private NotesAdapter mAdapter;
    private List<Note> notesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_informazioni:
                        Intent intent1 = new Intent(ActivityThree.this, ActivityOne.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_prenotazione:
                        Intent intent2 = new Intent(ActivityThree.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
        Button bhome3= (Button) findViewById(R.id.button3);
        bhome3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(ActivityThree.this, MainActivity.class);
                startActivity(intent6);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);
        ratingBar = findViewById(R.id.rating_bar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNoteDialog(null, -1,0);
                }
            });
        } else {
            Toast.makeText(this,"Per inserire una recensione devi aver effettuato il login",Toast.LENGTH_SHORT).show();
        }

        mAdapter = new NotesAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration
                (this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public void createNewReview (String note, float rating){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String,Object> review_data = new HashMap<>();
        review_data.put("testo",note);
        review_data.put("userID",userID);
        review_data.put("valutazione",rating);
        //Note notes = new Note();
        //notes.setNote(note);
        //notes.setUser_id(userID);
        //notes.setValue(value);

        db.collection("Recensioni").add(review_data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                if(documentReference != null){
                    Toast.makeText(ActivityThree.this,"Inserimento riuscito",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityThree.this, "Inserimento non riuscito", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showNoteDialog(final Note note, final int position, final float rating) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(ActivityThree.this);
        alertDialogBuilderUserInput.setView(view);

        //final RatingBar ratingBar = new RatingBar(this);
        final RatingBar ratingBar1 = view.findViewById(R.id.rating_bar);
        final EditText inputNote = view.findViewById(R.id.note);
        //TextView dialogTitle = view.findViewById(R.id.dialog_title);

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Salva", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        float rating1 = ratingBar1.getRating();
                        dialogBox.dismiss();
                        createNewReview(inputNote.getText().toString(),rating1);
                    }
                })
                .setNegativeButton("Cancella",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
/*
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(inputNote.getText().toString())) {
                    Toast.makeText(ActivityThree.this, "Inserisci la tua review!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }
                if ( note != null) {
                    createNewReview(inputNote.getText().toString());
                }
            }

        });
        */
    }
/*
    private void toggleEmptyNotes() {
        if (db.getNotesCount() > 0) {
            noNotesView.setVisibility(View.GONE);
        } else {
            noNotesView.setVisibility(View.VISIBLE);
        }
    }
*/
    @Override
    public void onClick(View v) {

    }
}
