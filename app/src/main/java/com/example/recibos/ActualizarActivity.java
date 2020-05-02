package com.example.recibos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActualizarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText tipo_recibo, direccion, medida, valor;
    Button actualizar;
    public RadioGroup selected;
    public RadioButton rbtAgua, rbtEnergia, rbtGas, rbtTelefono;
    java.util.Date fecha = new Date();
    public String numero_recibo_seleccionado;

    ReciboController rc;
    Recibo rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rc = new ReciboController(getApplicationContext());
        Cursor mCursor = rc.allRecibos();

        //tipo_recibo = findViewById(R.id.edt_tipo_recibo);
        rbtAgua = (RadioButton) findViewById(R.id.rbt_recibo_agua);
        rbtEnergia = (RadioButton) findViewById(R.id.rbt_recibo_energia);
        rbtGas = (RadioButton) findViewById(R.id.rbt_recibo_gas);
        rbtTelefono = (RadioButton) findViewById(R.id.rbt_recibo_telefono);

        direccion = findViewById(R.id.edt_direccion);
        medida = findViewById(R.id.edt_medida);
        valor = findViewById(R.id.edt_valor);
        actualizar = findViewById(R.id.btnactualizar);

        ArrayList<String> list = new ArrayList<>();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            list.add( mCursor.getString(mCursor.getColumnIndexOrThrow("_id"))); //add the item
            mCursor.moveToNext();
        }

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rec = new Recibo();

                rec.setNumero_recibo(Integer.parseInt(numero_recibo_seleccionado));
                rec.setTipo_recibo(Type(rbtAgua, rbtEnergia, rbtGas, rbtTelefono));
                rec.setDireccion(direccion.getText().toString());
                rec.setMedida(medida.getText().toString());
                rec.setValor(valor.getText().toString());
                /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fecha_String = sdf.format(new Date());
                rec.setFecha(fecha_String);*/

                long id = rc.actualizarRecibo(rec);
                Toast.makeText(getApplicationContext(), "Recibo actualizado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        numero_recibo_seleccionado = parent.getItemAtPosition(position).toString();
        Cursor mCursor = rc.allRecibos();

        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            if (mCursor.getString(mCursor.getColumnIndexOrThrow("_id")).equals(numero_recibo_seleccionado) ){
                if(mCursor.getString(mCursor.getColumnIndexOrThrow("tipo_recibo")).equals("AGUA")){
                    rbtAgua.setChecked(true);
                }
                if(mCursor.getString(mCursor.getColumnIndexOrThrow("tipo_recibo")).equals("ENERGIA")){
                    rbtEnergia.setChecked(true);
                }
                if(mCursor.getString(mCursor.getColumnIndexOrThrow("tipo_recibo")).equals("GAS")){
                    rbtGas.setChecked(true);
                }
                if(mCursor.getString(mCursor.getColumnIndexOrThrow("tipo_recibo")).equals("TELEFONIA")){
                    rbtTelefono.setChecked(true);
                }
                direccion.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("direccion")));
                medida.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("medida")));
                valor.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("valor")));
            }
            mCursor.moveToNext();
        }

        Toast.makeText(parent.getContext(), numero_recibo_seleccionado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String Type(RadioButton rbtAgua, RadioButton rbtEnergia, RadioButton rbtGas, RadioButton rbtTelefono){
        String data="";
        if(rbtAgua.isChecked()){
            data = "AGUA";
        }
        if(rbtEnergia.isChecked()){
            data = "ENERGIA";
        }
        if(rbtGas.isChecked()){
            data = "GAS";
        }
        if(rbtTelefono.isChecked()){
            data = "TELEFONIA";
        }
        return data;
    }

}
