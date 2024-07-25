package com.example.borrarahoratambien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrincipal extends AppCompatActivity {

    private LinearLayout btn_nosotros, btn_producto,CerrarSesion,btn_provedores;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    private TextView NombresPrincipal, CorreoPrincipal;
    private ProgressBar progressBarDatos;

    private DatabaseReference usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btn_producto = findViewById(R.id.btn_producto);
        btn_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Producto.class));

            }
        });


        btn_provedores = findViewById(R.id.btn_provedores);
        btn_provedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Producto.class));

            }
        });


        btn_nosotros = findViewById(R.id.btn_nosotros);
        btn_nosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Nosotros.class));

            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("H&Q Tech");
        }

        NombresPrincipal = findViewById(R.id.NombresPrincipal);
        CorreoPrincipal = findViewById(R.id.CorreoPrincipal);
        progressBarDatos = findViewById(R.id.progressBarDatos);

        usuarios = FirebaseDatabase.getInstance().getReference("usuarios");

        CerrarSesion = findViewById(R.id.CerrarSesión); // Asegúrate de que este ID coincida con el definido en tu archivo XML.
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        CerrarSesion.setOnClickListener(view -> SalirAplicacion());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ComprobarInicioSesion();
    }

    private void ComprobarInicioSesion() {
        if (user != null) {
            // El usuario ha iniciado sesión
            CargaDeDatos();
        } else {
            // Lo dirigirá al MainActivity
            startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
            finish();
        }
    }

    private void CargaDeDatos() {
        usuarios.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Si el usuario existe
                if (snapshot.exists()) {
                    // El progressBar se oculta
                    progressBarDatos.setVisibility(View.GONE);
                    // Los textView se muestran
                    NombresPrincipal.setVisibility(View.VISIBLE);
                    CorreoPrincipal.setVisibility(View.VISIBLE);

                    // Obtener los datos
                    String nombres = "" + snapshot.child("nombres").getValue();
                    String correo = "" + snapshot.child("correo").getValue();

                    // Setear los datos en los respectivos TextView
                    NombresPrincipal.setText(nombres);
                    CorreoPrincipal.setText(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar el error aquí si es necesario
            }
        });
    }

    private void SalirAplicacion() {
        firebaseAuth.signOut();
        startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
        Toast.makeText(this, "Cerraste sesión exitosamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
