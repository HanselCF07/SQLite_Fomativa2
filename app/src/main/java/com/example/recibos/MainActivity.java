package com.example.recibos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText tipo_recibo, direccion, medida, valor;
    Button guardar, listado;
    public RadioGroup selected;
    public RadioButton rbtAgua, rbtEnergia, rbtGas, rbtTelefono;
    java.util.Date fecha = new Date();
    //   Button actualizar, consultar;

    //Estudiante est;
    Recibo rec;
    //EstudianteController c;
    ReciboController c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //tipo_recibo = findViewById(R.id.edt_tipo_recibo);
        rbtAgua = (RadioButton) findViewById(R.id.rbt_recibo_agua);
        rbtEnergia = (RadioButton) findViewById(R.id.rbt_recibo_energia);
        rbtGas = (RadioButton) findViewById(R.id.rbt_recibo_gas);
        rbtTelefono = (RadioButton) findViewById(R.id.rbt_recibo_telefono);

        direccion = findViewById(R.id.edt_direccion);
        medida = findViewById(R.id.edt_medida);
        valor = findViewById(R.id.edt_valor);
        guardar = findViewById(R.id.btnguardar);

        c = new ReciboController(getApplicationContext());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rec = new Recibo();

                rec.setTipo_recibo(Type(rbtAgua, rbtEnergia, rbtGas, rbtTelefono));
                rec.setDireccion(direccion.getText().toString());
                rec.setMedida(medida.getText().toString());
                rec.setValor(valor.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fecha_String = sdf.format(new Date());
                rec.setFecha(fecha_String);

                long id = c.agregarRecibo(rec);
                Toast.makeText(getApplicationContext(), "Recibo registrado", Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        });

    }

    public void limpiarFormulario(){
        rbtAgua.setChecked(false);
        rbtEnergia.setChecked(false);
        rbtGas.setChecked(false);
        rbtTelefono.setChecked(false);
        direccion.setText("");
        medida.setText("");
        valor.setText("");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_listar) {
            Intent i = new Intent(getApplicationContext(), ListadoActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_actualizar) {
            Intent i = new Intent(getApplicationContext(), ActualizarActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
