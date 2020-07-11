package com.example.ejerciciofarmaciauser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder>{

    private static ClickListener clickListener;
    ArrayList<DatosVo> listaDatos = new ArrayList();

    public AdaptadorRecycler(ArrayList<DatosVo> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public AdaptadorRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personalizado, null, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycler.ViewHolder holder, int position) {

        holder.campo.setText(listaDatos.get(position).getDescripcion());
        holder.img.setImageResource(listaDatos.get(position).getImagen());

    }

    @Override
    public int getItemCount() {
        return this.listaDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView campo;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            campo = itemView.findViewById(R.id.txtDesM);
            img = itemView.findViewById(R.id.imgM);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public static void setOnItemClickListener(ClickListener clickListener){
        AdaptadorRecycler.clickListener = clickListener;
    }

    public interface  ClickListener{
        public void onItemClick(int position, View v);
    }

}
