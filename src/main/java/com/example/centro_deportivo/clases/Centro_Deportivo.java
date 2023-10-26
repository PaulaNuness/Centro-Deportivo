package com.example.centro_deportivo.clases;

import java.util.ArrayList;

/**
 * La case Centro_Deportivo fue criada para describir todo que tenemos en el centro
 * la cantidad de salas, los monitores que hay, los clientes que hay y las actividades qye hay
 */
public class Centro_Deportivo {
    private static int cantidadSalas;
    private static ArrayList<Monitor> monitores;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Atividad> actividades;

    public Centro_Deportivo() {
    }

    public int getCantidadSalas() {
        return cantidadSalas;
    }

    public static void setCantidadSalas(int cantidadSalas) {
        cantidadSalas = cantidadSalas;
    }

    public static ArrayList<Monitor> getMonitores() {
        return monitores;
    }

    public static void setMonitores(ArrayList<Monitor> m) {
        monitores = m;
    }

    public static  ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Cliente> c) {
        clientes = c;
    }

    public static ArrayList<Atividad> getActividades() {
        return actividades;
    }

    public static void setActividades(ArrayList<Atividad> a) {
        actividades = a;
    }


    public static String datos() {
        return "En el Centro_Deportivo tenemos:\n" +cantidadSalas+" salas\n"+"Los monitores:"+ monitores +"\nLos clientes:"+clientes+"\nLas atividades:"+actividades;
    }
}
