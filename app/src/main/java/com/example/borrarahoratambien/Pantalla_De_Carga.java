package com.example.borrarahoratambien;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Pantalla_De_Carga extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_carga);

        firebaseAuth = FirebaseAuth.getInstance();

        int tiempo = 3000; // Tiempo en milisegundos (3 segundos)
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                verificarUsuario();
            }
        }, tiempo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void verificarUsuario() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            startActivity(new Intent(Pantalla_De_Carga.this, MainActivity.class));
        } else {
            // Si el usuario está autenticado, redirigir a otra actividad, por ejemplo:
            startActivity(new Intent(Pantalla_De_Carga.this, MenuPrincipal.class));
        }
        finish(); // Finalizar esta actividad para que no vuelva a la pantalla de carga al presionar atrás
    }
}
