package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.entidades.Artista;
import sample.entidades.Usuario;
import sample.gestor.Gestor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private Gestor gestor = new Gestor();

    public void listarArtistas() throws SQLException {
        List<Artista> artistas = gestor.listArtistas();
        for (Artista artista: artistas) {
            System.out.println(artista.toString());
        }
    }

    @FXML
    private TextField userInput;

    @FXML
    private TextField passInput;

    @FXML
    private Label userLabel;

    @FXML
    private Label labelPass;

    @FXML
    private Button registerBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private Button listarArtistasBtn;


    public void login(ActionEvent actionEvent) throws IOException {
        if(userInput.getText() != "" && passInput.getText() != "") {
            try{
                int userNum = Integer.parseInt(userInput.getText());
                Usuario usuario = gestor.encontrarUsuarioPorID(userNum);
                if(usuario != null) {
                    if (usuario.getPassword().equals(passInput.getText())) {
                        //Ir al menú principal
                        System.out.println("Usuario Correcto");
                    } else {
                        //Contraseña incorrecta
                        System.out.println("Contraseña incorrecta");
                    }
                }  else {
                    // Usuario no existe
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ui/AlertBox.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("Usuario no existe");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
            } catch(NumberFormatException num) {
                num.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Espacios en blanco
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ui/AlertBox.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Espacios en blanco no permitidos");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    @FXML
    void openRegisterWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ui/RegisterUsuario.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Usuario no existe");
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
