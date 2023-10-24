package com.example.centro_deportivo.controllers;

import com.example.centro_deportivo.BBDD.Conexion_Base_de_Datos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pantalla_RegistrarController {

    @FXML
    private Button botton_ok;

    @FXML
    private Button botton_limpiar;

    @FXML
    private Button botton_salir;
    @FXML
    private TextField texto_email;

    @FXML
    private TextField texto_contrasena;

    /**
     *Cuando pulso en el botton Limpiar_datos, los datos que tengo en los textos email y contraseña son borrados
     * @param event parametro comunes de eventos en Javafx, representa el evento que acionou el metodo
     */
    @FXML
    void Limpiar(ActionEvent event) {
        try{
            texto_email.clear();
            texto_contrasena.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cuando pulso en el botton OK, nos registramos en la aplicacion y ya podemos tener acesso, los datos son añadidos a la base de datos
     * @param event parametro comunes de eventos en Javafx, representa el evento que acionou el metodo
     */
    @FXML
    void Ok(ActionEvent event) {
        try {
            String em = texto_email.getText();
            int numero = Conexion_Base_de_Datos.obtenerultimoIDUsuario();
            String cont = texto_contrasena.getText();
            Pattern pat = Pattern.compile("^[^@]{6}@hotmail.com$");//inicia a string,que teve ter 6 caracteres y  que nao pode contener @,seguido de @hotmail.com
            Matcher mat = pat.matcher(em);
            if (Conexion_Base_de_Datos.email_existe(em)) {//se el email ya existe
                Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("ERROR");//com o titulo
                alert.setContentText("Perdona este email ya existe");
                alert.show();
            }
            if (!Conexion_Base_de_Datos.email_existe(em)) {//se el email no existe
                if (mat.find()) {//se el email cumpli las normas
                    if (Conexion_Base_de_Datos.insertar_usuario(numero, em, cont)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                        alert.setTitle("Confirmado!!!");//com o titulo
                        alert.setContentText("Añadido los datos");
                        alert.show();
                    }
                }else {//se el email no cumple las normas
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                    alert.setTitle("Formato incorrecto!!!");//com o titulo
                    alert.setContentText("Email debe tener:\n 6 caracteres al inicio \n@hotmail.com\nejemplo: pepito@hotmail.com");
                    alert.show();
                }
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
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

