package com.example.recibos;

import java.text.DecimalFormat;

public class Recibo {

    public Integer numero_recibo;
    public String tipo_recibo;
    public String direccion;
    public String medida;
    public String valor;
    public String fecha;
    DecimalFormat formato = new DecimalFormat("#.##");

    public Recibo() {
    }

    public Recibo(Integer numero_recibo, String tipo_recibo, String direccion, String medida, String valor, String fecha) {
        this.numero_recibo = numero_recibo;
        this.tipo_recibo = tipo_recibo;
        this.direccion = direccion;
        this.medida = medida;
        this.valor = valor;
        this.fecha = fecha;
    }

    public Integer getNumero_recibo() {
        return numero_recibo;
    }

    public void setNumero_recibo(Integer numero_recibo) {
        this.numero_recibo = numero_recibo;
    }

    public String getTipo_recibo() {
        return tipo_recibo;
    }

    public void setTipo_recibo(String tipo_recibo) {
        this.tipo_recibo = tipo_recibo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Recibo{" +
                "numero_recibo='" + numero_recibo + '\'' +
                ", tipo_recibo='" + tipo_recibo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", medida='" + medida + '\'' +
                ", valor='" + valor + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

}
