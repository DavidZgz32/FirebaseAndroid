package com.example.david.conecta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddMessage extends AppCompatActivity {

    private Button mGuardarButton;
    private EditText edtMensaje, edtCategoria;

    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        mFirestore = FirebaseFirestore.getInstance();

        mGuardarButton = findViewById(R.id.btnEnviarMensaje);
        edtMensaje = findViewById(R.id.editTextMensaje);
        edtCategoria = findViewById(R.id.editTextCategoria);

        mGuardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mensaje = edtMensaje.getText().toString();
                String categoria = edtCategoria.getText().toString();
                Date currentTime = Calendar.getInstance().getTime();

                //Map
                Map<String,String> userMap = new HashMap<>();

                userMap.put("mensaje", mensaje);
                userMap.put("fecha", String.valueOf(currentTime));
                userMap.put("categoria",categoria);

                mFirestore.collection("mensajes").add(userMap);

            }
        });
    }
}
