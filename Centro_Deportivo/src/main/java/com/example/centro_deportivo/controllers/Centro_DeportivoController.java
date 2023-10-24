package com.example.centro_deportivo.controllers;

import com.example.centro_deportivo.BBDD.Conexion_Base_de_Datos;
import com.example.centro_deportivo.Centro_DeportivoApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class Centro_DeportivoController {

    @FXML
    private Button botton_entrar;

    @FXML
    private Button botton_registrar;

    @FXML
    private TextField texto_email;

    @FXML
    private TextField texto_contrasena;

    @FXML
    private Button botton_limpiar_datos;

    @FXML
    private Button botton_salir;

    public static int id_usuario;
    public static String email;

    public static int id_usuario() {
        return id_usuario;
    }

    public static String getEmail() {
        return email;
    }

    @FXML
    void Entrar(ActionEvent event) {
        try{
            Conexion_Base_de_Datos.conectar();//chamei o metodo que tenho na classe ConexionBBDD
            System.out.println("conectado?"+Conexion_Base_de_Datos.estadoConexion());//quero mostrar no terminar o estado da conexao
            if(!texto_email.getText().isEmpty() && !texto_contrasena.getText().isEmpty() ){//se o texto_usuario  e o texto_contraseña nao estao vazios
                if (Conexion_Base_de_Datos.conectar()) {//se hay conexao com mysql
                    if (Conexion_Base_de_Datos.usuario_existe(texto_email.getText(), texto_contrasena.getText()) == 0) {//se  email y contraseña nao estao corretos
                        Alert alert =new Alert(Alert.AlertType.ERROR);//criar alert de error
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Usuario no registrado");
                        alert.setContentText("El usuario que ha introducido no existe.");
                        alert.showAndWait();
                    } else if (Conexion_Base_de_Datos.usuario_existe(texto_email.getText(), texto_contrasena.getText()) == 1) {//se somente o email esta correto
                        Alert alert =new Alert(Alert.AlertType.ERROR);//criar alert de error
                        alert.setTitle("Contraseña Incorrecta");
                        alert.setContentText("La contraseña introducida es erronea.");
                        alert.showAndWait();
                    } else if (Conexion_Base_de_Datos.usuario_existe(texto_email.getText(), texto_contrasena.getText()) == 2) {//se email e contraseña estao corretos
                        if(texto_email.getText().equals("admin")){//se o email for admin, abre a pantalla admin
                            FXMLLoader fxmlLoader = new FXMLLoader(Centro_DeportivoApplication.class.getResource("pantalla_admin.fxml"));//quando eu clicar en entrar, iremos para a ventana seguinte
                            Parent root = fxmlLoader.load();
                            Pantalla_AdminController controlador2 = fxmlLoader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
                            stage.setScene(scene);
                            stage.show();
                        }else{//se nao for admin, a a pantalla de usuario
                            email=texto_email.getText();
                            FXMLLoader fxmlLoader = new FXMLLoader(Centro_DeportivoApplication.class.getResource("pantalla_usuario.fxml"));//quando eu clicar en entrar, iremos para a ventana seguinte
                            Parent root = fxmlLoader.load();
                            Pantalla_UsuarioController controlador = fxmlLoader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                }else {//se nao tiver conexao com mysql
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("No conectado");
                    alert.setContentText("No hay conexion con el servidor.");
                    alert.showAndWait();
                }
            }else{//se texto usuario e texto contraseña estao vazios
                Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("ERROR!!!");//com o titulo
                alert.setContentText("Rellenar usuario y contraseña");
                alert.show();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *Cuando pulso en el botton Limpiar_datos, los datos que tengo en los textos email y contraseña son borrados
     * @param event parametro comunes de eventos en Javafx, representa el evento que acionou el metodo
     */
    @FXML
    void Limpiar_Datos(ActionEvent event) {
        try{
            texto_email.clear();
            texto_contrasena.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Caundo pulso en el botton Registrar, el usuario que no tiene cadastro,puede registrarse y tener acesso a la aplicacion
     * @param event parametro comunes de eventos en Javafx, representa el evento que acionou el metodo
     */
    @FXML
    void Registrar(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Centro_DeportivoApplication.class.getResource("pantalla_registrar.fxml"));
            Parent root = fxmlLoader.load();
            Pantalla_RegistrarController con3 = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
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



