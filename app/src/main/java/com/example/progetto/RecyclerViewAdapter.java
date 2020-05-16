package com.example.progetto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder2> {


    private List<Umbrella_obj> umbrellalist;
    ActivityTwo activityTwo;

    public RecyclerViewAdapter(ActivityTwo activityTwo, List<Umbrella_obj> umbrellalist){
        this.activityTwo = activityTwo;
        this.umbrellalist = umbrellalist;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        MyViewHolder2 myViewHolder2 = new MyViewHolder2(view);
        return myViewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder2 holder, final int position) {
         final Umbrella_obj umbrella_obj = umbrellalist.get(position);
         if(umbrella_obj.getPrenotato()){
             holder.imageView.setImageResource(R.drawable.umbrella_busy);
         } else {
             holder.imageView.setImageResource(R.drawable.umbrella_free);
         }

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                 if(user != null){
                     if(umbrella_obj.getPrenotato()){
                         Toast.makeText(activityTwo," Ombrellone Prenotato",Toast.LENGTH_SHORT).show();
                     } else {
                         LayoutInflater inflater= activityTwo.getLayoutInflater();

                         final AlertDialog.Builder alert = new AlertDialog.Builder(activityTwo);
                         View mView = inflater.inflate(R.layout.freeumbrelladialog, null);

                         Button btn_cancel =  mView.findViewById(R.id.btn_cancel);
                         Button btn_confirm = mView.findViewById(R.id.btn_confirm);

                         alert.setView(mView);

                         final AlertDialog alertDialog = alert.create();
                         alertDialog.setCanceledOnTouchOutside(false);

                         alertDialog.show();

                         btn_cancel.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 Toast.makeText(activityTwo,"Prenotazione annullata",Toast.LENGTH_SHORT).show();
                                 alertDialog.dismiss();
                             }
                         });

                         btn_confirm.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 FirebaseFirestore db = FirebaseFirestore.getInstance();
                                 DocumentReference umbrellareference = db.collection("Ombrelloni").document(umbrellalist.get(position).getId());
                                 umbrellareference.update("prenotato", true);
                                 umbrellalist.get(position).setPrenotato(true);
                                 notifyDataSetChanged();
                                 Toast.makeText(activityTwo,"Penotazione Effettuata",Toast.LENGTH_SHORT).show();
                                 alertDialog.dismiss();
                             }
                         });
                     }
                 } else {
                        Toast.makeText(activityTwo,"Per prenotare un ombrellone devi essere autenticato",Toast.LENGTH_SHORT).show();
                 }

             }
         });

    }

    @Override
    public int getItemCount() {
        return umbrellalist.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewOmb);

        }

    }

}
