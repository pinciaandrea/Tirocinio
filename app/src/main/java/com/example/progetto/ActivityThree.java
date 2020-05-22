package com.example.progetto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progetto.Database.Model.Note;
import com.example.progetto.Utils.MyDividerItemDecoration;
import com.example.progetto.Utils.NotesAdapter;
import com.example.progetto.Utils.VerticalSpaceItemDecoration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
        bottomNavigationView.setSelectedItemId(R.id.action_recensioni);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent intent1 = new Intent(ActivityThree.this, ActivityOne.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_prenotazione:
                        Intent intent2 = new Intent(ActivityThree.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_profilo:
                        Intent intent4 = new Intent(ActivityThree.this,MainActivity.class);
                        startActivity(intent4);
                }
                return false;
            }
        });

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);
        ratingBar = findViewById(R.id.rating_bar);

        FloatingActionButton fab = findViewById(R.id.fab);

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
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration
                (this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);
        LoadDataFromdatabase();

    }

    public void createNewReview (String note, float rating, int position){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String userID = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        Map<String,Object> review_data = new HashMap<>();
        review_data.put("testo",note);
        review_data.put("userID",userID);
        review_data.put("valutazione",rating);
        review_data.put("posizione",position);

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
        mAdapter.update(notesList);
        notesList.clear();
        LoadDataFromdatabase();
    }

    private void LoadDataFromdatabase(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Recensioni").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot querySnapshot : task.getResult()){
                    Note n = new Note(querySnapshot.getString("testo"), querySnapshot.getString("userID"), querySnapshot.getLong("valutazione"));
                    notesList.add(n);
                    notesList.size();
                    if(notesList.size() == 0){

                    } else {
                        noNotesView.setVisibility(View.INVISIBLE);
                    }
                }
                mAdapter = new NotesAdapter(ActivityThree.this, notesList);
                recyclerView.setAdapter(mAdapter);
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
                        createNewReview(inputNote.getText().toString(),rating1,position);
                        dialogBox.dismiss();
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
