package com.example.centro_deportivo.clases;

/**
 * La clase Administrador fue criada para decir en que Centro deportivo trabalha el administrador, y como
 * el atributo es un objeto de la clase Centro_deportivo,aqui podemos tener todas las informacoes el centro
 */
class Administrador {

    private Centro_Deportivo centro;

    public Administrador(Centro_Deportivo c) {
        centro = c;
    }

    public Centro_Deportivo getCentro() {
        return centro;
    }

    public void setCentro(Centro_Deportivo centro) {
        this.centro = centro;
    }

}


