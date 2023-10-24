package com.example.centro_deportivo.clases;

public class Cliente {
    private String nombre;
    private Tipo_Cliente tipo;
    private boolean pagoRealizado;
    private int penalizacionDias;

    public Cliente(String nombre, Tipo_Cliente tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.pagoRealizado = false;
        this.penalizacionDias = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo_Cliente getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_Cliente tipo) {
        this.tipo = tipo;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    public int getPenalizacionDias() {
        return penalizacionDias;
    }

    public void setPenalizacionDias(int penalizacionDias) {
        this.penalizacionDias = penalizacionDias;
    }

    @Override
    public String toString() {
        return "Datos del Cliente:\n" +
                "Nombre: " + nombre + "\n" +
                "Tipo:" + tipo +"\n" +
                "Pagado: " + pagoRealizado +"\n" +
                "Penalizacion" + penalizacionDias +" dias";
    }
}
