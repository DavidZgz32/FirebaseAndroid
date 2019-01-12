package com.example.david.conecta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Filters extends AppCompatActivity {

    EditText palabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        palabra = findViewById(R.id.editTextPalabra);

    }
}
