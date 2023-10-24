package com.example.centro_deportivo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Pantalla_ClientesController {

    @FXML
    private Button botton_salir;

    /**
     * Cuando pulso en el botton Salir, sale un alert que pide para el usuario confirmar si quiere salir,hay dos opiciones, OK(cerrar la ventana) y Cancelar(continuamos en la ventana)
     * @param event parametro comunes de eventos en Javafx, representa el evento que acionou el metodo
     */
    @FXML
    void Salir(ActionEvent event) {
        try{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cerrando...");
            alert.setContentText("Quieres Salir?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()== ButtonType.OK){
                Stage stagePrincipal=(Stage) botton_salir.getScene().getWindow();
                stagePrincipal.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
