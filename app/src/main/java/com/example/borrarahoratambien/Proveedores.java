package com.example.borrarahoratambien;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Proveedores extends AppCompatActivity {
    RecyclerView rv1;
    String[] nombres = {"Marco Polo", "Roberto Macetas", "Jose Carlos", "Miranda Cabrera", "Soledad Antunez", "Mela Gomez", "Tony Cabrejo", "Manuel Lesbi"};
    String[] empresa = {"softstore", "TronosMarket", "SofiaStore", "Sociedad Anonima", "Soledad Antunez", "hardProyect", "SofiaTech", "rocatech"};
    int[] Pedidos = {20, 19, 14, 25, 33, 15, 29, 27};
    int[] fotos = {R.drawable.persona1, R.drawable.persona2, R.drawable.persona3, R.drawable.persona4, R.drawable.persona5, R.drawable.persona6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proveedores);

        rv1 = findViewById(R.id.rv1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(linearLayoutManager);
        rv1.setAdapter(new AdaptadorPersona());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.AdaptadorPersonaHolder> {
        @NonNull
        @Override
        public AdaptadorPersonaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorPersonaHolder(getLayoutInflater().inflate(R.layout.layout_card, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorPersonaHolder holder, int position) {
            if (position < nombres.length && position < Pedidos.length && position < fotos.length) {
                holder.imprimir(position);
            }
        }

        @Override
        public int getItemCount() {
            return Math.min(nombres.length, Math.min(Pedidos.length, fotos.length));
        }

        class AdaptadorPersonaHolder extends RecyclerView.ViewHolder {
            ImageView iv1;
            TextView tv1, tv2, tv3;

            public AdaptadorPersonaHolder(@NonNull View itemView) {
                super(itemView);
                iv1 = itemView.findViewById(R.id.imageView3);
                tv1 = itemView.findViewById(R.id.tvnombre);
                tv2 = itemView.findViewById(R.id.tvedad);
                tv3 = itemView.findViewById(R.id.tvempresa);
            }

            public void imprimir(int position) {
                iv1.setImageResource(fotos[position]);
                tv1.setText(nombres[position]);
                tv2.setText("Pedidos: " + Pedidos[position]);
                tv3.setText("Empresa: " + empresa[position]);
            }
        }
    }
}
