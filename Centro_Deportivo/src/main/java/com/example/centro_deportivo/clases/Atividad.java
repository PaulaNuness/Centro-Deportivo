package com.example.centro_deportivo.clases;

import java.util.ArrayList;

public class Atividad {
    private String nombre;
    private int capacidad_Maxima;
    private ArrayList<Cliente> clientesInscritos;

    public Atividad(String nombre, int maximo) {
        this.nombre = nombre;
        this.capacidad_Maxima = maximo;
        this.clientesInscritos = new ArrayList<>();
    }
}
