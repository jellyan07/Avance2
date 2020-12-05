package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.gestor.Gestor;

import java.sql.SQLException;

public class RegisterUsuario {

    private Gestor gestor = new Gestor();

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputApellido1;

    @FXML
    private TextField inputApellido2;

    @FXML
    private TextField inputCorreo;

    @FXML
    private TextField inputNombreUsuario;

    @FXML
    private TextField inputEdad;

    @FXML
    private TextField inputPais;

    @FXML
    private TextField inputPassword;

    @FXML
    private TextField inputImg;

    @FXML
    private Button registrarBtn;

    @FXML
    void registrar(ActionEvent event) throws SQLException {

        if(inputNombre.getText() != null &&
                inputApellido1.getText() != null &&
                inputApellido2.getText() != null &&
                inputCorreo.getText() != null &&
                inputNombreUsuario.getText() != null &&
                inputEdad.getText() != null &&
                inputPais.getText() != null &&
                inputPassword.getText() != null &&
                inputImg.getText() != null) {

            String nombre = inputNombre.getText();
            String apellido1 = inputApellido1.getText();
            String apellido2 = inputApellido2.getText();
            String correo = inputCorreo.getText();
            String nombre_usuario = inputNombreUsuario.getText();
            int edad = Integer.parseInt(inputEdad.getText());
            String pais = inputPais.getText();
            String password = inputPassword.getText();
            String img = inputImg.getText();

            gestor.crearUsuario(nombre, apellido1, apellido2, correo, password, nombre_usuario, edad, pais, img);
        } else {

        }
    }
}
