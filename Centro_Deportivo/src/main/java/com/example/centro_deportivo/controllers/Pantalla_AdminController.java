package com.example.centro_deportivo.controllers;

import com.example.centro_deportivo.Centro_DeportivoApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class Pantalla_AdminController {

    @FXML
    private Button botton_salir;
    @FXML
    private Button botton_getionar_monitores;

    @FXML
    private Button botton_gestionar_clientes;

    @FXML
    private Button botton_ver_horarios;
    @FXML
    private TextArea text_area_horarios;

    @FXML
    void Gestionar_Monitores(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Centro_DeportivoApplication.class.getResource("pantalla_monitores.fxml"));
            Parent root = fxmlLoader.load();
            Pantalla_MonitoresController con4 = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void Gstionar_clientes(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Centro_DeportivoApplication.class.getResource("pantalla_clientes.fxml"));
            Parent root = fxmlLoader.load();
            Pantalla_ClientesController con5 = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void Ver_Horarios(ActionEvent event) {

    }

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
