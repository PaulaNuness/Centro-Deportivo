package com.example.centro_deportivo.controllers;

import com.example.centro_deportivo.BBDD.Conexion_Base_de_Datos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Optional;

public class Pantalla_UsuarioController {

    @FXML
    private Button botton_salir;
    @FXML
    private Button botton_ver_datos;

    @FXML
    private TextArea texto_area;
    @FXML
    private Button bottonEnviar;

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

    /**
     * metodo que muestra en la area de texto los datos del usuario que hizo la entrada
     * @param event
     * @throws SQLException
     */
    @FXML
    void VerDatos(ActionEvent event) throws SQLException {
        try{
            int id=Conexion_Base_de_Datos.obtenerIDUsuario(Centro_DeportivoController.getEmail());
            texto_area.setText(Conexion_Base_de_Datos.obtener_Datos_Usuario(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnviarEmail(ActionEvent event) {
        if(java.awt.Desktop.isDesktopSupported()){
            Desktop desktop=java.awt.Desktop.getDesktop();
            try{
                URI url=new URI("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=16&ct=1698176973&rver=7.0.6738.0&wp=MBI_SSL&wreply=https%3a%2f%2foutlook.live.com%2fowa%2f%3fcobrandid%3dab0455a0-8d03-46b9-b18b-df2f57b9e44c%26nlp%3d1%26deeplink%3dowa%252f%26RpsCsrfState%3ddc97813f-a8c1-4f6c-9b03-8603f995c94c&id=292841&aadredir=1&CBCXT=out&lw=1&fl=dob%2cflname%2cwld&cobrandid=ab0455a0-8d03-46b9-b18b-df2f57b9e44c");
                desktop.browse(url);
            }catch (URISyntaxException | IOException ex){
                ex.printStackTrace();
            }
        }
    }

}

