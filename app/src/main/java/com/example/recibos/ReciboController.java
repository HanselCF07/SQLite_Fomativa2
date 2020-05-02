package com.example.recibos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReciboController {

    private BaseDatos db;

    public ReciboController(Context context) {
        this.db = new BaseDatos(context);
    }

    public long agregarRecibo(Recibo rec){
        try{

            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();

            valores.put(DefDB.col_numero_recibo, rec.numero_recibo);
            valores.put(DefDB.col_tipo_recibo, rec.tipo_recibo);
            valores.put(DefDB.col_direccion, rec.direccion);
            valores.put(DefDB.col_medida, rec.medida);
            valores.put(DefDB.col_valor, rec.valor);
            valores.put(DefDB.col_fecha, rec.fecha);
            long id = database.insert(DefDB.tabla_Recibos,null,valores);
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al insertar");
            return 0;
        }
    }

    public boolean buscarRecibo(String num_recibo){
        SQLiteDatabase database = db.getReadableDatabase();
        String[] args ={num_recibo};

        Cursor c = database.query(DefDB.tabla_Recibos, null, "numero_recibo=?", args, null, null, null);

        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;
        }

    }

    public long eliminarRecibo(String num_recibo){
        SQLiteDatabase database = db.getWritableDatabase();
        String[] args ={num_recibo};

        long id = database.delete(DefDB.tabla_Recibos,"numero_recibo=?",args);
        database.close();

        return id;
    }

    public long actualizarRecibo(Recibo rec){
        try{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();

            String numero_recibo = String.valueOf(rec.numero_recibo);
            String[] args ={numero_recibo};

            valores.put(DefDB.col_tipo_recibo, rec.tipo_recibo);
            valores.put(DefDB.col_direccion, rec.direccion);
            valores.put(DefDB.col_medida, rec.medida);
            valores.put(DefDB.col_valor, rec.valor);

            long id = database.update(DefDB.tabla_Recibos, valores,"numero_recibo=?",args);
            System.out.println(id);
            System.out.println(numero_recibo);
            System.out.println(args);
            System.out.println(valores);
            database.close();

            return id;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return 0;
        }

    }
    public Cursor allRecibos() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur = database.rawQuery("select numero_recibo as _id , tipo_recibo, direccion, medida, valor, fecha from Recibos", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }
}