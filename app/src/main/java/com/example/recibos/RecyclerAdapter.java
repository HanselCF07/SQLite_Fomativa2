package com.example.recibos;

import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private Cursor mCursor;
    public  String numero_recibo;
    DecimalFormat formato = new DecimalFormat("#.##");


    public RecyclerAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        numero_recibo = mCursor.getString(mCursor.getColumnIndexOrThrow("_id"));
        String tipo_recibo = mCursor.getString(mCursor.getColumnIndexOrThrow("tipo_recibo"));
        String direccion= mCursor.getString(mCursor.getColumnIndexOrThrow("direccion"));
        String medida= mCursor.getString(mCursor.getColumnIndexOrThrow("medida"));
        String valor= mCursor.getString(mCursor.getColumnIndexOrThrow("valor"));
        String fecha= mCursor.getString(mCursor.getColumnIndexOrThrow("fecha"));

        //numero_recibo = "N° Recibo: "+numero_recibo+"";
        String informacion = "Dirección: "+direccion+" \nFecha: "+fecha+" \nMedida: "+medida+" \nValor: "+valor+"";
        holder.tv_tipo_recibo.setText(tipo_recibo);
        holder.tv_numero_recibo.setText(numero_recibo);
        holder.tv_informacion.setText(informacion);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public String NumeroRecibo() {
        return numero_recibo = mCursor.getString(mCursor.getColumnIndexOrThrow("_id"));
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView tv_tipo_recibo, tv_numero_recibo, tv_informacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tv_tipo_recibo = itemView.findViewById(R.id.txt_tipo_recibo);
            tv_numero_recibo = itemView.findViewById(R.id.txt_numero_recibo);
            tv_informacion = itemView.findViewById(R.id.txt_informacion);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(view.getContext(), "Recibo # "+tv_numero_recibo.getText()+"", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Desliza a la izquierda para Eliminar", Toast.LENGTH_SHORT).show();
        }
    }




}















