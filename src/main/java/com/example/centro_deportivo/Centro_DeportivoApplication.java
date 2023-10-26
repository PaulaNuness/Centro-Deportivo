package com.example.centro_deportivo;

import com.example.centro_deportivo.BBDD.Conexion_Base_de_Datos;
import com.example.centro_deportivo.clases.Centro_Deportivo;
import com.example.centro_deportivo.controllers.Centro_DeportivoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class Centro_DeportivoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Centro_DeportivoApplication.class.getResource("inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        Conexion_Base_de_Datos.actualizar_cliente("paulaa@hotmail.com");
       //launch();

    }
}