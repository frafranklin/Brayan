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

public class Producto extends AppCompatActivity {
    RecyclerView rv1;
    String[] nombres = {"audifonos", "Cable sata", "CPU", "laptop", "mouse", "mochila", "Tony Cabrejo", "Manuel Lesbi"};
    String[] Precio = {"s/20", "s/19","s/ 14", "s/25","s/ 33", "s/15", "s/29", "s/27"};
    int[] stock = {20, 19, 14, 25, 33, 15, 29, 27};
    int[] fotos = {R.drawable.audifonos, R.drawable.cabesal, R.drawable.cpu, R.drawable.laptop, R.drawable.mause, R.drawable.mochila};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto);

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
            return new AdaptadorPersonaHolder(getLayoutInflater().inflate(R.layout.loyout_card, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorPersonaHolder holder, int position) {
            if (position < nombres.length && position < Precio.length && position < fotos.length) {
                holder.imprimir(position);
            }
        }

        @Override
        public int getItemCount() {
            return Math.min(nombres.length, Math.min(Precio.length, fotos.length));
        }

        class AdaptadorPersonaHolder extends RecyclerView.ViewHolder {
            ImageView iv1;
            TextView tv1, tv2, tv3;

            public AdaptadorPersonaHolder(@NonNull View itemView) {
                super(itemView);
                iv1 = itemView.findViewById(R.id.imageView3);
                tv1 = itemView.findViewById(R.id.tvnombre);
                tv2 = itemView.findViewById(R.id.tvedad);
                tv3 = itemView.findViewById(R.id.tvstock);
            }

            public void imprimir(int position) {
                iv1.setImageResource(fotos[position]);
                tv1.setText(nombres[position]);
                tv2.setText("Precio: " + Precio[position]);
                tv3.setText("Stock: " + stock[position]);
            }
        }
    }
}
