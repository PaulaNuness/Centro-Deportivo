package com.example.centro_deportivo.controllers;

import com.example.centro_deportivo.BBDD.Conexion_Base_de_Datos;
import com.example.centro_deportivo.clases.Cliente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class Pantalla_ClientesController implements Initializable {

    @FXML
    private Button botton_salir;
    @FXML
    private TextField texto_email;

    @FXML
    private ListView<String> lista_clientes;
    ArrayList<String> lista_emails = Conexion_Base_de_Datos.obteneremailsUsuario();


    @FXML
    private Button botton_actualiza_datos;

    @FXML
    private Button botton_eliminar_cliente;

    @FXML
    private ComboBox<String> combobox_tipo_de_cliente;
    String[] tipos = {"Premium", "Regular", "Ocasional"};

    @FXML
    private ComboBox<String> combobox_falta;
    String[] si_no = {"Si", "No"};

    @FXML
    private ComboBox<String> combobox_pago;

    @FXML
    private TextField texto_nombre_apellido;
    @FXML
    private Button botton_nuevo_cliente;

    @FXML
    private Button botoon_ver_datos;

    public Pantalla_ClientesController() throws SQLException, IOException, ClassNotFoundException {

    }

    @FXML
    void Actualizar_datos(ActionEvent event) {
        try {
            String em = texto_email.getText();
            if (lista_clientes.getSelectionModel().getSelectedItem() != null) {
                Conexion_Base_de_Datos.actualizar_cliente(texto_email.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("OK!!!");//com o titulo
                alert.setContentText("Cliente actualizado");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("Eliges un cliente!!!");//com o titulo
                alert.setContentText("Hay que eligir un cliente");
                alert.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Nuevo_cliente(ActionEvent event) {
        try {
            texto_email.clear();
            texto_nombre_apellido.clear();
            combobox_pago.setValue(null);
            combobox_falta.setValue(null);
            combobox_tipo_de_cliente.setValue(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
            alert.setTitle("Confirmado!!!");//com o titulo
            alert.setContentText("Limpiamos los campos arriba");
            alert.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Dar_de_alta(ActionEvent event) {
        String em = texto_email.getText();
        String n = texto_nombre_apellido.getText();
        String t = combobox_tipo_de_cliente.getValue();
        String pa = combobox_pago.getValue();
        String pe = combobox_falta.getValue();
        if (Conexion_Base_de_Datos.email_existe(em)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
            alert.setTitle("Email ya existe!!!");//com o titulo
            alert.setContentText("No puedes dar de alta, ya existe el email");
            alert.show();
        } else {
            Conexion_Base_de_Datos.insertar_cliente(em, n, t, pa, pe);
        }
    }
    @FXML
    void seleccionarCliente(Event event) {
        try {
            if (lista_clientes.getSelectionModel().getSelectedItem() != null) {
                texto_email.setText(lista_clientes.getSelectionModel().getSelectedItem());
                String no = lista_clientes.getSelectionModel().getSelectedItem();
                texto_nombre_apellido.setText(Conexion_Base_de_Datos.obtener_nombre_Usuario(no));
                combobox_tipo_de_cliente.setValue(Conexion_Base_de_Datos.obtener_tipo_Usuario(no));
                combobox_pago.setValue(Conexion_Base_de_Datos.obtener_Pago_Usuario(no));
                combobox_falta.setValue(Conexion_Base_de_Datos.obtener_Falta_Usuario(no));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Eliminar_cliente(ActionEvent event) {
        try {
            String em = texto_email.getText();
            if (lista_clientes.getSelectionModel().getSelectedItem() != null) {
                Conexion_Base_de_Datos.eliminar_cliente(texto_email.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("OK!!!");//com o titulo
                alert.setContentText("Cliente eliminado");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("Eliges un cliente!!!");//com o titulo
                alert.setContentText("Hay que eligir un cliente");
                alert.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void Salir(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cerrando...");
            alert.setContentText("Quieres Salir?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stagePrincipal = (Stage) botton_salir.getScene().getWindow();
                stagePrincipal.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox_tipo_de_cliente.getItems().addAll(tipos);
        combobox_falta.getItems().addAll(si_no);
        combobox_pago.getItems().addAll(si_no);
        lista_clientes.getItems().addAll(lista_emails);

    }
}
