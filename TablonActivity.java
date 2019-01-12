package com.example.david.conecta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class TablonActivity extends AppCompatActivity{

    //Toolbar
    private Toolbar toolbar;

    //usuers auth
    FirebaseAuth mAuth;


    //list
    private RecyclerView mainList;
    private FirebaseFirestore mFirestore;
    private UsersListAdapter usersListAdapter;
    //List error
    private static final String TAG = "Firelog";
    //List of messages
    private List<Mensajes> mensajesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablon);



        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //Users
        mAuth = FirebaseAuth.getInstance();

        //Messages List
        mensajesList = new ArrayList<>();
        usersListAdapter = new UsersListAdapter(mensajesList);

        //RecyclerView list
        mainList = findViewById(R.id.main_list);
        mainList.setHasFixedSize(true);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        mainList.setAdapter(usersListAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("mensajes").orderBy("fecha", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if( e != null){
                    Log.d(TAG, "Error: "+ e.getMessage());
                }

                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){

                    if(doc.getType() == DocumentChange.Type.ADDED){

                        //userid
                        //String userId = doc.getDocument().getId();

                        Mensajes mensajes = doc.getDocument().toObject(Mensajes.class);
                        mensajesList.add(mensajes);

                        usersListAdapter.notifyDataSetChanged();
                    }

                }

            }
        });


        //Button AddMessage
        Button mbutton = findViewById(R.id.buttonMensaje);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String name = currentUser.getEmail();

                if(name == null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "ES ANONIMO", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent intent = new Intent(TablonActivity.this, AddMessage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu,menu);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FirebaseUser currentUser = mAuth.getCurrentUser();
        String name = currentUser.getEmail();

        switch (item.getItemId()) {
            case R.id.action_profile:
                if (name == null){
                    Toast toast = Toast.makeText(getApplicationContext(), "ES ANONIMO", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "NO ES ANONIMO"+name, Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;

            case R.id.action_messages:

                if (name == null){
                    Toast toast = Toast.makeText(getApplicationContext(), "ES ANONIMO", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "NO ES ANONIMO"+name, Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;

            case R.id.action_filter:

                if (name == null){
                    Toast toast = Toast.makeText(getApplicationContext(), "ES ANONIMO", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent intent = new Intent(TablonActivity.this, Filters.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                return true;

            case R.id.buttonMensaje:

                if (name == null){
                    Toast toast = Toast.makeText(getApplicationContext(), "ES ANONIMO", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent intent = new Intent(TablonActivity.this, AddMessage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                return true;



            default:

                return super.onOptionsItemSelected(item);
        }

    }


}
