package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AlertBox {

    @FXML
    private Button okBtn;

    @FXML
    private Text warning_text;

    public  AlertBox (String title) {
        warning_text.setText(title);
    }

    @FXML
    void closeWindow(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) okBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
