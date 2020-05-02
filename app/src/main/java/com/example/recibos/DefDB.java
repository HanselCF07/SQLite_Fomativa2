package com.example.recibos;

public class DefDB {

    public static final String nameDb = "Servicios";
    public static final String tabla_Recibos = "Recibos";
    public static final String col_numero_recibo = "numero_recibo";
    public static final String col_tipo_recibo = "tipo_recibo";
    public static final String col_direccion = "direccion";
    public static final String col_medida = "medida";
    public static final String col_valor = "valor";
    public static final String col_fecha = "fecha";

    public static final String create_tabla_recibos = "CREATE TABLE IF NOT EXISTS recibos(" +
            "  numero_recibo INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  tipo_recibo TEXT," +
            "  direccion TEXT," +
            "  medida TEXT," +
            "  valor TEXT," +
            "  fecha TEXT" +
            ");";

}
